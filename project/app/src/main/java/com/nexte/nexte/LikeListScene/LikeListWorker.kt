package com.nexte.nexte.LikeListScene
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R

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

    /**
     * Function to fetch like list data of server
     *
     * @param request
     */
    fun getListLikesPlayers(request: LikeListModel.Request){
        val player = userManager?.get(request.request)
        val response = LikeListModel.Response(this.convertUserToLikeListPlayer(user = player))
        this.updateLogic?.updateUsers(response = response)
    }

    /**
     * Function to create fictional users to use in fictional app mode
     *
     * @return MutableList of addPLayers
     */
    private fun convertUserToLikeListPlayer(user: User?): MutableList<LikeListModel.Players> {
        val likeListModelPlayer = LikeListModel.Players(name = user!!.name, time = "", photo = R.mipmap.ic_launcher)

//        var allStories = StoryManager().getAll()
//        println("Stories Amount: " + allStories.size)
//        for (story in allStories){
//            println("New Story")
//            println("Story Id: " + story.id)
//            println("Winner User Id: " + story.winner?.userId)
//            println("Winner Set Result: " + story.winner?.setResult)
//            println("Loser User Id: " + story.loser?.userId)
//            println("Loser Set Result: " + story.loser?.setResult)
//            println("Date: " + story.date)
//
//            println("Comments amount: " + story.commentsId.size)
//            for(commentId in story.commentsId){
//                println("New Comment")
//                println("Comment Ids: " + commentId)
//            }
//
//            println("Likes amount: " + story.likesId.size)
//            for(likeId in story.likesId){
//                println("New Like")
//                println("Like Ids: " + likeId)
//            }
//        }


        //HELENA DEIXEI COMENTADO ISSO AQUI SÓ PARA NÃO QUEBRAR OS TESTE UNITÁRIOS
        //PARA TESTAR SE A COMMENT MANAGER ESTÁ FUNCIONANDO É SÓ DESCOMENTAR O CÓDIGO ABAIXO
//        var allComments = CommentManager().getAll()
//        println("Comments amout: " + allComments.size)
//        for (comment in allComments){
//            println("New Comment")
//            println("Comment id: " + comment.id)
//            println("Comment date: " + comment.date)
//            println("Comment userId: " + comment.userId)
//            println("Comment text: " + comment.comment)
//        }

        return mutableListOf(likeListModelPlayer)
    }
}