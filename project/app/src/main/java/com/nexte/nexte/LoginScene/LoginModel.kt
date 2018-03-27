package com.nexte.nexte.LoginScene

/**
 * Created by baldissera on 24/03/2018.
 */
class LoginModel {

    // Model data of a view request
    class Request(userName: String, password: String) {

        var userName: String = ""
        var password: String = ""

        init {
            this.userName = userName
            this.password = password
        }
    }

    // Model data of interactor/worker response
    class Response(tokenId: String) {

        var tokenId: String = ""

        init {
            this.tokenId = tokenId
        }
    }

    // Model of present data in view
    class ViewModel(message: String) {

        var message: String = ""

        init {
            this.message = message
        }
    }
}