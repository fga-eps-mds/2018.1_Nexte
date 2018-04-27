package com.nexte.nexte.ChallengeScene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nexte.nexte.FeedScene.FeedModel
import com.nexte.nexte.FeedScene.FeedView
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

    var challengeAdapter: ChallengeAdapter? = null
    var interactor: ChallengeInteractor? = null

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

        this.challengeAdapter = ChallengeAdapter(mutableListOf(), this)
        challengeRecyclerView.adapter = this.challengeViewAdapter
        challengeRecyclerView.layoutManager = LinearLayoutManager(this)

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

        challengeAdapter?.updateActivities(viewModel.formattedPlayer)

        /**
         * Adapter Class to control recycler view of users that can be challenged
         *
         * @property activities List of all feed activities
         * @property context Context that will show this adapter
         */
        class ChallengeAdapter(private var challenged: MutableList<ChallengeModel.FormattedPlayer>,
                          private val context: Context) :
                            RecyclerView.Adapter<ChallengeView.ChallengeAdapter.ViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeView.ChallengeAdapter.ViewHolder {

                val view = LayoutInflater.from(context).inflate(R.layout.columns_challenged, parent,false)
                return ChallengeView.ChallengeAdapter.ViewHolder(view)
            }

            override fun onBindViewHolder(holder: ChallengeView.ChallengeAdapter.ViewHolder, position: Int) {

                holder.bindView(challenged[position], context)
            }

            override fun getItemCount(): Int {

                return this.challenged.size
            }
    }
}
