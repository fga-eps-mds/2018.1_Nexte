package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

interface RankPresentationLogic {
    fun presentRank(response: RankingModel.Response)
}

class RankPresenter : RankPresentationLogic {
    constructor()

    var viewScene: RankDisplayLogic? = null

    override fun presentRank(response: RankingModel.Response) {
        var message: String = ""

        if(response?.players?.get(0)?.name == "Gabriel Albino" && response?.players?.get(1)?.name == "Helena Goulart") {
            message = "Iterou por todas as camadas da arquitetura!"
        }
        else {
            message = "algo deu errado!"
        }
        var viewModel: RankingModel.ViewModel = RankingModel.ViewModel(message)

        viewScene?.displayRankInScreen(viewModel)
    }
}