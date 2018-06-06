package com.nexte.nexte.LikeListScene

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nexte.nexte.R
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.UserManager
import kotlinx.android.synthetic.main.row_likes.view.*

/**
 * Interface to define Display Logic to LikeListFragment Class that will receive information
 * from Presenter
 */
interface LikeListDisplayLogic {

    fun displayLikeList(viewModel: LikeListModel.ViewModel)
}

/**
 * Class that implements [LikeListDisplayLogic] and is responsible to control
 * like list screen
 *
 * @property interactor it's a Interactor layer for sending requests [LikeListInteractor]
 */
class LikeListFragment : Fragment(), LikeListDisplayLogic {

    var interactor: LikeListInteractor? = null
    var likesListRecyclerView: RecyclerView? = null
    var likeManager: LikeManager? = null
    var storyManager: StoryManager? = null
    var userManager: UserManager? = null

    fun getInstance(): LikeListFragment{
        val likeListFragment = LikeListFragment()
        return likeListFragment
    }

    /**
     * On Create method that will setup this scene and call first Request for Interactor
     *
     * @param savedInstanceState
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val newView = inflater?.inflate(R.layout.activity_list_like, container, false)
        likesListRecyclerView = newView?.findViewById(R.id.likesListRecyclerView)
        likesListRecyclerView?.layoutManager = LinearLayoutManager(this.activity)
        this.likeManager = LikeManager()
        this.userManager = UserManager()
        this.storyManager = StoryManager()
        this.setUpLikeListScene()
        this.createFetchDataRequest()

        return newView
    }



    /**
     * Method responsible for creating the fetch data to list request and passing it to the interactor
     */
    fun createFetchDataRequest(){
        val request = LikeListModel.Request("12312312",
                "d2c02630-b20d-45fc-a5f3-41c399dbd075")
        interactor?.fetchDataToList(request)
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    fun setUpLikeListScene() {

        val view = this
        val interactor = LikeListInteractor()
        val presenter = LikeListPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        interactor.worker.likeManager = likeManager
        interactor.worker.updateLogic = interactor
        interactor.worker.userManager = userManager
        interactor.worker.storyManager = storyManager
        presenter.viewList = view
    }

    /**
    * Method responsible to receive the viewModel from Presenter and show it to the user
    *
    * @param viewModel is received from presenter to show on screen
    */
    override fun displayLikeList(viewModel: LikeListModel.ViewModel) {
        likesListRecyclerView!!.adapter = LikesListAdapter(viewModel.playersFormatted, this)
    }

    /**
     * Adapter Class to control recycler fragment on ListLike
     *
     * @property listOfPlayers It's a list of all players
     * @property fragment Fragment that will show this adapter
     */
    class LikesListAdapter(private val listOfPlayers: MutableList<LikeListModel.PlayersFormatted>,
                           private val fragment: Fragment):
                            RecyclerView.Adapter<LikesListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
                                        LikesListAdapter.ViewHolder {
            val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_likes, parent,
                        false)
            return LikesListAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(listOfPlayers[position])
        }

        override fun getItemCount(): Int {

            return this.listOfPlayers.size
        }

        /**
         * View Holder Class to control items that will show on Recycler fragment
         *
         * @property itemView View that contains properties to show on recycler fragment
         */
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about printedPLayer
             *
             * @param printedPlayer Formatted data to show in row of LikeList Row
             */
            fun bindView(printedPlayer: LikeListModel.PlayersFormatted) {
                itemView.PlayerName.text = printedPlayer.name
            }
        }
    }
}