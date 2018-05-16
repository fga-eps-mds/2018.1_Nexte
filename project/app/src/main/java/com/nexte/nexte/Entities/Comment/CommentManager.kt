package com.nexte.nexte.Entities.Comment

import com.nexte.nexte.Mocker.CommentsMocker

class CommentManager(val commentAdapter: CommentAdapter = CommentAdapterRealm()) {
    fun get(identifier: String): Comment? {
        return commentAdapter.get(identifier)
    }

    fun getAll(): List<Comment> {
        return commentAdapter.getAll()
    }

    fun update(comment: Comment): Comment? {
        return commentAdapter.updateOrInsert(comment)
    }

    fun updateMany(comments: List<Comment>): List<Comment> {
        val newComments: MutableList<Comment> = mutableListOf()
        for (comment in comments) {
            val updatedComment = commentAdapter.updateOrInsert(comment)
            updatedComment?.let {
                newComments.add(it)
            }
        }
        return newComments.toList()
    }

    fun delete(identifier: String): Comment? {
        return commentAdapter.delete(identifier)
    }

    fun createInitialMocker(): List<Comment> {
        val commentsInMocker = CommentsMocker.generateCommentsList() // nao achei a mocker dele
        val insertedComment: MutableList<Comment> = mutableListOf()

        for (comment in commentsInMocker) {

            val insertedComments = commentAdapter.updateOrInsert(comment)
            insertedComments?.let {
                insertedComments.add(it)
            }
        }

        return insertedComment.toList()
    }



}