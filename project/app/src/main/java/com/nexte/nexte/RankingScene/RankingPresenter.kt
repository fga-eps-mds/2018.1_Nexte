package com.nexte.nexte.RankingScene

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
 * @property viewScene Reference to the ranking where data will be displayed on [RankingFragment]
 */
class RankingPresenter( var viewScene: RankingDisplayLogic? = null) : RankingPresentationLogic {

    /**
     * Formats user information contained in [RankingModel.Response]
     * and sends it to [RankingFragment]
     *
     * @param response contains unformatted data received from [RankingModel]
     */
    override fun presentRanking(response: RankingModel.Response) {

        val viewModel = RankingModel.ViewModel(this.formatPlayers(response.players))

        viewScene?.displayRankingInScreen(viewModel)
    }

    /**
     * Formats players information into a list
     *
     * @param players array of players that will be shown on ranking
     */
    private fun formatPlayers(players: Array<RankingModel.Player>): List<RankingModel.FormattedPlayerInfo> {

        val rankingPlayerFormatted: MutableList<RankingModel.FormattedPlayerInfo> = mutableListOf()

        for(player in players) {

            val playerFormatted = RankingModel.FormattedPlayer(player.name,
                    player.pictureURL,
                    String.format("Vitórias: %d", player.wins),
                    String.format("%d", player.rankingPosition),
                    String.format("Último Jogo: %s", player.lastGame),
                    String.format("Aproveitamento: %s", player.efficiency),
                    player.playerCategory)

            val playerFormattedInfo = RankingModel.FormattedPlayerInfo(playerFormatted,false)
            rankingPlayerFormatted.add(playerFormattedInfo)
        }

        return rankingPlayerFormatted.toList()
    }
}