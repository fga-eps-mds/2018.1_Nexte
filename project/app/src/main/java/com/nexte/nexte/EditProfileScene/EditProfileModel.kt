package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.*

/**
 * Created by albino on 27/03/18.
 */

/**
 * Class responsible to determinate what will be passed between classes of scene
 */
class EditProfileModel {

    /**
     * This class is responsible to determinate what will be passed between classes of scene when recovering an existing user
     */
    class FirstRequest {

        /**
         * Class responsible to holds the information that will be passed from [ViewModel] to [EditProfileInteractor]
         */
        class Request(var username: String, var tokenID: String)

        /**
         * Class responsible to holds the information that will be passed for [EditProfilePresenter] by [EditProfileWorker]
         */
        class Response(var user: Player)

        /**
         * Class responsible to holds the information that will be passed from [EditProfilePresenter] to [EditProfileView]
         */
        class ViewModel( var playerToEdit: FormattedPlayer)

        //-----------------------------------Aux classes ---------------------------------------------

        /**
         * Class responsible to holds formatted player information ceded by [EditProfilePresenter]
         */
        class FormattedPlayer(var username: String, //name of the logged user
                             var ranking: String, // ranking of the logged user
                             var club: String, // club of the logged user
                             var age: String, //age of the logged user
                             var email: String) //email of the logged user
    }

    /**
     * This class is responsible to determinate what will be passed between classes of scene when editing an existing user
     */
    class SecondRequest {
        /**
         * Class responsible to holds the information that will be passed from [EditProfileView] to [EditProfileInteractor]
         */
        class Request(var user: Player)

        /**
         * Class responsible to holds the information that will be passed for [EditProfilePresenter] by [EditProfileWorker]
         */
        class Response(var errorID: Int?,
                       var newUser: Player?)

        /**
         * Class responsible to holds the information that will be passed from [EditProfilePresenter] to [EditProfileView]
         */
        class ViewModel(var errorMessage: String?)
    }
}