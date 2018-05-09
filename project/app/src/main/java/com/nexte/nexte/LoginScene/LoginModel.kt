package com.nexte.nexte.LoginScene

import com.facebook.accountkit.AccountKitLoginResult


/**
 * Class to define Model of Login Scene to send informations between layers
 */
class LoginModel {

    /**
     * Class responsible to store received information from View to Interactor      *
     * @param userName username registered by user
     * @param password password registered by user
     */
    class Request(val userName: String,  val password: String)

    /**
     * Class responsible to store received information from worker to Presenter
     * @param tokenId authentication token
     */
    class Response(val tokenId: String, val authenticateStatus: AuthenticationStatus)

    /**
     * Class responsible to store received information from Presenter to View
     * @param message message according with authentication result
     */
    class ViewModel(val message: String)

    /**
     *  Enum class to handle with Error
     * @param value status code provided from server
     */
    enum class AuthenticationStatus(val value: Int) {

        UNAUTHORIZED(401), AUTHORIZED(200), UNREGISTERED(404), FACEBOOK_REQUEST(101)
    }
}


class LoginM {

    class Request(loginResult: AccountKitLoginResult)

    class Response(statusCode: StatusCode)

    class ViewModel(message: String)

    enum class StatusCode(val value: Int) {
        OK(200), AUTHORIZED(204)
    }

}