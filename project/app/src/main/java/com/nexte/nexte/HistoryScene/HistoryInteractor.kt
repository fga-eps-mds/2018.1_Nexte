package com.nexte.nexte.HistoryScene

/**
 * Created by helena on 03/04/18.
 */

interface HistoryBusinessLogic {
    fun getPlayerGames(request: HistoryModel.Request)
}
/*
This class is responsible for the communication between worker and presenter
 */
class HistoryInteractor : HistoryBusinessLogic {
    var worker = HistoryWorker() // Reference to worker
    var presenter: HistoryPresentationLogic? = null // Reference to presenter

    /*
    This method is responsible to get the response of the worker and send it to the presenter
     */
    override fun getPlayerGames(request: HistoryModel.Request) {
        worker.filterPlayerGames(request) { response ->
            presenter?.formatPlayerMatches(response)
        }
    }
}