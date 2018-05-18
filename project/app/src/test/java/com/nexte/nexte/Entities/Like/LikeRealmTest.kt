package com.nexte.nexte.Entities.Like
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


class StoryRealmTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testInstatiateStoryRealm(){
        //prepare
        val id = "1"
        val userId = "1"
        val date = Date()
        //call
        val likeRealm = LikeRealm(id, userId, date)

        //assert
        Assert.assertEquals(id, likeRealm.id)
        Assert.assertEquals(userId, likeRealm.userId)
        Assert.assertEquals(date, likeRealm.date)
    }

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val likeRealmRequest = LikeRealm.ServerRequest.LIKE.request
        val likesRealmRequest = LikeRealm.ServerRequest.LIKES.request
        val unlikesRealmRequest = LikeRealm.ServerRequest.UNLIKE.request

        // Asserts
        Assert.assertEquals("Like request is incorrect!", likeRealmRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Likes request is incorrect!", likesRealmRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Unlikes request is incorrect!", unlikesRealmRequest.keys, setOf("route", "method"))

    }

    @Test
    fun testStoryRealmSet(){
        //prepare
        val id = "1"
        val userId = "1"
        val date = Date()

        //call
        val likeRealm = LikeRealm()
        likeRealm.id = id
        likeRealm.userId = userId
        likeRealm.date = date

        //assert
        Assert.assertEquals(id, likeRealm.id)
        Assert.assertEquals(userId, likeRealm.userId)
        Assert.assertEquals(date, likeRealm.date)
    }

    @After
    fun tearDown() {
    }
}