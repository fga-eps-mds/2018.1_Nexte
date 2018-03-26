package com.nexte.nexte.LoginScene

/**
 * Created by baldissera on 24/03/2018.
 */

interface LoginBusinessLogic {

    fun doAuthentication(request: LoginModel.Request)
}

class LoginIteractor : LoginBusinessLogic {

    var worker = LoginWorker() // Referece to worker
    var presenter: LoginPresentationLogic? =  null// Reference to presenter

     // Connect response from worker to presenter
    override fun doAuthentication(request: LoginModel.Request) {

        worker.authenticateUser(request) { response ->
            this.presenter?.presentLogin(response)
        }
    }
}