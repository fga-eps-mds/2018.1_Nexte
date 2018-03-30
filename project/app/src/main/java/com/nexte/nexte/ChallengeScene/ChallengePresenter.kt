package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.ShowProfile.ShowProfileDisplayLogic


interface ChallengePresentationLogic {

    fun presentChallenge(response : ChallengeModel.Response)

}


class ChallengePresenter : ChallengePresentationLogic {

    var viewChallenge: ChallengeDisplayLogic? = null

    override fun presentChallenge(response: ChallengeModel.Response) {

        var message: String? = null
        var matchStatus: Boolean? = response.challengedAnswer

        if(matchStatus == true) {
            message = "The match is set! Let's play!!!"
        }
        else if(matchStatus == false) {
            message = "Your opponent declined :/ Try again!"
        }
        else if(matchStatus == null) {
            message = "Waiting for the opponent answer"
        }

        var viewModel: ChallengeModel.ViewModel = ChallengeModel.ViewModel(message)
        this.viewChallenge?.displayChallengeAnswer(viewModel)
    }





}