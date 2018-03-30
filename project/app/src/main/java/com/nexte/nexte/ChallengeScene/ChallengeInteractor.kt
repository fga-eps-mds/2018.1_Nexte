package com.nexte.nexte.ChallengeScene


interface ChallengeBussinessLogic {

    /*The 'request' variable equals to the server response due to the challenged response*/
    fun sendChallenge(request: Boolean?)
}

class ChallengeInteractor : ChallengeBussinessLogic {

    var worker = ChallengeWorker()
    var presenter: ChallengePresentationLogic? = null

    override fun sendChallenge(request: Boolean?) {

        worker.setMatch(request) { response ->

            this.presenter?.presentChallenge(response)

        }
    }

}