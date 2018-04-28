package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton

/**
 * Class responsible to get a request and generate a response
 */
class EditProfileWorker {

    companion object {

        const val minPasswordLenght = 6
    }

    /**
     * Gets an username and returns user information
     *
     * @param request Contains the username and TokenID that will be used to recover the user
     * @param completion Unformatted activity sent to Interactor
     */
    fun getUserProfileToEdit(request: EditProfileModel.RecoverUserRequest.Request,
                             completion: (EditProfileModel.RecoverUserRequest.Response) -> Unit) {

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

        val response: EditProfileModel.RecoverUserRequest.Response =
                EditProfileModel.RecoverUserRequest.Response(returnedUser!!)

        completion(response)
    }

    /**
     * Responsible to get edited user information, validate it and if there is no error, set it
     * to our user, otherwise sends and error code.
     *
     * @param request Contains the edited user information
     * @param completion Unformatted activity sent to Interactor
     */
    fun editUserProfile(request: EditProfileModel.EditProfileRequest.Request,
                        completion: (EditProfileModel.EditProfileRequest.Response) -> Unit) {

        val user = request.user
        var errorID: Int? = null
        var newUser: Player? = null

        if(!user.email.contains('@')) {
            errorID = 1
        } else if(user.password.isNotEmpty() && user.password.length < minPasswordLenght) {
            errorID = 2
        } else {
            newUser = user
            if(user.password.isEmpty()) {
                user.password = UserSingleton.getUserInformations().password
            }
        }

        val response: EditProfileModel.EditProfileRequest.Response =
                EditProfileModel.EditProfileRequest.Response(errorID, newUser)

        completion(response)
    }
}