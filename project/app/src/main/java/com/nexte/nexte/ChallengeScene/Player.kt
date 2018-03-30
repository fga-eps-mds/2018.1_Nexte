package com.nexte.nexte.ChallengeScene

import java.text.FieldPosition


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