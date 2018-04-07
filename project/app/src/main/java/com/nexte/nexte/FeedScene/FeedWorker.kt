package com.nexte.nexte.FeedScene

/**
 * Created by helena on 03/04/18.
 */

/*
This class is responsible to interpret the request and turns it into a response
 */
class FeedWorker {

    fun requestFeedData(request: HistoryModel.Request, completion: (HistoryModel.Response)
     -> Unit) {
        val matches: Array<HistoryModel.Match> = arrayOf(
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 2),
                        HistoryModel.Player("Gabriel", "", 2, 3)),
                        HistoryModel.Date(2009, 1, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Larissa", "", 7, 5),
                        HistoryModel.Player("Helena", "", 4, 4)),
                        HistoryModel.Date(2018, 10, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Gabriel", "", 2, 7),
                        HistoryModel.Player("Helena", "", 1, 4)),
                        HistoryModel.Date(2017, 10, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Leticia", "", 4, 5),
                        HistoryModel.Player("Helena", "", 1, 6)),
                        HistoryModel.Date(2018, 1, 2, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 5, 2),
                        HistoryModel.Player("Luis", "", 3, 3)),
                        HistoryModel.Date(2018, 1, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 9),
                        HistoryModel.Player("Larissa", "", 7, 0)),
                        HistoryModel.Date(2018, 1, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 2, 2),
                        HistoryModel.Player("Lorrany", "", 6, 5)),
                        HistoryModel.Date(2018, 10, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Alexandre", "", 8, 3),
                        HistoryModel.Player("Helena", "", 1, 7)),
                        HistoryModel.Date(2017, 10, 1, 10, 50)),
                HistoryModel.Match(arrayOf(
                        HistoryModel.Player("Helena", "", 1, 5),
                        HistoryModel.Player("Leticia", "", 4, 1)),
                        HistoryModel.Date(2018, 10, 1, 10, 50))
        )// Mocked matches that will be used for test display

        var playerMatches: Array<HistoryModel.Match> = arrayOf() //Empty array that will store only matches played by the user

        for (game in matches) {
            if(game.userPlayed(request.name!!)) {
                playerMatches += game
            }
        }

        var response: HistoryModel.Response = HistoryModel.Response(playerMatches, request.context, request.name!!)
        completion(response) //calls completion for response

    }
}