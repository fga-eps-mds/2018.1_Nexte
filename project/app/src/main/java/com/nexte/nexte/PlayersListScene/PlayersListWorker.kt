package com.nexte.nexte.PlayersListScene


import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

interface PlayerListUpdateLogic{

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun getPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response)

    /**
     * Method that will be used to pass response of challenge for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun generateChallengeReponse(response: PlayersListModel.ChallengeButtonRequest.Response)
}
/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class PlayersListWorker {

    var userManager: UserManager? = null
    var challengeManager: ChallengeManager = ChallengeManager()
    var updateLogic: PlayerListUpdateLogic? = null
    var httpBool: Boolean? = null
    val httpHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->
        when (result) {
            is Result.Failure -> {
                println(result.getException())
            }
            is Result.Success -> {
                val json = result.get()
                createNewResponseFromServer(json.obj())

            }
        }
    }
    val httpPostHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->

        when (result) {
            is Result.Failure -> {
                httpBool = false
                println(result.getException())
            }

            is Result.Success -> {
                httpBool = true
                println(result.get())
            }
        }
    }

    /**
     * Method that will create the new response from data that came from server
     */
    fun createNewResponseFromServer(json: JSONObject){
        var usersList = this.convertJsonToListOfUsers(json).sortedBy { it.rankingPosition }
        usersList = userManager?.updateMany(usersList)!!
        usersList = usersList.take(5) // First 5 players
        val newResponse = PlayersListModel.ShowRankingPlayersRequest.Response(usersList)
        updateLogic?.getPlayersToChallenge(newResponse)
    }


    /**
     * Function to get players 5 rank positions above the logged player
     *
     * @param request Challenge Model request that contains needed information to send to server
     */
    fun fetchPlayersToChallenge (request: PlayersListModel.ShowRankingPlayersRequest.Request) {

        val playerPosition = request.challengerRankingPosition
        var availablePlayers : List<User> = listOf()
        val users = userManager?.getAll()

        users?.forEach {
            if (it.rankingPosition >= playerPosition - rankingGap && it.rankingPosition < playerPosition) {
                availablePlayers += it
            }
        }

        val response = PlayersListModel.ShowRankingPlayersRequest.Response(availablePlayers)
        updateLogic?.getPlayersToChallenge(response)

        if (UserSingleton.userType != UserType.MOCKED) {
            val url = "/users"
            val header = mapOf("Content-Type" to "application/json",
                    "Accept-Version" to "0.1.0")
            Fuel.get(url).header(header).responseJson(httpHandler)
        }

    }

    /**
     * Function to get the clicked player detailed info
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun fetchChallengedDetails (request: PlayersListModel.SelectPlayerForChallengeRequest.Request,
                                completion: (PlayersListModel.SelectPlayerForChallengeRequest.Response) -> Unit) {

        val challengedPosition = request.challengedRankingPosition
        var selectedPlayer: User?= null

        val players = userManager?.getAll()

        players?.forEach {
            if (it.rankingPosition == challengedPosition){
                selectedPlayer = it
            }
        }

        val response = PlayersListModel.SelectPlayerForChallengeRequest.Response(selectedPlayer!!)
        completion(response)
    }

    /**
     * Function to get a user that's going to be challenged
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun generateChallenge(request: PlayersListModel.ChallengeButtonRequest.Request) {

        val challengedUser = userManager?.get(request.userChallenged)
        challengedUser?.let {
            val challenge = Challenge(UUID.randomUUID().toString(),
                    UserSingleton.loggedUserID,
                    it.id,
                    Date(),
                    Challenge.Status.CONFIRMED,
                    Challenge.Stage.Scheduled())

            challengeManager.update(challenge)


            val picture = userManager?.get(UserSingleton.loggedUserID)?.profilePicture
            val challengerPicture = if (picture != null) {
                picture.toInt()
            } else {
                R.drawable.profile_image9
            }
            val challengedProfile = if (it.profilePicture != null) {
                it.profilePicture.toInt()
            } else {
                R.mipmap.ic_launcher_round
            }
            val challenged = MatchModel.MatchPlayer(challengedUser.name, challengedProfile)
            val challenger = MatchModel.MatchPlayer(UserSingleton.loggedUser.name, challengerPicture)
            val match = MatchModel.MatchData(challenged, challenger, challenge.id)
            val response = PlayersListModel.ChallengeButtonRequest.Response(challengedUser.name, match)

            this.updateLogic?.generateChallengeReponse(response)

            if(UserSingleton.userType != UserType.MOCKED) {
                val header = mapOf("Content-Type" to "application/json",
                        "Accept-Version" to "0.1.0")
                val url = "/challenges"
                val challengeJSON = createChallengeJson(challenge)
                Fuel.post(url).header(header).body(challengeJSON.toString()).responseJson(httpPostHandler)

            }
        }
    }

    /**
     * Method responsible for transforming a challenge to JSON
     *
     * @param challenge challenge data to become json
     *
     * @return JSON form of the challenge
     */
    fun createChallengeJson(challenge: Challenge): JSONObject{
        val jsonChallenge = JSONObject()
        jsonChallenge.put("challenger", challenge.challengerId)
        jsonChallenge.put("challenged", challenge.challengedId)
        jsonChallenge.put("date", challenge.challengeDate.toString())

        val returnJson = JSONObject()
        returnJson.put("challenge", jsonChallenge)

        return returnJson
    }

    /**
     * Method resposible for tranforming a jsonObject, that constains the response of the api request,
     * into a list of users
     *
     * @param jsonObject jsonObject that contains the response data from the api
     * @return list of users
     */
    fun convertJsonToListOfUsers(jsonObject: JSONObject): List<User>{
        val dataJson = jsonObject["data"] as JSONObject
        val usersJsonArray = dataJson["users"] as JSONArray
        val usersMutableList = mutableListOf<User>()

        for (counter in 0 until usersJsonArray.length()){
            val jsonUser = usersJsonArray.getJSONObject(counter)
            val user = User.createUserFromJsonObject(jsonUser)
            usersMutableList.add(user)
        }

        return usersMutableList.toList()
    }

    companion object { const val rankingGap = 5 }
}