package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton

/**
 * Created by lorrany on 27/03/18.
 */

class EditProfileWorker {

    fun getUserProfileToEdit(request: EditProfileModel.FirstRequest.Request, completion:
        (EditProfileModel.FirstRequest.Response) -> Unit) {

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
        if(tokenID == "") {
            returnedUser = emptyUser
            UserSingleton.setUserInformations(emptyUser)
        } else if(username == "gabrielalbino") {
            returnedUser = UserSingleton.getUserInformations()
        }

        val response: EditProfileModel.FirstRequest.Response = EditProfileModel.FirstRequest.Response(returnedUser!!)

        completion(response)
    }

    fun editUserProfile(request: EditProfileModel.SecondRequest.Request, completion:
                       (EditProfileModel.SecondRequest.Response) -> Unit) {

        val user = request.user
        var errorID: Int? = null
        var newUser: Player? = null

        if(!user.email.contains('@')) {
            errorID = 1
        } else if(user.password.length < 6) {
            errorID = 2
        } else {
            newUser = user
        }

        val response: EditProfileModel.SecondRequest.Response = EditProfileModel.SecondRequest.Response(errorID, newUser)

        completion(response)
    }
}