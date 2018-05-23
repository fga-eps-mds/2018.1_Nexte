package com.nexte.nexte.Entities.Story

interface StoryAdapter {
    fun getAll(): List<Story>
    fun get(identifier: String): Story?
    fun updateOrInsert(story: Story): Story?
    fun delete(identifier: String): Story?
}