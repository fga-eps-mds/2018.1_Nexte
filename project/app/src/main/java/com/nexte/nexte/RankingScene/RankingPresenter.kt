package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R
import java.util.*

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

    var challengeManager: ChallengeManager? = null

    /**
     * Formats user information contained in [RankingModel.Response]
     * and sends it to [RankingView]
     *
     * @param response contains unformatted data received from [RankingModel]
     */
    override fun presentRanking(response: RankingModel.Response) {
        val viewModel = RankingModel.ViewModel(this.formatPlayers(response.users))

        viewScene?.displayRankingInScreen(viewModel)
    }

    /**
     * Method used to format list of [User] to array of [RankingModel.FormattedPlayerInfo]
     *
     * @param users list with all users
     *
     * @return an array of [RankingModel.FormattedPlayerInfo]
     */
    private fun formatPlayers(users: Array<User>): List<RankingModel.FormattedPlayerInfo> {
        val rankingModelPlayersMutable = mutableListOf<RankingModel.FormattedPlayerInfo>()

        for (user in users){
            val name = user.name
            val rankingPosition = user.rankingPosition
            val wins = user.wins
            val losses = user.loses
            val efficiency = calculatePlayerEfficiency(wins, losses)
            user.latestGames = challengeManager?.getLastFiveChallenges(user.id)
            val lastGame = calculatePlayerLastGame(user.latestGames)
            var playerCategory = ""
            if (user.category != null){
                playerCategory = user.category?.name
            }
            val playerFormatted = RankingModel.FormattedPlayer(name,
                    R.mipmap.ic_launcher, String.format("Vitórias: %d", wins),
                    String.format("%d", rankingPosition), String.format("Último Jogo: %s", lastGame),
                    String.format("Aproveitamento: %s", efficiency), playerCategory)
            val playerFormattedInfo = RankingModel.FormattedPlayerInfo(playerFormatted,false)

            rankingModelPlayersMutable.add(playerFormattedInfo)
        }

        return rankingModelPlayersMutable.toList()
    }

    /**
     * Method used to get player effiency based on his wins and losses
     *
     * @return a string that represents player efficiency
     */
    private fun calculatePlayerEfficiency(wins: Int, losses: Int): String{
        val allGames = wins + losses
        val efficiency: String?
        if (allGames != 0){
            efficiency = "" + (wins/allGames*oneHundredPercent).toString() + "%"
        }else{
            efficiency = "0%"
        }

        return efficiency!!
    }

    /**
     * Method that is calculate when was the user last game
     *
     * @return a string that represents a player last game
     */
    private fun calculatePlayerLastGame(latestGames: List<Challenge>?): String{
        if (latestGames == null || latestGames.isEmpty()){
            return "Nenhum jogo"
        }

        val latestGameDate = latestGames.first().challengeDate
        val today = Date()
        var latestGame = "Nenhum jogo"

        if(today.year == latestGameDate.year){
            if (today.month == latestGameDate.month){
                if (today.day == latestGameDate.day){
                    latestGame = "hoje"
                }else if(today.day == latestGameDate.day - 1){
                    latestGame = "ontem"
                }else{
                    latestGame = "" + (today.day - latestGameDate.day) + " days"
                }
            }else{
                latestGame = "" + (today.month - latestGameDate.month) + " months"
            }
        }else{
            latestGame = "" + (today.year - latestGameDate.year) + " years"
        }

        return latestGame
    }

    companion object {
        const val oneHundredPercent = 100
    }
}