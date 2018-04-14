package com.nexte.nexte.LikeListScene

import kotlinx.android.synthetic.main.row_feed.view.*
import kotlinx.android.synthetic.main.row_ranking.view.*


/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListPresentationLogic {

    fun formatLikeList(response: LikeListModel.Response)
}

class LikeListPresenter(viewList: LikeListDisplayLogic?= null) : LikeListPresentationLogic {

    override fun formatLikeList(response: LikeListModel.Response) {
        val viewModel = LikeListModel.ViewModel(this.formatPlayers(response.Players))
        viewList.displayLikeList


    }
    private fun formatPlayers(likeplayers: MutableList<LikeListModel.Players>):
            MutableList<LikeListModel.PlayersFormatted>{
        val playersformatted: MutableList<LikeListModel.PlayersFormatted> = mutableListOf()

    for (likeplayer in likeplayers) {
        val likeplayerformatted = LikeListModel.PlayersFormatted(
                likeplayer.Players.name,
                likeplayer.Players.photo,
                likePlayer.Players.time.toString())

        playersformatted.add(likeplayerformatted)
    }

            return playersformatted
    }
}
