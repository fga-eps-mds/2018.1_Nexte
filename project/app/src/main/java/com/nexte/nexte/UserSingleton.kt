package com.nexte.nexte

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager

enum class UserType { REAL, MOCKED }

/**
 * This class represents the user, with the
 * characteristics of interest for the functionality
 */
object UserSingleton {
    var userType: UserType = UserType.MOCKED
    var loggedUserID: String = "9"
        private set
    val loggedUser: User
        get() {
            return if (userType == UserType.MOCKED){
                UserManager().get(loggedUserID)!!
            } else {
                UserManager().get(loggedUserID)!!
            }
        }

    fun setLoggedUser(identifier: String, userType: UserType = UserType.MOCKED) {
        this.loggedUserID = identifier
        this.userType = userType
    }
}