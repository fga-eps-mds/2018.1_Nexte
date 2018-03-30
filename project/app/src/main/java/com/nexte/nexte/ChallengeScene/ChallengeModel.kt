package com.nexte.nexte.ChallengeScene

/**
 * Created by Alexandre Miguel at 30/03/2018
 */



class ChallengeModel{


    class Request{


        var challenger: String? = ""
        var place: String? = ""
        var hour: Int? = null


        constructor(challenger: Player, place: String) {
            this.place = place
        }

    }

    class Response{}

    class ViewModel{}



}