package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

interface RankingBusinessLogic {
    fun getPlayersRanksForScene(request: RankingModel.Request)
}

class RankingInteractor : RankingBusinessLogic {

    var worker = RankingWorker()
    var presenter: RankingPresentationLogic? = null

    override fun getPlayersRanksForScene(request: RankingModel.Request) {
        worker.getUsersInRanking(request){ response ->
            presenter?.presentRanking(response)
        }

    }
}