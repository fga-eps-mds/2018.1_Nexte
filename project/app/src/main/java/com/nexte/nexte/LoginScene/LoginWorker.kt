package com.nexte.nexte.LoginScene

/**
 * Created by miguelpimentel on 24/03/18.
 */


class LoginWorker {

    constructor() { }

    // Manipulate a request from interactor to a response
    fun authenticateUser(request: LoginModel.Request, completion: (LoginModel.Response) -> Unit) {

        val password: String = request.password
        val username: String = request.userName
        var token: String = ""

        if (username.equals("miguelpimentel") && password.equals("123456")) {
            token = "sd723gs261g2sv1234ss"
        } else {
            token = ""
        }

        val response: LoginModel.Response = LoginModel.Response(token)
        completion(response)
    }
}
