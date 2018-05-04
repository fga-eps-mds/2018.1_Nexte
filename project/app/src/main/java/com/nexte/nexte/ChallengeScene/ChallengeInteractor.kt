package com.nexte.nexte.ChallengeScene

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
    fun requestPlayersToChallenge(request: ChallengeModel.ShowRankingPlayersRequest.Request)

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the player clicked by the user
     *
     * @param request variable equals the rank position of the clicked player
     */
    fun requestChallengedUser(request: ChallengeModel.SelectPlayerForChallengeRequest.Request)

    fun requestMessageForChallenger(request: ChallengeModel.ChallengeButtonRequest)
}



/**
 * Class that implements [ChallengeBusinessLogic] and is responsible for the communication*
 * @property worker Reference to worker [ChallengeWorker]
 * @property presenter Reference to presenter [ChallengePresenter]
 */
class ChallengeInteractor : ChallengeBusinessLogic {

    var worker = ChallengeWorker() // Reference for the worker
    var presenter: ChallengePresentationLogic? = null //Reference for the presenter

    /**
     * Method that pass the request to Worker and send the response to Presenter for the request
     * responsible to get first list of players
     *
     * @param request variable equals to the server response due to the challenged response
     */
    override fun requestPlayersToChallenge(request: ChallengeModel.ShowRankingPlayersRequest.Request) {
        worker.fetchPlayersToChallenge(request) { response ->
            this.presenter?.formatPlayersToChallenge(response)
        }
    }

    /**
     * Method that pass the request to worker and send the response to the presenter for the request
     * responsible to get the player clicked by the user
     *
     * @param request variable equals the rank position of the clicked player
     */
    override fun requestChallengedUser(request: ChallengeModel.SelectPlayerForChallengeRequest.Request) {

        worker.fetchChallengedDetails(request) { response ->
            this.presenter?.formatExpandedChallengedInfo(response)
        }

    }

    override fun requestMessageForChallenger(request: ChallengeModel.ChallengeButtonRequest.Request) {

        worker.fetchMessageForChallenge(request) { response ->
            this.presenter?.formatMessage(response)
        }
    }
}