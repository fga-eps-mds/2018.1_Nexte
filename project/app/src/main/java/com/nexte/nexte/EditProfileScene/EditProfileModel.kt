package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User

/**
 * Class responsible to determinate what will be passed between classes of scene
 */
class EditProfileModel {

    /**
     * This class is responsible to determinate what will be passed between classes
     * of scene when recovering an existing user
     */
    class RecoverUserRequest {

        /**
         * Class responsible to holds the information that will be passed
         * from [ViewModel] to [EditProfileInteractor]
         *
         * @property id Id of the logged User
         * @property tokenID contains the token id
         */
        class Request(var id: String, var tokenID: String)

        /**
         * Class responsible to holds the information that will be passed
         * for [EditProfilePresenter] by [EditProfileWorker]
         *
         * @property user
         */
        class Response(var user: User)

        /**
         * Class responsible to holds the information that will be passed
         * from [EditProfilePresenter] to [EditProfileFragment]
         *
         * @property playerToEdit
         */
        class ViewModel(var playerToEdit: FormattedPlayer)

        //--------------------- Aux classes ---------------------

        /**
         * Class responsible to hold formatted player information ceded by [EditProfilePresenter]
         * @property username Name of the logged user
         * @property ranking Ranking of the logged user
         * @property club Club of the logged user
         * @property email Email of the logged user
         * @property phone Phone of the user
         * @property picture Picture of user
         */
        class FormattedPlayer(var username: String,
                              var ranking: String,
                              var club: String,
                              var email: String,
                              var phone: String,
                              var picture: Int)
    }

    /**
     * This class is responsible to determinate what will be passed
     * between classes of scene when editing an existing user
     */
    class EditProfileRequest {
        /**
         * Class responsible to holds the information that will be
         * passed from [EditProfileFragment] to [EditProfileInteractor]
         *
         * @property user
         */
        class Request(var user: User,
                      var password: String)

        /**
         * Class responsible to holds the information that will be
         * passed for [EditProfilePresenter] by [EditProfileWorker]
         *
         * @property errorID
         * @property newUser
         */
        class Response(var errorID: Int?,
                       var newUser: User?)

        /**
         * Class responsible to holds the information that will be
         * passed from [EditProfilePresenter] to [EditProfileFragment]
         *
         * @property errorMessage
         */
        class ViewModel(var errorMessage: String?)
    }
}