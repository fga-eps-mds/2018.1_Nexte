package com.nexte.nexte.ChallengeScene


class ChallengeWorker {


    fun setMatch (request: ChallengeModel.Request, completion: (ChallengeModel.Response) -> Unit) {

        var challengedAnswer: Boolean? = null
        var isValid: Boolean? = null
        var place = request.place

        if(place == "FGA")
            isValid = true
        else
            isValid = false

        challengedAnswer = isValid




        val response: ChallengeModel.Response = ChallengeModel.Response(challengedAnswer)
        completion(response)

    }




}