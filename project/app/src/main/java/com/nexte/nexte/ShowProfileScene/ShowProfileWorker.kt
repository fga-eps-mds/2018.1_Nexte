package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.UserSingleton
import com.nexte.nexte.Player

/**
 * This class verifies if the logged user is valid and return the user information as response
 */
class ShowProfileWorker {

    /**
     * Method responsible to get the [ShowProfileModel.Request] with data to be validated
     * and generate an [ShowProfileModel.Response] with user information.
     *
     * @param request Contains the username and TokenID that will be used to recover the user
     * @param completion Unformatted activity sent to Interactor
     */
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

        // This condition verifies if exists a user
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
