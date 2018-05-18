package com.nexte.nexte.Entities.Like
import com.nexte.nexte.Entities.Story.*
import com.nexte.nexte.Entities.User.UserAdapterSpy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


class LikeManagerTest {

    var likeManager: LikeManager? = null

    @Before
    fun setUp() {
        LikeMocker.likeAdapter = UserAdapterSpy()
        storyManager = StoryManager(storyAdapter = StoryAdapterSpy())
    }

    @Test
    fun testCreateInitialMocker(){
        val stories = storyManager?.createInitialMocker()

        Assert.assertEquals(stories is List<Story>, true)
        Assert.assertTrue("User manager have size between 0 and 20", stories!!.size >= 0 && stories!!.size <= 20)
    }

    @Test
    fun testGetSucess(){
        val identifier = "1"

        val story = storyManager?.get(identifier)

        Assert.assertEquals(story?.id, "1")
        Assert.assertEquals(story?.winner?.userId, "1")
        Assert.assertEquals(story?.winner?.setResult, 5)
        Assert.assertEquals(story?.loser?.userId, "2")
        Assert.assertEquals(story?.loser?.setResult, 4)
    }

    @Test
    fun testGetNull(){
        val identifier = "2"

        val story = storyManager?.get(identifier)

        Assert.assertNull(story)
    }

    @Test
    fun testGetAll(){
        val stories = storyManager?.getAll()

        Assert.assertTrue("Stories has the lenght of 4", stories!!.size == 4)
    }

    @Test
    fun testUpdate(){
        val identifier = "1"
        val story = Story(id = "1", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf())

        val storyUpdated = storyManager?.update(story = story)

        Assert.assertEquals(storyUpdated?.id, "1")
        Assert.assertEquals(storyUpdated?.winner?.userId, "1")
        Assert.assertEquals(storyUpdated?.winner?.setResult, 5)
        Assert.assertEquals(storyUpdated?.loser?.userId, "2")
        Assert.assertEquals(storyUpdated?.loser?.setResult, 4)
    }

    @Test
    fun testUpdateNull(){
        val story = Story(id = "2", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf())

        val storyUpdated = storyManager?.update(story = story)

        Assert.assertNull(storyUpdated)
    }

    @Test
    fun testUpdateMany(){
        val identifier = "1"
        val stories = listOf(Story(id = "1", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf()))

        val storyList = storyManager?.updateMany(stories = stories)

        Assert.assertEquals(storyList!![0].id, "1")
        Assert.assertEquals(storyList!![0].winner?.userId, "1")
        Assert.assertEquals(storyList!![0].winner?.setResult, 5)
        Assert.assertEquals(storyList!![0].loser?.userId, "2")
        Assert.assertEquals(storyList!![0].loser?.setResult, 4)
    }

    @Test
    fun testUpdateManyNull(){
        val stories = listOf(Story(id = "2", date = Date(), likesId = listOf(), loser = StoryPlayer(), winner = StoryPlayer(), commentsId = listOf()))

        val storyList = storyManager?.updateMany(stories = stories)

        Assert.assertEquals(storyList?.size, 0)
    }



    @After
    fun tearDown() {
    }
}