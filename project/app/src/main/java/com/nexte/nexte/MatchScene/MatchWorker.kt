package com.nexte.nexte.MatchScene

import com.nexte.nexte.R

/**
 * Created by leticia on 30/04/18.
 */

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
}

/**
 * Class responsible to receive request, get Response from server and
 * call completion to send data for called class
 */
class MatchWorker {

    var updateLogic: MatchUpdateWorkerLogic? = null

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
}