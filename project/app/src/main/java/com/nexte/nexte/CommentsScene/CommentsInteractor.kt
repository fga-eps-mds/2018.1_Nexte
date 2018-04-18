package com.nexte.nexte.CommentsScene

/**
 * Interface to define Business Logic to Feed Class
 * that will used to call this Interactor on other class layer
 */
interface CommentsBusinessLogic {

    /**
     * Method that defines recent comments informations that will be responsible to
     * pass the request to Worker and send the response to Presenter
     *
     * @param request Request model of comments that contains data to pass for Worker
     */
    fun recentComments (request: CommentsModel.Request)
}

/**
* Class that implements [CommentsBusinessLogic] and is responsible for the communication
* between worker and presenter
*
* @property worker Reference to worker [CommentsWorker]
* @property presenter Reference to presenter [CommentsPresenter]
*/
class CommentsInteractor : CommentsBusinessLogic {

    var worker = CommentsWorker()
    var presenter : CommentsPresentationLogic? = null

    override fun recentComments(request: CommentsModel.Request) {
        worker.getCommentsData(request) { response ->
            this.presenter?.presentComment(response)
        }
    }
}