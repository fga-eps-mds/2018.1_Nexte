package com.nexte.nexte.EditProfileScene

import android.util.Log
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
        Log.d("EDITPROFILEPRENTER","To na presenter galera")
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
                email
        )

        firstView?.displayProfileToEdit(viewModel)

    }

    override fun sendEditedProfileStatus(response: EditProfileModel.SecondRequest.Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}