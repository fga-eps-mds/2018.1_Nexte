package com.nexte.nexte.LikeListScene

import kotlinx.android.synthetic.main.row_feed.view.*
import kotlinx.android.synthetic.main.row_ranking.view.*


/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListPresentationLogic {

    fun formatLikeList(response: LikeListModel.Response)
}

class LikeListPresenter(var viewList: LikeListDisplayLogic? = null) : LikeListPresentationLogic {

    override fun formatLikeList(response: LikeListModel.Response) {

        val viewModel = LikeListModel.ViewModel(this.formatPlayers(response.Players))
        viewList?.displayLikeList(viewModel)

    }


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
