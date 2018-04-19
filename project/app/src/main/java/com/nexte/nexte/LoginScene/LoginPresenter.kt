package com.nexte.nexte.LoginScene

/**
 * Interface resposible to format request provided in Interactor to data as
 * it should be presented at view as viewModel
 */
interface LoginPresentationLogic {

    fun presentLogin(response: LoginModel.Response)
}

/**
 * Class that formats data from LoginInteractor(request) to LoginView(viewModel)
 *
 * @property view reference to view
 */
class LoginPresenter: LoginPresentationLogic {

    var view: LoginDisplayLogic? = null

    /**
     * Method that formats data from response to viewModel
     *
     * @property view reference to view
     */
    override fun presentLogin(response: LoginModel.Response) {

        var message: String
        val tokenId: String = response.tokenId
        println("TOOKEN: ${response.tokenId}")

        if(tokenId.equals("")) {
            message = "Something is wrong. Try again"
        } else {
            message = "Congratz! U get it"
        }

        val viewModel: LoginModel.ViewModel = LoginModel.ViewModel(message)
        this.view?.displayAuthenticateState(viewModel)
    }
}