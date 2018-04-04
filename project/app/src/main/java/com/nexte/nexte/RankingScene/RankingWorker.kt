package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

class RankingWorker {
    constructor()

    fun getUsersInRank(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {

        //The request is empty, so there is no further validation needed.
        var players: Array<RankingModel.Player> = arrayOf(RankingModel.Player("Gabriel Albino", "someURLHere", 100, 0, "10/10/2010", 1),
                                                        RankingModel.Player("Helena Goulart", "someURLHere", 99, 1, "10/10/2011", 2))
        var response: RankingModel.Response = RankingModel.Response(players)

        completion(response)

    }

}