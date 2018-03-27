package com.nexte.nexte.FeedScene

/**
 * Created by leticia on 27/03/18.
 */

class FeedModel {

    class Request {
        var lastGame: String? = null
        var tokenID: String? = null
        var maxShowGames: Int? = null

        constructor(lastGame: String?, tokenID: String?, maxShowGames: Int?) {
            this.lastGame = lastGame
            this.tokenID = tokenID
            this.maxShowGames = maxShowGames
        }

    }

    class Response {

        var firstPlayer: String? = null
        var secondPlayer: String? = null
        var rankFirstPlayer: Int? = null
        var rankSecondPlayer: Int? = null
        var linkFirstProfilePicture: String? = null
        var linkSecondProfilePicture: String? = null


        constructor(firstPlayer: String?, secondPlayer: String?, rankFirstPlayer: Int?,
                    rankSecondPlayer: Int?, linkFirstProfilePicture: String?,
                    linkSecondProfilePicture: String?) {

            this.firstPlayer = firstPlayer
            this.secondPlayer = secondPlayer
            this.rankFirstPlayer = rankFirstPlayer
            this.rankSecondPlayer = rankSecondPlayer
            this.linkFirstProfilePicture = linkFirstProfilePicture
            this.linkSecondProfilePicture = linkSecondProfilePicture
        }
    }

    class ViewModel {
        var message: String? = null

        constructor(message: String){
            this.message = message
        }

    }

}