package com.nexte.nexte.CommentsScene

import com.nexte.nexte.CommentsScene.CommentsModel.GetCommentsRequest.Response
import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import java.util.*

interface CommentsWorkerUpdateLogic {

    fun updateComment(response: Response)
    fun updateNewComment(response: CommentsModel.PublishCommentRequest.Response)
    fun updateDeleteComment(response: CommentsModel.DeleteCommentRequest.Response)
}

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class CommentsWorker {

    var updateLogic: CommentsWorkerUpdateLogic? = null
    var commentsManager: CommentManager? = null
    /**
     * Variable created to simulate mocked data to be implemented on Package mocker
     */

    /**
     * Function to get comments data of server
     *
     * @param request Comments model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun getCommentsData (request: CommentsModel.GetCommentsRequest.Request) {

        val commentManager= commentsManager?.getAll()
        val response = Response(commentManager!!.toMutableList())
        updateLogic?.updateComment(response)
    }

    /**
     * Function responsible to set new comment that contains a message, updated date and an author
     * and passed the new comment to response
     * @param request Comments model from PublishCommentRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun setNewComment (request: CommentsModel.PublishCommentRequest.Request) {

        val message = request.commentToPost
        val today = Date()
        val author = "1"
        val newComment = Comment(idComment, author, message, today)
        val commentManager= commentsManager?.update(newComment)
        val response = CommentsModel.PublishCommentRequest.Response(newComment)

        updateLogic?.updateNewComment(response)
    }

    /**
     * Function responsible to submit an alert message when the user wants to report a comment
     * and passed the message to response.
     * @param request Comments model from ComplaintRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun sendComplaint (request: CommentsModel.ComplaintRequest.Request) {

        val serverCode = okMessage
        val response = CommentsModel.ComplaintRequest.Response(serverCode)

    }

    /**
     * Function responsible to delete the comment at the position set by request
     * and to send the list [commentsMockedData] after deletion as the response to interactor
     *
     * @param request Position of the comment to be deleted
     * @param completion List of unformatted list of comments after deletion
     */
    fun getToDeleteComment (request: CommentsModel.DeleteCommentRequest.Request){

        val comments = this.commentsManager?.delete(request.commentIdentifier.toString())
        val response = CommentsModel.DeleteCommentRequest.Response(comments!!)

       updateLogic?.updateDeleteComment(response)
    }

    /**
     * Function to create fictional comments to use in fictional app mode
     *
     * @return MutableList of addComments
     */
//    private fun generateCommentsList(): MutableList<CommentsModel.Comment> {
//
//        val player1 = CommentsModel.Player("Lorrany", R.mipmap.ic_launcher)
//        val player2 = CommentsModel.Player("Alexandre", R.mipmap.ic_launcher)
//        val player3 = CommentsModel.Player("Larissa", R.mipmap.ic_launcher)
//        val player4 = CommentsModel.Player("Letícia", R.mipmap.ic_launcher)
//
//        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
//                                                Date(),
//                                                player1, 1)
//        val comment2 = CommentsModel.Comment("Boa galera, vocês arrasaram",
//                                                Date(),
//                                                player2, 2)
//        val comment3 = CommentsModel.Comment("Isso mesmo, man. Que jogão",
//                                                Date(),
//                                                player3, 3)
//        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
//                                                Date(),
//                                                player4, 4)
//
//        val addComments: MutableList<CommentsModel.Comment> = mutableListOf(
//                comment1,
//                comment2,
//                comment3,
//                comment4)
//
//        return addComments
//    }

    companion object {
        const val okMessage = 200
        const val idComment = "108"
    }
}