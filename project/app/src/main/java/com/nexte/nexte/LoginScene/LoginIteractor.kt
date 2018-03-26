package com.nexte.nexte.LoginScene

/**
 * Created by baldissera on 24/03/2018.
 */

interface LoginBusinessLogic {

    fun doAuthentication(request: LoginModel.Request)
}

class LoginIteractor : LoginBusinessLogic {

    var worker = LoginWorker()
    var presenter: LoginPresentationLogic? = null

    override fun doAuthentication(request: LoginModel.Request) {

        worker.authenticateUser(request) { response ->
            this.presenter?.presentLogin(response)
        }
    }
}