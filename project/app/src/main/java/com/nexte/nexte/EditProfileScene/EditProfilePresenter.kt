package com.nexte.nexte.EditProfileScene

/**
 * Created by lorrany on 27/03/18.
 */
interface EditProfilePresentationLogic{

    fun presentEditProfile(response: EditProfileModel.Response)
}
class EditProfilePresenter: EditProfilePresentationLogic{
    var view: EditProfileDisplayLogic?= null

    override fun presentEditProfile(response: EditProfileModel.Response){
        var message: String= ""
        if(response.sex == 'U'){
            message= "Não foi possível editar o usuário"
        }
        else {
            message= "Recuperei o usuário"
        }
        var viewModel: EditProfileModel.ViewModel = EditProfileModel.ViewModel(message)

        view?.displayEditProfileState(viewModel)
    }
}