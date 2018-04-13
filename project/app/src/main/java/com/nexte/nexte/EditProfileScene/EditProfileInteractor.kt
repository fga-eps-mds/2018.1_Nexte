package com.nexte.nexte.EditProfileScene

/**
 * Created by lorrany on 27/03/18.
 */

interface EditProfileBusinessLogic{
    fun getProfileToEdit(request: EditProfileModel.Request)
}

class EditProfileInteractor : EditProfileBusinessLogic {
    var presenter: EditProfilePresentationLogic? = null
    var worker = EditProfileWorker()

    override fun getProfileToEdit(request: EditProfileModel.Request){
        worker.editUserProfile(request){response ->
            presenter?.presentEditProfile(response)
        }
    }

}
