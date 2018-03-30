package com.nexte.nexte.ShowProfile

/**
 * Created by albino on 25/03/18.
 */

interface ShowProfilePresentationLogic {
    fun presentUserProfile(response: ShowProfileModel.Response)
}

class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null

    override fun presentUserProfile(response: ShowProfileModel.Response) {
        var message: String
        val age : Int? = response.age

        if(age == (-1)) {
            message = "Não foi possível recuperar o usuário!"
        } else {
            message = "Usuário recuperado com sucesso!"
        }

        var viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(message)

        viewScene?.displayProfile(viewModel)
    }
}
