package com.nexte.nexte.FeedScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject

/**
 * Interface to define Response Logic of Ranking Class
 * that will be used to make the communication between worker and interactor
 */
interface FeedWorkerUpdateLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun updateFeed(response: FeedModel.GetFeedActivities.Response)
}

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class FeedWorker {

    var updateLogic: FeedWorkerUpdateLogic? = null
    var storyManager: StoryManager? = null

    /**
     * Function to fetch feed data of server
     */
    fun fetchFeedData(request: FeedModel.GetFeedActivities.Request) {

        val allStories = storyManager?.getAll()
        val response = FeedModel.GetFeedActivities.Response(allStories!!)
        updateLogic?.updateFeed(response)


        if (UserSingleton.userType != UserType.MOCKED){
            val url = "http://10.0.2.2:3000/feed"
            url.httpGet().responseJson { _, _, result ->
                when(result){
                    is Result.Failure -> {
                        println(result.getException())
                    }

                    is Result.Success -> {
                        val json = result.get()
                        val stories = convertJsonStoryToStories(json.obj())

                        val newResponse = FeedModel.GetFeedActivities
                                .Response(stories)
                        updateLogic?.updateFeed(newResponse)
                    }
                }
            }
        } else {
            //Do nothing
        }

    }

    /**
     * Function that receives the unformattedActivity from Interactor and sends it
     * unformatted Activity to Presenter as a completion, after calling the function to add
     * or remove users from likes list
     *
     * @param request String as the identifier to find the activity to be altered
     * @param completion unformatted activity sent to interactor
     */
    fun manageLikes(request: FeedModel.LikeAndUnlike.Request,
                    completion: (FeedModel.LikeAndUnlike.Response) -> Unit) {

        var activity = FeedManager.getFeedActivity(request.identifier)
         activity = FeedManager.addAndRemoveUser(activity)

        val updatedActivity = FeedModel.LikeAndUnlike.Response(
                FeedManager.updateFeedActivity(request.identifier, activity))

        completion(updatedActivity)
    }

    /**
     * Method resposible for tranforming a jsonObject, that contains the response of the api request,
     * into a stories list
     *
     * @param jsonObject jsonObject that contains the response data from the api
     *
     * @return stories
     */
    fun convertJsonStoryToStories(jsonObject: JSONObject) : List<Story> {
        val dataJson = jsonObject["data"] as JSONObject
        val storiesJsonArray = dataJson["stories"] as JSONArray

        val storiesMutableList = mutableListOf<Story>()
        for (counter in 0 until storiesJsonArray.length()){
            val jsonStory = storiesJsonArray.getJSONObject(counter)
            val story = Story.createStoryFromJsonObject(jsonStory)
            storiesMutableList.add(story)

        }
        return storiesMutableList.toList()
    }
}