package com.nexte.nexte.FeedScene

import android.content.Intent
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.LikeListScene.LikeListView
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_feed_view.*
import kotlinx.android.synthetic.main.row_feed.view.*

/**
 * Interface to define Display Logic to FeedView Class that will that will
 * receive information from Presenter
 */
interface FeedDisplayLogic {

    fun displayFeed(viewModel: FeedModel.GetFeedActivities.ViewModel)
    fun updateLike(viewModel: FeedModel.LikeAndUnlike.ViewModel)
}

/**
 * Class that implements [FeedDisplayLogic] and is responsible to control feed screen
 *
 * @property interactor Interactor layer for send requests [FeedInteractor]
 * @property feedViewAdapter FeedAdapter instance for broad using on class
 */
class FeedView : AppCompatActivity(), FeedDisplayLogic {

    var interactor: FeedInteractor? = null
    var feedViewAdapter: FeedAdapter? = null

    /**
     * On Create method that will setup this scene and call first Request for Interactor.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_view)

        this.setupFeedScene()

        this.feedViewAdapter = FeedAdapter(mutableListOf(), this)
        feedRecyclerView.adapter = this.feedViewAdapter
        feedRecyclerView.layoutManager = LinearLayoutManager(this)


        val request = FeedModel.GetFeedActivities.Request()
        interactor?.fetchFeed(request)
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    private fun setupFeedScene() {

        val view = this
        val presenter = FeedPresenter()
        val interactor = FeedInteractor()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewScene = view
    }

    /**
     * Method to open LikesList scene
     */
    private fun goToLikesList() {

        val intent = Intent(this, LikeListView::class.java)
        startActivity(intent)
    }

    /**
     * Method responsible to receive the viewModel from Presenter and show it to the user
     *
     * @param viewModel Feed view model received from Presenter to show on screen
     */
    override fun displayFeed(viewModel: FeedModel.GetFeedActivities.ViewModel) {

        feedViewAdapter?.updateActivities(viewModel.feedActivities)
    }

    /**
     * Method responsible to receive identifier and call interactor
     * to add or remove user on likesList of specific activity
     *
     * @param identifier parameter to identify activity
     */
    private fun sendLike(identifier: FeedModel.LikeAndUnlike.Request) {

        interactor?.fetchLikes(identifier)
    }

    /**
     * Method to update the formatted like list of activities
     *
     * @param viewModel Formatted Activity that needs to be added on activities list
     */
    override fun updateLike(viewModel: FeedModel.LikeAndUnlike.ViewModel) {

        feedViewAdapter?.actualizeFormattedList(viewModel.formattedLikedActivities)
    }

    /**
     * Adapter Class to control recycler view on feed activity
     *
     * @property activities List of all feed activities
     * @property context Context that will show this adapter
     */
    class FeedAdapter(private var activities: MutableList<FeedModel.FeedActivityFormatted>,
                      private val context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {

            val view = LayoutInflater.from(context).inflate(R.layout.row_feed, parent,false)
            return FeedAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(activities[position], context)
        }

        override fun getItemCount(): Int {

            return this.activities.size
        }

        /**
         * Function that updates the entire set o Formatted Feed Activites on list
         *
         * @param newActivities MutableList of elements to be displayed
         */
        fun updateActivities(newActivities: MutableList<FeedModel.FeedActivityFormatted>) {

            this.activities = newActivities
            this.notifyDataSetChanged()
        }

        /**
         * Function that replaces an deprecated item on MutableList displayed on screen
         * with an updated item
         *
         * @param formattedActivity Activity that needs to be replaced
         */
        fun actualizeFormattedList(formattedActivity: FeedModel.FeedActivityFormatted) {

            val identifier = formattedActivity.identifier
            val activityToChange = activities.find { it.identifier.equals(identifier) }
            val indexOfActivityToChange = activities.indexOf(activityToChange)

            activities.removeAt(indexOfActivityToChange)
            activities.add(indexOfActivityToChange, formattedActivity)
            notifyItemChanged(indexOfActivityToChange)
        }

        /**
         * View Holder Class to control itens that will be shown on Recycler view
         *
         * @property itemView View that contains properties to show on recycler view
         */
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about activity
             *
             * @param activity Formatted data to show in row of activity feed
             */

            fun bindView(activity: FeedModel.FeedActivityFormatted,
                         context: Context) {

                itemView.challengerName.text = activity.challengerName
                itemView.challengerPhoto.setImageResource(activity.challengerPhoto)
                itemView.challengerSet.text = activity.challengerSets

                itemView.challengedName.text = activity.challengedName
                itemView.challengedPhoto.setImageResource(activity.challengedPhoto)
                itemView.challengedSet.text = activity.challengedSets
                itemView.numberOfLikes.text = activity.numberOfLikes

                itemView.likesButton.setOnClickListener {

                    val sendLikesAux = FeedModel.LikeAndUnlike.Request(activity.identifier)
                    (context as FeedView).sendLike(sendLikesAux)
                }

                itemView.numberOfLikes.setOnClickListener {

                    (context as FeedView).goToLikesList()
                }
            }
        }
    }
}