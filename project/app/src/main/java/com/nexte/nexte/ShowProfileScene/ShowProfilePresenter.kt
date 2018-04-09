package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

interface ShowProfilePresentationLogic {

    fun presentUserProfile(response: ShowProfileModel.Response)
}

/* Receives the response of Interactor,
    processing data to display in View */

class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null // Show datas from ViewModel on screen

    override fun presentUserProfile(response: ShowProfileModel.Response) {

        val name: String? = response.user?.name
        val rank: String? = "#" + response.user?.rankingPosition.toString()
        val club: String? = response.user?.club
        val email: String? = response.user?.email
        val age: String? = response.user?.age.toString()

        var viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(name, rank, club,
                                                                                email, age)

        viewScene?.displayProfile(viewModel)
    }
}
