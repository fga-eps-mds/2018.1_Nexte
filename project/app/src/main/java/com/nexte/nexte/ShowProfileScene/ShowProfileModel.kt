package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

/* This class declares the request,
    response, viewModel and player */

class ShowProfileModel {

    /* This class contain the parameters necessary
        for validation of the user */

    class Request {
        var username: String? = null // Name provided by user
        var tokenID: String? = null // TokenID provided by server

        constructor(username: String, tokenID: String) {
            this.username = username
            this.tokenID = tokenID
        }
    }

    /* This class will return the attributes of player
        after the validation step */

    class Response {
        var user: Player? = null // User returned after validation

        constructor(user: Player?) {

            this.user = user

        }
    }

    /* Receives the attribute already processed by Presenter class
        for display on screen */

    class ViewModel {

        var name: String? = null // Name displayed
        var rank: String? = null // The rank position displayed
        var club: String? = null // User club displayed
        var email: String? = null // User email displayed
        var age: String? = null // User age displayed

        constructor(name: String?, rank: String?, club: String?, email: String?, age: String?) {

            this.name = name
            this.rank = rank
            this.club = club
            this.email = email
            this.age = age
        }
    }
    /* This class represents the user, with the
        characteristics of interest for the functionality */

    class Player {

        var name: String // User name
        var rankingPosition: Int// Ranking position of user
        var pictureAdress: String// Picture profile
        var email: String // User email
        var gender: String // User gender
        var club: String // User club
        var age: Int // Age of user
        var password: String // User password

        constructor(name: String,
                    rankingPosition: Int,
                    pictureAdress: String,
                    email: String,
                    gender: String,
                    club: String,
                    age: Int,
                    password: String) {

            this.name = name
            this.rankingPosition = rankingPosition
            this.pictureAdress = pictureAdress
            this.email = email
            this.gender = gender
            this.club = club
            this.age = age
            this.password = password
        }


    }
}
