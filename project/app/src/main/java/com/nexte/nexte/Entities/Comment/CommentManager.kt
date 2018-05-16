package com.nexte.nexte.Entities.Comment
import com.nexte.nexte.Entities.Challenge.Challenge

class CommentManager {
    fun get(identifier: String): Comment? {
        return commentAdapter.get(identifier)
    }

    fun getAll(): List<Comment> {
        return commentAdapter.getAll()
    }

    fun update(comment: Comment): Comment? {
        return commentAdapter.updateOrInsert(comment)
    }


}