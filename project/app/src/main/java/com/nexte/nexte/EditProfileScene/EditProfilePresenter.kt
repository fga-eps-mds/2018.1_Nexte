package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton

/**
 * Created by lorrany on 27/03/18.
 */

/**
 * Interface responsible interpret received user information and send it to [EditProfileView]
 */
interface ShowProfileToEditPresentationLogic {

    fun presentProfileToEdit(response: EditProfileModel.FirstRequest.Response)
}

/**
 * Interface responsible interpret received editProfileError (if exists one) and shows it in [EditProfileView]
 */
interface SendEditedProfileDataPresentationLogic {

    fun sendEditedProfileStatus(response: EditProfileModel.SecondRequest.Response)
}

/**
 * Class responsible to interpretation of [EditProfileModel.FirstRequest]
 * and [EditProfileModel.SecondRequest] Responses and generate viewModels
 */
class EditProfilePresenter: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic{

    var firstView: ShowProfileToEditDisplayLogic? = null // Reference for show profile view with method to show user
    var secondView: EditProfileDisplayLogic? = null // Reference for show profile view with method to show edit profile error

    /**
     * Format user information contained in [EditProfileModel.FirstRequest.Response]
     * and sends it to [EditProfileView]
     */
    override fun presentProfileToEdit(response: EditProfileModel.FirstRequest.Response) {

        val username: String = response.user.name
        val ranking: String = String.format("#%d", response.user.rankingPosition)
        val club: String = response.user.club
        val age: String = String.format("%d", response.user.age)
        val email: String =  response.user.email

        val formattedPlayer: EditProfileModel.FirstRequest.FormattedPlayer =
                EditProfileModel.FirstRequest.FormattedPlayer(username,
        ranking,
        club,
        age,
        email)

        val viewModel: EditProfileModel.FirstRequest.ViewModel =
                EditProfileModel.FirstRequest.ViewModel(formattedPlayer)

        firstView?.displayProfileToEdit(viewModel)
    }

    /**
     * Format error contained in [EditProfileModel.FirstRequest.Response] (if exists) and sends it to [EditProfileView]
     */
    override fun sendEditedProfileStatus(response: EditProfileModel.SecondRequest.Response) {

        val errorID: Int? = response.errorID
        val messageError: String?
        val newUser: Player? = response.newUser

        messageError = when (errorID) {
            1 -> "Email inválido"
            2 -> "Sua senha deverá conter mais de 6 caracteres"
            else -> {
                UserSingleton.setUserInformations(newUser!!)
                null
            }
        }

        val viewModel: EditProfileModel.SecondRequest.ViewModel = EditProfileModel.SecondRequest.ViewModel(
            messageError
        )

        secondView?.displayEditedProfile(viewModel)
    }
}