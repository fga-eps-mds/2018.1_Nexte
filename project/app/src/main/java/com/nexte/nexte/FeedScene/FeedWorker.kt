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
     * Function that receives the unformattedActivity from Interactor and sends it
     * unformatted Activity to presenter as a completion, after calling the function to add
     * or remove users from likes list
     *
     * @param activity unformatted activity received from interactor [FeedInteractor]
     * @param identifier Activity identifier to use in updateFeedActivity function
     * @param updatedActivity unformatted activity updated with or without user on likesList
     * @param completion unformatted activity sent to interactor
     */
    fun manageLikes(activity: FeedModel.FeedActivity?, completion: (FeedModel.FeedActivity) -> Unit) {

        var newActivity:  FeedModel.FeedActivity? = addAndRemoveUser(activity)
        val identifier = activity?.identifier
        val updatedActivity = (FeedManager.updateFeedActivity(identifier, newActivity))
        completion(updatedActivity)
    }


    /**
     * Function that receives the unformattedActivity from Interactor and sends it
     * unformatted Activity to presenter as a completion, after calling the function to add
     * or remove users from likes list
     *
     * @param randomUser user that will be added to likesList
     * @param matchingUser user that corresponds to previous randomUser
     * @param indexToChange index of the found activity
     * @return activity changed, with or without the member on likes List
     */
    private fun addAndRemoveUser(activity: FeedModel.FeedActivity?): FeedModel.FeedActivity? {
        val randomUser = FeedModel.FeedPlayer("UserName",123456, 1)
        val matchingUser = activity?.likes?.find { it.name.equals("UserName") }
        val indexToChange = activity?.likes?.indexOf(matchingUser)

        (indexToChange as Int)

        if(matchingUser == null) {
            activity.likes.add(randomUser)
        }
        else {
            activity.likes.removeAt(indexToChange)
        }

        return activity
    }
}