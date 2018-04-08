package com.nexte.nexte
import com.nexte.nexte.ShowProfileScene.*

object UserSingleton {

    private var userInformations: ShowProfileModel.Player = ShowProfileModel.Player("gabrielalbino",
            2,
            "imgur.com/nudh486d4",
            "enggabriel@gmail.com",
            "masculino",
            "ASCAD",
            19)

    fun getUserInformations(): ShowProfileModel.Player {
        return userInformations
    }

    fun setUserInformations(userInformations: ShowProfileModel.Player) {
        this.userInformations = userInformations
    }
}