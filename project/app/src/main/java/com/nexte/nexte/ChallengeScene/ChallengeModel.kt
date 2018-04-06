package com.nexte.nexte.ChallengeScene

import java.text.DateFormat

/**
 * Created by Alexandre Miguel at 30/03/2018
 */



class ChallengeModel{


    class Request{


        var challenger: ChallengeModel.Player? = null
        var challenged: ChallengeModel.Player? = null
        var place: String? = ""
        var hour: String? = ""
        var date: String? = null

        constructor(challenger: ChallengeModel.Player?, challenged: ChallengeModel.Player?,
                    place: String?, hour: String?, date: String?) {
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

    class Player {

        var name: String? = ""
        var rankingPosition: Int? = null
        var wins: Int? = null
        var losses: Int? = null
        var pictureAdress: String? = ""


        constructor(name: String?, rankingPosition: Int?, wins: Int?, losses: Int?,
                    pictureAdress: String?) {
            this.name = name
            this.rankingPosition = rankingPosition
            this.wins = wins
            this.losses = losses
            this.pictureAdress = pictureAdress
        }



    }

}