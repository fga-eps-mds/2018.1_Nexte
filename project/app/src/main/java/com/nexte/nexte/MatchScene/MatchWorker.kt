package com.nexte.nexte.MatchScene
import com.nexte.nexte.R

import com.nexte.nexte.Player
import java.time.DayOfWeek

/**
 * Created by leticia on 30/04/18.
 */

class MatchWorker {

    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = MatchModel.InitScene.Response (this.generateMatchData())
        completion(response)
    }

     fun generateMatchData() {
        var challenger = MatchModel.MatchPlayer("Letícia",  R.mipmap.ic_launcher )                             "female", "ASCAD", 20, "jurassic123" )

        var challenged = MatchModel.MatchPlayer("Alexandre Miguel", R.mipmap.ic_launcher )

        val numberOfSets = MatchModel.SetsNumber.One

        val match = MatchModel.MatchData(challenger, challenged, numberOfSets)
    }
}