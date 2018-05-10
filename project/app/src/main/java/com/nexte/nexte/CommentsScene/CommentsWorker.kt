package com.nexte.nexte.CommentsScene

import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import java.util.*

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class CommentsWorker {

    /**
     * Variable created to simulate mocked data to be implemented on Package mocker
     */
    var CommentsMockedData = generateCommentsList()

    /**
     * Function to get comments data of server
     *
     * @param request Comments model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun getCommentsData (request: CommentsModel.GetCommentsRequest.Request,
                         completion: (CommentsModel.GetCommentsRequest.Response) -> Unit) {

        val response = CommentsModel.GetCommentsRequest.Response(this.generateCommentsList())
        completion(response)
    }

    /**
     * Function responsible to set new comment that contains a message, updated date and an author
     * and passed the new comment to response
     * @param request Comments model from PublishCommentRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun setNewComment (request: CommentsModel.PublishCommentRequest.Request,
                       completion: (CommentsModel.PublishCommentRequest.Response) -> Unit) {
        val message = request.commentToPost
        val today = Date()
        val author = CommentsModel.Player(UserSingleton.getUserInformations().name, R.mipmap.ic_launcher)
        val newComment = CommentsModel.Comment(message, today, author, 5)
        this.CommentsMockedData.add(newComment)
        val response = CommentsModel.PublishCommentRequest.Response(newComment)

        completion (response)
    }

    /**
     * Function responsible to submit an alert message when the user wants to report a comment
     * and passed the message to response.
     * @param request Comments model from ComplaintRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun sendComplaint (request: CommentsModel.ComplaintRequest.Request,
                       completion: (CommentsModel.ComplaintRequest.Response) -> Unit) {

        val serverCode = okMessage

        val response = CommentsModel.ComplaintRequest.Response(serverCode)

        completion(response)
    }

    fun getToDeleteComment (request: CommentsModel.DeleteCommentRequest.Request,
                            completion: (CommentsModel.DeleteCommentRequest.Response) -> Unit){

        this.CommentsMockedData.removeAt(request.commentIdentifier)
        val response = CommentsModel.DeleteCommentRequest.Response(this.CommentsMockedData)

        completion(response)
    }

    /**
     * Function to create fictional comments to use in fictional app mode
     *
     * @return MutableList of addComments
     */
    private fun generateCommentsList(): MutableList<CommentsModel.Comment> {

        val player1 = CommentsModel.Player("Lorrany", R.mipmap.ic_launcher)
        val player2 = CommentsModel.Player("Alexandre", R.mipmap.ic_launcher)
        val player3 = CommentsModel.Player("Larissa", R.mipmap.ic_launcher)
        val player4 = CommentsModel.Player("Letícia", R.mipmap.ic_launcher)

        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
                                                Date(),
                                                player1, 1)
        val comment2 = CommentsModel.Comment("Boa galera, vocês arrasaram",
                                                Date(),
                                                player2, 2)
        val comment3 = CommentsModel.Comment("Isso mesmo, man. Que jogão",
                                                Date(),
                                                player3, 3)
        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
                                                Date(),
                                                player4, 4)

        val addComments: MutableList<CommentsModel.Comment> = mutableListOf(
                comment1,
                comment2,
                comment3,
                comment4)

        return addComments
    }

    companion object {
        const val okMessage = 200
    }
}