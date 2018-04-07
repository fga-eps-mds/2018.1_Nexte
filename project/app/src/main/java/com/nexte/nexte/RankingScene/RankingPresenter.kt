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

class RankingPresenter : RankingPresentationLogic {

    class RankingAdapter : BaseAdapter {

        private val inflater: LayoutInflater
        private val context: Context //where will be formated.
        private val dataSource: Array<RankingModel.Player> //what will be formated

        constructor(context: Context, dataSource: Array<RankingModel.Player>) : super() {
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

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = inflater.inflate(R.layout.row_ranking, parent, false)

            rowView.name.text = dataSource[position].name
            rowView.victory.text = String.format("Vitórias: %d", dataSource[position].wins)
            rowView.losses.text = String.format("Derrotas: %d", dataSource[position].losses)
            rowView.position.text = String.format("#%d", dataSource[position].rankPosition)

            return rowView
        }
    }

    var viewScene: RankingDisplayLogic? = null

    override fun presentRanking(response: RankingModel.Response) {

        val rankingAdapter = RankingAdapter(response.context, response.players!!)

        val viewModel: RankingModel.ViewModel = RankingModel.ViewModel(rankingAdapter)

        viewScene?.displayRankInScreen(viewModel)
    }
}