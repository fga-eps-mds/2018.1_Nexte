package com.nexte.nexte.PlayersListScene


import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
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
}
/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class PlayersListWorker {

    var userManager: UserManager? = null
    var challengeManager: ChallengeManager = ChallengeManager()
    var updateLogic: PlayerListUpdateLogic? = null

    /**
     * Function to get players 5 rank positions above the logged player
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
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

        val url = "http://10.0.2.2:3000/users/"
        url.httpGet().responseJson { _, _, result ->
            when(result){
                is Result.Failure -> { println(result.getException()) }
                is Result.Success -> {
                    val json = result.get()
                    var usersList = convertJsonToListOfUsers(json.obj()).sortedBy { it.rankingPosition }
                    usersList = userManager?.updateMany(usersList)!!
                    usersList = usersList.take(5) // First 5 players
                    val newResponse = PlayersListModel.ShowRankingPlayersRequest.Response(usersList)
                    updateLogic?.getPlayersToChallenge(newResponse)
                }
            }
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
    fun generateChallenge(request: PlayersListModel.ChallengeButtonRequest.Request,
                          completion: (PlayersListModel.ChallengeButtonRequest.Response) -> Unit) {

        var challengedUser = userManager?.get(request.userChallenged)
        challengedUser?.let {
            val challenge = Challenge(UUID.randomUUID().toString(),
                    UserSingleton.loggedUserID,
                    it.id,
                    Date(),
                    Challenge.Status.CONFIRMED,
                    Challenge.Stage.Scheduled())

            challengeManager.update(challenge)

            val challenged = MatchModel.MatchPlayer(challengedUser.name, R.mipmap.ic_launcher_round)
            val challenger = MatchModel.MatchPlayer(UserSingleton.loggedUser.name, R.mipmap.ic_launcher_round)
            val match = MatchModel.MatchData(challenged, challenger)
            val response = PlayersListModel.ChallengeButtonRequest.Response(challengedUser.name, match)

            completion(response)
        }
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