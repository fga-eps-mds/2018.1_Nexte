package com.nexte.nexte.Entities

import com.nexte.nexte.Entities.Like.Like
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class LikeTest {

    @Before
    fun setUp() {}

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val likeRequest = Like.ServerRequest.LIKE.request
        val likesRequest = Like.ServerRequest.LIKES.request
        val unlikeRequest = Like.ServerRequest.UNLIKE.request

        // Asserts
        Assert.assertEquals("Like request is incorrect!", likeRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Likes request is incorrect!", likesRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Unlike request is incorrect!", unlikeRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successLikeConstructorTest() {

        // Prepare
        val id = "1234567890"
        val userId = "1234567890"
        val date = Date()

        // Call
        val like = Like(id, userId, date)

        // Asserts
        Assert.assertEquals(id, like.id)
        Assert.assertEquals(userId, like.userId)
        Assert.assertEquals(date, like.date)
    }

    @Test
    fun successLikeSetMethodsTest() {

        // Prepare
        val id = "1234567890"
        val userId = "1234567890"
        val date = Date()

        // Call
        val like = Like().apply {
            this.id = id
            this.userId = userId
            this.date = date
        }

        // Asserts
        Assert.assertNotNull(like)
        Assert.assertEquals(id, like.id)
        Assert.assertEquals(userId, like.userId)
        Assert.assertEquals(date, like.date)
    }

    @After
    fun tearDown() {}
}