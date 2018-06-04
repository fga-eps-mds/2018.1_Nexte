package com.nexte.nexte.LikeListScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Like.Like
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject

/**
 * Interface to define Response Logic of Like List Class
 * that will be used to make the communication between worker and interactor
 */
interface WorkerUpdateLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun updateUsers(response: LikeListModel.Response)
}

/**
 * Class responsible to do request from view, format response and call
 * completion to send data for called class
 */
class LikeListWorker {

    var updateLogic: WorkerUpdateLogic? = null
    var userManager: UserManager? = null
    var likeManager: LikeManager? = null
    var storyManager: StoryManager? = null

    /**
     * Function to fetch like list data of server
     *
     * @param request
     */
    fun getListLikesPlayers(request: LikeListModel.Request){
        val story = storyManager?.get(request.storyId)
        val likesIdsMutable = mutableListOf<String>()
        for(likeId in story?.likesId!!){
            likesIdsMutable.add(likeId)
        }
        val likes = likeManager?.getLikesFromStory(likesIdsMutable.toList())
        val allUsers = getUserFromLikes(likes!!)
        val response = LikeListModel.Response(allUsers)
        this.updateLogic?.updateUsers(response)

        if (UserSingleton.userType != UserType.MOCKED) {
            val url = "http://10.0.2.2:3000/likes/" + request.storyId
            url.httpGet().responseJson { _, _, result ->
                when(result){
                    is Result.Failure -> {
                        println(result.getException())
                    }

                    is Result.Success -> {
                        val json = result.get()
                        var likesList = convertJsonToListOfLikes(json.obj())
                        likeManager?.updateMany(likesList)
                        val users = getUserFromLikes(likesList)
                        val newResponse = LikeListModel.Response(users)
                        updateLogic?.updateUsers(newResponse)
                    }
                }
            }
        } else {
            // Do nothing
        }


    }

    /**
     * Method resposible for tranforming a jsonObject, that constains the response of the api request,
     * into a list of likes
     *
     * @param jsonObject jsonObject that contains the response data from the api
     *
     * @return list of likes
     */
    fun convertJsonToListOfLikes(jsonObject: JSONObject): List<Like>{
        val dataJson = jsonObject["data"] as JSONObject
        val likesJsonArray = dataJson["users"] as JSONArray

        val likesMutableList = mutableListOf<Like>()
        for (counter in 0 until likesJsonArray.length()){
            val jsonLike = likesJsonArray.getJSONObject(counter)
            val like = Like.createLikeFromJsonObject(jsonLike)
            likesMutableList.add(like)
        }
        return likesMutableList.toList()
    }

    /**
     * Method resposible for getting the users from a list of likes
     *
     * @param likes list of likes
     *
     * @return list of users
     */
    fun getUserFromLikes(likes: List<Like>): List<User> {
        val usersMutable = mutableListOf<User>()

        for(like in likes){
            val user = userManager?.get(like.userId!!)
            usersMutable.add(user!!)
        }

        return usersMutable.toList()
    }
}