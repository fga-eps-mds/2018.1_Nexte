package com.nexte.nexte.RankingScene

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
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


        val request = RankingModel.Request()

        interactor?.getPlayersRanksForScene(request)
    }

    /**
     * Method responsible to set the ranking information on screen
     */
    private fun setupRankingScene(){

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

        var expandedId = -1

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

                if(item.shouldDrawChild) {
                    expandedId = itemHolder.layoutPosition
                } else {
                    expandedId = -1
                }
                notifyItemChanged(expandedId)
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
