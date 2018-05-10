package com.nexte.nexte.ChallengeScene

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_challenger_sent.*
import kotlinx.android.synthetic.main.columns_challenged.view.*
import android.app.AlertDialog
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_challenger.*


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

    var recyclerView: RecyclerView?= null
    var sendChallengeButton: Button?= null
    var expandedLosses: TextView?= null
    var expandedWins: TextView?= null
    var expandedRankingTextView: TextView?= null
    var expandedName: TextView?= null
    var noPlayersText: TextView?= null
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
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewpager)
    }

    /**
     * Function responsible to get the formatted player data and exhibit it in a recycler view
     * between an adapter
     *
     * @param viewModel Contains the formatted player info to be displayed in the recycler view
     */
    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {

        this.recyclerView?.adapter = ChallengeAdapter(viewModel.formattedPlayer, this)
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
        recyclerView?.visibility = View.INVISIBLE
        noPlayersText?.visibility = View.VISIBLE
        sendChallengeButton?.isEnabled = false
        sendChallengeButton?.visibility = View.INVISIBLE
        val builder = AlertDialog.Builder(this)
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
     * Class that sets the tab fragment on the screen with the 'sent' and 'received' tabs
     */
    class TabFragment : Fragment() {

        var position = 0
        private var recyclerView: RecyclerView?= null
        var sendChallengeButton: Button?= null

        /**
         * Method that gets which tab is selected by the user
         */
        fun getInstance(position: Int) : TabFragment {

            val bundle = Bundle()
            val tabFragment = TabFragment()

            bundle.putInt("position", position)
            tabFragment.arguments = bundle
            return tabFragment
        }

        /**
         * Method that saves a position in a variable [position]
         */
        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            position = arguments.getInt("position")
        }

        /**
         * Method that set the correct fragment layout according to the selected tab
         */
        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val view: View?

            if(position == 0) {
                view = inflater?.inflate(R.layout.activity_challenger_sent, container, false)

                val viewContext = context as ChallengeView

                this.sendChallengeButton = view?.findViewById(R.id.sendChallengeButton)
                this.recyclerView = view?.findViewById(R.id.challengeRecyclerView)

                viewContext.recyclerView = view?.findViewById(R.id.challengeRecyclerView)
                viewContext.sendChallengeButton = this.sendChallengeButton
                viewContext.expandedLosses = view?.findViewById(R.id.expandedLosses)
                viewContext.expandedName = view?.findViewById(R.id.expandedName)
                viewContext.expandedRankingTextView = view?.findViewById(R.id.expandedRankingTextView)
                viewContext.expandedWins = view?.findViewById(R.id.expandedWins)
                viewContext.noPlayersText = view?.findViewById(R.id.noPlayersText)
            } else {
                view = inflater?.inflate(R.layout.activity_challenger_received, container,  false)
            }
            return view
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

            super.onViewCreated(view, savedInstanceState)

            if(position == 0) {

                sendChallengeButton?.setOnClickListener {
                    val request = ChallengeModel.ChallengeButtonRequest.Request(this.expandedName.text.toString())
                    (context as ChallengeView).interactor?.requestMessageForChallenger(request)
                }

                val request = ChallengeModel.ShowRankingPlayersRequest.Request(UserSingleton.getUserInformations().rankingPosition)
                (context as ChallengeView).interactor?.requestPlayersToChallenge(request)
            }

        }
    }

    /**
     * Adapter Class that populates the fragment
     */
    class ViewPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        private val pageTitles = listOf("Enviados", "Recebidos")

        override fun getCount(): Int {

            return pageTitles.size
        }

        override fun getItem(position: Int): Fragment {

            val tabFragment = TabFragment()

            return tabFragment.getInstance(position)
        }

        override fun getPageTitle(position: Int): CharSequence {

            return pageTitles.elementAt(position)
        }
    }

    /**
     * Adapter Class to control recycler view of users that can be challenged
     *
     * @property challenged List of the 5 players above the logged one
     * @property context Context that will show this adapter
     */
    class ChallengeAdapter(private var challenged: List<ChallengeModel.FormattedPlayer>,
                           private val context: Context) :
            RecyclerView.Adapter<ChallengeView.ChallengeAdapter.ViewHolder>() {

        private var expandedPlayer = -1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeView.ChallengeAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.columns_challenged, parent, false)
            return ChallengeView.ChallengeAdapter.ViewHolder(view)
        }

        /**
         * Method that sets expanded player information on the screen when a player is selected
         * and enables a button to send a challenge to this  selected player
         */
        override fun onBindViewHolder(holder: ChallengeView.ChallengeAdapter.ViewHolder, position: Int) {

            (context as ChallengeView).sendChallengeButton?.isEnabled = true

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
                        challenged[position].rankingPosition.removeRange(0, 1).toInt())
                context.getPlayerInfo(request)
            }

            if (expandedPlayer == holder.layoutPosition) {
                holder.view.checkTextView.visibility = View.VISIBLE
            } else {
                holder.view.checkTextView.visibility = View.INVISIBLE
            }

            if(expandedPlayer == -1) {
                context.removePlayerDetailedInfo()
                context.sendChallengeButton?.isEnabled = false
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