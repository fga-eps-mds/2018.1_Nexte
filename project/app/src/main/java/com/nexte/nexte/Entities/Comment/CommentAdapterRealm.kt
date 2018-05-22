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
        return convertCommentRealmToComment(commentRealm!!)
    }

    override fun getAll(): List<Comment> {
        val commentRealmResults = realm.where<CommentRealm>().findAll()
        return convertListCommentRealmToCommentList(commentRealmResults)

    }

    override fun updateOrInsert(comment: Comment): Comment? {
        convertCommentToCommentRealm(comment)?.let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return comment
        }

        return null
    }

    override fun delete(identifier: String): Comment? {

        return null
    }

    private fun convertCommentRealmToComment(commentRealm: CommentRealm) : Comment {

        val commentId = commentRealm.id

        val userId = commentRealm.userId

        val comment = commentRealm.comment

        val date = commentRealm.date

        val comments = Comment(id = commentId, userId = userId, comment = comment, date = date)
        return comments
    }

    private fun convertListCommentRealmToCommentList(commentRealm: List<CommentRealm>) : List<Comment> {
        val comments: MutableList<Comment> = mutableListOf()
        for(commentRealm in commentRealm) {
            convertCommentRealmToComment(commentRealm)?.let {
                comments.add(it)
            }
        }

        return comments.toList()
    }

    private fun convertCommentToCommentRealm(comment: Comment): CommentRealm {

        val id = comment.id

        val userId = comment.userId

        val comments = comment.comment

        val date = comment.date

        val commentRealm = CommentRealm(id = id, userId = userId, comment = comments, date = date)
        return commentRealm
    }

}