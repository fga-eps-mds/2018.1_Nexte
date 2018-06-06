package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
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

@Suppress("DEPRECATION")
/**
 * Class needed to format response for data can be displayed on View
 *
 * @property viewScene Reference to the ranking where data will be displayed on [RankingFragment]
 */
class RankingPresenter( var viewScene: RankingDisplayLogic? = null) : RankingPresentationLogic {

    var challengeManager: ChallengeManager? = null

    /**
     * Formats user information contained in [RankingModel.Response]
     * and sends it to [RankingFragment]
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
    fun formatPlayers(users: Array<User>): List<RankingModel.FormattedPlayerInfo> {
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
                playerCategory = user.category.name
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
     * Method used to get player efficiency based on his wins and losses
     *
     * @return a string that represents player efficiency
     */
    fun calculatePlayerEfficiency(wins: Int, losses: Int): String{
        val allGames = wins + losses
        val efficiency: String?
        efficiency = if (allGames != 0){
            "" + (wins/allGames*oneHundredPercent).toString() + "%"
        }else{
            "0%"
        }

        return efficiency
    }

    /**
     * Method that is calculate when was the user last game
     *
     * @return a string that represents a player last game
     */
    fun calculatePlayerLastGame(latestGames: List<Challenge>?): String{
        if (latestGames == null || latestGames.isEmpty()){
            return "Nenhum jogo"
        }

        val latestGameDate = latestGames.first().challengeDate
        val today = Date()

        return if(today.year == latestGameDate.year){
            if (today.month == latestGameDate.month){
                when {
                    today.day == latestGameDate.day -> "hoje"
                    today.day == latestGameDate.day - 1 -> "ontem"
                    else -> "" + (today.day - latestGameDate.day) + " days"
                }
            }else{
                "" + (today.month - latestGameDate.month) + " months"
            }
        }else{
            "" + (today.year - latestGameDate.year) + " years"
        }
    }

    companion object {
        const val oneHundredPercent = 100
    }
}