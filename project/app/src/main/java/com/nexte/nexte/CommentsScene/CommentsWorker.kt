package com.nexte.nexte.CommentsScene

/**
 * Created by leticia on 29/03/18.
 */


class CommentsWorker {

    constructor() {}

    fun getCommentsData (request: CommentsModel.Request, completion: (CommentsModel.Response) -> Unit) {

        val game: String? = null
        val user: String? = null

        var comment: String? = null
        var userName: String? = null
        var linkUserProfilePicture: String? = null
        var commentTime: String? = null
        var like: Boolean? = null

        if(game.equals("Gandalf vs Saruman") && user.equals("Frodo_Bolseiro")) {
            var comment: String = "Esse jogo foi digno da terra média"
            var userName: String = "Frodo_do_condado"
            var linkUserProfilePicture: String = "https://www.nexte.com.br/pictures/user/frodo_do_condado/avatar156x156.jpg"
            var commentTime: String = "16:50"
            var like: Boolean = true
        } else {
            var comment: String = ""
            var userName: String = ""
            var linkUserProfilePicture: String = ""
            var commentTime: String = ""
            var like: Boolean = false
        }
        var response: CommentsModel.Response = CommentsModel.Response(comment, userName, linkUserProfilePicture,
                                                                      commentTime, like)
        completion(response)
    }
}