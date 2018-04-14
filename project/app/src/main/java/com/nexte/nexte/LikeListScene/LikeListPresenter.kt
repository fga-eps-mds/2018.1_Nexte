package com.nexte.nexte.LikeListScene

import kotlinx.android.synthetic.main.row_feed.view.*
import kotlinx.android.synthetic.main.row_ranking.view.*


/**
 * Created by lorrany on 12/04/18.
 */

/**
 Interface to define Presentation Logic to LikeList Class that will used to call this Interactor on other class layer
 */
interface LikeListPresentationLogic {

    /**
     * Method responsible to format feed data and send for view.
     *
     * @param response
     */
    fun formatLikeList(response: LikeListModel.Response)
}

/**
* Class needed to format response for data can be displayed on activity
*
* @property viewList Reference to the activity where data will be displayed [LikeListView].
 */
class LikeListPresenter(var viewList: LikeListDisplayLogic? = null) : LikeListPresentationLogic {

    override fun formatLikeList(response: LikeListModel.Response) {

        val viewModel = LikeListModel.ViewModel(this.formatPlayers(response.Players))
        viewList?.displayLikeList(viewModel)

    }

    /**
     * Function that converts the MutableList PLayers  unformatted to MutableList PLayersformatted.
     *
     * @param likePlayers MutableList of unformatted players
     * @return MutableList of formatted players
     */

    private fun formatPlayers(likePlayers: MutableList<LikeListModel.Players>):
            MutableList<LikeListModel.PlayersFormatted> {

        val playersFormatted: MutableList<LikeListModel.PlayersFormatted> = mutableListOf()

    for (likePlayer in likePlayers) {
        val likeplayerformatted = LikeListModel.PlayersFormatted(
                likePlayer.name,
                likePlayer.photo,
                likePlayer.time.toString())

        playersFormatted.add(likeplayerformatted)
    }

            return playersFormatted
    }
}
