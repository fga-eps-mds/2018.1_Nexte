package com.nexte.nexte

/* This class represents the user, with the
    characteristics of interest for the functionality */

object UserSingleton {
    const val age = 19
    private var userInformations: Player = Player("gabrielalbino",
            2,
            "imgur.com/nudh486d4",
            "enggabriel@gmail.com",
            "masculino",
            "ASCAD",
            age,
            "feioso")

    fun getUserInformations(): Player {
        return userInformations
    }

    fun setUserInformations(userInformations: Player) {
        this.userInformations = userInformations
    }
}
