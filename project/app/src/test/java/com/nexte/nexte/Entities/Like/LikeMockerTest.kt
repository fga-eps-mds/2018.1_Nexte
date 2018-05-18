package com.nexte.nexte.Entities.Like
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LikeMockerTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testGenerateLikes(){
        val likeAdapterSpy = LikeAdapterSpy()
        LikeMocker.likeAdapter = likeAdapterSpy
        val randomLikes = LikeMocker.generateLikes()
        Assert.assertTrue("This game have between 0 and 20 likes", randomLikes.size >= 0 && randomLikes?.size <= 20)

        for (like in randomLikes){

            Assert.assertTrue("Each game has between 0 and 6 likes", like.likes.size >= 0 && like.likes.size <= 6)
        }
    }

    @After
    fun tearDown() {
    }
}