package com.nexte.nexte.RankingScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject

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

    val httpGetHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->

        when (result) {
            is Result.Failure -> {
                println(result.getException())
            }

            is Result.Success -> {
                val json = result.get()
                var usersList: List<User>? = convertJsonToListOfUsers(json.obj())
                usersList = sortListByRanking(usersList)
                usersList = userManager?.updateMany(usersList!!)
                val newResponse = RankingModel.Response(usersList?.toTypedArray())
                updateLogic?.updateUsersInRanking(newResponse)
            }
        }
    }

    fun sortListByRanking(list: List<User>?): List<User>?{
        return list?.sortedBy { it.rankingPosition }
    }

     /**
     * Function to get users in ranking
     *
     * @param request Ranking Model Request that contains need information to send for server
     */
    fun getUsersInRanking(request: RankingModel.Request) {

        var users = userManager?.getAll()
        users = sortListByRanking(users)
        val response = RankingModel.Response(users!!.toTypedArray())
        updateLogic?.updateUsersInRanking(response)

        if (UserSingleton.userType != UserType.MOCKED) {
            val url = "http://10.0.2.2:3000/users"
            url.httpGet().responseJson(httpGetHandler)
        } else {
            //Do nothing
        }
    }

    /**
     * Method resposible for tranforming a jsonObject, that constains the response of the api request,
     * into a list of users
     *
     * @param jsonObject jsonObject that contains the response data from the api
     *
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
}