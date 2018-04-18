package com.nexte.nexte.CommentsScene

/**
* Class to define the Model of Feed Scene to send informations between layers
*/
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
     * Class responsible to store received informations from Worker and pass it to Presenter
     *
     * @property comment the user's comment
     * @property userName the username of the user that commented
     * @property linkUserProfilePicture the profile picture of the user
     * @property commentTime the time of the comment
     * @property like like option to the comment
     */
    class Response(var comment: String?,
                   var userName: String?,
                   var linkUserProfilePicture: String?,
                   var commentTime: String?,
                   var like: Boolean?)

    /**
     * Class responsible to define how message will display the formatted data passed to view
     *
     * @property message message that will be shown in te screen
     */
    class ViewModel(var message: String?)
}