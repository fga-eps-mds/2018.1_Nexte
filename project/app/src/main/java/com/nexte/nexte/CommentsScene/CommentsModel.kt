package com.nexte.nexte.CommentsScene

/**
 * Created by leticia on 29/03/18.
 */

class CommentsModel {

    class Request {

        var game: String? = null
        var user: String? = null

        constructor(game: String?, user: String?) {
            this.game = game
            this.user = user
        }
    }

    class Response {

        var comment: String? = null
        var userName: String? = null
        var linkUserProfilePicture: String? = null
        var commentTime: String? = null
        var like: Boolean? = null

        constructor(comment: String?,
                    userName: String?,
                    linkUserProfilePicture: String?,
                    commentTime: String?,
                    like: Boolean?) {

            this.comment = comment
            this.userName = userName
            this.linkUserProfilePicture = linkUserProfilePicture
            this.commentTime = commentTime
            this.like = like
        }
    }

    class ViewModel {

        var message: String? = null

        constructor(message: String) {

            this.message = message
        }
    }
}