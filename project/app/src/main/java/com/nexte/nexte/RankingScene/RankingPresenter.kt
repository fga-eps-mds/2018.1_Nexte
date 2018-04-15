package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

interface RankingPresentationLogic {

    fun presentRanking(response: RankingModel.Response)
}

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