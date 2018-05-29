package com.nexte.nexte.PlayersListScene


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.columns_challenged.view.*
import android.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.nexte.nexte.ChallengeTabsFragment
import com.nexte.nexte.MainActivity
import com.nexte.nexte.UserSingleton


/**
 * Interface to define Display Logic to ChallengeTabsFragment Class that will
 * receive information from Presenter
 */
interface ChallengeDisplayLogic {

    /**
     * Method that defines how the players above the logged user formatted data will be displayed
     *
     * @param viewModel contains information about the players to be shown
     */
    fun displayPlayersToChallenge (viewModel: PlayersListModel.ShowRankingPlayersRequest.ViewModel)

    /**
     * Method that defines how the player clicked by the user formatted data will be displayed
     *
     * @param viewModel contains information about the player to be shown
     */
    fun displayPlayerDetailedInfo (viewModel: PlayersListModel.SelectPlayerForChallengeRequest.ViewModel)

    /**
     * Method that defines how the message to the user will be displayed
     *
     * @param viewModel contains information about the message to be shown
     */
    fun displayMessage (viewModel: PlayersListModel.ChallengeButtonRequest.ViewModel)

    /**
     * Method to display message saying that there are no players avaliable
     *
     * @param messageText contains message informing that there are no players avaliable
     */
    fun displayNoPlayersMessage(messageText: String)
}


/**
 * Class that sets the tab fragment on the screen with the 'sent' and 'received' tabs
 */
class PlayersListFragment : Fragment(), ChallengeDisplayLogic {

    var fragment: Fragment?= null
    var sendChallengeButton: Button?= null
    var expandedLosses: TextView?= null
    var expandedWins: TextView?= null
    var expandedRankingTextView: TextView?= null
    var expandedName: TextView?= null
    var noPlayersText: TextView?= null
    var message: TextView?= null
    var interactor: ChallengeBusinessLogic?= null
    private var hasMatch: Boolean?= null
    private var recyclerView: RecyclerView?= null

    /**
     * Method that gets which tab is selected by the user
     */
    fun getInstance(hasMatch: Boolean) : PlayersListFragment {

        val bundle = Bundle()
        val fragment = PlayersListFragment()

        bundle.putBoolean("has match", hasMatch)
        fragment.arguments = bundle

        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.hasMatch = this.arguments.getBoolean("has match")
        this.setupPlayersListScene()
    }

    /**
     * Method responsible to populate the references of the scene
     */
    fun setupPlayersListScene() {

        val interactor = PlayersListInteractor()
        val presenter = PlayersListPresenter()
        val view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewChallenge = view
    }

    /**
     * Method that set the correct fragment layout according to the selected tab
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val newView = inflater?.inflate(R.layout.activity_challenger_sent, container, false)

        this.sendChallengeButton = newView?.findViewById(R.id.sendChallengeButton)
        this.recyclerView = newView?.findViewById(R.id.challengeRecyclerView)
        this.expandedLosses = newView?.findViewById(R.id.expandedLosses)
        this.expandedName = newView?.findViewById(R.id.expandedName)
        this.expandedRankingTextView = newView?.findViewById(R.id.expandedRankingTextView)
        this.expandedWins = newView?.findViewById(R.id.expandedWins)
        this.noPlayersText = newView?.findViewById(R.id.noPlayersText)
        this.message = newView?.findViewById(R.id.message)

            if(hasMatch!!) {
                this.message?.visibility = View.VISIBLE
            }

        return newView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        sendChallengeButton?.setOnClickListener {
            val request = PlayersListModel.ChallengeButtonRequest.Request(this.expandedName?.text.toString())
            this.interactor?.requestChallenger(request)
        }

        val request = PlayersListModel.ShowRankingPlayersRequest.Request(UserSingleton.getUserInformations().rankingPosition)
        this.interactor?.requestPlayersToChallenge(request)
    }

    /**
     * Function responsible to get the formatted player data and exhibit it in a recycler view
     * between an adapter
     *
     * @param viewModel Contains the formatted player info to be displayed in the recycler view
     */
    override fun displayPlayersToChallenge(viewModel: PlayersListModel.ShowRankingPlayersRequest.ViewModel) {

        this.recyclerView?.adapter = PlayersListAdapter(viewModel.formattedPlayer, this)
    }

    /**
     * Function responsible to receive the request from the recycler view item and send
     * to the interactor
     *
     * @param request contains the rank of the clicked user in the recycler view
     */
    fun getPlayerInfo(request: PlayersListModel.SelectPlayerForChallengeRequest.Request){

        this.interactor?.requestChallengedUser(request)
    }

    /**
     * Method responsible for showing the clicked player detailed info
     *
     * @param viewModel contains the player data already formatted by [PlayersListPresenter]
     */
    override fun displayPlayerDetailedInfo(viewModel: PlayersListModel.SelectPlayerForChallengeRequest.ViewModel) {

        val currentPlayer = viewModel.challengedRankingDetails

        this.expandedLosses?.visibility = View.VISIBLE
        this.expandedLosses?.text = currentPlayer.loses
        this.expandedName?.visibility = View.VISIBLE
        this.expandedName?.text = currentPlayer.name
        this.expandedRankingTextView?.visibility = View.VISIBLE
        this.expandedRankingTextView?.text = currentPlayer.rankingPosition
        this.expandedWins?.visibility = View.VISIBLE
        this.expandedWins?.text = currentPlayer.wins
    }

    /**
     * Method responsible for showing the a alert button with a message about the
     * challenge sended
     *
     * @param viewModel contains the player data already formatted by [PlayersListPresenter]
     */
    override fun displayMessage(viewModel: PlayersListModel.ChallengeButtonRequest.ViewModel) {

        val fragActivity = this.activity as MainActivity

        if(viewModel.matchMessage != ""){
            fragment = fragActivity.supportFragmentManager.findFragmentByTag("challenge")
            (fragment as? ChallengeTabsFragment)?.match = viewModel.matchData
            this.message?.text = viewModel.matchMessage
            this.message?.visibility = View.VISIBLE
            this.sendChallengeButton?.isEnabled = false
            (fragment as? ChallengeTabsFragment)?.viewpager?.adapter?.notifyDataSetChanged()
        }
        
        val builder = AlertDialog.Builder(fragActivity)

        builder.setCancelable(true)
        builder.setMessage(viewModel.messageForChallenger)
        builder.setPositiveButton("Ok", { dialogInterface, _ ->
            (fragment as? ChallengeTabsFragment)?.tabs?.getTabAt(1)?.select()
            dialogInterface.cancel()
        })

        val alert = builder.create()
        alert.show()
    }

    /**
     * Method to display message informing that there are no players avaliable to set a match
     */
    override fun displayNoPlayersMessage(messageText: String) {
        recyclerView?.visibility = View.INVISIBLE
        noPlayersText?.visibility = View.VISIBLE
        sendChallengeButton?.isEnabled = false
        sendChallengeButton?.visibility = View.INVISIBLE
        val builder = AlertDialog.Builder(this.activity)
        builder.setCancelable(true)
        builder.setMessage(messageText)
        builder.setPositiveButton("ok", { dialogInterface, _ ->
            dialogInterface.cancel()
        })
        val noPlayersMessage = builder.create()
        noPlayersMessage.show()
    }

    /**
     * Method responsible to remove the user informations
     */
    fun removePlayerDetailedInfo() {

        this.expandedLosses?.visibility = View.INVISIBLE
        this.expandedLosses?.text = ""
        this.expandedName?.visibility = View.INVISIBLE
        this.expandedName?.text = ""
        this.expandedRankingTextView?.visibility = View.INVISIBLE
        this.expandedRankingTextView?.text = ""
        this.expandedWins?.visibility = View.INVISIBLE
        this.expandedWins?.text = ""
    }

    /**
     * Adapter Class to control recycler view of users that can be challenged
     *
     * @property challenged List of the 5 players above the logged one
     * @property fragment Fragment that will show this adapter
     */
    class PlayersListAdapter(private var challenged: List<PlayersListModel.FormattedPlayer>,
                           private val fragment: Fragment) :
            RecyclerView.Adapter<PlayersListFragment.PlayersListAdapter.ViewHolder>() {

        private var expandedPlayer = -1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersListFragment.PlayersListAdapter.ViewHolder {

            val view = LayoutInflater.from(fragment.activity).inflate(R.layout.columns_challenged, parent, false)
            return PlayersListFragment.PlayersListAdapter.ViewHolder(view)
        }

        /**
         * Method that sets expanded player information on the screen when a player is selected
         * and enables a button to send a challenge to this  selected player
         */
        override fun onBindViewHolder(holder: PlayersListFragment.PlayersListAdapter.ViewHolder, position: Int) {

            (fragment as PlayersListFragment).sendChallengeButton?.isEnabled = true

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

                val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(
                        challenged[position].rankingPosition.removeRange(0, 1).toInt())
                fragment.getPlayerInfo(request)
            }
            val checkTextView: TextView = holder.view.findViewById(R.id.checkTextView)
            if (expandedPlayer == holder.layoutPosition) {
                checkTextView.visibility = View.VISIBLE
            } else {
                checkTextView.visibility = View.INVISIBLE
            }

            if(expandedPlayer == -1) {
                fragment.removePlayerDetailedInfo()
                fragment.sendChallengeButton?.isEnabled = false
            }
            if(fragment.hasMatch!!){
                fragment.sendChallengeButton?.isEnabled = false
            }


        }

        override fun getItemCount(): Int {

            return this.challenged.size
        }

        class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

            fun bindView(player: PlayersListModel.FormattedPlayer) {
                view.userName.text = player.name
                view.rankingTextView.text = player.rankingPosition
            }
        }
    }

}

