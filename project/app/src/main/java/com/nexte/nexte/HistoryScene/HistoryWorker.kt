package com.nexte.nexte.HistoryScene

/**
 * Created by helena on 03/04/18.
 */

class HistoryWorker {
    fun filterPlayerGames(request: HistoryModel.Request, completion: (HistoryModel.Response)
     -> Unit) {
        val matches: Array<HistoryModel.Match> = arrayOf(
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 100),
                        HistoryModel.Player("Gabriel", "", 2, 4)))
        )

        var playerMatches: Array<HistoryModel.Match> = arrayOf()

        for (game in matches) {
            if(game.userPlayed(request.name!!)) {
                playerMatches += game
            }
        }

        var response: HistoryModel.Response = HistoryModel.Response(playerMatches)
        completion(response)

    }
}