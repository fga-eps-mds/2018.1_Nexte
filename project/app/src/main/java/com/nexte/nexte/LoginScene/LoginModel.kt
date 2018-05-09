package com.nexte.nexte.LoginScene

import com.facebook.accountkit.AccountKitLoginResult


/**
 * Class to define Model of Login Scene to send informations between layers
 */
class LoginModel {

    class Authentication {

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
        class Response(val tokenId: String, val authenticateStatusCode: StatusCode)

        /**
         * Class responsible to store received information from Presenter to View
         * @param message message according with authentication result
         */
        class ViewModel(val message: String)

        /**
         *  Enum class to handle with Error
         * @param value status code provided from server
         */
        enum class StatusCode(val value: Int) {

            UNAUTHORIZED(401), AUTHORIZED(200), UNREGISTERED(404), FACEBOOK_REQUEST(101)
        }
    }

    class AccountKit {

        companion object {
            val ACCOUNTKIT_CODE = 13
        }

        class Request(val loginResult: AccountKitLoginResult)

        class Response(val statusCode: StatusCode)

        class ViewModel(val message: String)

        enum class StatusCode {
            SUCESSED, CANCELLED, ERROR
        }
    }
}