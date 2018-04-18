package com.nexte.nexte.FeedScene

/**
 * Interface to define Business Logic of Feed Class
 * that will be used to call this Interactor on other class layer
 */
interface FeedBusinessLogic {

    /**
     * Method that defines fetch feed information that will be responsible to
     * pass the request to Worker and send the response to Presenter
     *
     * @param request Request model of feed that contains data to pass for Worker
     */
    fun fetchFeed(request: FeedModel.GetFeedActivities.Request)

    /**
     * Method that defines the identifier of the activity to be altered
     * due to the pressing of like button, sending this activity to be altered in
     * Worker and then sent to Presenter through this method.
     *
     * @param request Activity identifier to fetch the activity to be altered
     */
    fun fetchLikes(request: FeedModel.LikeAndUnlike.Request)
}

/**
 * Class that implements [FeedBusinessLogic] and is responsible for the communication
 * between worker and presenter
 *
 * @property worker Reference to worker [FeedWorker]
 * @property presenter Reference to presenter [FeedPresenter]
 */
class FeedInteractor(var presenter: FeedPresentationLogic? = null) : FeedBusinessLogic {

    val worker: FeedWorker = FeedWorker()

    override fun fetchFeed(request: FeedModel.GetFeedActivities.Request) {
        worker.fetchFeedData(request) { response ->
            presenter?.formatFeed(response)
        }
    }

    override fun fetchLikes(request: FeedModel.LikeAndUnlike.Request) {
        worker.manageLikes(request) { response ->
            presenter?.updateViewActivity(response)
        }
    }
}
