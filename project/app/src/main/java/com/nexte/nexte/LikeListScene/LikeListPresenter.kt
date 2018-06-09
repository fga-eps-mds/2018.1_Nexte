package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R

/**
 * Interface to define Presentation Logic to LikeList Class that will be used
 * to call this Interactor on other class layer
 */
interface LikeListPresentationLogic {

    /**
     * Method responsible to format like list data and send for view
     *
     * @param response
     */
    fun formatLikeList(response: LikeListModel.Response)
}

/**
 * Class needed to format response for data can be displayed on activity
 *
 * @property viewList Reference to the activity where data will be displayed [LikeListFragment].
 */
class LikeListPresenter(var viewList: LikeListDisplayLogic? = null) :
                        LikeListPresentationLogic {

    override fun formatLikeList(response: LikeListModel.Response) {

        val viewModel = LikeListModel.ViewModel(this.formatPlayers(response.players))
        viewList?.displayLikeList(viewModel)
    }

    /**
     * Function that converts the MutableList Players  unformatted to
     * MutableList PLayersformatted.
     *
     * @param likePlayers MutableList of unformatted players
     * @return MutableList of formatted players
     */
    private fun formatPlayers(likePlayers: List<User>):
            MutableList<LikeListModel.PlayersFormatted> {

        val playersFormatted: MutableList<LikeListModel.PlayersFormatted> = mutableListOf()

    for (likePlayer in likePlayers) {
        val likePlayerFormatted = LikeListModel.PlayersFormatted(
                likePlayer.name,
                R.mipmap.ic_launcher)

        playersFormatted.add(likePlayerFormatted)
    }

            return playersFormatted
    }
}
