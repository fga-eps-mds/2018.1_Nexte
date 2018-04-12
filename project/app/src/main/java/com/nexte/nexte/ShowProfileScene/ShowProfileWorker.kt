package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.*

/**
 * Created by albino on 25/03/18.
 */

/* This class verify if the user is validad
    and return the user as response */

class ShowProfileWorker {

    fun getUserProfile(request: ShowProfileModel.Request,
                       completion: (ShowProfileModel.Response) -> Unit) {

        val username = request.username
        val tokenID = request.tokenID

        val emptyUser = Player("",
            -1,
            "",
            "",
            "",
            "",
            -1,
            "")

        var returnedUser: Player? = null

        // This condition verify if exist a user
        if(tokenID.equals("")) {
            returnedUser = emptyUser
            UserSingleton.setUserInformations(emptyUser)
        } else if(username.equals("gabrielalbino")) {
            returnedUser = UserSingleton.getUserInformations()
        }

        val response: ShowProfileModel.Response = ShowProfileModel.Response(returnedUser)

        completion(response)
    }
}
