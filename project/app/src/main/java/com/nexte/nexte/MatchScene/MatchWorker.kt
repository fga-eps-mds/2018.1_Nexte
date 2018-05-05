package com.nexte.nexte.MatchScene

import com.nexte.nexte.R

/**
 * Created by leticia on 30/04/18.
 */

/**
 * Class responsible to receive request, get Response from server and
 * call completion to send data for called class
 */
class MatchWorker {

    /**
     * Function to get Match Data from server
     *
     * @param request String to identify the challenge to be passed
     * @param completion Challenge passed as a Response of the MatchData type
     */
    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = MatchModel.InitScene.Response (this.generateMatchData())
        completion(response)
    }

    /**
     * Function that generates the Data to be added to Response, in order to simulate the action of
     * the server to get actual players. The function returns the format expected on response
     */
     fun generateMatchData() : MatchModel.MatchData{

        var challenger = MatchModel.MatchPlayer("Letícia",  R.mipmap.ic_launcher )

        var challenged = MatchModel.MatchPlayer("Alexandre Miguel", R.mipmap.ic_launcher )

        val match = MatchModel.MatchData(challenger, challenged)

         return match
    }
}