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

        rankingRecyclerView.adapter = RankingAdapter(viewModel.rankingActivities, this)
    }

    class RankingAdapter(private val activities: List<RankingModel.RankingActivityFormatted>,
                         private val context: Context): RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

        override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): RankingAdapter.ViewHolder{
            val view: View
            if(TODO("Add condition to show expanded view(boolean == TRUE")) {
                view = LayoutInflater.from(context).inflate(R.layout.row_ranking, parent, false)
            }
            else {
                view = LayoutInflater.from(context).inflate(R.layout.row_ranking_not_expanded, parent, false)
            }
            view.setOnClickListener {
                TODO("changebolean (boolean = !boolean")
                TODO("update reciclelist")
            }
            return RankingAdapter.ViewHolder(view)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindView(activities[position])
        }

        override fun getItemCount(): Int {
            return this.activities.size
        }



        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            fun bindView(activity: RankingModel.RankingActivityFormatted) {
                itemView.picture_img_view.setImageResource(activity.userPictureURL)
                itemView.name.text = activity.userName
                itemView.victory.text = activity.userWins
                itemView.position.text = activity.userRankPosition
                itemView.losses.text = activity.userLosses
            }
        }
    }
}
