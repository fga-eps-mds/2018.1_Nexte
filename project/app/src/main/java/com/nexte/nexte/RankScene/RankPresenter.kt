package com.nexte.nexte.RankScene

/**
 * Created by albino on 02/04/18.
 */

interface RankPresentationLogic {
    fun presentRank(response: RankModel.Response)
}

class RankPresenter : RankPresentationLogic {
    constructor()

    var viewScene: RankDisplayLogic? = null

    override fun presentRank(response: RankModel.Response) {
        var message: String = ""

        if(response?.players?.get(0)?.name == "Gabriel Albino" && response?.players?.get(1)?.name == "Helena Goulart") {
            message = "Iterou por todas as camadas da arquitetura!"
        }
        else {
            message = "algo deu errado!"
        }
        var viewModel: RankModel.ViewModel = RankModel.ViewModel(message)

        viewScene?.displayRankInScreen(viewModel)
    }
}