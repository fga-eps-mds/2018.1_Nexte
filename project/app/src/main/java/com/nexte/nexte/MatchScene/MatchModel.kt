package com.nexte.nexte.MatchScene

import com.nexte.nexte.Player
import java.text.DateFormat
import java.time.DayOfWeek
import java.time.Month
import java.time.MonthDay
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by leticia on 30/04/18.
 */

class MatchModel {

    class Request (var matchID: String)

    class Response (var match: MatchData,
                    var set: MatchSet,
                    var time: MatchTime)

    class ViewModel (var matchFormatted: FormattedMatchData,
                     var setFormatted: FormattedMatchSet,
                     var timeFormatted: FormattedMatchTime)

    // --------- Aux classes ---------

    class MatchData (var challenged: Player,
                     var challenger: Player,
                     var numberOfSets: SetsNumber)

    class MatchSet (var label: String,
                    var setsChallenged: Int,
                    var setsChallenger: Int)

    class MatchTime (var matchDay: MatchDay,
                     var matchHour: DateTimeFormatter)


    class FormattedMatchData (var challengedName: String,
                              var challengedPhoto: Int,
                              var challengerName: String,
                              var challengerPhoto: Int)

    class FormattedMatchSet (var label: String,
                             var setsChallenged: String,
                             var setsChallenger: String)

    class FormattedMatchTime (var matchDay: String,
                              var matchHour: String)


    class MatchDay (var dayOfWeek: DayOfWeek,
                    var dayOfMonth: MonthDay,
                    var month: Month)


    enum class SetsNumber (val number: Int) {
        One(1),
        Three(3),
        Five(5),
        WO(0)
    }
}