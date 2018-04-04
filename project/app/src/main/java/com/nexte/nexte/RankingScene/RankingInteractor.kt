package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

interface RankBusinessLogic {
    fun getPlayersRanksForScene(request: RankingModel.Request)
}

class RankInteractor : RankBusinessLogic {

    var worker = RankingWorker()
    var presenter: RankPresentationLogic? = null

    override fun getPlayersRanksForScene(request: RankingModel.Request) {
        worker.getUsersInRank(request){ response ->
            presenter?.presentRank(response)
        }

    }
}