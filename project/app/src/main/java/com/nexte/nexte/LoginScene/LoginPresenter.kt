package com.nexte.nexte.LoginScene

/**
 * Interface resposible to format request provided in Interactor to data as
 * it should be presented at view as viewModel
 */
interface LoginPresentationLogic {

    /**
     * Method responsible to format login data and send for view
     *
     * @param response It's login model response containing unformatted data
     * received [LoginModel]
     */
    fun presentLogin(response: LoginModel.Response)

    /**
     * Method responsible to handle with authentication not expected
     *
     * @param response It's login model response containing unformatted data
     * received [LoginModel]
     */
    fun presentError(response: LoginModel.Response)

    fun presentAccountKit(response: LoginM.Response)

}

/**
 * Class that formats data from LoginInteractor(request) to LoginView(viewModel)
 *
 * @property view reference to view
 */
class LoginPresenter: LoginPresentationLogic {

    var view: LoginDisplayLogic? = null

    override fun presentLogin(response: LoginModel.Response) {

        val message: String
        val tokenId: String = response.tokenId

        if(tokenId.equals("")) {
            message = "Something is wrong. Try again"
        } else {
            message = "Congratz! U get it"
        }

        val viewModel: LoginModel.ViewModel = LoginModel.ViewModel(message)
        this.view?.displayAuthenticateState(viewModel)
    }

    override fun presentError(response: LoginModel.Response) { }

    override fun presentAccountKit(response: LoginM.Response) {

        val viewModel = LoginM.ViewModel("WOW")
        this.view?.displayAccountKit(viewModel)
    }
}

