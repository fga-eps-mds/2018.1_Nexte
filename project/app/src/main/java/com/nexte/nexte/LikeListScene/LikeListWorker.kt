package com.nexte.nexte.LikeListScene

/**
 * Created by lorrany on 12/04/18.
 */
class LikeListWorker {

    fun getListLikesPlayers(request: LikeListModel.Request, completion:
                           (LikeListModel.Response) -> Unit) {

        var players: List<LikeListModel.ListPlayers>
        var response: LikeListModel.Response = LikeListModel.Response(players)

        completion(response)
    }
}