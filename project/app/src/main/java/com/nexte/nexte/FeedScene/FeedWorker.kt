package com.nexte.nexte.FeedScene

import com.nexte.nexte.MainActivity
import com.nexte.nexte.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Class responsible to do request for anywhere, format response and call completion to send data for called class
 */
class FeedWorker {

    /**
     * Function to fetch feed data of server
     *
     * @param request Feed model request that contains need information to send for server
     * @param completion Method for call on parent class
     */
    fun fetchFeedData(request: FeedModel.Request, completion: (FeedModel.Response) -> Unit) {
        val response = FeedModel.Response(FeedManager.feedList)
        completion(response)
    }

    /**
     * Function that updates the data from server
     *
     * @param activity
     * @param identifier
     * @param completion
     */
    fun manageLikes(activity: FeedModel.FeedActivity, identifier: String, completion: (FeedModel.FeedActivity) -> Unit) {
        var activity = addAndRemoveUser(activity)
        val updatedActivity = (FeedManager.updateFeedActivity(identifier, activity))
        completion(updatedActivity)
    }

    private fun addAndRemoveUser(activity: FeedModel.FeedActivity): FeedModel.FeedActivity {
        val baldissera = FeedModel.FeedPlayer("Guilherme",123456, 1)
        val finder = activity.likes.find { it.equals(baldissera) }
        if(finder == null) {
            activity.likes.add(baldissera)
        }
        else {
            activity.likes.remove(baldissera)
        }

        return activity
    }
}