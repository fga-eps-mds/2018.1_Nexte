package com.nexte.nexte.RankingScene

/**
 * Interface to define Business Logic of Ranking Class
 * that will be used to call this Interactor on other class layer
 */
interface RankingBusinessLogic {

    /**
     * Method responsible for getting all the players that will be showing on rank
     *
     * @param request Request model of ranking for Worker
     */
    fun getPlayersRanksForScene(request: RankingModel.Request)
}

/**
 * Class that implements [RankingBusinessLogic] and is responsible for the communication
 * between Worker and Presenter
 *
 * @property worker Reference to worker [RankingWorker]
 * @property presenter Reference to presenter [RankingPresenter]
 */
class RankingInteractor(var presenter: RankingPresentationLogic? = null) : RankingBusinessLogic {

    var worker = RankingWorker()

    override fun getPlayersRanksForScene(request: RankingModel.Request) {

        worker.getUsersInRanking(request){ response ->
            presenter?.presentRanking(response)
        }
    }
}