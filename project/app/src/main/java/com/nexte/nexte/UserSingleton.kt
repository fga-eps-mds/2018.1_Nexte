package com.nexte.nexte

/* This class represents the user, with the
    characteristics of interest for the functionality */

object UserSingleton {

    private var userInformations: Player = Player("gabrielalbino",
            2,
            "imgur.com/nudh486d4",
            "enggabriel@gmail.com",
            "masculino",
            "ASCAD",
            19,
            "feioso")

    fun getUserInformations(): Player {
        return userInformations
    }

    fun setUserInformations(userInformations: Player) {
        this.userInformations = userInformations
    }
}