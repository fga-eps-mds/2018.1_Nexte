package com.nexte.nexte.CommentsScene

import java.text.DateFormat.getAvailableLocales
import java.text.DateFormat.getTimeInstance
import java.text.SimpleDateFormat
import java.util.*

/**
 * Interface to define Presentation Logic to Comment Class that
 * will be used to call this Interactor on other class layer
 */
interface CommentsPresentationLogic {

    /**
     * Method responsible to format comments data and send to view
     *
     * @param response contains unformatted data received from [CommentsModel]
     */
    fun presentComment(response: CommentsModel.GetCommentsRequest.Response)
    fun presentNewComment (response: CommentsModel.PublishCommentRequest.Response)
    fun presentComplaint (response: CommentsModel.ComplaintRequest.Response)
    fun presentPositionToDelete(response: CommentsModel.DeleteCommentRequest.Response)
}

/**
 * Class needed to format response so the data can be displayed on activity
 *
 * @property viewController Reference to the activity where data will be displayed
 * on view
 */
class CommentsPresenter : CommentsPresentationLogic {

    var viewController: CommentsDisplayLogic? = null

    override fun presentComment(response: CommentsModel.GetCommentsRequest.Response) {

        val viewModel = CommentsModel.GetCommentsRequest.ViewModel(formatComment(response.comments))
        viewController?.displayComments(viewModel)
    }

    /**
     * Function that formatted the new comment wrote by user to send to View
     * @param response
     */

    override fun presentNewComment(response: CommentsModel.PublishCommentRequest.Response) {

        val newComment = response.newComment
        val dateToShow = SimpleDateFormat("EEE, dd 'of' LLL")
        val time = dateToShow.format(newComment.date)

        val formatted = CommentsModel.CommentFormatted(

                newComment.comment,
                time,
                newComment.author.name,
                newComment.author.photo
        )

        val viewModel = CommentsModel.PublishCommentRequest.ViewModel(formatted)

        viewController?.displayPublishedComment(viewModel)
    }

    /**
     * Function that formatted the alert message to send to View and define the message in case
     * of success ou error.
     * @param response
     */
    override fun presentComplaint(response: CommentsModel.ComplaintRequest.Response) {
        val message: String

        if (response.serverResponse == okMessage) {
            message = "Denúncia efetuada com sucesso"
        }
        else {
            message = "Erro ao conectar com o servidor"
        }
        val viewModel = CommentsModel.ComplaintRequest.ViewModel(message)

        viewController?.displayComplaintMessage(viewModel)
    }

    override fun presentPositionToDelete(response: CommentsModel.DeleteCommentRequest.Response) {

        val viewModel = CommentsModel.DeleteCommentRequest.ViewModel(formatComment(response.delComments))
        viewController?.displayCommentsAfterDel(viewModel)
    }

    /**
     * Function that converts the MutableList Comment unformatted to
     * MutableList CommentFormatted.
     *
     * @param gameComments MutableList of unformatted comments
     * @return MutableList of formatted comments
     */
    private fun formatComment(gameComments: MutableList<CommentsModel.Comment>) :
            MutableList<CommentsModel.CommentFormatted> {

        val commentsFormatted: MutableList<CommentsModel.CommentFormatted> = mutableListOf()

        for (gameComment in gameComments) {

            val dateToShow = SimpleDateFormat("EEE, dd 'of' LLL")
            val time = dateToShow.format(gameComment.date)

            val commentFormatted = CommentsModel.CommentFormatted(
                    gameComment.comment,
                    time,
                    gameComment.author.name,
                    gameComment.author.photo)

            commentsFormatted.add(commentFormatted)
        }

        return commentsFormatted
    }


    companion object {
        const val okMessage = 200
    }
}