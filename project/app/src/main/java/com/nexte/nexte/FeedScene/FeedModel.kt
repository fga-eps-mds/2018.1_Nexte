package com.nexte.nexte.FeedScene

/**
 * Created by leticia on 27/03/18.
 */

class FeedModel {

    class Request {

        var challenged: String? = null
        var challenger: String? = null

        constructor(challenged: String?, challenger: String?) {
            this.challenged = challenged
            this.challenger = challenger
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

        constructor(message: String) {
            this.message = message
        }

    }

}