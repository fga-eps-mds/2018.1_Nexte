package com.nexte.nexte.ChallengeScene

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.R

/**
 * Interface to define Display Logic to ChallengeView Class that will
 * receive information from Presenter
 */
interface showPlayersToChallengeDisplayLogic {

    fun displayPlayersToChallenge (viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel)
}

class ChallengeView : AppCompatActivity(), showPlayersToChallengeDisplayLogic {

    val playerRanking = 8 //simulates the logged player ranking

    var getPlayersInteractor: requestPlayersToChallengeBusinessLogic?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenger)
        getPlayerToChallenge()
    }

    private fun getPlayerToChallenge(){
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(playerRanking)
        getPlayersInteractor?.requestPlayersToChallenge(request)
    }

    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {
        TODO("DEVE CRIAR UM ADAPTER QUE VAI UTILIZAR OS DADOS DA VIEWMODEL " +
                "DENTRO DO COLUMN_CHALLENGE XML E ADICIONAR AO RECYCLERVIEW")

        // HOW TO CREATE RECYCLERVIEW WITH CUSTOM XML IN KOTLIN?
        // adapter

    }
}
