package com.nexte.nexte.HistoryScene

import com.nexte.nexte.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_history.view.*

/**
 * Created by helena on 03/04/18.
 */

interface HistoryPresentationLogic {
    fun formatPlayerMatches(response: HistoryModel.Response)
}

class HistoryPresenter : HistoryPresentationLogic {

    var viewScene: HistoryDisplayLogic? = null

    override fun formatPlayerMatches(response: HistoryModel.Response) {
        val adapter = HistoryAdapter(response.match, response.context)
        var viewModel: HistoryModel.ViewModel = HistoryModel.ViewModel(adapter)
        viewScene?.displayPlayerMatches(viewModel)
    }


    class HistoryAdapter: BaseAdapter {

        var context: Context
        var playerMatches: Array<HistoryModel.Match>

        constructor (playerMatches: Array<HistoryModel.Match>, context: Context) : super() {
            this.playerMatches = playerMatches
            this.context = context
        }

        override fun getCount(): Int {
            return playerMatches.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(context)
            var currentRow = layoutInflater.inflate(R.layout.row_history, viewGroup, false)

            currentRow.firstPlayerName.text = playerMatches[position].players[0].playerName
            currentRow.secondPlayerName.text = playerMatches[position].players[1].playerName

            currentRow.firstPlayerScore.text = String.format("%d",playerMatches[position].players[0].score)
            currentRow.secondPlayerScore.text = String.format("%d",playerMatches[position].players[1].score)

            currentRow.firstPlayerRank.text = String.format("#%d", playerMatches[position].players[0].rank)
            currentRow.secondPlayerRank.text = String.format("#%d", playerMatches[position].players[1].rank)

            return currentRow
        }

        override fun getItem(position: Int): Any {
            return playerMatches[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}