package com.nexte.nexte.ChallengeScene

import android.util.Log


/**
 * Interface to define Presentation Logic to Challenge Class that
 * will be used to call this Interactor on other classes layer
 */
interface ChallengePresentationLogic {
    /**
     * Method responsible to format the user information about players above the logged user and send it to view
     *
     * @param response contains unformatted data received from [ChallengeModel]
     */
    fun formatPlayersToChallenge(response : ChallengeModel.ShowRankingPlayersRequest.Response)

    /**
     * Method responsible to format the user detailed information and send it to view
     *
     * @param response contains unformatted data received from [ChallengeModel]
     */
    fun formatExpandedChallengedInfo(response: ChallengeModel.SelectPlayerForChallengeRequest.Response)


    /**
     * Method responsible to format the message of the button and send it to view
     *
     * @param response contains unformatted data received from [ChallengeModel]
     */
    fun formatMessage(response : ChallengeModel.ChallengeButtonRequest.Response)

    /**
     * Method responsible to format the message tho inform that there are no
     * players to be challenged
     */
    fun formatNoPlayersMessage()

}

/**
* This class will be responsible to receive a [ChallengeModel.ShowRankingPlayersRequest.Response]
* and generate a [ChallengeModel.ShowRankingPlayersRequest.ViewModel], sending it to View
*
* @property viewChallenge Reference to display formatted data
*/

class ChallengePresenter : ChallengePresentationLogic {

    var viewChallenge: ChallengeDisplayLogic? = null // reference of the view

    /**
     * This method is responsible for formatting data contained on
     * [ChallengeModel.ShowRankingPlayersRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [ChallengeWorker]
     */
    override fun formatPlayersToChallenge(response: ChallengeModel.ShowRankingPlayersRequest.Response) {
        val selectedPlayers = response.usersAbove

        var formattedPlayers: List<ChallengeModel.FormattedPlayer> = listOf()

        for(player in selectedPlayers){
            val formattedPlayer = ChallengeModel.FormattedPlayer(player.name,
                    String.format("#%d", player.rankingPosition), player.pictureAddress)

            formattedPlayers += formattedPlayer
        }

        val viewModel = ChallengeModel.ShowRankingPlayersRequest.ViewModel(formattedPlayers)
        viewChallenge?.displayPlayersToChallenge(viewModel)
    }

    /**
     * This method is responsible for formatting data contained on
     * [ChallengeModel.SelectPlayerForChallengeRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [ChallengeWorker]
     */
    override fun formatExpandedChallengedInfo(response: ChallengeModel.SelectPlayerForChallengeRequest.Response) {
        val selectedChallenged = response.challengedPersonalDetails

        val formattedPlayer = ChallengeModel.FormattedRankingDetails(
                selectedChallenged.name,
                String.format("VITÓRIAS: %d", selectedChallenged.wins),
                String.format("DERROTAS: %d", selectedChallenged.loses),
                String.format("#%d", selectedChallenged.rankingPosition)
        )

        val viewModel = ChallengeModel.SelectPlayerForChallengeRequest.ViewModel(formattedPlayer)

        viewChallenge?.displayPlayerDetailedInfo(viewModel)
    }

    /**
     * This method is responsible for formatting data contained on
     * [ChallengeModel.ChallengeButtonRequest.Response] and send it to View
     *
     * @param response contains unformatted data received from [ChallengeWorker]
     */
    override fun formatMessage(response: ChallengeModel.ChallengeButtonRequest.Response) {
        val message: String

        message = String.format("Desafio enviado com sucesso para o jogador %s", response.username)

        val viewModel = ChallengeModel.ChallengeButtonRequest.ViewModel(message)

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