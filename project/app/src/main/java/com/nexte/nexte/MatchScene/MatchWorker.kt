package com.nexte.nexte.MatchScene
import com.nexte.nexte.R

import com.nexte.nexte.Player
import java.time.DayOfWeek

/**
 * Created by leticia on 30/04/18.
 */

class MatchWorker {

    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = MatchModel.InitScene.Response (this.generateMatchData(), this.generateSetData())
        completion(response)
    }

     fun generateMatchData() {
        var challenger = Player("Letícia", 1, "photo", "lmbs.geo@gmail.com",
                                "female", "ASCAD", 20, "jurassic123" )

        var challenged = Player("Alexandre Miguel", 2, "photo", "aleronupe@gmail.com",
                 "male", "ASCAD", 19, "senha" )

        val numberOfSets = MatchModel.SetsNumber.One

        val match = MatchModel.MatchData(challenger, challenged, numberOfSets)
    }

    fun generateSetData() {
        var setData = MatchModel.MatchSet("Primeiro Set", 6, 3)

    }

    fun generateTimeData() {
        var matchDay = MatchModel.MatchDay(DayOfWeek.FRIDAY)
    }
}