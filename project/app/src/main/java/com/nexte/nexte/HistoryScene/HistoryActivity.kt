package com.nexte.nexte.HistoryScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryDisplayLogic {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
    }

    override fun displayPlayerMatches(viewModel: HistoryModel.ViewModel) {
        historyListView.adapter = viewModel.adapter
    }
}
