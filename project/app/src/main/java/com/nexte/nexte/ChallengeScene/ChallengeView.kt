package com.nexte.nexte.ChallengeScene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_challenger.*

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
    TODO("CRIAR FUNÇÃO PARA EXIBIR OS DADOS DO JOGADOR EXPANDIDO")
}

/**
 * This class is responsible for treating user actions and also showing user needed information.
 */
class ChallengeView : AppCompatActivity(), ShowPlayersToChallengeDisplayLogic {

    var interactor: ChallengeInteractor? = null

    /**
     * Adapter Class to control recycler view of users that can be challenged
     *
     * @property challenged List of the 5 players above the logged one
     * @property context Context that will show this adapter
     */
    private class ChallengeAdapter(private var challenged: List<ChallengeModel.FormattedPlayer>,
                           private val context: Context) :
            RecyclerView.Adapter<ChallengeView.ChallengeAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeView.ChallengeAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.columns_challenged, parent, false)
            return ChallengeView.ChallengeAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChallengeView.ChallengeAdapter.ViewHolder, position: Int) {

            holder.bindView(challenged[position], context)
        }

        override fun getItemCount(): Int {

            return this.challenged.size
        }

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

            fun bindView(player: ChallengeModel.FormattedPlayer, context: Context) {
                TODO("BINDAR INFORMAÇÕES DO PLAYETR NA VIEW")
                // ex: view.nome.text = player.nome
                //context as ChallangeView -> getPlayerInfo(request) -> quando o caboclo clicar na foto
            }

        }
    }

    /**
     * This object is used for avoid magic numbers
     */
    companion object {
        const val playerRanking = 8 //simulates the logged player ranking
    }

    /**
     * This variable is responsible to call the interactor method to deal with the request
     */
    private var getPlayersInteractor: RequestPlayersToChallengeBusinessLogic?= null

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
     * 5 players above the ranking defined in the logged player
     */
    private fun getPlayerToChallenge(){
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(playerRanking)
        getPlayersInteractor?.requestPlayersToChallenge(request)
    }

    /**
     * Function responsible to get the formatted player data and exhibit it in a recycler view between an adapter .
     */
    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {
        this.recyclerView.adapter = ChallengeAdapter(viewModel.formattedPlayer, this)
    }

    /**
     * Function responsible to receive the request from the recycler view item and send to the interactor
     */
    fun getPlayerInfo(request: ChallengeModel.SelectPlayerForChallengeRequest.Request){
        TODO("ENVIAR A REQUEST DO PARÂMETRO PARA A INTERACTOR")
    }
}
