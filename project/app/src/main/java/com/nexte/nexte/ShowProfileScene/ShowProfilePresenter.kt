package com.nexte.nexte.ShowProfileScene

/**
 * This interface will define methods used to format player data and send it to [ShowProfileView]
 */
interface ShowProfilePresentationLogic {
    /**
     * Method responsible to present profile data
     *
     * @param response contains unformatted data received from [ShowProfileModel]
     */
    fun presentUserProfile(response: ShowProfileModel.Response)
}

/**
 * This class will be responsible to receive a [ShowProfileModel.Response]
 * and generate a [ShowProfileModel.ViewModel], sending it to the [ShowProfileView]
 *
 * @property viewScene Reference for the viewScene responsible to display formatted data
 */

class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null

    /**
     * This method is responsible for formatting data contained on
     * [ShowProfileModel.Response] and send it to [ShowProfileView]
     *
     * @param response contains unformatted data received from [ShowProfileModel]
     */
    override fun presentUserProfile(response: ShowProfileModel.Response) {

        val name: String? = response.user?.name
        val ranking: String? = "#" + response.user?.rankingPosition.toString()
        val club: String? = response.user?.club
        val email: String? = response.user?.email
        val age: String? = response.user?.age.toString()

        val formattedPlayer : ShowProfileModel.FormattedPlayer = ShowProfileModel.FormattedPlayer(
                name,
                ranking,
                club,
                email,
                age)

        val viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(formattedPlayer)

        viewScene?.displayProfile(viewModel)
    }
}
