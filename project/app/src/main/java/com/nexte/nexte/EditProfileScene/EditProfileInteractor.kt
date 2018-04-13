package com.nexte.nexte.EditProfileScene

/**
 * Created by lorrany on 27/03/18.
 */

interface GetProfileToEditBusinessLogic {

    fun getProfileToEdit(request: EditProfileModel.FirstRequest.Request)
}

interface EditProfileBusinessLogic {

    fun setEditedProfile(request: EditProfileModel.SecondRequest.Request)
}

class EditProfileInteractor : GetProfileToEditBusinessLogic, EditProfileBusinessLogic {

    var firstPresenter: ShowProfileToEditPresentationLogic? = null
    var secondPresenter: SendEditedProfileDataPresentationLogic? = null
    var worker = EditProfileWorker()

    override fun getProfileToEdit(request: EditProfileModel.FirstRequest.Request){

        worker.getUserProfileToEdit(request) { response ->
            firstPresenter?.presentProfileToEdit(response)
        }
    }

    override fun setEditedProfile(request: EditProfileModel.SecondRequest.Request) {

        worker.editUserProfile(request) { response ->
            secondPresenter?.sendEditedProfileStatus(response)
        }
    }
}
