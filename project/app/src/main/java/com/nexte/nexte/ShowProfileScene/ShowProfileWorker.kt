package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.Entities.User.UserMocker

/**
 * This class verifies if the logged user is valid and return the user information as response.
 * She format Response and call completion to send data for called class
 */

class ShowProfileWorker {

    /**
     * Method responsible to get the [ShowProfileModel.Request] with data to be validated
     * and generate an [ShowProfileModel.Response] with user information
     *
     * @param request Contains the username and TokenID that will be used to recover the user
     * @param completion Unformatted activity sent to Interactor
     */
    fun getUserProfile(request: ShowProfileModel.Request,
                       completion: (ShowProfileModel.Response) -> Unit) {

        val username = request.username
        val userList = UserManager().getAll()

        val emptyUser = User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null)

        var returnedUser: User? = null

        // This condition verifies if exists a user
        for(user in userList){
            if(user.name == username){
                returnedUser = user
                break
            }
        }

        if(returnedUser == null){
            returnedUser = emptyUser
        }

        val response: ShowProfileModel.Response = ShowProfileModel.Response(returnedUser)

        completion(response)
    }
}
