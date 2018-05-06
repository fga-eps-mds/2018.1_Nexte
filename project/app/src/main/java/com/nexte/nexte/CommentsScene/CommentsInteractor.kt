package com.nexte.nexte.CommentsScene

import com.github.kittinunf.fuel.core.interceptors.loggingResponseInterceptor

/**
 * Interface to define Business Logic to Comments Class
 * that will used to call this Interactor on other class layer
 */
interface CommentsBusinessLogic {

    /**
     * Method that defines recent comments information that will be responsible to
     * pass the request to Worker and send the response to Presenter
     *
     * @param request Request model from getCommentsRequest that contains data to pass for Worker
     */
    fun recentComments (request: CommentsModel.GetCommentsRequest.Request)

    /**
     * Method that defines the comments information published that will be responsable to
     * pass the request to Worker and send the response to Presenter.
     * @param request Resquest model from publishCommentsRequest that contains data to pass
     * for Worker.
     */
    fun publishNewComment(request: CommentsModel.PublishCommentRequest.Request)

    /**
     * Method that defines the alert message when the user wants to report a comment and will pass
     * the request to Worker and send the response to Presenter.
     * @param request Request model from ComplaintRequest that contains data to pass for Worker.
     */
    fun sendComplaint (request: CommentsModel.ComplaintRequest.Request)

    fun deleteComment (request: CommentsModel.DeleteCommentRequest.Request)
}

/**
* Class that implements [CommentsBusinessLogic] and is responsible for the communication
* between worker and presenter
*
* @property worker Reference to Worker [CommentsWorker]
* @property presenter Reference to Presenter [CommentsPresenter]
*/
class CommentsInteractor(var presenter : CommentsPresentationLogic? = null) : CommentsBusinessLogic {

    var worker = CommentsWorker()

    override fun recentComments(request: CommentsModel.GetCommentsRequest.Request) {
        worker.getCommentsData(request) { response ->
            this.presenter?.presentComment(response)
        }
    }

    override fun publishNewComment(request: CommentsModel.PublishCommentRequest.Request) {
        worker.setNewComment(request) { response ->
            this.presenter?.presentNewComment(response)
        }
    }

    override fun sendComplaint(request: CommentsModel.ComplaintRequest.Request) {
        worker.sendComplaint(request) { response ->
            this.presenter?.presentComplaint(response)
        }
    }

    override fun deleteComment(request: CommentsModel.DeleteCommentRequest.Request) {
        worker.getToDeleteComment(request) { response ->
            this.presenter?.presentPositionToDelete(response)
        }
    }
}