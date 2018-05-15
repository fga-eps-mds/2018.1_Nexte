package com.nexte.nexte.Entities.Comment

interface CommentAdapter {
    fun getAll(): List<Comment>
    fun get(identifier: String): Comment?
    fun updateOrInsert(challenge: Comment): Comment?
    fun delete(identifier: String): Comment?
}