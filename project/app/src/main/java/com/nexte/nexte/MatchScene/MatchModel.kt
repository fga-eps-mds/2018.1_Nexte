package com.nexte.nexte.MatchScene

import java.util.*

/**
 * Created by leticia on 30/04/18.
 */

class MatchModel {

    class Request (var matchID: String)

    class Response (var infoMatch: MatchData)

    class ViewModel (var matchFormatted: FormattedMatchData)

    class MatchData (var player1: String, var player2: String, var games: String,
                     var sets: Int, var day: Date, var hour: Date)

    class FormattedMatchData (var player1: String, var player2: String, var games: String,
                              var sets: String, var day: String, var hour: String)
}