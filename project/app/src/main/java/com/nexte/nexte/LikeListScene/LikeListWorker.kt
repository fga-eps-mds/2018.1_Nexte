package com.nexte.nexte.LikeListScene
import com.nexte.nexte.R
import java.util.*
import java.util.jar.Attributes

/**
 * Created by lorrany on 12/04/18.
 */
class LikeListWorker {

    fun getListLikesPlayers(request: LikeListModel.Request, completion:
    (LikeListModel.Response) -> Unit) {

        var response= LikeListModel.Response(this.generateLikeList())
        completion(response)

    }
        private fun generateLikeList(): MutableList<LikeListModel.Players> {

            val player1 = LikeListModel.Players("Alexandre", R.mipmap.ic_launcher, Date())
            val player2 = LikeListModel.Players("Lorrany", R.mipmap.ic_launcher, Date())
            val player3 = LikeListModel.Players("Letícia", R.mipmap.ic_launcher, Date())
            val player4 = LikeListModel.Players("Larissa", R.mipmap.ic_launcher, Date())

            val addPlayers: MutableList<LikeListModel.Players> = mutableListOf(player1, player2,
                                                                 player3, player4)
            return addPlayers
            }
        }