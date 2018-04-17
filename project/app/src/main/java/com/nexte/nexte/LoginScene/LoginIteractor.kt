package com.nexte.nexte.LoginScene

/**
 * Interface to define Business Logic to Login Class that will be used to call this
 * Interactor on other class layer
 */

interface LoginBusinessLogic {

    /**
     * Method that authenticate the user through getting pass request for the Worker
     * and send response to the Presenter
     *
     * @param request Request model of login that contains data to pass for Worker
     */

    fun doAuthentication(request: LoginModel.Request)
}

/**
 * Class that implements [LoginBusinessLogic] and is responsible for the communication
 * between worker and presenter
 *
 * @property worker Reference to worker [LoginWorker]
 * @property presenter Reference to presenter [LoginPresenter]
 */

class LoginIteractor (var presenter : LoginPresentationLogic? = null) : LoginBusinessLogic {

    val worker: LoginWorker = LoginWorker()

    override fun doAuthentication(request: LoginModel.Request) {
        worker.authenticateUser(request) { response ->
            this.presenter?.presentLogin(response)
        }
    }
}