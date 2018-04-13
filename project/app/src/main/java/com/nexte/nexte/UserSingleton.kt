package com.nexte.nexte

/* This class represents the user, with the
    characteristics of interest for the functionality */

object UserSingleton {
    private const val AGECONSTANT = 19
    private var userInformations: Player = Player("gabrielalbino",
            2,
            "imgur.com/nudh486d4",
            "enggabriel@gmail.com",
            "masculino",
            "ASCAD",
            AGECONSTANT,
            "feioso")

    fun getUserInformations(): Player {
        return userInformations
    }

    fun setUserInformations(userInformations: Player) {
        this.userInformations = userInformations
    }
}
