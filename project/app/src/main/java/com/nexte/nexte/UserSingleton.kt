package com.nexte.nexte

/**
 * This class represents the user, with the
 * characteristics of interest for the functionality
 */
object UserSingleton {

    private const val ageConstant = 19
    private const val rankConstant = 15
    private var userInformations: Player = Player("gabrielalbino",
            rankConstant,
            "imgur.com/nudh486d4",
            "enggabriel@gmail.com",
            "masculino",
            "ASCAD",
            ageConstant,
            "feioso")

    fun getUserInformations(): Player {
        return userInformations
    }

    fun setUserInformations(userInformations: Player) {
        this.userInformations = userInformations
    }
}
