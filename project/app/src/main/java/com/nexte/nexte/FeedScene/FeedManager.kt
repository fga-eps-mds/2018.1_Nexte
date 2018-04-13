package com.nexte.nexte.FeedScene

/**
 * Object class for use static mode in other classes.
 * This Object is responsible to control data in memory of Feed.
 *
 * @property feedListMutable Private variable to control list of memory
 * @property feedList Property to access feed list on memory
 */
object FeedManager {

    private var feedListMutable: MutableList<FeedModel.FeedActivity> = mutableListOf()

    var feedList: MutableList<FeedModel.FeedActivity> = mutableListOf()
        get() {
            if (feedListMutable.isEmpty()) {
                feedListMutable = FeedMocker.createFeedList()
            }
            return feedListMutable
        }
        private set

    /**
     * Method for get specific activity of feed
     *
     * @param identifier Use for find activity feed with this identifier
     * @return Feed activity with specific identifier
     */
    fun getFeedActivity(identifier: String): FeedModel.FeedActivity? {
        return feedList.find { it.identifier == identifier}
    }

    /**
     * Method to update one specific acitivity of feed
     *
     * @param identifier Use for find activity feed that will be updated
     * @param activity New data for store in specific feed activity
     * @return Activity that is updated
     */
    fun updateFeedActivity(identifier: String?, activity: FeedModel.FeedActivity?): FeedModel.FeedActivity {
        val activityOfFeed = feedList.find { it.identifier == identifier }
        (activity as FeedModel.FeedActivity)

        if (activityOfFeed == null) {
            this.feedListMutable.add(activity)
        } else {
            activity.identifier = activityOfFeed.identifier
            activityOfFeed.challenge = activity.challenge
            activityOfFeed.feedDate = activity.feedDate
            activityOfFeed.likes = activity.likes
        }

        return activity
    }
}
