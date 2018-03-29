package com.nexte.nexte.FeedScene

/**
 * Created by leticia on 27/03/18.
 */

class FeedWorker {

    constructor() { }

    fun getGame(request: FeedModel.Request, completion: (FeedModel.Response) -> Unit) {

        val challenger: String? = request.challenger
        val challenged: String? = request.challenged
        var firstPlayer: String? = null
        var secondPlayer: String? = null
        var rankFirstPlayer: Int? = null
        var rankSecondPlayer: Int? = null
        var linkFirstProfilePicture: String? = null
        var linkSecondProfilePicture: String? = null

        if (challenger.equals("larissa") && challenged.equals("leticia")){
            firstPlayer = "larissa"
            secondPlayer = "leticia"
            rankFirstPlayer = 1
            rankSecondPlayer = 2
            linkFirstProfilePicture = "www.nexte.com/profile/larissa/picture.jpg"
            linkSecondProfilePicture = "www.nexte.com/profile/leticia/picture.jpg"
        } else {
            firstPlayer = ""
            secondPlayer = ""
            rankFirstPlayer = -1
            rankSecondPlayer = -1
            linkFirstProfilePicture = ""
            linkSecondProfilePicture = ""
        }

        val response: FeedModel.Response = FeedModel.Response(firstPlayer, secondPlayer,
                                                              rankFirstPlayer, rankSecondPlayer,
                                                              linkFirstProfilePicture, linkSecondProfilePicture)
        completion(response)
    }
}