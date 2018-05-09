package com.nexte.nexte.LoginScene

import android.util.Log


/**
 * Interface that defines Business Login to Interactor and defines comunication
 * with worker and presenter
 */
interface LoginBusinessLogic {
    fun doAuthentication(request: LoginModel.Request)
    fun accountKitAuthentication(request: LoginM.Request)
}

/**
 * Class that defines a response from a request provided using comunication
 * between worker and presenter
 *
 * @property worker Request model of feed that contains data to pass for Worker
 * @property presenter
 */
class LoginInteractor : LoginBusinessLogic {

    var worker = LoginWorker()
    var presenter: LoginPresentationLogic? =  null

    /**
     * Method that will send request provided by worker to presenter as response
     *
     * @param request Request model of feed that contains data to pass for Worker
     */
    override fun doAuthentication(request: LoginModel.Request) {

        worker.authenticateUser(request) { response ->

            val responseStatus = response.authenticateStatus
            when(responseStatus) {
                LoginModel.AuthenticationStatus.AUTHORIZED -> {
                    this.presenter?.presentLogin(response)
                } else -> {
                    this.presenter?.presentError(response)
                }
            }
        }
    }

    override fun accountKitAuthentication(request: LoginM.Request) {

        worker.requestForAuth(request) { response ->
            this.presenter?.presentAccountKit(response)
        }
    }
}