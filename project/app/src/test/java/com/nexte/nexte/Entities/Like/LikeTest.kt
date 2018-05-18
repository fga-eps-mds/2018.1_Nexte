package com.nexte.nexte.Entities.Like
import io.realm.RealmList
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class LikeTest {

    var likes: List<String> = RealmList()

    @Before
    fun setup(){
        likes = listOf("1", "2", "3")
    }

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val likeRequest = Like.ServerRequest.LIKE.request

        // Asserts
        Assert.assertEquals("Like request is incorrect!", likeRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successLikeConstructorTest() {

        // Prepare
        val id = "1234567890"
        val userId = "0987654321"
        val date = Date()

        // Call
        val like = Like(id, userId, date)

        // Asserts
        Assert.assertEquals("Id of like is incorrect!", id, like.id)
        Assert.assertEquals("Id of user is incorrect!", userId, like.userId)
        Assert.assertEquals("Date is incorrect!", date, like.date)
    }

    @Test
    fun successStorySetMethodsTest() {

        // Prepare
        val id = "1234567890"
        val userId = "0987654321"
        val date = Date()

        // Call
        val like = Like()
        like.id = id
        like.userId = userId
        like.date = date

        // Asserts
        Assert.assertNotNull(like)
        Assert.assertEquals("Id of like is incorrect!", id, like.id)
        Assert.assertEquals("Id of user is incorrect!", userId, like.userId)
        Assert.assertEquals("This date is incorrect!", date, like.date)
    }

    @After
    fun tearDown() {
        likes = RealmList()
    }
}


