package com.nexte.nexte.LikeListScene
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R

/**
 * Class responsible to do request from view, format response and call
 * completion to send data for called class
 */
class LikeListWorker {

    var responseLogic: UpdateResponseLogic? = null

    /**
     * Function to fetch like list data of server
     *
     * @param request
     */
    fun getListLikesPlayers(request: LikeListModel.Request){
        val response = LikeListModel.Response(this.generateLikeList(request = request))
        this.responseLogic?.getUsers(response = response)
    }

    /**
     * Function to create fictional users to use in fictional app mode
     *
     * @return MutableList of addPLayers
     */
    private fun generateLikeList(request: LikeListModel.Request): MutableList<LikeListModel.Players> {
        val player = UserManager().get(identifier = request.request)
        val likeListModelPlayer = LikeListModel.Players(name = player!!.name, time = "", photo = R.mipmap.ic_launcher)
        return mutableListOf(likeListModelPlayer)
    }
}