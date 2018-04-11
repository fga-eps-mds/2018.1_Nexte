package com.nexte.nexte.FeedScene

/**
 * Interface to define Business Logic to Feed Class that will used to call this Interactor on other class layer
 */
interface FeedBusinessLogic {
    /**
     * Method that define fetch feed informations that will be responsable to get
     * pass request for the Worker and send response to the Presenter
     *
     * @param request Request model of feed that contains data to pass for Worker
     */
    fun fetchFeed(request: FeedModel.Request)
    fun fetchManageLikes(identifier: String, activity: FeedModel.FeedActivity)
}

/**
 * Class that implements [FeedBusinessLogic] and is responsible for the communication between worker and presenter
 *
 * @property worker Reference to worker [FeedWorker]
 * @property presenter Reference to presenter [FeedPresenter]
 */
class FeedInteractor(var presenter: FeedPresentationLogic? = null) : FeedBusinessLogic {

    val worker: FeedWorker = FeedWorker()

    override fun fetchFeed(request: FeedModel.Request) {
        worker.fetchFeedData(request) { response ->
            presenter?.formatFeed(response)
        }
    }

    override fun fetchManageLikes(identifier: String, activity: FeedModel.FeedActivity) {
        worker.manageLikes(activity, identifier) { updatedFeedActivity ->
            presenter?.updateViewActivity(updatedFeedActivity)
        }
    }
}
