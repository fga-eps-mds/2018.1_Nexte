package com.nexte.nexte.CommentsScene

/**
 * Interface to define Business Logic to Comments Class
 * that will used to call this Interactor on other class layer
 */
interface CommentsBusinessLogic {

    /**
     * Method that defines recent comments information that will be responsible to
     * pass the request to Worker and send the response to Presenter
     *
     * @param request Request model of comments that contains data to pass for Worker
     */
    fun recentComments (request: CommentsModel.GetCommentsRequest.Request)
    fun publishNewComment(request: CommentsModel.PublishCommentRequest.Request)
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
}