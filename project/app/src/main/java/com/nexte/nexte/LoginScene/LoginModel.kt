package com.nexte.nexte.LoginScene

import com.facebook.accountkit.AccountKitLoginResult


/**
 * Class to define Model of Login Scene to send informations between layers
 */
class LoginModel {

    /**
     * Class to  handler with manually authentication
     */
    class Authentication {

        /**
         * Class responsible to store received information from view to Interactor      *
         * @param userName username registered by user
         * @param password password registered by user
         */
        class Request(val userName: String,  val password: String)

        /**
         * Class responsible to store received information from worker to Presenter
         * @param tokenId authentication token
         */
        class Response(val tokenId: String, val authenticateStatusCode: StatusCode)

        /**
         * Class responsible to store received information from Presenter to View
         * @param message message according with authentication result
         */
        class ViewModel(val message: String)

        /**
         *  Enum class to handle with error and responses from server
         * @param value status code provided from server
         */
        enum class StatusCode(val value: Int) {
            UNAUTHORIZED(401), AUTHORIZED(200), UNREGISTERED(404), FACEBOOK_REQUEST(101)
        }
    }

    /**
     * Class responsible to handle with account kit authentication
     */
    class AccountKit {

        companion object {
           const val ACCOUNTKIT_CODE = 13
        }

        /**
         * Class responsible to store received information from Presenter to View
         * @param loginResult: Result to a request for facebook API
         */
        class Request(val loginResult: AccountKitLoginResult)

        /**
         * Class responsible to store received information from worker to Presenter
         * @param statusCode status from a request to facebook API
         */
        class Response(val statusCode: StatusCode)

        /**
         * Class responsible to store received information from Presenter to View
         * @param message message according with authentication result
         */
        class ViewModel(val message: String)

        /**
         * Enum class to handle with error and responses from server
         * (status code provided from Facebook API)
         */
        enum class StatusCode {
            SUCESSED, CANCELLED, ERROR
        }
    }
}