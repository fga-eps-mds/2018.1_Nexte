package com.nexte.nexte.Entities.Comment
import io.realm.Realm
import io.realm.kotlin.where

/**
 * Class to allow adaptations of data and sending to Realm.
 * */
class CommentAdapterRealm : CommentAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): Comment? {
        val commentRealm = realm.where<CommentRealm>().equalTo("id", identifier).findFirst()
        return convertCommentRealmToComment(commentRealm)
    }

    override fun getAll(): List<Comment> {
        val commentRealmResults = realm.where<CommentRealm>().findAll()
        return convertListCommentRealmToCommentList(commentRealmResults)

    }

    override fun updateOrInsert(comment: Comment): Comment? {
        convertCommentToCommentRealm(comment).let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return comment
        }

    }

    override fun delete(identifier: String): Comment? {
        val commentRealm = realm.where<CommentRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val comment = convertCommentRealmToComment(commentRealm.first())
        commentRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return comment
    }

    override fun getCommentsFromStory(commentsIds: List<String>): List<Comment>? {
        var commentsMutable = mutableListOf<Comment>()
        for (commentId in commentsIds) {
            val commentRealm = realm.where<CommentRealm>().equalTo("id", commentId).findFirst()
            if(commentRealm != null){
                convertCommentRealmToComment(commentRealm)?.let {
                    commentsMutable.add(it)
                }
            }
        }
        return commentsMutable.toList()
    }

    fun convertCommentRealmToComment(commentRealm: CommentRealm?) : Comment? {

        var comment: Comment? = null

        commentRealm?.let {
            val commentId = it.id
            val userId = it.userId
            val text = it.comment
            val date = it.date

            comment = Comment(id = commentId, userId = userId, comment = text, date = date)
        }

        return comment
    }

    fun convertCommentToCommentRealm(comment: Comment?): CommentRealm? {

        var commentRealm: CommentRealm? = null
        comment?.let {
            val id = it.id
            val userId = it.userId
            val text = it.comment
            val date = it.date

            commentRealm = CommentRealm(id = id, userId = userId, comment = text, date = date)
        }

        return commentRealm
    }

    fun convertListCommentRealmToCommentList(commentRealm: List<CommentRealm>) : List<Comment> {
        val comments: MutableList<Comment> = mutableListOf()

        if (commentRealm.size > 0) {
            for (currentCommentRealm in commentRealm) {
                convertCommentRealmToComment(currentCommentRealm)?.let {
                    comments.add(it)
                }
            }
        }

        return comments.toList()
    }
}