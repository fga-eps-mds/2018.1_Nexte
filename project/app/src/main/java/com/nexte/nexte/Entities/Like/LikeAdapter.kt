package com.nexte.nexte.Entities.Like

interface LikeAdapter {
    fun getAll(): List<Like>
    fun get(identifier: String): Like?
    fun updateOrInsert(like: Like): Like?
    fun delete(identifier: String): Like?
    fun getLikesFromStory(likesIds: List<String>): List<Like>?
}