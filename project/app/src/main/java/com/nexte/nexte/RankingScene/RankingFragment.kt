package com.nexte.nexte.RankingScene

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.ContentFrameLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import com.nexte.nexte.ShowProfileScene.ShowProfileFragment
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.row_ranking.view.*
import android.support.v7.widget.DividerItemDecoration
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.UserManager

/**
 * Interface responsible to define methods used to get user information data
 * from presenter and display it
 */
interface RankingDisplayLogic {

    fun displayRankingInScreen(viewModel: RankingModel.ViewModel)
}

/**
 * Class that implements [RankingDisplayLogic]
 */
class RankingFragment : Fragment(), RankingDisplayLogic {

    var interactor: RankingInteractor? = null //interactor responsible to receive request and send it to worker
    var rankingRecyclerView: RecyclerView?= null
    var fixedFragment: ContentFrameLayout?= null
    var rankingConstraintLayout: ConstraintLayout?= null
    var userManager: UserManager? = null
    var challengeManager: ChallengeManager?= null

    fun getInstance() : RankingFragment{
        return RankingFragment()
    }

    /**
     * Method called on scene creation
     *
     * @param savedInstanceState
     */

    override fun onCreate(savedInstanceState: Bundle?) {

        userManager = UserManager()
        challengeManager = ChallengeManager()

        super.onCreate(savedInstanceState)
        this.setupRankingScene()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(R.layout.activity_ranking, container, false)

        fixedFragment = view?.findViewById(R.id.fixedFragment)
        rankingRecyclerView = view?.findViewById(R.id.rankingRecyclerView)
        rankingConstraintLayout = view?.findViewById(R.id.rankingConstraintLayout)
        rankingRecyclerView?.layoutManager = LinearLayoutManager(this.activity)
        val dividerItemDecoration = DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL)
        rankingRecyclerView?.addItemDecoration(dividerItemDecoration)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fixedFragment, FixedRowRankingFragment())
        fragmentTransaction.commit()

        this.createGetPlayersRequest()

        this.rankingRecyclerView?.addOnScrollListener(OnScrollRankingRecyclerView(
                UserSingleton.getUserInformations().rankingPosition, this))

        setFixedRanking(this, this.rankingRecyclerView, UserSingleton.getUserInformations().rankingPosition)

        return view
    }

    private fun goToShowProfileView(name: String?) {
        val fragment = ShowProfileFragment().getInstance(name)
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit()
    }


    /**
     * Class responsible for generate the fragment of the fixed row ranking
     */
    class FixedRowRankingFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {

            return inflater.inflate(R.layout.row_ranking, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

            view?.position?.text = String.format("%d", UserSingleton.getUserInformations().rankingPosition)
            view?.name?.text = UserSingleton.getUserInformations().name
            view?.playerCategory?.text = UserSingleton.getUserInformations().category
            view?.rowRankingLayout?.background = ColorDrawable(Color.GRAY)
        }
    }

    /**
     * Function responsible to define when or where the fixed ranking row should appear
     *
     * @param fragment indicates the fragment that this fragment is contained in
     * @param recyclerView indicates the recycler view that will be used to control how the fixed row will be displayed
     * @param playerRanking Indicates the player position that will be shown on screen, and it is used for comparision.
     */
    private fun setFixedRanking(fragment: Fragment, recyclerView: RecyclerView?, playerRanking: Int) {

        val constraintSet = ConstraintSet()
        val rankingView = fragment as RankingFragment
        val layoutManager = recyclerView?.layoutManager as LinearLayoutManager

        if(layoutManager.findFirstCompletelyVisibleItemPosition() <= playerRanking - 1
                && playerRanking - 1 <= layoutManager.findLastCompletelyVisibleItemPosition()) {

            rankingView.fixedFragment?.visibility = View.INVISIBLE
        }

        else if (playerRanking - 1 > layoutManager.findLastCompletelyVisibleItemPosition()) {

            rankingView.fixedFragment?.visibility = View.VISIBLE
            constraintSet.clone(rankingView.rankingConstraintLayout)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.BOTTOM)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.TOP)
            constraintSet.connect(R.id.fixedFragment, ConstraintSet.BOTTOM, R.id.rankingConstraintLayout, ConstraintSet.BOTTOM)
            constraintSet.applyTo(rankingView.rankingConstraintLayout)
        }

        else if (playerRanking - 1 < layoutManager.findFirstCompletelyVisibleItemPosition()){

            rankingView.fixedFragment?.visibility = View.VISIBLE
            constraintSet.clone(rankingView.rankingConstraintLayout)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.BOTTOM)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.TOP)
            constraintSet.connect(R.id.fixedFragment, ConstraintSet.TOP, R.id.rankingConstraintLayout, ConstraintSet.TOP)
            constraintSet.applyTo(rankingView.rankingConstraintLayout)
        }
    }

    /**
     * Class responsible to control recycler fragment scrolling
     *
     * @param playerRanking indicates the position of the logged user
<<<<<<< HEAD:project/app/src/main/java/com/nexte/nexte/RankingScene/RankingView.kt
     * @param context indicates the context that the recycler fragment is inserted in
=======
     * @param fragment indicates the fragment that the recycler view is inserted in
>>>>>>> 436a600ad1d3c7d13e15052cb43dcd9891439e3f:project/app/src/main/java/com/nexte/nexte/RankingScene/RankingFragment.kt
     */
    private class OnScrollRankingRecyclerView(val playerRanking: Int, val fragment: Fragment) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

            (fragment as RankingFragment).setFixedRanking(fragment, recyclerView, playerRanking)
        }
    }

    /**
     * Method responsible for creating get players request and passing it to the interactor
     */
    fun createGetPlayersRequest(){
        val request = RankingModel.Request()
        interactor?.getPlayersRanksForScene(request)
    }

    /**
     * Method responsible to set the ranking information on screen
     */
    fun setupRankingScene(){

        val view = this
        val interactor = RankingInteractor()
        val presenter = RankingPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        interactor.worker.userManager = userManager
        presenter.challengeManager = challengeManager
        interactor.worker.updateLogic = interactor
        presenter.viewScene = view
    }

    /**
     * Method responsible to get user information and display it
     *
     * @param viewModel contains formatted player data
     */
    override fun displayRankingInScreen(viewModel: RankingModel.ViewModel) {

        rankingRecyclerView?.adapter = RankingAdapter(viewModel.formattedPlayers, this)
    }

    /**
     * Class responsible to expand user information on click
     *
     * @property playerInformation List of formatted information
     * @property fragment Fragment that will show this adapter
     */
    class RankingAdapter(private val playerInformation: List<RankingModel.FormattedPlayerInfo>,
                         private val fragment: Fragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var expandedId = -1

        /**
         * Method called on fragment holder creation
         *
         * @param parent cell not expanded
         * @param viewType condition to know which layout will be used
         * (parent or child, expanded or not)
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val inflater = LayoutInflater.from(fragment.activity)
            val view: View?

            view = inflater.inflate(R.layout.row_ranking, parent, false)

            return ItemHolder(view!!)
        }


        /**
         * Method responsible to set the fragment holder and expand player cell
         *
         * @param holder reuses the same cell to display another player information
         */
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

            val itemHolder = holder as? ItemHolder
            val item = playerInformation[position]

            itemHolder?.itemView?.setOnClickListener {
                if(expandedId >= 0) {
                    notifyItemChanged(expandedId)
                }

                item.shouldDrawChild = expandedId != itemHolder.layoutPosition

                expandedId = if(item.shouldDrawChild) {
                    itemHolder.layoutPosition
                } else {
                    -1
                }
                notifyItemChanged(expandedId)
            }

            if(itemHolder?.layoutPosition == UserSingleton.getUserInformations().rankingPosition-1){
                itemHolder.itemView?.background = ColorDrawable(Color.GRAY)
            }

            itemHolder?.nameText?.text = item.player.userName
            itemHolder?.playerCategory?.text = item.player.userCategory
            itemHolder?.rankingText?.text = item.player.userRankingPosition
            itemHolder?.victory?.text = item.player.userWins
            itemHolder?.lastGame?.text = item.player.userLastGame
            itemHolder?.efficiency?.text = item.player.userEfficiency
            itemHolder?.profileButton?.setOnClickListener{
                (fragment as RankingFragment).goToShowProfileView(item.player.userName)
            }

            if(expandedId == itemHolder?.layoutPosition) {
                itemHolder.expandedView.visibility = View.VISIBLE
            } else {
                itemHolder?.expandedView?.visibility = View.GONE
            }

        }

        /**
         * Method responsible to return size of ranking
         */
        override fun getItemCount(): Int {

            return this.playerInformation.size
        }

        /**
         * Class that shows user information on an expanded cell
         */
        inner class ItemHolder(v: View): RecyclerView.ViewHolder(v) {

            var nameText = v.name!!
            var rankingText = v.position!!
            var victory = v.victory!!
            var lastGame = v.lastGame!!
            var expandedView = v.expandedView!!
            var efficiency = v.efficiency!!
            var profileButton = v.profileButton!!
            var playerCategory = v.playerCategory!!
        }
    }
}