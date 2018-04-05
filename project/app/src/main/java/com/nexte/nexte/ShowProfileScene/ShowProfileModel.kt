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
        var user: Player? = null

        constructor(user: Player?){
            this.user = user
        }
    }



    class ViewModel {

        var name: String? = null
        var rank: String? = null
        var club: String? = null
        var email: String? = null
        var age: String? = null

        constructor(name: String?, rank: String?, club: String?, email: String?, age: String?){

            this.name = name
            this.rank = rank
            this.club = club
            this.email = email
            this.age = age
        }
    }

    class Player {

        var name: String? = ""
        var rankingPosition: Int? = null
        var pictureAdress: String? = ""
        var email: String? = ""
        var gender: String? = ""
        var club: String? = ""
        var age: Int? = -1

        constructor(name: String?, rankingPosition: Int?, pictureAdress: String?,
                    email: String?, gender: String?, club: String?, age: Int?) {
            this.name = name
            this.rankingPosition = rankingPosition
            this.pictureAdress = pictureAdress
            this.email = email
            this.gender = gender
            this.club = club
            this.age = age
        }



    }
}
