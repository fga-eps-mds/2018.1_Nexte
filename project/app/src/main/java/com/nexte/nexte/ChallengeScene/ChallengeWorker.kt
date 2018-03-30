package com.nexte.nexte.ChallengeScene


class ChallengeWorker {

    constructor() {}

    /* Here, the variable 'request' would get the answer from the server
    * as the actual challenged player give his response*/

    fun setMatch (request: Boolean?, completion: (ChallengeModel.Response) -> Unit) {


        var challengedAnswer: Boolean? = request


        val response: ChallengeModel.Response = ChallengeModel.Response(challengedAnswer)
        completion(response)

    }




}