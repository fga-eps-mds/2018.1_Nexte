package com.nexte.nexte.PlayersListScene

/**
 * Interface to define Business Logic to Challenge Class
 * that will used to call this Interactor on other class layer
 */
interface ChallengeBusinessLogic {

    /**
     * Method that pass the request to Worker and send the response to Presenter for the request
     * responsible to get first list of players
     *
     * @param request variable equals to the server response due to the challenged response
     */
    fun requestPlayersToChallenge(request: PlayersListModel.ShowRankingPlayersRequest.Request)

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the player clicked by the user
     *
     * @param request variable equals the ranking position of the clicked player
     */
    fun requestChallengedUser(request: PlayersListModel.SelectPlayerForChallengeRequest.Request)

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the message that  will be shown
     *
     * @param request variable that gets the request from the 'send challenge' button
     */
    fun requestChallenger(request: PlayersListModel.ChallengeButtonRequest.Request)
}

/**
 * Class that implements [ChallengeBusinessLogic] and is responsible for the communication
 *
 * @property worker Reference to worker [PlayersListWorker]
 * @property presenter Reference to presenter [PlayersListPresenter]
 */
class PlayersListInteractor : ChallengeBusinessLogic, PlayerListUpdateLogic {

    var worker = PlayersListWorker()
    var presenter: PlayersListPresentationLogic? = null

    override fun getPlayersToChallenge(request: PlayersListModel.ShowRankingPlayersRequest.Request) {
//        this.worker.fetchPlayersToChallenge(request)
    }

    /**
     * Method that pass the request to Worker and send the response to Presenter for the request
     * responsible to get first list of players
     *
     * @param request variable equals to the server response due to the challenged response
     */
    override fun requestPlayersToChallenge(request: PlayersListModel.ShowRankingPlayersRequest.Request) {
        worker.fetchPlayersToChallenge(request) { response ->
            if(response.usersAbove.isNotEmpty()) {
                this.presenter?.formatPlayersToChallenge(response)
            }
            else {
                this.presenter?.formatNoPlayersMessage()
            }
        }
    }

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the player clicked by the user
     *
     * @param request variable equals the ranking position of the clicked player
     */
    override fun requestChallengedUser(request: PlayersListModel.SelectPlayerForChallengeRequest.Request) {

        worker.fetchChallengedDetails(request) { response ->
            this.presenter?.formatExpandedChallengedInfo(response)
        }
    }

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the message
     *
     * @param request
     */
    override fun requestChallenger(request: PlayersListModel.ChallengeButtonRequest.Request) {

        worker.generateChallenge(request) { response ->
            this.presenter?.formatMatch(response)
        }
    }
}