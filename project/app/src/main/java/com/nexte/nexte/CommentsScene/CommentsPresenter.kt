package com.nexte.nexte.CommentsScene

import com.nexte.nexte.LoginScene.CommentsDisplayLogic

/**
 * Created by leticia on 29/03/18.
 */

interface CommentsPresentationLogic {

    fun presentLastComment(response: CommentsModel.Response)
}

class CommentsPresenter : CommentsPresentationLogic {
    var viewController: CommentsDisplayLogic? = null

    override fun presentLastComment(response: CommentsModel.Response) {
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