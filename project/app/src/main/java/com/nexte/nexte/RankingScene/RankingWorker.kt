package com.nexte.nexte.RankingScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Interface to define Response Logic of Ranking Class
 * that will be used to make the communication between worker and interactor
 */
interface RankingWorkerUpdateLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun updateUsersInRanking(response: RankingModel.Response)
}

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class RankingWorker {

    var updateLogic: RankingWorkerUpdateLogic? = null
    var userManager: UserManager? = null

    /**
     * Function to get users in ranking
     *
     * @param request Ranking Model Request that contains need information to send for server
     * @param completion Method to call on parent class
     */
    fun getUsersInRanking(request: RankingModel.Request) {

        val users = userManager?.getAll()
        var players = convertUsersToRankingModelPlayers(users!!)
        players = players.sortedBy { it.rankingPosition }.toTypedArray()
        val response = RankingModel.Response(players)

        updateLogic?.updateUsersInRanking(response)

        val url = "http://10.0.2.2:3000/users"

        url.httpGet().responseJson { request, response, result ->
            when(result){
                is Result.Failure -> {
                    println(result.getException())
                }

                is Result.Success -> {
                    val json = result.get()
                    var usersList = convertJsonToListOfUsers(json.obj())
                    usersList = userManager?.updateMany(usersList)!!
                    var players = convertUsersToRankingModelPlayers(usersList)
                    players = players.sortedBy { it.rankingPosition }.toTypedArray()
                    val newResponse = RankingModel.Response(players)

                    updateLogic?.updateUsersInRanking(newResponse)
                }
            }
        }

    }

    /**
     *
     */
    private fun convertJsonToListOfUsers(jsonObject: JSONObject): List<User>{
        val dataJson = jsonObject["data"] as JSONObject
        val usersJsonArray = dataJson["users"] as JSONArray

        var usersMutableList = mutableListOf<User>()
        for (counter in 0..usersJsonArray.length()-1){
            val jsonUser = usersJsonArray.getJSONObject(counter)
            val user = createUser(jsonUser)
            usersMutableList.add(user)

        }
        return usersMutableList.toList()

    }

    /**
     *
     */
    private fun createUser(jsonUser: JSONObject): User{
        val id =  jsonUser["id"] as String
        val name = jsonUser["name"] as String
        val profilePicture = jsonUser["profileImageURL"] as String
        val nickname = jsonUser["nickname"] as String
        val rankingPosition = jsonUser["rankPosition"] as Int
        val email = jsonUser["email"] as String
        val phone = jsonUser["phone"] as String
        val wins = jsonUser["wins"] as Int
        val loses = jsonUser["loses"] as Int
        val birthDate = SimpleDateFormat("dd-MM-yyyy")
                .parse((jsonUser["birthDate"] as String))
        val genderString = jsonUser["gender"] as String
        var gender: User.Gender? = null
        if (genderString.equals("M")){
            gender = User.Gender.MALE
        }else{
            gender = User.Gender.FEMALE
        }
        val categoryInt = jsonUser["category"] as Int
        var category = UserCategoryManager().get(categoryInt.toString())
        val statusInt = jsonUser["status"] as Int
        var status: User.Status? = null
        when(statusInt){
            0 ->
                status = User.Status.AVAILABLE
            1 ->
                status = User.Status.INJURED
            2 ->
                status = User.Status.UNAVAILABLE
            else ->{
                status = null
            }
        }
        val challengeSended = null
        val challengeReceived = null
        var latestGames = listOf<Challenge>()

        val user = User(id, name, profilePicture, nickname, birthDate,
                rankingPosition, email, phone, wins, loses, gender,
                category, status!!, challengeSended, challengeReceived, latestGames)
        return user
    }

    /**
     * Method used to convert list of users to array of ranking model players
     *
     * @return an array of ranking model players
     */
    private fun convertUsersToRankingModelPlayers(users: List<User>): Array<RankingModel.Player> {
        val rankingModelPlayersMutable = mutableListOf<RankingModel.Player>()
        for (user in users){
            val name = user.name
            val rankingPosition = user.rankingPosition
            val wins = user.wins
            val losses = user.loses
            val efficiency = calculatePlayerEfficiency(wins, losses)
            val lastGame = calculatePlayerLastGame(user.latestGames!!)
            var playerCategory = ""
            if (user.category != null){
                playerCategory = user.category?.name
            }
            val player = RankingModel.Player(name = name,
                    rankingPosition = rankingPosition, wins = wins, losses = losses,
                    pictureURL = R.mipmap.ic_launcher, efficiency = efficiency,
                    lastGame = lastGame, playerCategory = playerCategory)
            rankingModelPlayersMutable.add(player)
        }

        return rankingModelPlayersMutable.toTypedArray()
    }

    /**
     * Method used to get player effiency based on his wins and losses
     *
     * @return a string that represents player efficiency
     */
    private fun calculatePlayerEfficiency(wins: Int, losses: Int): String{
        val allGames = wins + losses
        val efficiency: String?
        if (allGames != 0){
            efficiency = "" + (wins/allGames*100).toString() + "%"
        }else{
            efficiency = "100%"
        }

        return efficiency!!
    }

    /**
     * Method that is calculate when was the user last game
     *
     * @return a string that represents a player last game
     */
    private fun calculatePlayerLastGame(latestGames: List<Challenge>): String{
        if (latestGames.isEmpty()) {
            return "Nenhum jogo"
        }
        val latestGamesSorted = latestGames.sortedBy { it.challengeDate }
        val latestGameDate = latestGamesSorted.last().challengeDate
        val today = Date()
        var latestGame = ""

        if(today.year == latestGameDate.year){
            if (today.month == latestGameDate.month){
                if (today.day == latestGameDate.day){
                    latestGame = "hoje"
                }else if(today.day == (latestGameDate.day - 1)){
                    latestGame = "ontem"
                }else{
                    latestGame = "" + (today.day - latestGameDate.day) + " days"
                }
            }else{
                latestGame = "" + (today.month - latestGameDate.month) + " months"
            }
        }else{
            latestGame = "" + (today.year - latestGameDate.year) + " years"
        }

        return latestGame
    }
}