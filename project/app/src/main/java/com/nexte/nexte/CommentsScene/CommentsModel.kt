package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Player
import java.util.*

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
    */
    class Request(var request: String?)

    /**
     * Class responsible to store received information from Worker and pass it to Presenter
     * @param comments
     */
    class Response(var comments: MutableList<Comment>)

    /**
     * Class responsible to define how message will display the formatted data passed to view
     *
     * @param commentsFormatted MutableList that hold information to show in screen
     */
    class ViewModel(var commentsFormatted: MutableList<CommentFormatted>)

    // ---------- Aux classes ----------
    /**
     * Class with information about users who will be displayed in the comments list
     * @param name
     * @param photo
     * @param time
     */
    class Player(var name: String, var photo: Int)

    /**
     * Class with formatted information about users and how they will be displayed in View
     * @param: name
     * @param:photo
     * @param: time
     */

    class Comment(var comment: String,
                  var date: Date,
                  var author: Player)

    class CommentFormatted(var comment: String,
                           var commentDate: String,
                           var username: String,
                           var profilePic: Int)
}