package com.nexte.nexte.PlayersListScene

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

        val formattedPlayer = PlayersListModel.FormattedRankingDetails(
                selectedChallenged.name,
                String.format("VITÓRIAS: %d / %d", selectedChallenged.wins, selectedChallenged.loses),
                String.format("%d", selectedChallenged.rankingPosition),
                selectedChallenged.categ
                        String.format("%d", selectedChallenged.ef)
                String.format("Aproveitamento: %d", selectedChallenged.category)

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