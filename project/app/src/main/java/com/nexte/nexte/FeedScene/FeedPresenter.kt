package com.nexte.nexte.FeedScene

/**
 * Created by larissa on 27/03/18.
 */

interface FeedPresentationLogic {

    fun presentLastGame(response: FeedModel.Response)
}

class FeedPresenter : FeedPresentationLogic {

    var viewScene: FeedDisplayLogic? = null


    override fun presentLastGame(response: FeedModel.Response){

        var message: String = ""
        var firstPlayer: String? = response.firstPlayer
        var secondPlayer: String? = response.secondPlayer

        if (firstPlayer.equals("") || secondPlayer.equals("")) {
            message = "Erro ao mostrar últimas partidas"
        } else {
            message = "Última partida recuperada com sucesso"
        }

        val viewModel: FeedModel.ViewModel = FeedModel.ViewModel(message)
        this.viewScene?.displayRecentGames(viewModel)
    }


}