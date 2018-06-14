package com.nexte.nexte.ShowProfileScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryAdapter
import com.nexte.nexte.Entities.User.UserManager
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

    val httpRequestHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result->
        when(result) {
            is Result.Failure -> {
                println(result.getException())
            }

            is Result.Success -> {
                val json = result.get()
                val user = convertJsonUserToUser(json.obj())
                userManager?.update(user)
                val newResponse = ShowProfileModel.Response(user)
                updateLogic?.updateUserProfile(newResponse)
            }
        }
    }



    /**
     * Method responsible to get the [ShowProfileModel.Request] with data to be validated
     * and generate an [ShowProfileModel.Response] with user information
     *
     * @param request Contains the username and TokenID that will be used to recover the user
     */
    fun getUserProfile(request: ShowProfileModel.Request) {

        val userId = request.userId

        val emptyUser = User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null)
        val returnedUser = userManager?.get(userId) ?: emptyUser

        val response: ShowProfileModel.Response = ShowProfileModel.Response(returnedUser)
        updateLogic?.updateUserProfile(response)
        if(!returnedUser.id.isEmpty()) {
            val url = "http://10.0.2.2:3000/users/" + returnedUser.id
            url.httpGet().responseJson(httpRequestHandler)
        }else{
            //Do nothing
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
    fun convertJsonUserToUser(jsonObject: JSONObject, userCategoryManagerArgument: UserCategoryAdapter? = null): User {
        val dataJson = jsonObject["data"] as JSONObject
        val userJson = dataJson["user"] as JSONObject
        return User.createUserFromJsonObject(userJson, userCategoryManagerArgument)
    }
}
