package com.nexte.nexte.HistoryScene

/**
 * Created by helena on 03/04/18.
 */

interface HistoryPresentationLogic {
    fun formatPlayerMatches(response: HistoryModel.Response)
}

class HistoryPresenter : HistoryPresentationLogic {

    var viewScene: HistoryDisplayLogic? = null

    override fun formatPlayerMatches(response: HistoryModel.Response) {
        var message: String = ""
        if(response.match.isNotEmpty()) {
            message = "Uau! Você joga bem"
        } else {
            message = "Poxa usuario, algo deu errado :("
        }
        var viewModel: HistoryModel.ViewModel = HistoryModel.ViewModel(message)
        viewScene?.displayPlayerMatches(viewModel)
    }
}