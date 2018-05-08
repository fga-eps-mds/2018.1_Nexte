package com.nexte.nexte.Entities

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Like.Like
import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryPlayer
import java.util.*
import io.realm.RealmList
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert

class StoryTest {

    var likes: RealmList<Like> = RealmList()
    var comments: RealmList<Comment> = RealmList()

    @Before
    fun setUp() {

        likes = RealmList(
                Like("1", "1", Date()),
                Like("2", "2", Date()),
                Like("3", "3", Date())
        )
        comments = RealmList(
                Comment("1", "1", "First Comment", Date()),
                Comment("2", "2", "Second Comment", Date()),
                Comment("3", "3", "Third Comment", Date())
        )
    }


    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val storiesRequest = Story.ServerRequest.STORIES.request

        // Asserts
        Assert.assertEquals("Stories request is incorrect!", storiesRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successStoryConstructorTest() {

        // Prepare
        val id = "1234567890"
        val winner = StoryPlayer("1", 0)
        val loser = StoryPlayer("2", 1)
        val date = Date()

        // Call
        val story = Story(id, winner, loser, date, comments, likes)

        // Asserts
        Assert.assertEquals("Id of story is incorrect!", id, story.id)
        Assert.assertEquals("Winner isn't the same!", winner, story.winner)
        Assert.assertEquals("Loser isn't the same!", loser, story.loser)
        Assert.assertEquals("Date is incorrect!", date, story.date)
        Assert.assertEquals("Commet list isn't the same!", comments, story.comments)
        Assert.assertEquals("Like list isn't the same!", likes, story.likes)
    }

    @Test
    fun successStorySetMethodsTest() {

        // Prepare
        val id = "1234567890"
        val winner = StoryPlayer("1", 0)
        val loser = StoryPlayer("2", 1)
        val date = Date()

        // Call
        val story = Story()
        story.id = id
        story.winner = winner
        story.loser = loser
        story.date = date
        story.likes = this.likes
        story.comments = this.comments


        // Asserts
        Assert.assertNotNull(story)
        Assert.assertEquals("Id of story is incorrect!", id, story.id)
        Assert.assertEquals("Winner isn't the same!", winner, story.winner)
        Assert.assertEquals("Loser isn't the same!", loser, story.loser)
        Assert.assertEquals("Date is incorrect!", date, story.date)
        Assert.assertEquals("Commet list isn't the same!", this.comments, story.comments)
        Assert.assertEquals("Like list isn't the same!", this.likes, story.likes)
    }



    @After
    fun tearDown() {
        likes = RealmList()
        comments = RealmList()
    }
}