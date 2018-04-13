package com.nexte.nexte.EditProfileScene

import android.util.Log
import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton

/**
 * Created by lorrany on 27/03/18.
 */

interface ShowProfileToEditPresentationLogic {

    fun presentProfileToEdit(response: EditProfileModel.FirstRequest.Response)
}

interface SendEditedProfileDataPresentationLogic {

    fun sendEditedProfileStatus(response: EditProfileModel.SecondRequest.Response)
}

class EditProfilePresenter: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic{

    var firstView: ShowProfileToEditDisplayLogic? = null
    var secondView: EditProfileDisplayLogic? = null

    override fun presentProfileToEdit(response: EditProfileModel.FirstRequest.Response) {

        val username: String = response.user.name
        val ranking: String = String.format("#%d", response.user.rankingPosition)
        val club: String = response.user.club
        val age: String = String.format("%d", response.user.age)
        val email: String =  response.user.email

        val viewModel: EditProfileModel.FirstRequest.ViewModel = EditProfileModel.FirstRequest.ViewModel(
            username,
            ranking,
            club,
            age,
            email)

        firstView?.displayProfileToEdit(viewModel)
    }

    override fun sendEditedProfileStatus(response: EditProfileModel.SecondRequest.Response) {

        val errorID: Int? = response.errorID
        var messageError: String? = ""
        val newUser: Player? = response.newUser

        if(errorID == 1) {
            messageError = "Email inválido"
        } else if(errorID == 2) {
            messageError = "Sua senha deverá conter mais de 6 caracteres"
        } else {
            UserSingleton.setUserInformations(newUser!!)
            messageError = null
        }

        val viewModel: EditProfileModel.SecondRequest.ViewModel = EditProfileModel.SecondRequest.ViewModel(
            messageError
        )

        secondView?.displayEditedProfile(viewModel)
    }
}