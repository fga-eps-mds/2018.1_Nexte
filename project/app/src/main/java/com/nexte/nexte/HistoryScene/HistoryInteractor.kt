package com.nexte.nexte.HistoryScene

/**
 * Created by helena on 03/04/18.
 */

interface HistoryBusinessLogic {
    fun getPlayerGames(request: HistoryModel.Request)
}

class HistoryInteractor : HistoryBusinessLogic {
    var worker = HistoryWorker()
    var presenter: HistoryPresentationLogic? = null

    override fun getPlayerGames(request: HistoryModel.Request) {
        worker.filterPlayerGames(request) { response ->
            presenter?.formatPlayerMatches(response)
        }

    }
}