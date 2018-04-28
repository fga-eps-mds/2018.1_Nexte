package com.nexte.nexte.LoginScene

import org.json.JSONObject

/**
 * Class responsible to do request for anywhere, format response and
 * call completion to send data for called class
 */
class LoginWorker {

    /**
     * Function to authenticated the user data of server
     *
     * @param request Login Model Request that contains need information to send for server
     * @param completion Method for call on parent class
     */
    fun authenticateUser(request: LoginModel.Request, completion: (LoginModel.Response) -> Unit) {

        val password = request.password
        val username = request.userName
        val token: String

        if (username.equals("miguelpimentel") && password.equals("123456")) {
            token = "sd723gs261g2sv1234ss"
        } else {
            token = ""
        }

        val response: LoginModel.Response = LoginModel.Response(token)
        completion(response)
    }
}
