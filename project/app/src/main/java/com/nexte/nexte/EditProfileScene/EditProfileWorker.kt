package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User
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


        val response: EditProfileModel.RecoverUserRequest.Response =
                EditProfileModel.RecoverUserRequest.Response(UserSingleton.loggedUser)

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
        var newUser: User? = null
        val password = request.password

        if(!user.email.contains('@')) {
            errorID = 1
        } else if(password.isNotEmpty() && password.length < minPasswordLenght) {
            errorID = 2
        } else {
            newUser = user
        }

        val response: EditProfileModel.EditProfileRequest.Response =
                EditProfileModel.EditProfileRequest.Response(errorID, newUser)

        completion(response)
    }
}