package com.nexte.nexte.ShowProfileScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * Interface to define Response Logic of Ranking Class
 * that will be used to make the communication between worker and interactor
 */
interface ShowProfileWorkerUpdateLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun updateUserProfile(response: ShowProfileModel.Response)
}

/**
 * This class verifies if the logged user is valid and return the user information as response.
 * She format Response and call completion to send data for called class
 */

class ShowProfileWorker {

    var userManager: UserManager? = null
    var updateLogic: ShowProfileWorkerUpdateLogic? = null

    /**
     * Method responsible to get the [ShowProfileModel.Request] with data to be validated
     * and generate an [ShowProfileModel.Response] with user information
     *
     * @param request Contains the username and TokenID that will be used to recover the user
     * @param completion Unformatted activity sent to Interactor
     */
    fun getUserProfile(request: ShowProfileModel.Request) {

        val userId = request.userId
        val user = userManager?.get(userId)

        val emptyUser = User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null)
        var returnedUser: User? = null

        if (user != null){
            returnedUser = user
        }else{
            returnedUser = emptyUser
        }

        val response: ShowProfileModel.Response = ShowProfileModel.Response(returnedUser)
        updateLogic?.updateUserProfile(response)

        val url = "http://10.0.2.2:3000/users"
        url.httpGet().responseJson { _, _, result ->
            when(result){
                is Result.Failure -> {
                    println(result.getException())
                }

                is Result.Success -> {
                    val json = result.get()
                    val user = convertJsonUserToUser(json.obj())
                    val newResponse = ShowProfileModel.Response(user)
                    updateLogic?.updateUserProfile(newResponse)
                }
            }
        }

    }

    /**
     * Method resposible for tranforming a jsonObject, that constains the response of the api request,
     * into a user
     *
     * @param jsonObject jsonObject that contains the response data from the api
     *
     * @return users
     */
    fun convertJsonUserToUser(jsonObject: JSONObject): User {
        val dataJson = jsonObject["data"] as JSONObject
        val usersJsonArray = dataJson["users"] as JSONArray

        val jsonUser = usersJsonArray.getJSONObject(0)
        val user = User.createUserFromJsonObject(jsonUser)

        return user
    }
}
