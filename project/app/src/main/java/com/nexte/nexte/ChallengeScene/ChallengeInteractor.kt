package com.nexte.nexte.ChallengeScene

/**
 * Interface to define Business Logic to Challenge Class
 * that will used to call this Interactor on other class layer
 */
interface ChallengeBusinessLogic {

    /**
     * Method that pass the request to Worker and send the response to Presenter
     *
     * @param request variable equals to the server response due to the challenged response
     */
    fun sendChallenge(request: ChallengeModel.Request)
}

/**
 * Class that implements [ChallengeBusinessLogic] and is responsible for the communication
 * between worker and presenter
 *
 * @property worker Reference to worker [ChallengeWorker]
 * @property presenter Reference to presenter [ChallengePresenter]
 */
class ChallengeInteractor : ChallengeBusinessLogic {

    var worker = ChallengeWorker()
    var presenter: ChallengePresentationLogic? = null

    override fun sendChallenge(request: ChallengeModel.Request) {

        worker.setMatch(request) { response ->
            this.presenter?.presentChallenge(response)
        }
    }
}