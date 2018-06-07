package com.nexte.nexte.Entities.Comment

/**
 * Interface to allow Comment Entity to assume different values according
 * to Realm
 */
interface CommentAdapter {
    fun getAll(): List<Comment>
    fun get(identifier: String): Comment?
    fun updateOrInsert(challenge: Comment): Comment?
    fun delete(identifier: String): Comment?
    fun getCommentsFromStory(commentsIds: List<String>): List<Comment>?
}