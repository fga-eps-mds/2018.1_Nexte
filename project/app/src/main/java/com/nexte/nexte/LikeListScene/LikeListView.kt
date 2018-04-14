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

/**
 * Interface to define Display Logic to LikeListView Class that will used received call of Presenter
 */
interface LikeListDisplayLogic {
    fun displayLikeList(viewModel: LikeListModel.ViewModel)
}


/**
 * Class that implements [LikeListDisplayLogic] and is responsible for control feed screen
 *
 * @property interactor Interactor layer for send requests [LikeListInteractor]
 */
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

    /**
     * Method responsible to setup all the references of this scene
     */
    private fun setUpLikeListScene() {

        var view = this
        var interactor = LikeListInteractor()
        var presenter = LikeListPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewList = view
    }

    /**
    * Method responsible to receive the viewModel from presenter and show it to the user
    *
    * @param viewModel Feed view model received for presenter to show on screen
    */
    override fun displayLikeList(viewModel: LikeListModel.ViewModel) {
        likesListRecyclerView.adapter = LikesListAdapter(viewModel.PlayersFormatted, this)
    }

    /**
     * Adapter Class to control recycler view on ListLike
     *
     * @property listOflikes List of all players
     * @property context Context that will show this adapter
     */
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


        /**
         * View Holder Class to control itens that will show on Recycler view
         *
         * @property itemView View that contains properties to show on recycler view
         */
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about printedPLayer
             *
             * @param printedPlayer Formatted data to show in row of LikeList Row
             */
            fun bindView(printedPlayer: LikeListModel.PlayersFormatted) {
                itemView.PlayerName.text = printedPlayer.name
                itemView.LikeDate.text = printedPlayer.time
            }
        }
    }
}