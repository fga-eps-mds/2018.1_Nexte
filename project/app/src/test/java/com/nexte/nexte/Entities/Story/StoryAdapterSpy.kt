package com.nexte.nexte.Entities.Story
import java.util.*

class StoryAdapterSpy: StoryAdapter {

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
        if(story.id != "1" && userId != "1"){
            return null
        }
        else{
            val retStory = mockStory()
            val storyIndex = retStory.likesId.contains("1")
            val mutableLikes = story.likesId.toMutableList()

            if(storyIndex){
                mutableLikes.remove("1")
            }
            else{
                mutableLikes.add("1")
            }

            retStory.likesId = mutableLikes.toList()
            return retStory
        }

    }

    override fun addComment(story: Story, commentId: String): Story? {
        if(story.id != "1"){
            return null
        }
        else{
            val retStory =  mockStory()
            val mutableComments = retStory.commentsId.toMutableList()
            mutableComments.add(commentId)
            retStory.commentsId = mutableComments.toList()
            return retStory
        }


    }

    override fun removeComment(story: Story, commentPos: Int): Story? {
        if(story.id != "1" && commentPos != 1){
            return null
        }
        else{
            val retStory =  mockStory()
            val mutableComments = retStory.commentsId.toMutableList()
            mutableComments.removeAt(1)
            retStory.commentsId = mutableComments.toList()
            return retStory
        }

    }



    private fun mockStory(): Story{
        val id: String? = "1"
        val winner: StoryPlayer? = StoryPlayer(userId = "1", setResult = 5)
        val loser: StoryPlayer? = StoryPlayer(userId = "2", setResult = 4)
        val date: Date? = Date()
        val commentsId: List<String> = listOf("A", "B", "C")
        val likesId: List<String> = listOf("1", "2", "3")

        val story = Story(id, winner, loser, date, commentsId, likesId)

        return story
    }
}