package com.nexte.nexte.RankingScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity(), RankingDisplayLogic {

    var interactor: RankingInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        setupScene()

        val request = RankingModel.Request(this)

        interactor?.getPlayersRanksForScene(request)

    }

    private fun setupScene(){
        val viewScene = this
        val interactor = RankingInteractor()
        val presenter = RankingPresenter()

        viewScene.interactor = interactor
        interactor.presenter = presenter
        presenter.viewScene = viewScene
    }

    override fun displayRankInScreen(viewModel: RankingModel.ViewModel) {
        ranking_list_view.adapter = viewModel.adapter
    }
}
