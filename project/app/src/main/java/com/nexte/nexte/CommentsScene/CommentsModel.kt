package com.nexte.nexte.CommentsScene

import java.util.*

/**
 * Class to define Model of Comments Scene to enable the conversation between users.
 * It contains Request, Response and ViewModel classes to be used on the flow
 * of getting the game to be commented and setting details of personal information from user
 * on the system and his actions on game
 * */
class CommentsModel {

    /**
     * This class is responsible to get the comments when user opens the scene
     */
    class GetCommentsRequest {
        /**
         * Class responsible to pass data from View to Interactor and then to worker,
         * so it can request data
         */
        class Request

        /**
         * Class responsible to store received information from Worker and pass it to Presenter
         * @param comments contains all the comments that already exists on a game
         */
        class Response(var comments: MutableList<Comment>)

        /**
         * Class responsible to define how message will display the formatted data passed to view
         *
         * @param commentsFormatted MutableList that hold information to show in screen
         */
        class ViewModel(var commentsFormatted: MutableList<CommentFormatted>)
    }

    /**
     * This class is responsible to show an alert message when the user click on report button
     */

    class ComplaintRequest {

        /**
         * Class responsible to pass data from View to Interactor and then to worker,
         * so it can request data
         *
         * @param commentID contains the identifier of the reported comment
         */
        class Request(var commentID: Int)
        /**
         * Class responsible to store received information from Worker and pass it to Presenter
         * @param serverResponse contains the server response to the user
         */
        class Response(var serverResponse: Int)
        /**
         * Class responsible to define how message will display the formatted data passed to view
         *
         * @param alertMessage contains the formatted error or success message that will be displayed to the user
         */

        class ViewModel(var alertMessage: String)

    }

    /**
     * This class is responsable to publish the comments when the user wrote.
     */

    class PublishCommentRequest {
        /**
         * Class responsible to pass data from View to Interactor and then to worker,
         * so it can request data
         * @param commentToPost contains the message of the comment that will be posted
         */
        class Request(var commentToPost: String)

        /**
         * Class responsible to store received information from Worker and pass it to Presenter
         * @param newComment contains the comment with user and id data
         */
        class Response(var newComment: Comment)

        /**
         * Class responsible to define how message will display the formatted data passed to view
         *
         * @param newCommentFormatted contains the formatted comment, ready to be displayed
         */
        class ViewModel(var newCommentFormatted: CommentFormatted)

    }

    class DeleteCommentRequest {

        class Request (var deleteCommentID: String)

        class Response (var positionComment: Int)

        class ViewModel (var positionComment: Int)
    }
    // ---------- Aux classes ----------
    /**
     * Class with information about the player who will be displayed in the comments list
     * @param name contains the player name
     * @param photo cointains the player picture
     */
    class Player(var name: String, var photo: Int)

    /**
     * Class with formatted information about users and how they will be displayed in View
     * @param comment contains the comment message
     * @param date contains the date when the comment was posted
     * @param author contains the player that posted the comment
     * @param commentId contains the comment identifier
     */
    class Comment(var comment: String,
                  var date: Date,
                  var author: Player,
                  var commentId: Int)

    /**
     * Class with formatted information about users and how they will be displayed in View
     * @param comment contains the comment message
     * @param commentDate contains the formatted date to be displayed
     * @param username contains the comment's owner name
     * @param profilePic  contains the comment's owner picture
     */
    class CommentFormatted(var comment: String,
                           var commentDate: String,
                           var username: String,
                           var profilePic: Int)
}
