package com.nexte.nexte.ChallengeScene


class ChallengeWorker {

    constructor() {}

    fun setMatch (request: ChallengeModel.Request, completion: (ChallengeModel.Response) -> Unit) {

        var challengedAnswer: Boolean? = null

        challengedAnswer = request.place.equals("FGA")




        val response: ChallengeModel.Response = ChallengeModel.Response(challengedAnswer)
        completion(response)

    }




}