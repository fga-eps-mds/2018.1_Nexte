package com.nexte.nexte.LoginScene


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

        Unauthorized(401), Authorized(200), Unregistered(404)
    }

}