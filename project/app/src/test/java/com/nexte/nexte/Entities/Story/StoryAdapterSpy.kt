package com.nexte.nexte.Entities.Story
import java.util.*

abstract class StoryAdapterSpy: StoryAdapter{

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

    override fun updateLikes(story: Story, userId: String): Story? {
        val storyIndex = story.likesId.contains(userId)

        if (storyIndex) {
            return mockStory()
        } else {
            return null
        }

    }

    override fun addComment(story: Story, commentId: String): Story? {
        val mutableComments = story.commentsId.contains(commentId)

        if (mutableComments) {
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
        val commentsId: List<String> = listOf("1")
        val likesId: List<String> = listOf()

        val story = Story(id, winner, loser, date, commentsId, likesId)

        return story
    }
}