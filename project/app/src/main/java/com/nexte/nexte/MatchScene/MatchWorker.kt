package com.nexte.nexte.MatchScene

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R

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
}

/**
 * Class responsible to receive request, get Response from server and
 * call completion to send data for called class
 */
class MatchWorker {

    var updateLogic: MatchUpdateWorkerLogic? = null
    var challengeManager: ChallengeManager? = null

    /**
     * Function to get Match Data from server
     *
     * @param request String to identify the challenge to be passed
     * @param completion Challenge passed as a Response of the MatchData type
     */
    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = if(request.match == null) {
            MatchModel.InitScene.Response (this.generateMatchData())
        }
        else{
            MatchModel.InitScene.Response(request.match!!)
        }
        completion(response)
    }

    /**
     * Function that generates the Data to be added to Response, in order to simulate the action of
     * the server to get actual players. The function returns the format expected on response
     */
     fun generateMatchData() : MatchModel.MatchData{

        val challenger = MatchModel.MatchPlayer("Letícia",  R.mipmap.ic_launcher )

        val challenged = MatchModel.MatchPlayer("Alexandre Miguel", R.mipmap.ic_launcher )

        return MatchModel.MatchData(challenger, challenged)
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
    fun declineMatch(request: MatchModel.DeclineChallengeRequest.Request){
        val deletedChallenge = challengeManager?.delete(request.challengeId)
        var response: MatchModel.DeclineChallengeRequest.Response? = null

        if (deletedChallenge != null){
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.SUCCESS)
        }else{
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.ERROR)
        }
        updateLogic?.declineMatchResultResponse(response)
    }

    fun sortListByChallenges(list: List<Challenge>?): List<Challenge>?{
        return list?.sortedBy { it.id }
    }

    fun getUserChallenges(request: MatchModel.SentChallenge.Request) {

        var sentChallenges = challengeManager?.getAll()
        sentChallenges = sortListByChallenges(sentChallenges)
        val response = MatchModel.SentChallenge.Response(sentChallenges!!.toTypedArray())
    }
}