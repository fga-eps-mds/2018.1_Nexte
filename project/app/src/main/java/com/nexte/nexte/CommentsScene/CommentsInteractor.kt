package com.nexte.nexte.CommentsScene

/**
 * Created by leticia on 29/03/18.
 */

interface CommentsBusinessLogic {

    fun recentComments (request: CommentsModel.Request)
}

class CommentsInteractor : CommentsBusinessLogic {

    var worker = CommentsWorker()
    var presenter : CommentsPresentationLogic? = null

    override fun recentComments(request: CommentsModel.Request) {
        worker.getCommentsData(request) { response ->
            this.presenter?.presentLastComment(response)
        }
    }
}