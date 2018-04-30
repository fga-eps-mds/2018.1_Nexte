package com.nexte.nexte.MatchScene

/**
 * Created by leticia on 30/04/18.
 */

class MatchWorker {

    fun fetchMatchData (request: MatchModel.Request, completion: (MatchModel.Response) -> Unit) {

        val response = MatchModel.Response (this.generateMatchData())
        completion(response)
    }

    private fun generateMatchData() {
        
    }
}