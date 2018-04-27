package com.nexte.nexte.CommentsScene

import com.nexte.nexte.R
import java.util.*

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class CommentsWorker {

    /**
     * Function to get comments data of server
     *
     * @param request Comments model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun getCommentsData (request: CommentsModel.Request,
                         completion: (CommentsModel.Response) -> Unit) {

        val response = CommentsModel.Response(this.generateCommentsList())
        completion(response)
    }

    /**
     * Function to create fictional comments to use in fictional app mode
     *
     * @return MutableList of addComments
     */
    private fun generateCommentsList(): MutableList<CommentsModel.Comment> {

        val player1 = CommentsModel.Player("Lorrany", R.mipmap.ic_launcher)
        val player2 = CommentsModel.Player("Alexandre", R.mipmap.ic_launcher)
        val player3 = CommentsModel.Player("Larissa", R.mipmap.ic_launcher)
        val player4 = CommentsModel.Player("Letícia", R.mipmap.ic_launcher)

        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
                                                Date(),
                                                player1)
        val comment2 = CommentsModel.Comment("Boa galera, vocês arrasaram",
                                                Date(),
                                                player2)
        val comment3 = CommentsModel.Comment("Isso mesmo, man. Que jogão",
                                                Date(),
                                                player3)
        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
                                                Date(),
                                                player4)

        val addComments: MutableList<CommentsModel.Comment> = mutableListOf(
                comment1,
                comment2,
                comment3,
                comment4)

        return addComments
    }
}