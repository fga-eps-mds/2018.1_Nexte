package com.nexte.nexte.FeedScene

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.CommentsScene.CommentsFragment
import com.nexte.nexte.LikeListScene.LikeListView
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.row_feed.view.*

/**
 * Interface to define Display Logic to FeedFragment Class that will receive information
 * from Presenter
 */
interface FeedDisplayLogic {

    fun displayFeed(viewModel: FeedModel.GetFeedActivities.ViewModel)
    fun updateLike(viewModel: FeedModel.LikeAndUnlike.ViewModel)
}

/**
 * Test for the Realm Database
 */
//open class Person: RealmObject() {
//    @PrimaryKey
//    var id: Long = 0
//    var name: String? = null
//}

/**
 * Class that implements [FeedDisplayLogic] and is responsible to control feed screen
 *
 * @property interactor Interactor layer for send requests [FeedInteractor]
 * @property feedViewAdapter FeedAdapter instance for broad using on class
 */
class FeedFragment : Fragment(), FeedDisplayLogic {

    var interactor: FeedInteractor? = null
    var feedViewAdapter: FeedAdapter? = null
    var feedRecyclerView : RecyclerView? = null

    fun getInstance() : FeedFragment {
        return FeedFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        //       setContentView(R.layout.activity_feed_view)

        this.setupFeedScene()
        val newView = inflater?.inflate(R.layout.activity_feed_view, container, false)
        this.feedRecyclerView = newView?.findViewById(R.id.feedRecyclerView)
        this.feedViewAdapter = FeedAdapter(mutableListOf(), this)
        feedRecyclerView?.adapter = this.feedViewAdapter
        feedRecyclerView?.layoutManager = LinearLayoutManager(activity)

        this.createGetActivitiesRequest()
        return newView
    }


    /**
     * Method responsible to setup all the references of this scene
     */
    fun setupFeedScene() {

        val view = this
        val presenter = FeedPresenter()
        val interactor = FeedInteractor()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view
    }

    /**
     * Method responsible for creating the get activities request and passing it to the interactor
     */
    fun createGetActivitiesRequest(){
        val request = FeedModel.GetFeedActivities.Request()
        interactor?.fetchFeed(request)
    }

    /**
     * Method to open LikesList scene
     */
    private fun goToLikesList() {

        val intent = Intent(activity, LikeListView::class.java)
        startActivity(intent)
    }


    private fun goToCommentsList() {

        val commentsFragment = CommentsFragment().getInstance()
        val fragmentManager = activity.fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, commentsFragment, "comments")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    /**
     * Method responsible to receive the viewModel from Presenter and show it to the user
     *
     * @param viewModel Feed fragment model received from Presenter to show on screen
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
    fun sendLike(identifier: FeedModel.LikeAndUnlike.Request) {

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
     * Adapter Class to control recycler fragment on feed activity
     *
     * @property activities List of all feed activities
     * @property fragment Fragment that will show this adapter
     */
    class FeedAdapter(private var activities: MutableList<FeedModel.FeedActivityFormatted>,
                      private val fragment: Fragment) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {

            val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_feed, parent,false)
            return FeedAdapter.ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(activities[position], fragment)
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
         * View Holder Class to control itens that will be shown on Recycler fragment
         *
         * @property itemView View that contains properties to show on recycler fragment
         */
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about activity
             *
             * @param activity Formatted data to show in row of activity feed
             */

            fun bindView(activity: FeedModel.FeedActivityFormatted,
                         fragment: Fragment) {

                itemView.challengerName.text = activity.challengerName
                itemView.challengerPhoto.setImageResource(activity.challengerPhoto)
                itemView.challengerSet.text = activity.challengerSets

                itemView.challengedName.text = activity.challengedName
                itemView.challengedPhoto.setImageResource(activity.challengedPhoto)
                itemView.challengedSet.text = activity.challengedSets

                itemView.numberOfLikes.text = String.format("%s curtidas", activity.numberOfLikes)

                if (activity.challengerSets > activity.challengedSets) {
                    itemView.whoWon.text = String.format("%s ganhou de %s", activity.challengerName, activity.challengedName)
                } else {
                    itemView.whoWon.text = String.format("%s ganhou de %s", activity.challengedName, activity.challengerName)
                }

                if (activity.currentUserLiked == true) {
                    itemView.likesButton.setImageResource(R.mipmap.feed_like_fill)
                } else {
                    itemView.likesButton.setImageResource(R.mipmap.feed_like)
                }

                itemView.likesButton.setOnClickListener {

                    val sendLikesAux = FeedModel.LikeAndUnlike.Request(activity.identifier)
                    (fragment as FeedFragment).sendLike(sendLikesAux)
                }

                itemView.numberOfLikes.setOnClickListener {

                    (fragment as FeedFragment).goToLikesList()
                }

                itemView.comments.setOnClickListener {

                    (fragment as FeedFragment).goToCommentsList()
                }

                itemView.commentsButton.setOnClickListener {

                    (fragment as FeedFragment).goToCommentsList()
                }
            }
        }
    }

    companion object {
        fun newInstance() : FeedFragment = FeedFragment()
    }
}