package com.nexte.nexte.CommentsScene

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
        val message: String
        val comment: String? = response.comment

        message = when {
            comment.equals("") -> "Não há comentário"
            else -> "Um comentário existente"
        }

        val viewModel : CommentsModel.ViewModel = CommentsModel.ViewModel(message)

        viewController?.displayComments(viewModel)
    }
}