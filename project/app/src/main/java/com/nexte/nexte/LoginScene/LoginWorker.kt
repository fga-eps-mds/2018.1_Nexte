package com.nexte.nexte.LoginScene

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import org.json.JSONObject
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.NexteApplication

/**
 * Interface to define Response Logic of Ranking Class
 * that will be used to make the communication between worker and interactor
 */
interface LoginWorkerUpdateLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun authenticateUser(response: LoginModel.Authentication.Response)

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun requestAuth(response: LoginModel.AccountKit.Response)
}

/**
 * Class responsible to manager request provided from interactor to response
 * and request user authentication for nexte server
 */

class LoginWorker {

    var updateLogic: LoginWorkerUpdateLogic? = null
    var userManager: UserManager? = UserManager()

    val authenticateHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->
        result.success {
            val json = result.get().obj()
            val data = json["data"] as JSONObject
            val userJson = data ["user"] as JSONObject
            val user  = User.createUserFromJsonObject(userJson)
            NexteApplication().updateUserLoggedStatus(user)

            val status = LoginModel.Authentication.StatusCode.AUTHORIZED
            val response = LoginModel.Authentication.Response(user.id, status)
            updateLogic?.authenticateUser(response)
        }

        result.failure {
            val token = ""
            val status = LoginModel.Authentication.StatusCode.UNAUTHORIZED
            val response = LoginModel.Authentication.Response(token, status)
            updateLogic?.authenticateUser(response)
        }
    }

    val requestAuthHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->
        result.success {
            val json = result.get().obj()
            val data = json["data"] as JSONObject
            val userJson = data ["user"] as JSONObject
            val user  = User.createUserFromJsonObject(userJson)
            NexteApplication().updateUserLoggedStatus(user)

            val response = LoginModel.AccountKit.Response(LoginModel.AccountKit.StatusCode.SUCESSED)
            updateLogic?.requestAuth(response)
        }

        result.failure {
            val response = LoginModel.AccountKit.Response(LoginModel.AccountKit.StatusCode.ERROR)
            updateLogic?.requestAuth(response)
        }
    }

    /**
     * Method that realize request for user authentication
     * pass request for the Worker and send response to the Presenter
     *
     * @param request request provided from Interactor
     * @completion task to be completed at Interactor
     */
    fun authenticateUser(request: LoginModel.Authentication.Request) {

        val authentication = "http://10.0.2.2:3000:3000/sessions" // http://10.0.2.2:3000
        val headers = mapOf("Content-Type" to "application/json",
                "Accept-Version" to "1.0.0")
        val json = JSONObject()
        json.put("username", request.userName) // Expected ramires
        json.put("password",  request.password) // Expected test-nexte-ramires

        Fuel.post(authentication).header(headers).body(json.toString()).responseJson(authenticateHandler)
    }

    /**
     * Method that realize request for user authentication
     * pass request for the Worker and send response to the Presenter
     *
     * @param request request provided from Interactor
     * @completion task to be completed at Interactor
     */
    fun requestForAuth(request: LoginModel.AccountKit.Request) {

        val authentication = "http://10.0.2.2:3000:3000/users" // Local route for auth
        val headers = mapOf("Content-Type" to "application/json",
                "Accept-Version" to "1.0.0")
        val body = defineBodyForAccountKitAuth(request.token)

        Fuel.post(authentication).header(headers).body(body).responseJson(requestAuthHandler)
    }

    /**
     * Define body to authenticate user with Nexte main server
     * @param token Token from a user - used in Account Kit auth
     */
    fun defineBodyForAccountKitAuth(token: String): String {
        val json = JSONObject()
        json.put("fbAuthCode",  token)  // Expected ramires
        return json.toString()
    }
}
