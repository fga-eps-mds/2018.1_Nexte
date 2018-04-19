package com.nexte.nexte.LoginScene

/**
 * Interface to define Presentation Logic to Login Class that
 * will be used to call this Interactor on other class layer
 */
interface LoginPresentationLogic {

    /**
     * Method responsible to format feed data and send for view
     *
     * @param response It's login model response containing unformatted data
     * received [LoginModel]
     */
    fun presentLogin(response: LoginModel.Response)
}

/**
 * Class needed to format response for data can be displayed on activity
 *
 * @property viewController Reference to the activity where data will be displayed on View
 */
class LoginPresenter(var viewController: LoginDisplayLogic? = null): LoginPresentationLogic {

    override fun presentLogin(response: LoginModel.Response) {

        val message: String
        val tokenId: String = response.tokenId

        if(tokenId.equals("")) {
            message = "Something is wrong. Try again"
        } else {
            message = "Congratz! U get it"
        }

        val viewModel: LoginModel.ViewModel = LoginModel.ViewModel(message)
            this.viewController?.displayAuthenticateState(viewModel)
    }
}