package com.nexte.nexte.Entities.Story

interface StoryAdapter {
    fun getAll(): List<Story>
    fun get(identifier: String): Story?
    fun updateOrInsert(story: Story): Story?
    fun updateLikes(story: Story, userId: String): Story?
    fun addComment(story: Story, commentId: String): Story?
    fun delete(identifier: String): Story?
    fun removeComment(story: Story, commentPos: Int): Story?
}