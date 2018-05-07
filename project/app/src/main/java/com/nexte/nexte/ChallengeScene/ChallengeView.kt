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
import kotlinx.android.synthetic.main.columns_challenged.view.*
import android.app.AlertDialog


/**
 * Interface to define Display Logic to ChallengeView Class that will
 * receive information from Presenter
 */
interface ChallengeDisplayLogic {

    /**
     * Method that defines how the players above the logged user formatted data will be displayed
     *
     * @param viewModel contains information about the players to be shown
     */
    fun displayPlayersToChallenge (viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel)

    /**
     * Method that defines how the player clicked by the user formatted data will be displayed
     *
     * @param viewModel contains information about the player to be shown
     */
    fun displayPlayerDetailedInfo (viewModel: ChallengeModel.SelectPlayerForChallengeRequest.ViewModel)

    /**
     * Method that defines how the message to the user will be displayed
     *
     * @param viewModel contains information about the message to be shown
     */
    fun displayMessage (viewModel: ChallengeModel.ChallengeButtonRequest.ViewModel)

    /**
     * Method to display message saying that there are no players avaliable
     *
     * @param messageText contains message informing that there are no players avaliable
     */
    fun displayNoPlayersMessage(messageText: String)
}

/**
 * This class is responsible for treating user actions and also showing user needed information.
 *
 * @property interactor
 * @property context
 */
class ChallengeView : AppCompatActivity(), ChallengeDisplayLogic {

    var interactor: ChallengeBusinessLogic? = null
    private val context: Context? = null

    /**
     * This object is used for avoid magic numbers
     */
    companion object {

        const val playerRanking = 8 //simulates the logged player ranking
    }

    /**
     * Method called whenever the view is created, responsible for create first
     * request and set listeners
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_challenger)
        this.setupChallengeScene()
        this.getPlayerToChallenge()

        sendChallengeButton.setOnClickListener {

            val request = ChallengeModel.ChallengeButtonRequest.Request(this.expandedName.text.toString())
            interactor?.requestMessageForChallenger(request)
        }
    }

    /**
     * This method is responsible for calling the request for the
     * 5 players above the ranking defined in the logged player
     */
    fun getPlayerToChallenge() {

        val request = ChallengeModel.ShowRankingPlayersRequest.Request(playerRanking)
        interactor?.requestPlayersToChallenge(request)
    }

    /**
     * Function responsible to get the formatted player data and exhibit it in a recycler view
     * between an adapter
     *
     * @param viewModel Contains the formatted player info to be displayed in the recycler view
     */
    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {

        this.recyclerView.adapter = ChallengeAdapter(viewModel.formattedPlayer, this, this)
    }

    /**
     * Function responsible to receive the request from the recycler view item and send
     * to the interactor
     *
     * @param request contains the rank of the clicked user in the recycler view
     */
    fun getPlayerInfo(request: ChallengeModel.SelectPlayerForChallengeRequest.Request){

        this.interactor?.requestChallengedUser(request)
    }

    /**
     * Method responsible for showing the clicked player detailed info
     *
     * @param viewModel contains the player data already formatted by [ChallengePresenter]
     */
    override fun displayPlayerDetailedInfo(viewModel: ChallengeModel.SelectPlayerForChallengeRequest.ViewModel) {

        val currentPlayer = viewModel.challengedRankingDetails

        this.expandedLosses.visibility = View.VISIBLE
        this.expandedLosses.text = currentPlayer.loses
        this.expandedName.visibility = View.VISIBLE
        this.expandedName.text = currentPlayer.name
        this.expandedRankingTextView.visibility = View.VISIBLE
        this.expandedRankingTextView.text = currentPlayer.rankingPosition
        this.expandedWins.visibility = View.VISIBLE
        this.expandedWins.text = currentPlayer.wins
    }

    /**
     * Method responsible for showing the a alert button with a message about the
     * challenge sended
     *
     * @param viewModel contains the player data already formatted by [ChallengePresenter]
     */
    override fun displayMessage(viewModel: ChallengeModel.ChallengeButtonRequest.ViewModel) {

        val builder = AlertDialog.Builder(this)

        builder.setCancelable(true)
        builder.setMessage(viewModel.messageForChallenger)
        builder.setPositiveButton("Ok", { dialogInterface, _ ->
            dialogInterface.cancel()
        })

        val alert = builder.create()
        alert.show()
    }

    /**
     * Method to display message informing that there are no players avaliable to set a match
     */
    override fun displayNoPlayersMessage(messageText: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setMessage(messageText)
        
        builder.setPositiveButton("ok", { dialogInterface, i ->
            dialogInterface.cancel()
        })
        val noPlayersMessage = builder.create()
        noPlayersMessage.show()
    }

    /**
     * Method responsible to remove the user informations
     */
    fun removePlayerDetailedInfo() {

        this.expandedLosses.visibility = View.INVISIBLE
        this.expandedLosses.text = ""
        this.expandedName.visibility = View.INVISIBLE
        this.expandedName.text = ""
        this.expandedRankingTextView.visibility = View.INVISIBLE
        this.expandedRankingTextView.text = ""
        this.expandedWins.visibility = View.INVISIBLE
        this.expandedWins.text = ""
    }

    /**
     * Method responsible to populate the references of the scene
     */
    fun setupChallengeScene() {

        val interactor = ChallengeInteractor()
        val presenter = ChallengePresenter()
        val view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewChallenge = view
    }

    /**
     * Adapter Class to control recycler view of users that can be challenged
     *
     * @property challenged List of the 5 players above the logged one
     * @property context Context that will show this adapter
     */
    private class ChallengeAdapter(private var challenged: List<ChallengeModel.FormattedPlayer>,
                                   private val context: Context,
                                   var view: ChallengeView) :
            RecyclerView.Adapter<ChallengeView.ChallengeAdapter.ViewHolder>() {

        var expandedPlayer = -1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeView.ChallengeAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.columns_challenged, parent, false)
            return ChallengeView.ChallengeAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChallengeView.ChallengeAdapter.ViewHolder, position: Int) {

            view.sendChallengeButton.isEnabled = true
            holder.bindView(challenged[position])
            holder.view.userPicture.setOnClickListener {
                if (expandedPlayer >= 0) {
                    notifyItemChanged(expandedPlayer)
                }

                val shouldDrawChild = expandedPlayer != holder.layoutPosition

                expandedPlayer = if (shouldDrawChild) {
                    holder.layoutPosition
                } else {
                    -1
                }
                notifyItemChanged(expandedPlayer)

                val request = ChallengeModel.SelectPlayerForChallengeRequest.Request(
                        challenged[position].rankingPosition.removeRange(0, 1).toInt()
                )
                (context as ChallengeView).getPlayerInfo(request)
            }

            if (expandedPlayer == holder.layoutPosition) {
                holder.view.checkTextView.visibility = View.VISIBLE
            } else {
                holder.view.checkTextView.visibility = View.INVISIBLE
            }

            if(expandedPlayer == -1) {
                (context as ChallengeView).removePlayerDetailedInfo()
                view.sendChallengeButton.isEnabled = false
            }
        }

        override fun getItemCount(): Int {

            return this.challenged.size
        }

        class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

            fun bindView(player: ChallengeModel.FormattedPlayer) {
                view.userName.text = player.name
                view.rankingTextView.text = player.rankingPosition
            }
        }
    }
}