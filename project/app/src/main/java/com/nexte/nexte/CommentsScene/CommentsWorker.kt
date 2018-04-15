package com.nexte.nexte.CommentsScene

/**
 * Created by leticia on 29/03/18.
 */


class CommentsWorker {


    fun getCommentsData (request: CommentsModel.Request, completion: (CommentsModel.Response) -> Unit) {

        val game = request.game
        val user = request.user

        var comment = ""
        var userName = ""
        var linkUserProfilePicture = ""
        var commentTime = ""
        var like = false

        if(game.equals("Gandalf vs Saruman") && user.equals("Frodo_Bolseiro")) {
            comment = "Esse jogo foi digno da terra média"
            userName = "Frodo_do_condado"
            linkUserProfilePicture = "https://www.nexte.com.br/pictures/user/frodo_do_condado/avatar156x156.jpg"
            commentTime = "16:50"
            like = true
        } else {
            comment = ""
            userName = ""
            linkUserProfilePicture = ""
            commentTime = ""
            like = false
        }
        var response: CommentsModel.Response = CommentsModel.Response(comment, userName, linkUserProfilePicture,
                                                                      commentTime, like)
        completion(response)
    }
}