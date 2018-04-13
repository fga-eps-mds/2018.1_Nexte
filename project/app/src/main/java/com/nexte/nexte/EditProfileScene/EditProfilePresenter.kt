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

    fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response)
}

/**
 * Interface responsible interpret received editProfileError (if exists one) and shows it in [EditProfileView]
 */
interface SendEditedProfileDataPresentationLogic {

    fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response)
}

/**
 * Class responsible to interpretation of [EditProfileModel.RecoverUserRequest]
 * and [EditProfileModel.EditProfileRequest] Responses and generate viewModels
 */
class EditProfilePresenter: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic{

    var viewToShowUserInformation: ShowProfileToEditDisplayLogic? = null // Reference for
    // show profile view with method to show user
    var viewToShowEditProfileError: EditProfileDisplayLogic? = null // Reference for show profile view with method
    // to show edit profile error

    /**
     * Format user information contained in [EditProfileModel.RecoverUserRequest.Response]
     * and sends it to [EditProfileView]
     */
    override fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response) {

        val username: String = response.user.name
        val ranking: String = String.format("#%d", response.user.rankingPosition)
        val club: String = response.user.club
        val age: String = String.format("%d", response.user.age)
        val email: String =  response.user.email

        val formattedPlayer: EditProfileModel.RecoverUserRequest.FormattedPlayer =
                EditProfileModel.RecoverUserRequest.FormattedPlayer(username,
        ranking,
        club,
        age,
        email)

        val viewModel: EditProfileModel.RecoverUserRequest.ViewModel =
                EditProfileModel.RecoverUserRequest.ViewModel(formattedPlayer)

        viewToShowUserInformation?.displayProfileToEdit(viewModel)
    }

    /**
     * Format error contained in [EditProfileModel.RecoverUserRequest.Response] (if exists) and sends it to [EditProfileView]
     */
    override fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response) {

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

        val viewModel: EditProfileModel.EditProfileRequest.ViewModel = EditProfileModel.EditProfileRequest.ViewModel(
            messageError
        )

        viewToShowEditProfileError?.displayEditedProfile(viewModel)
    }
}