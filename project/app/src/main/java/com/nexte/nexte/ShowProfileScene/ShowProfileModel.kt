package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Player

/**
 * Class to define Model of ShowProfile Scene to enable the access to users
 * This class declares the [Request], [Response], [ViewModel] and [Player],
 * informations that will define what will be passed between classes
 */
class ShowProfileModel {

    /**
     * Class responsible to pass data of View to Interactor and after to Worker so it can
     * request data
     *
     * @property username variable that holds the name chosen by the user on the app
     * @property tokenID variable that holds the token that validates the user in the system
     **/
    class Request(var username: String, var tokenID: String)

    /**
     * Class responsible to store received informations of worker to pass for Presenter
     *
     * @property user variable that return the attributes of player after the validation step
     **/
    class Response(var user: Player?) // User returned after validation

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @property playerInfo attribute already processed by [ShowProfilePresenter] class for display on screen
     **/
    class ViewModel(var playerInfo: FormattedPlayer)

    //------------------------------------- Aux Classes ------------------------------------------

    /**
     * This class holds the formatted player information that will be defined on [ShowProfilePresenter]
     *
     * @property name Name displayed
     * @property rank The ranking position displayed
     * @property club User club displayed
     * @property email User email displayed
     * @property age User age displayed
     */
    class FormattedPlayer(
            var name: String?,
            var rank: String?,
            var club: String?,
            var email: String?,
            var age: String?)
}
