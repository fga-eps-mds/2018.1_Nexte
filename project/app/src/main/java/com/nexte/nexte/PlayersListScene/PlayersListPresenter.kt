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
    var rankingPresenter: RankingPresenter = RankingPresenter()

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
     * This method is responsible for formatting data contained on
     * [PlayersListModel.SelectPlayerForChallengeRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [PlayersListWorker]
     */
    override fun formatExpandedChallengedInfo(response: PlayersListModel.SelectPlayerForChallengeRequest.Response) {
        val selectedChallenged = response.challengedPersonalDetails
        val totalGames = selectedChallenged.wins.toFloat()+selectedChallenged.loses
        val efficiency = selectedChallenged.wins /totalGames * oneHundredPercent
        val percent = "%"
        val latestChallenges = challengeManager?.getLastFiveChallenges(selectedChallenged.id)
        val lastGame = rankingPresenter.calculatePlayerLastGame(latestChallenges,Date())//calculatePlayerLastGame(latestChallenges,Date())
        val formattedPlayer = PlayersListModel.FormattedRankingDetails(
                selectedChallenged.name,
                String.format("VITÓRIAS: %d / %d", selectedChallenged.wins,selectedChallenged.wins+selectedChallenged.loses),
                String.format("%d", selectedChallenged.rankingPosition),
                selectedChallenged.category.toString(),
                String.format("Aproveitamento: %.2f %s",efficiency,percent),
                String.format("Último Jogo: %s",lastGame),
                rankingPresenter.getPlayerLastFiveGamesArray(latestChallenges,selectedChallenged.id),
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
    companion object {
        const val oneHundredPercent = 100
    }
}