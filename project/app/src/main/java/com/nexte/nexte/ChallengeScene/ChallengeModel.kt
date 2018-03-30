package com.nexte.nexte.ChallengeScene

import java.text.DateFormat

/**
 * Created by Alexandre Miguel at 30/03/2018
 */



class ChallengeModel{


    class Request{


        var challenger: Player? = null
        var challenged: Player? = null
        var place: String? = ""
        var hour: String? = ""
        var date: DateFormat? = null

        constructor(challenger: Player?, challenged: Player?, place: String?, hour: String?,
                    date: DateFormat?) {
            this.challenger = challenger
            this.challenged = challenged
            this.place = place
            this.hour = hour
            this.date = date
        }

    }

    class Response{

        var challengedAnswer: Boolean? = null

        constructor(challengedAnswer: Boolean?) {
            this.challengedAnswer = challengedAnswer
        }
    }

    class ViewModel {

        var message: String? = null

        constructor(message: String?) {
            this.message = message
        }
    }



}