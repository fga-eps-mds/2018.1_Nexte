package com.nexte.nexte.Entities.Story
import java.util.*

class StoryAdapterSpy: StoryAdapter{

    override fun delete(identifier: String): Story? {
        if (identifier == "1") {
            return mockStory()
        } else {
            return null
        }
    }

    override fun get(identifier: String): Story? {
        if (identifier == "1") {
            return mockStory()
        } else {
            return null
        }
    }

    override fun getAll(): List<Story> {
        val storyList: MutableList<Story> = mutableListOf(mockStory(), mockStory(), mockStory(), mockStory())
        return storyList.toList()
    }

    override fun updateOrInsert(story: Story): Story? {
        if (story.id == "1") {
            return mockStory()
        } else {
            return null
        }
    }

    private fun mockStory(): Story{
        val id: String? = "1"
        val winner: StoryPlayer? = StoryPlayer(userId = "1", setResult = 5)
        val loser: StoryPlayer? = StoryPlayer(userId = "2", setResult = 4)
        val date: Date? = Date()
        val commentsId: List<String> = listOf()
        val likesId: List<String> = listOf("1")

        val story = Story(id, winner, loser, date, commentsId, likesId)

        return story
    }
}