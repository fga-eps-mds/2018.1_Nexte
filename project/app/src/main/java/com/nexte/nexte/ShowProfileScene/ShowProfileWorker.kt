package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.UserSingleton

/**
 * Created by albino on 25/03/18.
 */

/* This class verify if the user is validad
    and return the user as response */

class ShowProfileWorker {

    constructor() { }

    fun getUserProfile(request: ShowProfileModel.Request,
                       completion: (ShowProfileModel.Response) -> Unit) {

        val username = request.username
        val tokenID = request.tokenID

        val emptyUser = ShowProfileModel.Player("",
            -1,
            "",
            "",
            "",
            "",
            -1)

        var returnedUser: ShowProfileModel.Player? = null

        // This condition verify if exist a user
        if(tokenID.equals("")) {
            returnedUser = emptyUser
            UserSingleton.setUserInformations(emptyUser)
        } else if(username.equals("gabrielalbino")) {
            returnedUser = UserSingleton.getUserInformations()
        }

        var response: ShowProfileModel.Response = ShowProfileModel.Response(returnedUser)

        completion(response)
    }
}
