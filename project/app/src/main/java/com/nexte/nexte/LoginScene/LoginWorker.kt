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

        val AUTHENTICATION = "http://192.168.100.2:3000/auth/login" // Works only with IP
        var headers = mapOf("Content-Type" to "application/json", "Accept-Version" to "1.0.0")
        var token: String = ""
        val json = JSONObject()
        json.put("username",  request.userName) // Expected ramires
        json.put("password",  request.password) // Expected test-nexte-ramires

        Fuel.post(AUTHENTICATION).header(headers).body(json.toString()).responseString { request, response, result ->

            Log.d("Request", request.toString())
            Log.d("Response", response.toString())
            Log.d("Result", result.toString())

            result.success {
                token = "1820uf09183h9d12db092ed9has9d1j020hf90aasfjialuch"
                val response = LoginModel.Response(token)
                completion(response)
            }

            result.failure {
                token = ""
                val response = LoginModel.Response(token)
                completion(response)
            }
        }
    }
}


