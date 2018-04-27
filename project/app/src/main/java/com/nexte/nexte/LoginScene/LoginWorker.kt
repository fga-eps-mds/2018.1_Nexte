package com.nexte.nexte.LoginScene

import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import org.json.JSONObject

/**
 * Class responsible to manager request provided from interactor to response
 * and request user authentication for nexte server
 */

class LoginWorker {


    /**
     * Method that realize request for user authentication
     * pass request for the Worker and send response to the Presenter
     *
     * @param request request provided from Interactor
     * @completion task to be completed at Interactor
     */
    fun authenticateUser(request: LoginModel.Request,
                         completion: (LoginModel.Response) -> Unit) {


        val authentication = "http://192.168.100.2:3000/auth/login" // Works only with IP
        val headers = mapOf("Content-Type" to "application/json", "Accept-Version" to "1.0.0")

        val json = JSONObject()
        json.put("username",  request.userName) // Expected ramires
        json.put("password",  request.password) // Expected test-nexte-ramires

        Fuel.post(authentication).header(headers).body(json.toString()).responseString { request, response, result ->

            result.success {
                val token = "1820uf09183h9d12db092ed9has9d1j020hf90aasfjialuch"
                val status = LoginModel.AuthenticationStatus.authorized
                val response = LoginModel.Response(token, status)
                completion(response)
            }

            result.failure {
                val token = ""
                val status = LoginModel.AuthenticationStatus.unauthorized
                val response = LoginModel.Response(token, status)
                completion(response)
            }
        }
    }
}


