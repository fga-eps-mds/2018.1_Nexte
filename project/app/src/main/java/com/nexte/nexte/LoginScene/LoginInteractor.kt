package com.nexte.nexte.LoginScene
import android.util.Log

/**
 * Interface that defines Business Login to Interactor and defines comunication
 * with worker and presenter
 */
interface LoginBusinessLogic {

    /**
     * Method that will send request provided by worker to presenter as response
     *
     * @param request Request model of feed that contains data to pass for Worker associated with Authentication Process
     */
    fun doAuthentication(request: LoginModel.Authentication.Request)

    /**
     * Method that will send request provided by worker to presenter as response
     *
     * @param request Request model of feed that contains data to pass for Worker associated with AccountKit
     */
    fun accountKitAuthentication(request: LoginModel.AccountKit.Request)
}

/**
 * Class that defines a response from a request provided using comunication
 * between worker and presenter
 *
 * @property worker Request model of feed that contains data to pass for Worker
 * @property presenter
 */
class LoginInteractor : LoginBusinessLogic, LoginWorkerUpdateLogic {

    var worker = LoginWorker()
    var presenter: LoginPresentationLogic? =  null


    override fun doAuthentication(request: LoginModel.Authentication.Request) {
        worker.authenticateUser(request)
    }

    override fun authenticateUser(response: LoginModel.Authentication.Response) {
        val responseStatus = response.authenticateStatusCode
        when(responseStatus) {
            LoginModel.Authentication.StatusCode.AUTHORIZED -> {
                this.presenter?.presentLogin(response)
            } else -> {
                this.presenter?.presentError(response)
            }
        }
    }

    override fun accountKitAuthentication(request: LoginModel.AccountKit.Request) {
        worker.requestForAuth(request)
    }

    override fun requestAuth(response: LoginModel.AccountKit.Response) {
        this.presenter?.presentAccountKit(response)
    }
}