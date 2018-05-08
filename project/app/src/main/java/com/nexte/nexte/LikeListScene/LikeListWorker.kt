package com.nexte.nexte.LikeListScene
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
        val response = LikeListModel.Response(this.generateLikeList())
        this.responseLogic?.getUsers(response = response)
    }

    /**
     * Function to create fictional users to use in fictional app mode
     *
     * @return MutableList of addPLayers
     */
    private fun generateLikeList(): MutableList<LikeListModel.Players> {

        val player1 = LikeListModel.Players("Alexandre", R.mipmap.ic_launcher, String())
        val player2 = LikeListModel.Players("Lorrany", R.mipmap.ic_launcher, String())
        val player3 = LikeListModel.Players("Letícia", R.mipmap.ic_launcher, String())
        val player4 = LikeListModel.Players("Larissa", R.mipmap.ic_launcher, String())

        val addPlayers: MutableList<LikeListModel.Players> = mutableListOf(
                player1,
                player2,
                player3,
                player4)
        return addPlayers
    }
}