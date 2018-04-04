package com.nexte.nexte.RankScene

/**
 * Created by albino on 02/04/18.
 */

interface RankBusinessLogic {
    fun getPlayersRanksForScene(request: RankModel.Request)
}

class RankInteractor : RankBusinessLogic {

    var worker = RankWorker()
    var presenter: RankPresentationLogic? = null

    override fun getPlayersRanksForScene(request: RankModel.Request) {
        worker.getUsersInRank(request){ response ->
            presenter?.presentRank(response)
        }

    }
}