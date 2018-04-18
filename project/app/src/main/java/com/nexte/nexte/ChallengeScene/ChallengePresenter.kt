package com.nexte.nexte.ChallengeScene

/**
 * Interface to define Presentation Logic to Challenge Class that
 * will be used to call this Interactor on other classes layer
 */
interface ChallengePresentationLogic {
    /**
     * Method responsible to present challenge data and send it to View
     *
     * @param response contains unformatted data received from [ChallengeModel]
     */
    fun presentChallenge(response : ChallengeModel.Response)
}

/**
* This class will be responsible to receive a [ChallengeModel.Response]
* and generate a [ChallengeModel.ViewModel], sending it to View
*
* @property viewChallenge Reference to display formatted data
*/

class ChallengePresenter : ChallengePresentationLogic {

    var viewChallenge: ChallengeDisplayLogic? = null

    /**
     * This method is responsible for formatting data contained on
     * [ChallengeModel.Response] and send it to View
     *
     * @param response contains unformatted data received from [ChallengeModel]
     */
    override fun presentChallenge(response: ChallengeModel.Response) {

        val message: String?
        val matchStatus: Boolean? = response.challengedAnswer

        message = when (matchStatus) {

            true -> "The match is set! Let's play!!!"
            false -> "Your opponent declined :/ Try again!"
            null -> "Waiting for the opponent answer"
        }

        val viewModel: ChallengeModel.ViewModel = ChallengeModel.ViewModel(message)
        this.viewChallenge?.displayChallengeAnswer(viewModel)
    }
}