package com.nexte.nexte.LoginScene

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

        val password: String = request.password
        val username: String = request.userName
        var token: String

        if (username.equals("miguelpimentel") && password.equals("123456")) {
            token = "sd723gs261g2sv1234ss"
        } else {
            token = ""
        }

        val response: LoginModel.Response = LoginModel.Response(token)
        completion(response)
    }
}
