package com.nexte.nexte.CommentsScene

import android.view.ContextMenu

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
    fun presentComment(response: CommentsModel.Response)
}

/**
 * Class needed to format response so the data can be displayed on activity
 *
 * @property viewController Reference to the activity where data will be displayed
 * on view
 */
class CommentsPresenter : CommentsPresentationLogic {
    var viewController: CommentsDisplayLogic? = null

    override fun presentComment(response: CommentsModel.Response) {

        val viewModel = CommentsModel.ViewModel(formatComment(response.comments))

        viewController?.displayComments(viewModel)
    }

    private fun formatComment(gameComments: MutableList<CommentsModel.Comment>) :
            MutableList<CommentsModel.CommentFormatted> {

        val commentsFormatted: MutableList<CommentsModel.CommentFormatted> = mutableListOf()

        for (gameComment in gameComments) {

            val commentFormatted = CommentsModel.CommentFormatted(
                    gameComment.comment,
                    gameComment.date.toString(),
                    gameComment.author.name,
                    gameComment.author.photo)

            commentsFormatted.add(commentFormatted)
        }

        return commentsFormatted
    }
}