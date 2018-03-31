package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */
class ShowProfileModel{
    class Request {
        var username: String? = null
        var tokenID: String? = null

        constructor(username: String, tokenID: String){
            this.username = username
            this.tokenID = tokenID
        }
    }

    class Response {
        var facebookUsername: String? = null
        var pictureAddress: String? = null
        var rankingPosition: Int? = null
        var team: String? = null
        var teamLocation: String? = null
        var age: Int? = null

        constructor(facebookUsername: String?, pictureAddress: String?, rankingPosition: Int?,
                    team: String?, teamLocation: String?, age: Int?){
            this.facebookUsername = facebookUsername
            this.pictureAddress = pictureAddress
            this.rankingPosition = rankingPosition
            this.team = team
            this.teamLocation = teamLocation
            this.age = age
        }
    }

    class ViewModel {
        var message: String? = null

        constructor(message: String){
            this.message = message
        }
    }
}
