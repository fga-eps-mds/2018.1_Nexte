package com.nexte.nexte.MatchScene

import com.nexte.nexte.R

/**
 * Created by leticia on 30/04/18.
 */

class MatchWorker {

    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = MatchModel.InitScene.Response (this.generateMatchData())
        completion(response)
    }

     fun generateMatchData() : MatchModel.MatchData{

        var challenger = MatchModel.MatchPlayer("Letícia",  R.mipmap.ic_launcher )                         

        var challenged = MatchModel.MatchPlayer("Alexandre Miguel", R.mipmap.ic_launcher )

        val numberOfSets = MatchModel.SetsNumber.One

        val match = MatchModel.MatchData(challenger, challenged, numberOfSets)

         return match
    }
}