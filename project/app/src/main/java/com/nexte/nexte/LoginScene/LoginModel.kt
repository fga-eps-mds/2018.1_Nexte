package com.nexte.nexte.LoginScene

/**
 * Class to define Model of Login Scene to enable the acess to users. It contains [Request],
 * [Response] and [ViewModel] classes to be used on the flow of getting the login data and
 * setting the token that contains personal information from user on the system
 */
class LoginModel {

    /**
     * Class responsible to pass data of View to Interactor and after to worker can do
     * request data
     *
     * @property userName variable that hold the name chosen by the user on the app
     * @property password variable that hold the secret keyword of the user
     */
    class Request(var userName: String, var password: String)

    /**
     * Class responsible to store received informations of worker to pass for Presenter
     *
     * @property tokenId variable that contains the user's personal data in the system and
     * validates them in the application
     */
    class Response(var tokenId: String)

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @property message that hold informations to show in screen if login was sucessful or not
     */
    class ViewModel(var message: String)
}