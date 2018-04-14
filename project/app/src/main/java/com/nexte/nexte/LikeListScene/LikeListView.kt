package com.nexte.nexte.LikeListScene

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nexte.nexte.R
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_list_like.*
import kotlinx.android.synthetic.main.row_likes.view.*

/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListDisplayLogic {
    fun displayLikeList(viewModel: LikeListModel.ViewModel)
}

class LikeListView : AppCompatActivity(), LikeListDisplayLogic {

    var interactor: LikeListInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_like)

        likesListRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setUpLikeListScene()

        val request = LikeListModel.Request("exampleStringv")
        interactor?.fetchDataToList(request)

    }

    private fun setUpLikeListScene() {

        var view = this
        var interactor = LikeListInteractor()
        var presenter = LikeListPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewList = view
    }

    override fun displayLikeList(viewModel: LikeListModel.ViewModel) {
        likesListRecyclerView.adapter = LikesListAdapter(viewModel.PlayersFormatted, this)
    }


    class LikesListAdapter(private val listOfPlayers: MutableList<LikeListModel.PlayersFormatted>,
                           private val context: Context): RecyclerView.Adapter<LikesListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesListAdapter.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.row_likes, parent, false)
            return LikesListAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindView(listOfPlayers[position])
        }

        override fun getItemCount(): Int {
            return this.listOfPlayers.size
        }

        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


            fun bindView(printedPlayer: LikeListModel.PlayersFormatted) {
                itemView.PlayerName.text = printedPlayer.name
                itemView.LikeDate.text = printedPlayer.time
            }
        }
    }
}