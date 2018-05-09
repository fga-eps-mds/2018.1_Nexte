package com.nexte.nexte.RankingScene

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_ranking.*
import kotlinx.android.synthetic.main.row_ranking.view.*

/**
 * Interface responsible to define methods used to get user information data
 * from presenter and display it
 */
interface RankingDisplayLogic {

    fun displayRankInScreen(viewModel: RankingModel.ViewModel)
}

/**
 * Class that implements [RankingDisplayLogic]
 *
 * @property interactor responsible to receive request and send it to worker
 */
class RankingView : AppCompatActivity(), RankingDisplayLogic {

    var interactor: RankingInteractor? = null

    /**
     * Method called on scene creation
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        rankingRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setupRankingScene()


        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fixedFragment, FixedRowRankingFragment())
        fragmentTransaction.commit()

        this.createGetPlayersRequest()

        this.rankingRecyclerView.addOnScrollListener(OnScrollRankingRecyclerView(
                UserSingleton.getUserInformations().rankingPosition, this))

        setFixedRanking(this, this.rankingRecyclerView, UserSingleton.getUserInformations().rankingPosition)
    }

    class FixedRowRankingFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.row_ranking, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            view?.position?.text = String.format("#%d", UserSingleton.getUserInformations().rankingPosition)
            view?.name?.text = UserSingleton.getUserInformations().name
            view?.rowRankingLayout?.background = ColorDrawable(Color.GRAY)

        }
    }

    private fun setFixedRanking(context: Context, recyclerView: RecyclerView?, playerRanking: Int){
        val constraintSet = ConstraintSet()
        val rankingView = (context as RankingView)
        val layoutManager = recyclerView?.layoutManager as LinearLayoutManager

        if(layoutManager.findFirstCompletelyVisibleItemPosition() <= (playerRanking - 1) && (playerRanking - 1) <= layoutManager.findLastCompletelyVisibleItemPosition()) {
            rankingView.fixedFragment.visibility = View.INVISIBLE
        }
        else if ((playerRanking - 1) > layoutManager.findLastVisibleItemPosition()) {
            rankingView.fixedFragment.visibility = View.VISIBLE
            constraintSet.clone(rankingView.rankingConstraintLayout)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.BOTTOM)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.TOP)
            constraintSet.connect(R.id.fixedFragment, ConstraintSet.BOTTOM, R.id.rankingConstraintLayout, ConstraintSet.BOTTOM)
            constraintSet.applyTo(rankingView.rankingConstraintLayout)
        }
        else if ((playerRanking-1 < layoutManager.findFirstVisibleItemPosition())){
            rankingView.fixedFragment.visibility = View.VISIBLE
            constraintSet.clone(rankingView.rankingConstraintLayout)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.BOTTOM)
            constraintSet.clear(R.id.fixedFragment, ConstraintSet.TOP)
            constraintSet.connect(R.id.fixedFragment, ConstraintSet.TOP, R.id.rankingConstraintLayout, ConstraintSet.TOP)
            constraintSet.applyTo(rankingView.rankingConstraintLayout)
        }
    }

    private class OnScrollRankingRecyclerView(val playerRanking: Int, val context: Context) : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            (context as RankingView).setFixedRanking(context, recyclerView, playerRanking)
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
        presenter.viewScene = view
    }

    /**
     * Method responsible to get user information and display it
     *
     * @param viewModel contains formatted player data
     */
    override fun displayRankInScreen(viewModel: RankingModel.ViewModel) {

        rankingRecyclerView.adapter = RankingAdapter(viewModel.formattedPlayers, this)
    }

    /**
     * Class responsible to expand user information on click
     *
     * @property playerInformation List of formatted information
     * @property context Context that will show this adapter
     */
    class RankingAdapter(private val playerInformation: List<RankingModel.FormattedPlayerInfo>,
                         private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var expandedId = -1

        /**
         * Method called on view holder creation
         *
         * @param parent cell not expanded
         * @param viewType condition to know which layout will be used
         * (parent or child, expanded or not)
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            val inflater = LayoutInflater.from(context)
            val view: View?

            view = inflater.inflate(R.layout.row_ranking, parent, false)

            return ItemHolder(view!!)
        }

        /**
         * Method responsible to set the view holder and expand player cell
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
            if(item.player.userRankPosition.removeRange(0, 1).toInt() == UserSingleton.getUserInformations().rankingPosition){
                itemHolder?.itemView?.background = ColorDrawable(Color.GRAY)
            }
            itemHolder?.nameText?.text = item.player.userName
            itemHolder?.rankingText?.text = item.player.userRankPosition
            itemHolder?.victory?.text = item.player.userWins
            itemHolder?.losses?.text = item.player.userLosses

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

            var nameText = v.name
            var rankingText = v.position
            var victory = v.victory
            var losses = v.losses
            var expandedView = v.expandedView
        }
    }
}
