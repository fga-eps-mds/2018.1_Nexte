package com.nexte.nexte

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import io.realm.Realm
import io.realm.RealmConfiguration

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
        when (userType) {
            UserType.MOCKED -> {
                val config = RealmConfiguration.Builder().name("mockerRealm.realm").build()
                Realm.setDefaultConfiguration(config)
            }
            UserType.REAL -> {
                val config =  RealmConfiguration.Builder().name("realRealm.realm").build()
                Realm.setDefaultConfiguration(config)
            }
        }

        this.loggedUserID = identifier
        this.userType = userType
    }
}