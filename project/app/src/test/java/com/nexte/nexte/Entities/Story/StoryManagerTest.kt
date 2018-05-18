package com.nexte.nexte.Entities.Story

import com.nexte.nexte.Entities.Comment.CommentAdapter
import com.nexte.nexte.Entities.Like.LikeAdapterSpy
import com.nexte.nexte.Entities.Like.LikeMocker
import com.nexte.nexte.Entities.User.UserAdapterSpy
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class StoryManagerTest {

    var storyManager: StoryManager? = null

    @Before
    fun setUp() {
        StoryMocker.userAdapter = UserAdapterSpy()
        StoryMocker.likeAdapter = LikeAdapterSpy()
        LikeMocker.userAdapter = UserAdapterSpy()
        LikeMocker.likeAdapter = LikeAdapterSpy()
        StoryMocker.commentAdapter = CommentAdapterSpy()
        storyManager = StoryManager(storyAdapter = StoryAdapterSpy())
    }

    @Test
    fun testCreateInitialMocker(){
        val stories = storyManager?.createInitialMocker()

        assertEquals(stories is List<Story>, true)
        assertTrue("User manager have size between 0 and 20", stories!!.size >= 0 && stories!!.size <= 20)
    }

    @Test
    fun testGetSucess(){
        val identifier = "1"

        val story = storyManager?.get(identifier)

        assertEquals(story?.id, "1")
        assertEquals(story?.winner?.userId, "1")
        assertEquals(story?.winner?.setResult, 5)
        assertEquals(story?.loser?.userId, "2")
        assertEquals(story?.loser?.setResult, 4)
    }

    @Test
    fun testGetNull(){
        val identifier = "2"

        val story = storyManager?.get(identifier)

        assertNull(story)
    }

    @Test
    fun testGetAll(){
        val stories = storyManager?.getAll()

        assertTrue("Stories has the lenght of 4", stories!!.size == 4)
    }

    @Test
    fun testUpdate(){
        val identifier = "1"
        val story = Story(id = "1", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf())

        val storyUpdated = storyManager?.update(story = story)

        assertEquals(storyUpdated?.id, "1")
        assertEquals(storyUpdated?.winner?.userId, "1")
        assertEquals(storyUpdated?.winner?.setResult, 5)
        assertEquals(storyUpdated?.loser?.userId, "2")
        assertEquals(storyUpdated?.loser?.setResult, 4)
    }

    @Test
    fun testUpdateNull(){
        val story = Story(id = "2", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf())

        val storyUpdated = storyManager?.update(story = story)

        assertNull(storyUpdated)
    }

    @Test
    fun testUpdateMany(){
        val identifier = "1"
        val stories = listOf(Story(id = "1", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf()))

        val storyList = storyManager?.updateMany(stories = stories)

        assertEquals(storyList!![0].id, "1")
        assertEquals(storyList!![0].winner?.userId, "1")
        assertEquals(storyList!![0].winner?.setResult, 5)
        assertEquals(storyList!![0].loser?.userId, "2")
        assertEquals(storyList!![0].loser?.setResult, 4)
    }

    @Test
    fun testUpdateManyNull(){
        val stories = listOf(Story(id = "2", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf()))

        val storyList = storyManager?.updateMany(stories = stories)

        assertEquals(storyList?.size, 0)
    }



    @After
    fun tearDown() {
    }
}