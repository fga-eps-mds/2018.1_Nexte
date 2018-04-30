package com.nexte.nexte.MatchScene

import com.nexte.nexte.Player
import java.util.*

/**
 * Created by leticia on 30/04/18.
 */

class MatchModel {

    class Request (var matchID: String)

    class Response (var match: MatchData)

    class ViewModel (var matchFormatted: FormattedMatchData)

    class MatchData (var player1: Player, var player2: Player, var betterThan: Enum<1, 3, 5, "WO">,
                     var sets: Int, var matchDate: Date)
                             

    class FormattedMatchData (var player1: String, var player2: String, var games: String,
                              var sets: String, var day: String, var hour: String)
}