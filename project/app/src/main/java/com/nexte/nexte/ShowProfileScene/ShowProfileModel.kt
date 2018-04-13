package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Player

/**
 * Created by albino on 25/03/18.
 */

/**
 * This class declares the request, response, viewModel and player, information that will define what will be passed between classes
 */
class ShowProfileModel {

    /**
     * This class contain the parameters necessary for validation of the logged user
     **/
    class Request(username: String, tokenID: String) {
        var username: String? = username // Name provided by user
        var tokenID: String? = tokenID // TokenID provided by server
    }

    /**
     * This class will return the attributes of player after the validation step
     **/
    class Response(
            var user: Player?)// User returned after validation

    /**
     * Receives the attribute already processed by [ShowProfilePresenter] class for display on screen
     **/
    class ViewModel(var playerInfo: FormattedPlayer)

    //---------------------------------------------Aux Classes ------------------------------------------

    /**
     * This class holds the formatted player information that will be defined on [ShowProfilePresenter]
     */
    class FormattedPlayer(
            var name: String?,// Name displayed
            var rank: String?,// The rank position displayed
            var club: String?,// User club displayed
            var email: String?,// User email displayed
            var age: String?)// User age displayed
}
