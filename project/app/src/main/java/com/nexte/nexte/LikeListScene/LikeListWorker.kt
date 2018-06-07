    package com.nexte.nexte.LikeListScene

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
        val likeListModelPlayer = LikeListModel.Players(name = user!!.name, photo = R.mipmap.ic_launcher)

        return mutableListOf(likeListModelPlayer)
    }
}