package com.nexte.nexte.FeedScene

/**
 * Created by leticia on 27/03/18.
 */

interface FeedBusinessLogic {

    fun recentGames(request: FeedModel.Request)
}

class FeedInteractor : FeedBusinessLogic {

    var worker = FeedWorker()
    var presenter : FeedPresentationLogic? = null

    override fun recentGames(request: FeedModel.Request) {
        worker.getGame(request) { response ->
            this.presenter?.presentLastGame(response)
        }
    }
}