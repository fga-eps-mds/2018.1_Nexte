package com.nexte.nexte.LoginScene

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

        val AUTHENTICATION = "http://localhost:3000/auth/login"
        val header = "Accept-Version" to "1.0.0"
        var token: String = ""
        val json = JSONObject()
        json.put("username", request.userName) // Expected ramires
        json.put("password", request.password) // Expected test-nexte-ramires

        val (request, response, result) = Fuel.post(AUTHENTICATION).
                                               header(header).
                                               body(json.toString()).
                                               responseString()

        result.success {

            token = "Usuário autenticado com sucesso"
            //UserSingleton.getUserInformations().isLoggedIn = truex
        }

        result.failure { token  = "Mensagem inválida" }

        completion(LoginModel.Response(token))
    }

    // Another way

//        Fuel.post(AUTHENTICATION).header(header).body(json.toString()).responseString { request, response, result ->
//
//            println(request)
//            println(response.statusCode)
//        }
}
