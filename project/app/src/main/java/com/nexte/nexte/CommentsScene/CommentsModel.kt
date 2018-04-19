package com.nexte.nexte.CommentsScene

/**
 * Class to define Model of Comments Scene to enable the conversation between users.
 * It contains [Request],  * [Response] and [ViewModel] classes to be used on the flow
 * of getting the game to be commented and setting details of personal information from user
 * on the system and his actions on game
 * */
class CommentsModel {

    /**
    * Class responsible to pass data from View to Interactor and then to worker,
    * so it can request data
    *
    * @property game which game the user commented at
    * @property user which user commented
    */
    class Request(var game: String?,
                  var user: String?)

    /**
     * Class responsible to store received information from Worker and pass it to Presenter
     *
     * @property comment it's the user's comment
     * @property userName it's the username of the user that commented
     * @property linkUserProfilePicture it's the profile picture of the user
     * @property commentTime it's the time of the comment
     * @property like it's a like option to the comment
     */
    class Response(var comment: String?,
                   var userName: String?,
                   var linkUserProfilePicture: String?,
                   var commentTime: String?,
                   var like: Boolean?)

    /**
     * Class responsible to define how message will display the formatted data passed to view
     *
     * @property message is a text message that will be show in te screen
     */
    class ViewModel(var message: String?)
}