package com.nexte.nexte.FeedScene

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.service.carrier.CarrierIdentifier
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.FeedScene.FeedModel.FeedActivity
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_feed_view.*
import kotlinx.android.synthetic.main.row_feed.*
import kotlinx.android.synthetic.main.row_feed.view.*

/**
 * Interface to define Display Logic to FeedVeiw Class that will used received call of Presenter
 */
interface FeedDisplayLogic {
    fun displayFeed(viewModel: FeedModel.ViewModel)
    fun actualizeLike(formattedActivity: FeedModel.FeedActivityFormatted)
    fun sendLike(identifier: String)
}

/**
 * Class that implements [FeedLogic] and is responsible for control feed screen
 *
 * @property interactor Interactor layer for send requests [FeedInteractor]
 */
class FeedView : AppCompatActivity(), FeedDisplayLogic {

    var interactor: FeedInteractor? = null

    /**
     * On Create method that will setup this scene and call first request for interactor.
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_view)

        feedRecyclerView.layoutManager = LinearLayoutManager(this)
        this.setupFeedScene()

        val request = FeedModel.Request()
        interactor?.fetchFeed(request)

        var FeedViewAdapter = FeedAdapter(mutableListOf(),this)
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
     * Method responsible to receive the viewModel from presenter and show it to the user
     *
     * @param viewModel Feed view model received for presenter to show on screen
     */
    override fun displayFeed(viewModel: FeedModel.ViewModel) {
        feedRecyclerView.adapter = FeedAdapter(viewModel.feedActivities,
                                                       this)
    }

    override fun sendLike(identifier: String) {
        interactor?.fetchManageLikes(activity, listOfActivities)

    }



    /**
     * Adapter Class to control recycler view on feed activity
     *
     * @property activities List of all feed activities
     * @property context Context that will show this adapter
     */
    class FeedAdapter(private val activities: MutableList<FeedModel.FeedActivityFormatted>,
                      private val context: Context): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.row_feed, parent, false)
            return FeedAdapter.ViewHolder(view)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindView(activities[position], context)
        }

        override fun getItemCount(): Int {
            return this.activities.size
        }


        fun actualizeFormattedList (formattedActivity: FeedModel.FeedActivityFormatted) {
            val identifier = formattedActivity.identifier
            val activityToChange = activities.find { it.identifier.equals(identifier) }
            val indexOfActivityToChange = activities.indexOf(activityToChange)
            activities.add(indexOfActivityToChange, formattedActivity)
        }







        /**
         * View Holder Class to control itens that will show on Recycler view
         *
         * @property itemView View that contains properties to show on recycler view
         */
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

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

                itemView.likesButtom.setOnClickListener {

                    (context as FeedModel.ViewModel)

                }
            }

        }
    }
}

