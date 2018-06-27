package com.nexte.nexte.FeedScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.Story.StoryPlayer
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

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

    /**
     * Method that will be used to pass response data for the presenter class
     *
     * @param response Reponse model of list with the altered inner list of likes, which means
     * that the actual user liked or unliked the story
     */
    fun updateLikes(response: FeedModel.LikeAndUnlike.Response)
}

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class FeedWorker {

    var updateLogic: FeedWorkerUpdateLogic? = null
    var storyManager: StoryManager? = null
    var senderHTTP = false

    val httpRequestHandler: (com.github.kittinunf.fuel.core.Request,
                             com.github.kittinunf.fuel.core.Response,
                             Result<Json, FuelError>) -> Unit = { _, _, result ->
        when(result){
            is Result.Failure -> {
                println(result.getException())
            }

            is Result.Success -> {
                val json = result.get()
                val stories = convertJsonStoryToStories(json.obj())
                storyManager?.updateMany(stories)
                val newResponse = FeedModel.GetFeedActivities
                        .Response(stories)
                updateLogic?.updateFeed(newResponse)
            }
        }
    }

    /**
     * Function to fetch feed data of server
     */
    fun fetchFeedData(request: FeedModel.GetFeedActivities.Request) {

        var allStories = storyManager?.getAll()
        allStories = if (allStories == null) {
            listOf()
        } else {
            allStories
        }
        val response = FeedModel.GetFeedActivities.Response(allStories)
        updateLogic?.updateFeed(response)

        if (UserSingleton.userType != UserType.MOCKED){
            senderHTTP = true
            val header = mapOf("accept-version" to "0.1.0")
            val url = "http://10.0.2.2:3000/stories"
            url.httpGet().header(header).responseJson(httpRequestHandler)
        } else {
            senderHTTP = false
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
    fun manageLikes(request: FeedModel.LikeAndUnlike.Request){

        val storyId = request.identifier
        var storyToChange = storyManager?.get(storyId)

        storyToChange = if (storyToChange == null) {
            Story()
        } else {
            storyToChange
        }

        val nullUser = StoryPlayer("", 0)
        var newStory = storyManager?.updateLike(storyToChange, UserSingleton.loggedUserID)

        newStory = if (newStory == null) {
            Story("", nullUser, nullUser,  Date(), listOf(), listOf())
        } else {
            newStory
        }
        val response = FeedModel.LikeAndUnlike.Response(newStory)
        updateLogic?.updateLikes(response)

//TODO
//        if (UserSingleton.userType != UserType.MOCKED){
//            senderHTTP = true
//            val header = mapOf("accept-version" to "0.1.0")
//            val url = "http://10.0.2.2:3000/stories"
//            url.httpGet().header(header).responseJson(httpRequestHandler)
//        } else {
//            senderHTTP = false
//        }

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
        val storiesJsonArray = dataJson["feed"] as JSONArray

        val storiesMutableList = mutableListOf<Story>()
        for (counter in 0 until storiesJsonArray.length()){
            val jsonStory = storiesJsonArray.getJSONObject(counter)
            val story = Story.createStoryFromJsonObject(jsonStory)
            storiesMutableList.add(story)

        }
        return storiesMutableList.toList()
    }
}