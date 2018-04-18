package com.nexte.nexte.RankingScene

import com.nexte.nexte.FeedScene.FeedModel
import com.nexte.nexte.FeedScene.FeedView


/**
 * Interface to define Presentation Logic to Ranking Class that
 * will be used to call this Interactor on other class layer
 */
interface RankingPresentationLogic {

    /**
     * Method responsible to present ranking to view
     *
     * @param response contains unformatted data received from [RankingModel]
     */
    fun presentRanking(response: RankingModel.Response)
}

/**
 * Class needed to format response for data can be displayed on View
 *
 * @property viewScene Reference to the ranking where data will be displayed on [RankingView]
 */
class RankingPresenter( var viewScene: RankingDisplayLogic? = null) : RankingPresentationLogic {

    override fun presentRanking(response: RankingModel.Response) {

        val viewModel = RankingModel.ViewModel(this.formatPlayers(response.players))

        viewScene?.displayRankInScreen(viewModel)
    }

    private fun formatPlayers(players: Array<RankingModel.Player>): List<RankingModel.FormattedPlayerInfo> {

        val rankingPlayerFormatted: MutableList<RankingModel.FormattedPlayerInfo> = mutableListOf()

        for(player in players) {
            val playerFormatted = RankingModel.FormattedPlayer(player.name,
                    player.pictureURL,
                    String.format("Vitórias: %d", player.wins),
                    String.format("Derrotas: %d", player.losses),
                    String.format("#%d", player.rankPosition))
            val playerFormattedInfo = RankingModel.FormattedPlayerInfo(playerFormatted, false)
            rankingPlayerFormatted.add(playerFormattedInfo)
        }
        return rankingPlayerFormatted.toList()
    }
}