package com.nexte.nexte.HistoryScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity(), HistoryDisplayLogic {

    var historyInterator: HistoryInteractor?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupHistoryScene()

        val request = HistoryModel.Request("Helena", this)

        historyInterator?.getPlayerGames(request)
    }

    private fun setupHistoryScene(){
        val viewScene = this
        val interactor = HistoryInteractor()
        val presenter = HistoryPresenter()

        viewScene.historyInterator = interactor
        interactor.presenter = presenter
        presenter.viewScene = viewScene
    }

    override fun displayPlayerMatches(viewModel: HistoryModel.ViewModel) {
        historyListView.adapter = viewModel.adapter
    }
}
