package com.nexte.nexte.RankingScene

import com.nexte.nexte.FeedScene.FeedModel

/**
 * Created by albino on 02/04/18.
 */

class RankingWorker {

    fun getUsersInRanking(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {
        val response: RankingModel.Response(this.)
        //The request is empty, so there is no further validation needed.


        completion(response)

    }

    private fun generateRankingMockData(): Array<RankingModel.RankingActivity> {
        var players: Array<RankingModel.RankingPlayer> = arrayOf(RankingModel.Player("Gabriel Albino", "someURLHere", 100, 0, "10/10/2010", 1),
                RankingModel.Player("Helena Goulart", "someURLHere", 99, 1, "10/10/2011", 2))
        var response: RankingModel.Response = RankingModel.Response(players, request.context)
    }
}