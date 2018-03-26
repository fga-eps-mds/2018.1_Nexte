package com.nexte.nexte.LoginScene

/**
 * Created by miguelpimentel on 24/03/18.
 */

interface LoginPresentationLogic {

    fun presentLogin(response: LoginModel.Response)
}

class LoginPresenter: LoginPresentationLogic {

    var viewControler: LoginDisplayLogic? = null // Reference to view

    // Format data to expected
    override fun presentLogin(response: LoginModel.Response) {

        var message: String = ""
        val tokenId: String = response.tokenId

        if(tokenId.equals("")) {
            message = "Something is wrong. Try again"
        } else {
            message = "Congratz! U get it"
        }

        val viewModel: LoginModel.ViewModel = LoginModel.ViewModel(message)
        this.viewControler?.displayAuthenticateState(viewModel)
    }
}