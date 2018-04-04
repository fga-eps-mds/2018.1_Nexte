package com.nexte.nexte.RankingScene

/**
 * Created by albino on 02/04/18.
 */

class RankingModel {
    class Request {
        constructor()
    }

    class Player {
        var name: String? = null
        var pictureURL: String? = null
        var wins: Int? = null
        var losses: Int? = null
        var lastGame: String? = null
        var rankPosition : Int? = null
        constructor(name: String, pictureURL: String, wins: Int, losses: Int, lastGame: String, rankPosition: Int) {
            this.name = name
            this.pictureURL = pictureURL
            this.wins = wins
            this.losses = losses
            this.lastGame = lastGame
            this.rankPosition = rankPosition
        }
    }

    class Response {
        var players: Array<Player>? = null
        constructor(players: Array<Player>) {
            this.players = players
        }
    }

    class ViewModel {
        var message: String? = null

        constructor(message: String) {
            this.message = message
        }
    }


}