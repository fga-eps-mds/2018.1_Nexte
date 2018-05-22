package com.nexte.nexte.Entities.Story

class StoryManager(val storyAdapter: StoryAdapter = StoryAdapterRealm()) {
    fun createInitialMocker(): List<Story>{
        val storiesInMocker = StoryMocker.generateRandomStories()
        val insertedStories: MutableList<Story> = mutableListOf()

        for(story in storiesInMocker){
            val insertedStory = storyAdapter.updateOrInsert(story)
            insertedStory?.let {
                insertedStories.add(it)
            }
        }

        return insertedStories.toList()
    }

    fun get(identifier: String): Story? {
        return storyAdapter.get(identifier)
    }

    fun getAll(): List<Story> {
        return storyAdapter.getAll()
    }

    fun update(story: Story): Story? {
        return storyAdapter.updateOrInsert(story)
    }

    fun updateMany(stories: List<Story>): List<Story> {
        val newStories: MutableList<Story> = mutableListOf()
        for (story in stories) {
            val updatedUser = storyAdapter.updateOrInsert(story)
            updatedUser?.let {
                newStories.add(it)
            }
        }
        return newStories.toList()
    }

    fun delete(identifier: String): Story? {
        return storyAdapter.delete(identifier)
    }
}