package com.nexte.nexte.RankingScene

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_ranking.view.*

/**
 * Created by albino on 02/04/18.
 */

interface RankingPresentationLogic {

    fun presentRanking(response: RankingModel.Response)
}

class RankingPresenter( var viewScene: RankingDisplayLogic? = null) : RankingPresentationLogic {

    override fun presentRanking(response: RankingModel.Response) {
        val viewModel = RankingModel.ViewModel(this.formatRankingActivities(response.rankingActivities))

        viewScene?.displayRankInScreen(viewModel)
    }

    private fun formatRankingActivities(activities: Array<RankingModel.RankingActivity>): List<RankingModel.RankingActivityFormatted> {
        var rankingActivitiesFormatted: MutableList<RankingModel.RankingActivityFormatted> = mutableListOf()

        for(activity in activities) {
            val rankingActivityFormated = RankingModel.RankingActivityFormatted(activity.userRanking.name,
                    activity.userRanking.pictureURL.toInt(), activity.userRanking.wins.toString(), activity.userRanking.losses.toString(),
                    activity.userRanking.rankPosition.toString(),true)
            rankingActivitiesFormatted.add(rankingActivityFormated)
        }
        return rankingActivitiesFormatted.toList()
    }
}