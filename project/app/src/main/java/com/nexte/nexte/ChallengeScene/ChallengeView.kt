package com.nexte.nexte.ChallengeScene

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.R

/**
 * Interface to define Display Logic to ChallengeView Class that will
 * receive information from Presenter
 */
interface ShowPlayersToChallengeDisplayLogic {

    /**
     * Method that defines how the player formatted data will be displayed
     *
     * @param viewModel contains information about the players to be shown formatted
     */
    fun displayPlayersToChallenge (viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel)
}

/**
 * This class is responsible for treating user actions and also showing user needed information.
 */
class ChallengeView : AppCompatActivity(), ShowPlayersToChallengeDisplayLogic {

    /**
     * This object is used for avoid magic numbers
     */
    companion object {
        const val playerRanking = 8 //simulates the logged player ranking
    }

    /**
     * This variable is responsible to call the interactor method to deal with the request
     */
    var getPlayersInteractor: RequestPlayersToChallengeBusinessLogic?= null

    /**
     * Method called whenever the view is created, responsible for create first request and set listeners.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenger)
        getPlayerToChallenge()
    }

    /**
     * This method is responsible for calling the request for the
     * 5 players above the ranking defined in the object
     */
    private fun getPlayerToChallenge(){
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(playerRanking)
        getPlayersInteractor?.requestPlayersToChallenge(request)
    }

    /**
     * Function responsible to get the formatted player data and exhibit it in a recycler view.
     */
    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {
        TODO("DEVE CRIAR UM ADAPTER QUE VAI UTILIZAR OS DADOS DA VIEWMODEL " +
                "DENTRO DO COLUMN_CHALLENGE XML E ADICIONAR AO RECYCLERVIEW")

        // HOW TO CREATE RECYCLERVIEW WITH CUSTOM XML IN KOTLIN? R: Adapter.

    }
}
