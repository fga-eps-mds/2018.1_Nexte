package com.nexte.nexte

/**
 * This class represents the user, with the
 * characteristics of interest for the functionality
 */
object UserSingleton {

    private const val ageConstant = 25
    private const val rankConstant = 12
    private var userInformations: Player = Player("Nick Cairo",
            rankConstant,
            "imgur.com/nudh486d4",
            "cairo@nexte.com",
            "masculino",
            "ASCAD",
            ageConstant,
            "feioso",
            "Profissional")

    fun getUserInformations(): Player {
        return userInformations
    }

    fun setUserInformations(userInformations: Player) {
        this.userInformations = userInformations
    }
}
