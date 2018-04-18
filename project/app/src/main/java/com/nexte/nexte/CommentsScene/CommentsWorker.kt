package com.nexte.nexte.CommentsScene

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class CommentsWorker {

    /**
     * Function to get comments data of server
     *
     * @param request comments model request that contains needed informations to send to server
     * @param completion Method to call on parent class
     */
    fun getCommentsData (request: CommentsModel.Request,
                         completion: (CommentsModel.Response) -> Unit) {

        val game = request.game
        val user = request.user

        val comment: String
        val userName: String
        val linkUserProfilePicture: String
        val commentTime: String
        val like: Boolean

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

        val response: CommentsModel.Response = CommentsModel.Response(comment,
                userName,
                linkUserProfilePicture,
                commentTime,
                like)

        completion(response)
    }
}