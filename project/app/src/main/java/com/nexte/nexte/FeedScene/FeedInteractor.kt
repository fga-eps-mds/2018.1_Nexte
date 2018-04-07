package com.nexte.nexte.FeedScene

/**
 * Created by helena on 03/04/18.
 */

interface FeedBusinessLogic {
    fun fetchFeed(request: HistoryModel.Request)
}
/*
This class is responsible for the communication between worker and presenter
 */
class FeedInteractor : FeedBusinessLogic {

    var worker = FeedWorker() // Reference to worker
    var presenter: FeedPresentationLogic? = null // Reference to presenter

    /*
    This method is responsible to get the response of the worker and send it to the presenter
     */
    override fun fetchFeed(request: HistoryModel.Request) {

        worker.requestFeedData(request) { response ->
            presenter?.formatFeed(response)
        }
    }
}