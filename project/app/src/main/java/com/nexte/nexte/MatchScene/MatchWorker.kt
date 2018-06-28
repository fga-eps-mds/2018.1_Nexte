package com.nexte.nexte.MatchScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType

/**
 * Interface to define Response Logic of Match Class
 * that will be used to make the communication between worker and interactor
 */
interface MatchUpdateWorkerLogic {

    /**
     * Method that will be used to communicate with the presenter
     *
     * @param response contains the data about the status of the match result
     */
    fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response)

    fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response)

    fun updateSentChallenge(response: MatchModel.InitScene.Response)

}

/**
 * Class responsible to receive request, get Response from server and
 * call completion to send data for called class
 */
class MatchWorker {

    var updateLogic: MatchUpdateWorkerLogic? = null
    var challengeManager: ChallengeManager? = null
    var userManager: UserManager? = null


    fun getUserChallenges(sendChallenges: MatchModel.InitScene.Request, user: User) {

        val challenges = challengeManager?.getAll()
        val challengesId = Challenge()
        val sentChalleges = challengeManager?.getSentChallengeFromRealm(challengesId.toString())
        val response: MatchModel.InitScene.Response = MatchModel.InitScene.Response(sentChalleges!!.toList())
        updateLogic?.updateSentChallenge(response)
        for (sendchallenge in sendChallenges) {
            if (UserSingleton.loggedUserID == challengesId.id){
                sentChalleges
            }
        }


        if (UserSingleton.userType != UserType.MOCKED) {
            val url = "http://10.0.2.2:3000/users"
            url.httpGet().responseJson()
        } else {
            //Do nothing
        }
    }


    /**
     * Method responsible for saving the match result on the database
     *
     * @param request data that contains the request of the match result
     */
    fun generateMatchResult(request: MatchModel.SendMatchResult.Request) {
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)
        updateLogic?.getMatchResultResponse(response)

    }

    /**
     *  Function to decline a match
     *
     *  @param request the request of a challenge
     */
    fun declineMatch(request: MatchModel.DeclineChallengeRequest.Request) {
        val deletedChallenge = challengeManager?.delete(request.challengeId)
        var response: MatchModel.DeclineChallengeRequest.Response? = null

        if (deletedChallenge != null) {
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.SUCCESS)
        } else {
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.ERROR)
        }
        updateLogic?.declineMatchResultResponse(response)
    }

}

