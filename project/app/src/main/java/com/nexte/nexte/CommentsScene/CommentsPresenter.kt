package com.nexte.nexte.CommentsScene

/**
 * Created by leticia on 29/03/18.
 */

interface CommentsPresentationLogic {

    fun presentComment(response: CommentsModel.Response)
}

class CommentsPresenter : CommentsPresentationLogic {
    var viewController: CommentsDisplayLogic? = null

    override fun presentComment(response: CommentsModel.Response) {
        var message: String
        val comment: String? = response.userName

        if(comment.equals("")) {
            message = "Não há comentário"
        } else {
            message = "Um comentário existente"
        }
        var viewModel : CommentsModel.ViewModel = CommentsModel.ViewModel(message)

        viewController?.displayComments(viewModel)
    }

}