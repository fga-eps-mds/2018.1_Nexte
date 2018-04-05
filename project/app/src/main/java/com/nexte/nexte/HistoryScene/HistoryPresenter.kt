package com.nexte.nexte.HistoryScene

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter

/**
 * Created by helena on 03/04/18.
 */

interface HistoryPresentationLogic {
    fun formatPlayerMatches(response: HistoryModel.Response)
}

class HistoryPresenter : HistoryPresentationLogic {

    var viewScene: HistoryDisplayLogic? = null

    override fun formatPlayerMatches(response: HistoryModel.Response) {
        val adapter = HistoryAdapter(response.match)
        var viewModel: HistoryModel.ViewModel = HistoryModel.ViewModel(adapter)
        viewScene?.displayPlayerMatches(viewModel)
    }

    class HistoryAdapter(playerMatches: Array<HistoryModel.Match>) : BaseAdapter() {

        var playerMatches: Array<HistoryModel.Match>

        init {
            this.playerMatches = playerMatches
        }

        override fun getCount(): Int {
            return playerMatches.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        }

        override fun getItem(position: Int): Any {
            return playerMatches[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}