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
                    activity.userRanking.rankPosition.toString())
            rankingActivitiesFormatted.add(rankingActivityFormated)
        }
        return rankingActivitiesFormatted.toList()
    }

    class RankingAdapter : BaseAdapter {

        private val inflater: LayoutInflater
        private val context: Context //where will be formated.
        private val dataSource: Array<RankingModel.RankingPlayer> //what will be formated

        constructor(context: Context, dataSource: Array<RankingModel.RankingPlayer>) : super() {
            this.context = context
            this.dataSource = dataSource
            inflater = LayoutInflater.from(context)
        }

        override fun getCount(): Int {
            return dataSource.size
        }

        override fun getItem(position: Int): Any {
            return dataSource[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int,
                             convertView: View?,
                             parent: ViewGroup?): View {

            val rowView = inflater.inflate(R.layout.row_ranking, parent, false)

            rowView.name.text = dataSource[position].name
            rowView.victory.text = String.format("Vitórias: %d", dataSource[position].wins)
            rowView.losses.text = String.format("Derrotas: %d", dataSource[position].losses)
            rowView.position.text = String.format("#%d", dataSource[position].rankPosition)

            return rowView
        }
    }




}