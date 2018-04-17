package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

interface RankingBusinessLogic {

    fun getPlayersRanksForScene(request: RankingModel.Request)
}

class RankingInteractor(var presenter: RankingPresentationLogic? = null) : RankingBusinessLogic {

    var worker = RankingWorker()


    override fun getPlayersRanksForScene(request: RankingModel.Request) {

        worker.getUsersInRanking(request){ response ->
            presenter?.presentRanking(response)
        }
    }
}