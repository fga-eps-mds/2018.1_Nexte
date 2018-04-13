package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

/**
 * This interface will define methods used to format player data and send it to [ShowProfileView]
 */
interface ShowProfilePresentationLogic {

    fun presentUserProfile(response: ShowProfileModel.Response)
}

/**
 * This class will be responsible to receive a [ShowProfileModel.Response] and generate a [ShowProfileModel.ViewModel], sending it to the [ShowProfileView]
 */

class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null // Reference for the viewScene responsible to display formatted data

    /**
     * This method is responsible for formatting data contained on [ShowProfileModel.Response] and send it to [ShowProfileView]
     */
    override fun presentUserProfile(response: ShowProfileModel.Response) {

        val name: String? = response.user?.name
        val rank: String? = "#" + response.user?.rankingPosition.toString()
        val club: String? = response.user?.club
        val email: String? = response.user?.email
        val age: String? = response.user?.age.toString()

        val formattedPlayer : ShowProfileModel.FormattedPlayer = ShowProfileModel.FormattedPlayer(
                name,
                rank,
                club,
                email,
                age)

        val viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(formattedPlayer)

        viewScene?.displayProfile(viewModel)
    }
}
