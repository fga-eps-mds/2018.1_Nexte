package com.nexte.nexte.RankScene

/**
 * Created by albino on 02/04/18.
 */

class RankWorker {
    constructor()

    fun getUsersInRank(request: RankModel.Request, completion: (RankModel.Response) -> Unit) {

        //The request is empty, so there is no further validation needed.
        var players: Array<RankModel.Player> = arrayOf(RankModel.Player("Gabriel Albino", "someURLHere", 100, 0, "10/10/2010", 1),
                                                        RankModel.Player("Helena Goulart", "someURLHere", 99, 1, "10/10/2011", 2))
        var response: RankModel.Response = RankModel.Response(players)

        completion(response)

    }

}