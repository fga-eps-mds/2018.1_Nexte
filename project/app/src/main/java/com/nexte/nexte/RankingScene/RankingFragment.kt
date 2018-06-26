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
import android.widget.ImageView
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
    var loggedPlayer: RankingModel.FormattedPlayerInfo? = null

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
                UserSingleton.loggedUser.rankingPosition, this))

        setFixedRanking(this, this.rankingRecyclerView, UserSingleton.loggedUser.rankingPosition)

        return view
    }

    private fun goToShowProfileView(id: String) {
        val fragment = ShowProfileFragment().getInstance(id)
        fragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).addToBackStack(null).commit()
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

            view?.position?.text = String.format("%d", UserSingleton.loggedUser.rankingPosition)
            view?.name?.text = UserSingleton.loggedUser.name
            UserSingleton.loggedUser.profilePicture?.let {
                view?.picture_img_view?.setImageResource(it.toInt())
            }
            view?.playerCategory?.text = UserSingleton.loggedUser.category?.name
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

        rankingView.fixedFragment?.setBackgroundResource(R.drawable.cell_border_light)
        rankingView.setUserGameCircle(rankingView.fixedFragment?.circulo1,
                rankingView.loggedPlayer?.player?.userLastGames!![0])
        rankingView.setUserGameCircle(rankingView.fixedFragment?.circulo2,
                rankingView.loggedPlayer?.player?.userLastGames!![1])
        rankingView.setUserGameCircle(rankingView.fixedFragment?.circulo3,
                rankingView.loggedPlayer?.player?.userLastGames!![2])
        rankingView.setUserGameCircle(rankingView.fixedFragment?.circulo4,
                rankingView.loggedPlayer?.player?.userLastGames!![3])
        rankingView.setUserGameCircle(rankingView.fixedFragment?.circulo5,
                rankingView.loggedPlayer?.player?.userLastGames!![4])

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
     * @param fragment indicates the fragment that the recycler view is inserted in
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

        this.loggedPlayer = viewModel.formattedPlayers.find { it.player.id == UserSingleton.loggedUserID }
        rankingRecyclerView?.adapter = RankingAdapter(viewModel.formattedPlayers, this)
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

            if(item.player.id.equals(UserSingleton.loggedUserID)) {
                itemHolder?.itemView?.setBackgroundResource(R.drawable.cell_border_light)
                hideOrShowLoggedUserExpandedInformation(true, itemHolder)
            } else {
                itemHolder?.itemView?.setBackgroundResource(R.drawable.cell_border)
                hideOrShowLoggedUserExpandedInformation(false, itemHolder)
            }

            itemHolder?.nameText?.text = item.player.userName
            itemHolder?.playerCategory?.text = item.player.userCategory
            itemHolder?.rankingText?.text = item.player.userRankingPosition
            itemHolder?.victory?.text = item.player.userWins
            itemHolder?.lastGame?.text = item.player.userLastGame
            itemHolder?.efficiency?.text = item.player.userEfficiency
            itemHolder?.profileImage?.setImageResource(item.player.userPictureURL)
            itemHolder?.profileButton?.setOnClickListener{
                (fragment as RankingFragment).goToShowProfileView(item.player.id)
            }

            val rankingFragment = fragment as RankingFragment
            rankingFragment.setUserGameCircle(itemHolder?.itemView?.circulo1, item.player.userLastGames[0])
            rankingFragment.setUserGameCircle(itemHolder?.itemView?.circulo2, item.player.userLastGames[1])
            rankingFragment.setUserGameCircle(itemHolder?.itemView?.circulo3, item.player.userLastGames[2])
            rankingFragment.setUserGameCircle(itemHolder?.itemView?.circulo4, item.player.userLastGames[3])
            rankingFragment.setUserGameCircle(itemHolder?.itemView?.circulo5, item.player.userLastGames[4])

            itemHolder?.expandedView?.currentUser?.setImageResource(item.player.userPictureURL)
            UserSingleton.loggedUser.profilePicture?.let {
                itemHolder?.expandedView?.loggedUser?.setImageResource(it.toInt())
            }


            if(expandedId == itemHolder?.layoutPosition) {
                itemHolder.expandedView.visibility = View.VISIBLE
            } else {
                itemHolder?.expandedView?.visibility = View.GONE
            }
        }

        /**
         * Method responsible for hiding or showing the information about 1x1 game in raking recycler view
         * of the logged user
         *
         * @param hide boolean that will be used to hide or show the content
         * @param itemHolder cell to show or hide the content
         */
        fun hideOrShowLoggedUserExpandedInformation(hide: Boolean, itemHolder: ItemHolder?){

            if (hide) {
                itemHolder?.expandedView?.profileButton?.visibility = View.GONE
                itemHolder?.expandedView?.currentUser?.visibility = View.GONE
                itemHolder?.expandedView?.loggedUser?.visibility = View.GONE
                itemHolder?.expandedView?.versus?.visibility = View.GONE
                itemHolder?.expandedView?.gamesLoggedUser?.visibility = View.GONE
                itemHolder?.expandedView?.gamesCurrentUser?.visibility = View.GONE
                itemHolder?.expandedView?.profileButton?.visibility = View.GONE
            } else {
                itemHolder?.expandedView?.profileButton?.visibility = View.VISIBLE
                itemHolder?.expandedView?.currentUser?.visibility = View.VISIBLE
                itemHolder?.expandedView?.loggedUser?.visibility = View.VISIBLE
                itemHolder?.expandedView?.versus?.visibility = View.VISIBLE
                itemHolder?.expandedView?.gamesLoggedUser?.visibility = View.VISIBLE
                itemHolder?.expandedView?.gamesCurrentUser?.visibility = View.VISIBLE
                itemHolder?.expandedView?.profileButton?.visibility = View.VISIBLE
            }
        }


        /**
         * Method responsible to return sma ize of ranking
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
            var profileImage = v.picture_img_view!!
        }
    }
}