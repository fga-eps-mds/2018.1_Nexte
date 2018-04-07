package com.nexte.nexte.FeedScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_history.*

/*
Class responsible to exhibit player history
 */

interface HistoryDisplayLogic {
    fun displayPlayerMatches(viewModel: HistoryModel.ViewModel)
}

class FeedActivity : AppCompatActivity(), HistoryDisplayLogic {

    var historyInteractor: HistoryInteractor?= null // reference to the interactor

    /*
    This is the function called when the activity is created, and it is responsible to initiate the request
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupHistoryScene()

        val request = HistoryModel.Request("Helena", this)

        historyInteractor?.getPlayerGames(request)
    }

    /*
    This method is responsible to set up all the references of this scene
     */
    private fun setupHistoryScene(){
        val viewScene = this
        val interactor = HistoryInteractor()
        val presenter = HistoryPresenter()

        viewScene.historyInteractor = interactor
        interactor.presenter = presenter
        presenter.viewScene = viewScene
    }

    /*
    Method responsible to receive the viewModel from presenter and show it to the user
    */
    override fun displayPlayerMatches(viewModel: HistoryModel.ViewModel) {
        historyListView.adapter = viewModel.adapter
    }
}
