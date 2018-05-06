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
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewpager)

        this.getPlayerToChallenge()
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

        ((this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0) as TabFragment?)?.setRecyclerViewAdapter(ChallengeAdapter(viewModel.formattedPlayer, this))

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

        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedLosses?.visibility = View.VISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedLosses?.text = currentPlayer.loses
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedName?.visibility = View.VISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedName?.text = currentPlayer.name
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedRankingTextView?.visibility = View.VISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedRankingTextView?.text = currentPlayer.rankingPosition
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedWins?.visibility = View.VISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedWins?.text = currentPlayer.wins
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
     * Method responsible to remove the user informations
     */
    fun removePlayerDetailedInfo() {

        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedLosses?.visibility = View.INVISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedLosses?.text = ""
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedName?.visibility = View.INVISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedName?.text = ""
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedRankingTextView?.visibility = View.INVISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedRankingTextView?.text = ""
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedWins?.visibility = View.INVISIBLE
        (this.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)?.expandedWins?.text = ""
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

    class TabFragment : Fragment() {

        var position = 0
        var recyclerView: RecyclerView?= null
        var sendChallengeButton: Button?= null

        fun getInstance(position: Int) : TabFragment {

            val bundle = Bundle()
            val tabFragment = TabFragment()

            bundle.putInt("position", position)
            tabFragment.arguments = bundle
            return tabFragment
        }

        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            position = arguments.getInt("position")
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            val view: View?
            if(position == 0) {
                view = inflater?.inflate(R.layout.activity_challenger_sent, container, false)
                recyclerView = view?.findViewById(R.id.recyclerView)
                sendChallengeButton = view?.findViewById(R.id.sendChallengeButton)
            } else {
                view = inflater?.inflate(R.layout.activity_challenger_received, container,  false)
            }
            return view
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            sendChallengeButton?.setOnClickListener {
                val request = ChallengeModel.ChallengeButtonRequest.Request(this.expandedName.text.toString())
                (context as ChallengeView).interactor?.requestMessageForChallenger(request)
            }
        }

        fun setRecyclerViewAdapter(adapter: ChallengeAdapter) {
            Log.d("adapterset", "to na adapterset")
            recyclerView?.adapter = adapter
        }
    }

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

        var expandedPlayer = -1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeView.ChallengeAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.columns_challenged, parent, false)
            return ChallengeView.ChallengeAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ChallengeView.ChallengeAdapter.ViewHolder, position: Int) {

            ((context as ChallengeView).viewpager.adapter as ViewPagerAdapter?)?.getItem(0)
                    ?.sendChallengeButton?.isEnabled = true
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
                (context.viewpager.adapter as ViewPagerAdapter?)?.getItem(0)
                        ?.sendChallengeButton?.isEnabled = false
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
