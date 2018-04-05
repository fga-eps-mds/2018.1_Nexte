package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

interface ShowProfilePresentationLogic {
    fun presentUserProfile(response: ShowProfileModel.Response)
}

class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null

    override fun presentUserProfile(response: ShowProfileModel.Response) {
        var player: ShowProfileModel.Player? = null
        player = response.user


        var viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(player)

        viewScene?.displayProfile(viewModel)
    }
}
