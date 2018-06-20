package com.nexte.nexte.PlayersListScene

import android.graphics.Color
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.RankingScene.RankingPresenter
import java.util.*

/**
 * Interface to define Presentation Logic to Challenge Class that
 * will be used to call this Interactor on other classes layer
 */
interface PlayersListPresentationLogic {
    /**
     * Method responsible to format the user information about players above the logged user and send it to view
     *
     * @param response contains unformatted data received from [PlayersListModel]
     */
    fun formatPlayersToChallenge(response : PlayersListModel.ShowRankingPlayersRequest.Response)

    /**
     * Method responsible to format the user detailed information and send it to view
     *
     * @param response contains unformatted data received from [PlayersListModel]
     */
    fun formatExpandedChallengedInfo(response: PlayersListModel.SelectPlayerForChallengeRequest.Response)


    /**
     * Method responsible to format the message of the button and send it to view
     *
     * @param response contains unformatted data received from [PlayersListModel]
     */
    fun formatMatch(response : PlayersListModel.ChallengeButtonRequest.Response)

    /**
     * Method responsible to format the message tho inform that there are no
     * players to be challenged
     */
    fun formatNoPlayersMessage()

}

/**
* This class will be responsible to receive a [PlayersListModel.ShowRankingPlayersRequest.Response]
* and generate a [PlayersListModel.ShowRankingPlayersRequest.ViewModel], sending it to View
*
* @property viewChallenge Reference to display formatted data
*/

class PlayersListPresenter : PlayersListPresentationLogic {

    var viewChallenge: PlayersListDisplayLogic? = null // reference of the view
    var challengeManager: ChallengeManager = ChallengeManager()

    /**
     * This method is responsible for formatting data contained on
     * [PlayersListModel.ShowRankingPlayersRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [PlayersListWorker]
     */
    override fun formatPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
        val selectedPlayers = response.usersAbove

        var formattedPlayers: List<PlayersListModel.FormattedPlayer> = listOf()

        for(player in selectedPlayers){
            val formattedPlayer = PlayersListModel.FormattedPlayer(player.id, player.name,
                    String.format("#%d", player.rankingPosition), "")//TODO: replace picture adress

            formattedPlayers += formattedPlayer
        }

        val viewModel = PlayersListModel.ShowRankingPlayersRequest.ViewModel(formattedPlayers)
        viewChallenge?.displayPlayersToChallenge(viewModel)
    }

    /**
     * Method that is calculate when was the user last game
     *
     * @return a string that represents a player last game
     */
    fun calculatePlayerLastGame(latestGames: List<Challenge>?, today: Date): String{
        if (latestGames == null || latestGames.isEmpty()){
            return "Nenhum jogo"
        }

        val latestGameDate = latestGames.first().challengeDate

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
        } else {
            "" + (today.year - latestGameDate.year) + " years"
        }
    }

    /**
     *  Method responsible for creating the array with the corresponding colors
     *  to the player last five games
     *
     *  @param latestGames list with user last games
     *  @param userId id of the user
     *
     *  @return list with the colors arrays
     */
    fun getPlayerLastFiveGamesArray(latestGames: List<Challenge?>?, userId: String) :
            List<Int> {
        var gamesMutable = mutableListOf<Int>()

        if (latestGames != null) {

            for (counter in RankingPresenter.firstGameIndex..RankingPresenter.fifthGameindex) {

                if (latestGames.getOrNull(counter) == null) {
                    gamesMutable.add(Color.GRAY)
                }else if (latestGames[counter]?.winner == userId) {
                    gamesMutable.add(Color.GREEN)
                } else {
                    gamesMutable.add(Color.RED)
                }
            }

        } else {
            for(counter in RankingPresenter.firstGameIndex..RankingPresenter.fifthGameindex) {
                gamesMutable.add(Color.GRAY)
            }
        }

        gamesMutable = checkIfUserWonAllLatestFiveGames(gamesMutable)
        return gamesMutable.toList()

    }

    /**
     * Method responsible to check if the user have won the last five games
     * and if he has all colors of the array will be changed to yellow
     *
     * @param latestGames list with the colors of the games
     *
     * @return list with yellow colors if the user has won the last five games, and if he has not
     *         this will be the same list passed
     */
    fun checkIfUserWonAllLatestFiveGames(latestGames: MutableList<Int>) :
            MutableList<Int> {
        var wonAll = true

        for (counter in RankingPresenter.firstGameIndex..RankingPresenter.fifthGameindex) {
            if (latestGames[counter] != Color.GREEN) {
                wonAll = false
            }
        }

        if (wonAll)  {
            for (counter in RankingPresenter.firstGameIndex..RankingPresenter.fifthGameindex) {
                latestGames[counter] = Color.YELLOW
            }
        } else {
            // Do nothing
        }

        return latestGames
    }

    /**
     * This method is responsible for formatting data contained on
     * [PlayersListModel.SelectPlayerForChallengeRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [PlayersListWorker]
     */
    override fun formatExpandedChallengedInfo(response: PlayersListModel.SelectPlayerForChallengeRequest.Response) {
        val selectedChallenged = response.challengedPersonalDetails
        val totalGames = selectedChallenged.wins.toFloat()+selectedChallenged.loses
        val efficiency = (selectedChallenged.wins /totalGames) * 100
        val percent = "%"
        val latestChallenges = challengeManager?.getLastFiveChallenges(selectedChallenged.id)
        val lastGame = calculatePlayerLastGame(latestChallenges,Date())
        val formattedPlayer = PlayersListModel.FormattedRankingDetails(
                selectedChallenged.name,
                String.format("VITÓRIAS: %d / %d", selectedChallenged.wins,(selectedChallenged.wins+selectedChallenged.loses)),
                String.format("%d", selectedChallenged.rankingPosition),
                selectedChallenged.category.toString(),
                String.format("Aproveitamento: %.2f %s",efficiency,percent),
                String.format("Último Jogo: %s",lastGame),
                getPlayerLastFiveGamesArray(latestChallenges,selectedChallenged.id),
                selectedChallenged.id
        )

        val viewModel = PlayersListModel.SelectPlayerForChallengeRequest.ViewModel(formattedPlayer)

        viewChallenge?.displayPlayerDetailedInfo(viewModel)
    }

    /**
     * This method is responsible for formatting data contained on
     * [PlayersListModel.ChallengeButtonRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [PlayersListWorker]
     */
    override fun formatMatch(response: PlayersListModel.ChallengeButtonRequest.Response) {

        val message: String = String.format("Desafio enviado com sucesso para o jogador %s", response.username)

        val matchMessage: String = if(response.challenge.challenged.name != ""){
            "Você já enviou um desafio"
        } else{
            ""
        }

        val viewModel = PlayersListModel.ChallengeButtonRequest.ViewModel(message, matchMessage, response.challenge)

        viewChallenge?.displayMessage(viewModel)

    }

    /**
     * This method is responsible for setting the message
     */
    override fun formatNoPlayersMessage() {
        val message = "Sem jogadores disponíveis.\nTente novamente mais tarde."

        viewChallenge?.displayNoPlayersMessage(message)
    }
}