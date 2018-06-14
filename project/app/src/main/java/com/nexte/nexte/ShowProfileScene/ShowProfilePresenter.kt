package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.R
import kotlinx.android.synthetic.main.row_likes.view.*

/**
 * Interface to define Presentation Logic to Show Profile Class that
 * will be used to call this Interactor on other class layer
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
 * and generate a [ShowProfileModel.ViewModel], sending it to the [ShowProfileFragment]
 *
 * @property viewScene Reference to display formatted data
 */
class ShowProfilePresenter : ShowProfilePresentationLogic {

    var viewScene : ShowProfileDisplayLogic? = null

    /**
     * This method is responsible for formatting data contained on
     * [ShowProfileModel.Response] and send it to [ShowProfileFragment]
     *
     * @param response contains unformatted data received from [ShowProfileModel]
     */
    override fun presentUserProfile(response: ShowProfileModel.Response) {
        val name: String? = response.user?.name
        val ranking: String? = "#" + response.user?.rankingPosition.toString()
        val email: String? = response.user?.email

        var photo: Int? = null

        if (response.user?.profilePicture != null && response.user?.profilePicture != "") {
            photo = response.user?.profilePicture!!.toInt()
        } else {
            photo = R.mipmap.ic_launcher_round
        }

        val formattedPlayer : ShowProfileModel.FormattedPlayer = ShowProfileModel.FormattedPlayer(
                name,
                ranking,
                email,
                photo)
        val viewModel : ShowProfileModel.ViewModel = ShowProfileModel.ViewModel(formattedPlayer)
        viewScene?.displayProfile(viewModel)
    }
}
