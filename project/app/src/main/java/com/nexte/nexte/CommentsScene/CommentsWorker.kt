package com.nexte.nexte.CommentsScene

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.nexte.nexte.CommentsScene.CommentsModel.GetCommentsRequest.Response
import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

interface CommentsWorkerUpdateLogic {

    fun updateComment(response: CommentsModel.GetCommentsRequest.Response)
    fun updateNewComment(response: CommentsModel.PublishCommentRequest.Response)
    fun updateDeleteComment(response: CommentsModel.DeleteCommentRequest.Response)
    fun updateSendComplaint(response: CommentsModel.ComplaintRequest.Response)
}

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class CommentsWorker {

    var updateLogic: CommentsWorkerUpdateLogic? = null
    var commentsManager: CommentManager? = null
    var storyManager: StoryManager? = null
    /**
     * Variable created to simulate mocked data to be implemented on Package mocker
     */

    /**
     * Function to get comments data of server
     *
     * @param request Comments model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun getCommentsData (request: CommentsModel.GetCommentsRequest.Request) {
        val story = storyManager?.get(request.storyId)
        val commentsIdsMutable = mutableListOf<String>()
        for (commentId in story?.commentsId!!) {
            commentsIdsMutable.add(commentId)
        }
        val comments = commentsManager?.getCommentsFromStory(commentsIdsMutable.toList())
        val response = CommentsModel.GetCommentsRequest.Response(comments!!.toMutableList())
        updateLogic?.updateComment(response)

        if (UserSingleton.userType != UserType.MOCKED) {
            val url = "http://10.0.2.2:3000/comments/" + request.storyId
            url.httpGet().responseJson { _, _, result ->
                when(result){
                    is Result.Failure -> {
                        println(result.getException())
                    }

                    is Result.Success -> {
                        val json = result.get()
                        var commentsList = convertJsonToListOfComments(json.obj())
                        commentsManager?.updateMany(commentsList)
                        val newResponse = CommentsModel.GetCommentsRequest.Response(
                                commentsList.toMutableList())
                        updateLogic?.updateComment(newResponse)
                    }
                }
            }
        } else {
            //Do nothing
        }
    }

    /**
     * Method resposible for tranforming a jsonObject, that contains the response of the api request,
     * into a list of comments
     *
     * @param jsonObject jsonObject that contains the response data from the api
     *
     * @return list of comments
     */
    fun convertJsonToListOfComments(jsonObject: JSONObject) : List<Comment> {
        val dataJson = jsonObject["data"] as JSONObject
        val commentsJsonArray = dataJson["comments"] as JSONArray

        val commentsMutableList = mutableListOf<Comment>()
        for (counter in 0 until commentsJsonArray.length()){
            val jsonComment = commentsJsonArray.getJSONObject(counter)
            val comment = Comment.createCommentFromJsonObject(jsonComment)
            commentsMutableList.add(comment)
        }
        return commentsMutableList.toList()
    }

    /**
     * Function responsible to set new comment that contains a message, updated date and an author
     * and passed the new comment to response
     * @param request Comments model from PublishCommentRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun setNewComment (request: CommentsModel.PublishCommentRequest.Request) {

        val message = request.commentToPost
        val today = Date()
        val author = "1"
        var newComment = Comment(idComment, author, message, today)
        newComment = commentsManager?.update(newComment)!!
        val response = CommentsModel.PublishCommentRequest.Response(newComment)

        updateLogic?.updateNewComment(response)
    }

    /**
     * Function responsible to submit an alert message when the user wants to report a comment
     * and passed the message to response.
     * @param request Comments model from ComplaintRequest that contains need information to
     * send to server
     * @param completion Method to call on parent class
     */

    fun sendComplaint (request: CommentsModel.ComplaintRequest.Request) {

        val serverCode = okMessage
        val response = CommentsModel.ComplaintRequest.Response(serverCode)

        updateLogic?.updateSendComplaint(response)

    }

    /**
     * Function responsible to delete the comment at the position set by request
     * and to send the list [commentsMockedData] after deletion as the response to interactor
     *
     * @param request Position of the comment to be deleted
     * @param completion List of unformatted list of comments after deletion
     */
    fun getToDeleteComment (request: CommentsModel.DeleteCommentRequest.Request){

        val comments = this.commentsManager?.delete(request.commentIdentifier.toString())
        val response = CommentsModel.DeleteCommentRequest.Response(comments!!)

       updateLogic?.updateDeleteComment(response)
    }
    companion object {
        const val okMessage = 200
        const val idComment = "108"
    }
}