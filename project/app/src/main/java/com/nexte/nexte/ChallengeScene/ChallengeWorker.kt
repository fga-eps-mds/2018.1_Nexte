package com.nexte.nexte.ChallengeScene

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class ChallengeWorker {

    /**
     * Function to set a match
     *
     * @param request Challenge Model request that contains needed informations to send to server
     * @param completion Method to call on parent class
     */
    fun setMatch (request: ChallengeModel.Request, completion: (ChallengeModel.Response) -> Unit) {

        val challengedAnswer: Boolean?
        val isValid: Boolean?
        val place = request.place

        isValid = place == "FGA"

        challengedAnswer = isValid

        val response: ChallengeModel.Response = ChallengeModel.Response(challengedAnswer)
        completion(response)
    }
}