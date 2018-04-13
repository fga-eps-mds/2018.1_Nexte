package com.nexte.nexte.FeedScene

/**
 * Interface to define Presentation Logic to Feed Class that
 * will used to call this Interactor on other class layer
 */
interface FeedPresentationLogic {

    /**
     * Method responsible to format feed data and send for view
     *
     * @param response Feed model response containing unformatted data received [FeedModel]
     */
    fun formatFeed(response: FeedModel.Response)

    /**
     * Method responsible to format the updated Activity after the addition or removal
     * of the like in likes list
     *
     * @param activity Activity that needs to be altered for presentation on screen
     */
    fun updateViewActivity(activity: FeedModel.FeedActivity)
}

/**
 * Class needed to format response for data can be displayed on activity
 *
 * @property viewScene Reference to the activity where data will be displayed [FeedView]
 */
class FeedPresenter(var viewScene: FeedDisplayLogic? = null) : FeedPresentationLogic {

    override fun formatFeed(response: FeedModel.Response) {

        val viewModel = FeedModel.ViewModel(this.formatFeedActivities(response.feedActivities))
        viewScene?.displayFeed(viewModel)
    }


    override fun updateViewActivity(activity: FeedModel.FeedActivity) {

        val newActivityFormatted = this.formatFeedActivity(activity)
        viewScene?.updateLike(newActivityFormatted)
    }

    /**
     * Auxiliar function to convert [FeedModel.FeedActivity] to [FeedModel.FeedActivityFormatted]
     *
     * @param activities MutableList of not formatted activities
     * @return list of formatted activities
     */
    private fun formatFeedActivities(activities: MutableList<FeedModel.FeedActivity>):
            MutableList<FeedModel.FeedActivityFormatted> {
        val feedActivitiesFormatted: MutableList<FeedModel.FeedActivityFormatted> = mutableListOf()

        for (activity in activities) {
            val feedActivityFormatted = FeedModel.FeedActivityFormatted(
                    activity.identifier,
                    activity.challenge.challenger.name,
                    activity.challenge.challenger.photo,
                    activity.challenge.challenger.set.toString(),
                    activity.challenge.challenged.name,
                    activity.challenge.challenged.photo,
                    activity.challenge.challenged.set.toString(),
                    activity.feedDate.toString(),
                    activity.likes.size.toString())

            feedActivitiesFormatted.add(feedActivityFormatted)
        }

        return feedActivitiesFormatted
    }

    /**
     * Auxiliar function to convert [FeedModel.FeedActivity] to [FeedModel.FeedActivityFormatted]
     *
     * @param activity Unformatted activity
     * @return Formatted activity
     */
    private fun formatFeedActivity(activity: FeedModel.FeedActivity):
            FeedModel.FeedActivityFormatted {
        val feedActivityFormatted = FeedModel.FeedActivityFormatted(
                activity.identifier,
                activity.challenge.challenger.name,
                activity.challenge.challenger.photo,
                activity.challenge.challenger.set.toString(),
                activity.challenge.challenged.name,
                activity.challenge.challenged.photo,
                activity.challenge.challenged.set.toString(),
                activity.feedDate.toString(),
                activity.likes.size.toString())

        return feedActivityFormatted
    }
}