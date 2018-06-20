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
import android.graphics.Color
import com.nexte.nexte.Entities.User.UserManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.nexte.nexte.ChallengeTabsFragment
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.MainActivity
import com.nexte.nexte.RankingScene.RankingFragment
import com.nexte.nexte.ShowProfileScene.ShowProfileFragment
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_challenger_sent.*
import kotlinx.android.synthetic.main.row_ranking.view.*


/**
 * Interface to define Display Logic to ChallengeTabsFragment Class that will
 * receive information from Presenter
 */
interface PlayersListDisplayLogic {

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
 * @property userManager is the [UserManager] object that will be passed to the worker
 * @property fragment is the parent fragment instance ([MatchFragment]) that will be used to update tabs when user send challenge
 * @property sendChallengeButton is the instance of the button responsible to send the challenge
 * @property expandedLosses is the instance of field that will hold player losses when expanded view is visible
 * @property expandedWins is the instance of field that will hold player wins when expanded view is visible
 * @property expandedRankingTextView is the instance of field that will hold player ranking position when expanded view is visible]
 * @property expandedName is the instance of field that will hold player username when expanded view is visible
 * @property noPlayersText instance of text view that will be shown when user has no player to challenge
 * @property message instance of text view that will be shown when player already challenged someone
 * @property interactor holds an instance of interector that will be called to control view actions
 * @property hasMatch is tha variable that will control when the message will be shown
 * @property recyclerView is an instance of the recyclerview that is holding players avaliable for challenge
 */
class PlayersListFragment : Fragment(), PlayersListDisplayLogic {

    var userManager: UserManager? = null
    var fragment: Fragment?= null
    var sendChallengeButton: Button?= null
    var expandedLosses: TextView?= null
    var expandedWins: TextView?= null
    var expandedRankingTextView: TextView?= null
    var expandedName: TextView?= null
    //var imageExpanded: ImageView?= null
    var noPlayersText: TextView?= null
    var message: TextView?= null
    var interactor: ChallengeBusinessLogic?= null
    var expandedExploitation: TextView? = null
    var expandedLastestGame: TextView? = null
    var expandedCategory: TextView? = null
    var backgroundExpanded: LinearLayout? = null
    var circulo1: ImageView? = null
    var circulo2: ImageView? = null
    var circulo3: ImageView? = null
    var circulo4: ImageView? = null
    var circulo5: ImageView? = null
    var expanded_perfil: Button? = null


    private var hasMatch: Boolean?= null
    private var recyclerView: RecyclerView?= null

    var selectedUserIdentifier: String? = null

    /**
     * Companion Object responsible to create an instance of this fragment
     */
    companion object {

        /**
         * Method that gets which tab is selected by the user
         *
         * @param hasMatch defines if the challenge button will be enabled (if there isn't any match) or disabled (if there is)
         */
        fun getInstance(hasMatch: Boolean) : PlayersListFragment {

            val bundle = Bundle()
            val fragment = PlayersListFragment()

            bundle.putBoolean("has match", hasMatch)
            fragment.arguments = bundle

            return fragment
        }
    }

    /**
     * Method responsible to get the bundle arguments and transfer it to the class, and also call the set up function that will
     * load the instances required by [Interactor], [Presenter] and [Worker]
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.hasMatch = this.arguments.getBoolean("has match")
        userManager = UserManager()
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
        interactor.worker.userManager = userManager!!
        interactor.worker.updateLogic = interactor
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
        this.expandedWins = newView?.findViewById(R.id.expandedWins)
        this.noPlayersText = newView?.findViewById(R.id.noPlayersText)
        this.message = newView?.findViewById(R.id.message)
        this.expandedExploitation = newView?.findViewById(R.id.expandedExploitation)
        this.expandedLastestGame = newView?.findViewById(R.id.expandedLastestGame)
        this.expandedCategory = newView?.findViewById(R.id.expandedCategory)
        this.backgroundExpanded = newView?.findViewById(R.id.backgroundExpanded)
        this.expanded_perfil = newView?.findViewById(R.id.expanded_perfil)
        this.circulo1 = newView?.findViewById(R.id.circulo1)
        this.circulo2 = newView?.findViewById(R.id.circulo2)
        this.circulo3 = newView?.findViewById(R.id.circulo3)
        this.circulo4 = newView?.findViewById(R.id.circulo4)
        this.circulo5 = newView?.findViewById(R.id.circulo5)


            if(hasMatch!!) {
                this.message?.visibility = View.VISIBLE
            }

        return newView
    }

    /**
     * Method responsible to set action listeners
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        sendChallengeButton?.setOnClickListener {
            this.selectedUserIdentifier?.let {
                val request = PlayersListModel.ChallengeButtonRequest.Request(it)
                this.interactor?.requestChallenger(request)
            }
        }

        val request = PlayersListModel.ShowRankingPlayersRequest.Request(UserSingleton.loggedUser.rankingPosition)
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
    fun getPlayerInfo(selectedUserIdentifier: String, request: PlayersListModel.SelectPlayerForChallengeRequest.Request){

        this.selectedUserIdentifier = selectedUserIdentifier
        this.interactor?.requestChallengedUser(request)
    }

    private fun goToShowProfileView(name: String?) {
        val fragment = ShowProfileFragment().getInstance(name)
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).addToBackStack(null).commit()
    }

    /**
     * Method responsible for showing the clicked player detailed info
     *
     * @param viewModel contains the player data already formatted by [PlayersListPresenter]
     */
    override fun displayPlayerDetailedInfo(viewModel: PlayersListModel.SelectPlayerForChallengeRequest.ViewModel) {

        val currentPlayer = viewModel.challengedRankingDetails

        this.expandedLosses?.visibility = View.VISIBLE
        this.expandedName?.visibility = View.VISIBLE
        this.expandedName?.text = currentPlayer.name
        this.expandedRankingTextView?.visibility = View.VISIBLE
        this.expandedRankingTextView?.text = currentPlayer.rankingPosition
        this.expandedWins?.visibility = View.VISIBLE
        this.expandedWins?.text = currentPlayer.wins
        this.expandedLastestGame?.text = currentPlayer.latestGame
        this.expandedLastestGame?.visibility = View.VISIBLE
        this.expandedExploitation?.text = currentPlayer.efficiency
        this.expandedExploitation?.visibility = View.VISIBLE
        this.expandedCategory?.visibility = View.VISIBLE
        this.expandedCategory?.text = currentPlayer.category
        this.backgroundExpanded?.visibility = View.VISIBLE
        this.expanded_perfil?.visibility = View.VISIBLE
        this.expanded_perfil?.setOnClickListener{
            val fragment = ShowProfileFragment().getInstance(currentPlayer.id)
            fragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).addToBackStack(null).commit()
        }
        this.circulo1?.visibility = View.VISIBLE
        this.circulo2?.visibility = View.VISIBLE
        this.circulo3?.visibility = View.VISIBLE
        this.circulo4?.visibility = View.VISIBLE
        this.circulo5?.visibility = View.VISIBLE

        setUserGameCircle(this?.circulo1, currentPlayer.userLastGames[0])
        setUserGameCircle(this?.circulo2, currentPlayer.userLastGames[1])
        setUserGameCircle(this?.circulo3, currentPlayer.userLastGames[2])
        setUserGameCircle(this?.circulo4, currentPlayer.userLastGames[3])
        setUserGameCircle(this?.circulo5, currentPlayer.userLastGames[4])



    }

    /**
     * Method responsible for setting the apropriate resource to the plaeyr game circle
     *
     * @param imageView imageView that will display the player result
     * @param lastGame data that will indicate the player game status
     */
    fun setUserGameCircle(imageView: ImageView?, lastGame: Int) {
        val circleResource = if (lastGame == Color.GREEN) {
            R.drawable.circle_victory_ranking
        } else if (lastGame == Color.GRAY) {
            R.drawable.circle_empty_ranking
        } else if (lastGame == Color.RED) {
            R.drawable.circle_defeat_ranking
        } else {
            R.drawable.circle_undefeated_ranking
        }

        imageView?.setBackgroundResource(circleResource)
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
     *
     * @param messageText Text that will be displaed on alertDialog
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

        this.selectedUserIdentifier = null
        this.expandedLosses?.visibility = View.INVISIBLE
        this.expandedLosses?.text = ""
        this.expandedName?.visibility = View.INVISIBLE
        this.expandedName?.text = ""
        this.expandedRankingTextView?.visibility = View.INVISIBLE
        this.expandedRankingTextView?.text = ""
        this.expandedWins?.visibility = View.INVISIBLE
        this.expandedWins?.text = ""
        this.expandedLastestGame?.text = ""
        this.expandedLastestGame?.visibility = View.INVISIBLE
        this.expandedExploitation?.text = ""
        this.expandedExploitation?.visibility = View.INVISIBLE
        this.expandedCategory?.text = ""
        this.expandedCategory?.visibility = View.INVISIBLE
        this.backgroundExpanded?.visibility = View.INVISIBLE
        this.expanded_perfil?.visibility = View.INVISIBLE
        this.circulo1?.visibility = View.INVISIBLE
        this.circulo2?.visibility = View.INVISIBLE
        this.circulo3?.visibility = View.INVISIBLE
        this.circulo4?.visibility = View.INVISIBLE
        this.circulo5?.visibility = View.INVISIBLE
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
                fragment.getPlayerInfo(challenged[position].identifier, request)
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

