package com.nexte.nexte.FeedScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_history.*

/*
Class responsible to exhibit player history
 */
<<<<<<< HEAD:project/app/src/main/java/com/nexte/nexte/FeedScene/FeedActivity.kt
=======

interface HistoryDisplayLogic {
    fun displayPlayerMatches(viewModel: HistoryModel.ViewModel)
}

class FeedActivity : AppCompatActivity(), HistoryDisplayLogic {
>>>>>>> 637ee999a9228b18d1117ab4775be4389e8f731f:project/app/src/main/java/com/nexte/nexte/FeedScene/FeedActivity.kt

interface FeedDisplayLogic {
    fun displayFeed(viewModel: HistoryModel.ViewModel)
}

class FeedActivity : AppCompatActivity(), FeedDisplayLogic {

    var interactor: FeedInteractor?= null // reference to the interactor

    /*
    This is the function called when the activity is created, and it is responsible to initiate the request
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupFeedScene()

        val request = HistoryModel.Request("Helena", this)
        interactor?.fetchFeed(request)
    }

    /*
    This method is responsible to set up all the references of this scene
     */
    private fun setupFeedScene() {

        val view = this
        val interactor = FeedInteractor()
        val presenter = FeedPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewScene = view
    }

    /*
    Method responsible to receive the viewModel from presenter and show it to the user
    */
    override fun displayFeed(viewModel: HistoryModel.ViewModel) {
        historyListView.adapter = viewModel.adapter
    }
}
