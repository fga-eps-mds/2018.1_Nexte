package com.nexte.nexte.ShowProfile

/**
 * Created by albino on 25/03/18.
 */

class ShowProfileWorker {

    constructor() { }

    fun getUserProfile(request: ShowProfileModel.Request, completion: (ShowProfileModel.Response) -> Unit) {
        val username = request.username
        val tokenID = request.tokenID

        var facebookUsername: String = ""
        var pictureAddress: String = ""
        var rankingPosition: Int = -1
        var team: String = ""
        var teamLocation: String = ""
        var age: Int? = -1

        if(tokenID.equals("")){
            facebookUsername = ""
            pictureAddress = ""
            rankingPosition = -1
            team = ""
            teamLocation = ""
            age = -1
        } else if(username.equals("gabrielalbino")) {
            facebookUsername = "you.albino"
            pictureAddress = "https://www.nexte.com.br/pictures/user/gabrielalbino/avatar156x156.jpg"
            rankingPosition = 1
            team = "jagaritigaricaFon"
            teamLocation = "Brasília"
            age = 19
        }

        var response: ShowProfileModel.Response = ShowProfileModel.Response(facebookUsername,
                pictureAddress, rankingPosition, team, teamLocation, age)

        completion(response)
    }
}
