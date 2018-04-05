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

        val name = response.user?.name
        val rank = "#" + response.user?.rankingPosition.toString()
        val club = response.user?.club
        val email = response.user?.email
        val age = response.user?.age.toString()

        var viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(name, rank, club,
                                                                                email, age)

        viewScene?.displayProfile(viewModel)
    }
}
