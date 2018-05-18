package com.nexte.nexte.Entities.Like
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


class LikeManagerTest {

    var likeManager: LikeManager? = null

    @Before
    fun setUp() {
        LikeMocker.likeAdapter = LikeAdapterSpy()
        likeManager = LikeManager(likeAdapter = LikeAdapterSpy())
    }

    @Test
    fun testCreateInitialMocker(){
        val likes = likeManager?.createInitialMocker()

        Assert.assertEquals(likes is List<Like>, true)
        Assert.assertTrue("User have size between 0 and 20", likes!!.size >= 0 && likes.size <= 20)
    }

    @Test
    fun testGetSucess(){
        val identifier = "1"

        val like = likeManager?.get(identifier)

        Assert.assertEquals(like?.id, "1")
        Assert.assertEquals(like?.userId, "2")
    }

    @Test
    fun testGetNull(){
        val identifier = "2"

        val like = likeManager?.get(identifier)

        Assert.assertNull(like)
    }

    @Test
    fun testGetAll(){
        val likes = likeManager?.getAll()

        Assert.assertTrue("List of likes has the lenght of 4", likes!!.size == 4)
    }

    @Test
    fun testUpdate(){
        val identifier = "1"
        val likes = Like(id = "1", userId = "2", date = Date())

        val likeUpdated = likeManager?.update(like = likes)

        Assert.assertEquals(likeUpdated?.id, "1")
        Assert.assertEquals(likeUpdated?.userId, "2")
    }

    @Test
    fun testUpdateNull(){
        val like = Like(id = "2", userId = "3", date = Date())

        val likesUpdated = likeManager?.update(like = like)

        Assert.assertNull(likesUpdated)
    }

    @Test
    fun testUpdateMany(){
        val identifier = "1"
        val likes = listOf(Like(id = "1", userId = "2", date = Date()))

        val likeList = likeManager?.updateMany(likes = likes)

        Assert.assertEquals(likeList!![0].id, "1")
        Assert.assertEquals(likeList!![0].userId, "2")
    }

    @Test
    fun testUpdateManyNull(){
        val likes = listOf(Like(id = "2", userId = "3", date = Date()))

        val likeList = likeManager?.updateMany(likes = likes)

        Assert.assertEquals(likeList?.size, 0)
    }

    @After
    fun tearDown() {
    }
}