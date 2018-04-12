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

    var presenter: ShowProfileToEditPresentationLogic? = null
    var worker = EditProfileWorker()

    override fun getProfileToEdit(request: EditProfileModel.FirstRequest.Request){
        worker.getUserProfileToEdit(request){response ->
            presenter?.presentProfileToEdit(response)
        }
    }

    override fun setEditedProfile(request: EditProfileModel.SecondRequest.Request) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
