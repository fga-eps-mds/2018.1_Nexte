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

interface RankingDisplayLogic {
    fun displayRankInScreen(viewModel: RankingModel.ViewModel)
}

class RankingActivity : AppCompatActivity(), RankingDisplayLogic {

    var interactor: RankingInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        rankingRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setupRankingScene()


        val request = RankingModel.Request()

        interactor?.getPlayersRanksForScene(request)
    }

    private fun setupRankingScene(){

        val view = this
        val interactor = RankingInteractor()
        val presenter = RankingPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewScene = view
    }

    override fun displayRankInScreen(viewModel: RankingModel.ViewModel) {

        rankingRecyclerView.adapter = RankingAdapter(viewModel.formattedPlayers, this)
    }

    class RankingAdapter(private val playerInformation: List<RankingModel.FormattedPlayerInfo>,
                         private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        var expandedId = -1

        override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
            val inflater = LayoutInflater.from(context)
            var view: View? = null

            view = inflater.inflate(R.layout.row_ranking, parent, false)

            return ItemHolder(view!!)

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val  itemHolder = holder as? ItemHolder
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

        override fun getItemCount(): Int {
            return this.playerInformation.size
        }

        inner class ItemHolder(v: View): RecyclerView.ViewHolder(v) {
            var nameText = v.name
            var rankingText = v.position
            var victory = v.victory
            var losses = v.losses

            var expandedView = v.expandedView
        }

    }
}
