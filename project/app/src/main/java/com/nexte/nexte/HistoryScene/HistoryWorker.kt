package com.nexte.nexte.HistoryScene

/**
 * Created by helena on 03/04/18.
 */

class HistoryWorker {
    fun filterPlayerGames(request: HistoryModel.Request, completion: (HistoryModel.Response)
     -> Unit) {
        val matches: Array<HistoryModel.Match> = arrayOf(
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 2),
                        HistoryModel.Player("Gabriel", "", 2, 3))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Larissa", "", 7, 5),
                        HistoryModel.Player("Letícia", "", 4, 4))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Gabriel", "", 2, 7),
                        HistoryModel.Player("Helena", "", 1, 4))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Leticia", "", 4, 5),
                        HistoryModel.Player("Helena", "", 1, 6))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Miguel", "", 5, 2),
                        HistoryModel.Player("Luis", "", 3, 3))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 9),
                        HistoryModel.Player("Larissa", "", 7, 0))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Gabriel", "", 2, 2),
                        HistoryModel.Player("Lorrany", "", 6, 5))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Alexandre", "", 8, 3),
                        HistoryModel.Player("Helena", "", 1, 7))),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 5),
                        HistoryModel.Player("Leticia", "", 4, 1)))
        )

        var playerMatches: Array<HistoryModel.Match> = arrayOf()

        for (game in matches) {
            if(game.userPlayed(request.name!!)) {
                playerMatches += game
            }
        }

        var response: HistoryModel.Response = HistoryModel.Response(playerMatches, request.context)
        completion(response)

    }
}