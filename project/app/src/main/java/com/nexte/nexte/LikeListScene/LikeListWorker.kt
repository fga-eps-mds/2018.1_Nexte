package com.nexte.nexte.LikeListScene
import com.nexte.nexte.R

/**
 * Class responsible to do request from view, format response and call
 * completion to send data for called class
 */
class LikeListWorker {

    /**
     * Function to fetch like list data of server
     *
     * @param request
     * @param completion
     */
    fun getListLikesPlayers(request: LikeListModel.Request, completion:
    (LikeListModel.Response) -> Unit) {

        val response = LikeListModel.Response(this.generateLikeList())
        completion(response)
    }

    /**
     * Function to create fictions users to be use in fictional app mode
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