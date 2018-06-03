package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton

/**
 * Interface to define Presentation Logic to Feed Class that
 * will be used to call this Interactor on other class layer
 */
interface FeedPresentationLogic {

    /**
     * Method responsible to format feed data and send to view
     *
     * @param response contains unformatted data received from [FeedModel]
     */
    fun formatFeed(response: FeedModel.GetFeedActivities.Response)

    /**
     * Method responsible to format the updated Activity after the addition or removal
     * of the like in likes list
     *
     * @param response Activity that needs to be altered for presentation on screen
     */
    fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response)
}

/**
 * Class needed to format response for data can be displayed on activity
 *
 * @property viewController Reference to the activity where data will be displayed on [FeedFragment]
 */
class FeedPresenter(var viewController: FeedDisplayLogic? = null) : FeedPresentationLogic {

    var userManager: UserManager? = null

    override fun formatFeed(response: FeedModel.GetFeedActivities.Response) {

        val viewModel = FeedModel.GetFeedActivities.ViewModel(
                this.formatFeedActivities(response.feedActivities))
        viewController?.displayFeed(viewModel)
    }

    override fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response) {

        val viewModel = FeedModel.LikeAndUnlike.ViewModel(
                this.formatFeedActivity(response.likedActivity))

        viewController?.updateLike(viewModel)
    }

    /**
     * Auxiliar function to convert [FeedModel.FeedActivity] to [FeedModel.FeedActivityFormatted]
     *
     * @param activities MutableList of not formatted activities
     * @return list of formatted activities
     */
    private fun formatFeedActivities(activities: List<Story>):
            MutableList<FeedModel.FeedActivityFormatted> {

        val feedActivitiesFormatted: MutableList<FeedModel.FeedActivityFormatted> = mutableListOf()


        for (activity in activities) {

            val matchingUser = activity.likesId.find { it == UserSingleton.loggedUserID }
            var userIsOnLikeList = false

            if(matchingUser != null) {
                userIsOnLikeList = true
            }

            val challenger = userManager?.get(activity.winner?.userId!!)
            val challenged = userManager?.get(activity.loser?.userId!!)

            val feedActivityFormatted = FeedModel.FeedActivityFormatted(
                    activity.id!!,
                    challenger?.name!!,
                    R.mipmap.ic_launcher,
                    activity.winner?.setResult.toString(),
                    challenged?.name!!,
                    R.mipmap.ic_launcher,
                    activity.loser?.setResult.toString(),
                    activity.date.toString(),
                    activity.likesId.size.toString(),
                    userIsOnLikeList)

            feedActivitiesFormatted.add(feedActivityFormatted)
        }

        return feedActivitiesFormatted
    }

    /**
     * Auxiliary function to convert [FeedModel.FeedActivity] to [FeedModel.FeedActivityFormatted]
     *
     * @param activity Unformatted activity
     * @return Formatted activity
     */
    private fun formatFeedActivity(activity: FeedModel.FeedActivity):
            FeedModel.FeedActivityFormatted {

        val matchingUser = activity.likes.find { it.name == UserSingleton.loggedUser.name }
        var userIsOnLikeList = false

        if(matchingUser != null) {
            userIsOnLikeList = true
        }

        return FeedModel.FeedActivityFormatted(
                activity.identifier,
                activity.challenge.challenger.name,
                activity.challenge.challenger.photo,
                activity.challenge.challenger.set.toString(),
                activity.challenge.challenged.name,
                activity.challenge.challenged.photo,
                activity.challenge.challenged.set.toString(),
                activity.feedDate.toString(),
                activity.likes.size.toString(),
                userIsOnLikeList)
    }
}