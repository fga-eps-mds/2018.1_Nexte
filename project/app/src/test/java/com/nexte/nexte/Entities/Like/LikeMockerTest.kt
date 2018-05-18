package com.nexte.nexte.Entities.Like
import com.nexte.nexte.Entities.User.UserAdapterSpy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LikeMockerTest {

    @Before
    fun setUp() {
        val likeAdapterSpy = LikeAdapterSpy()
        LikeMocker.likeAdapter = likeAdapterSpy
        LikeMocker.userAdapter = UserAdapterSpy()
    }

    @Test
    fun testGenerateLikes(){

        val randomLikes = LikeMocker.generateLikes(1)

        Assert.assertEquals(1, randomLikes.size)
        Assert.assertEquals("1", randomLikes[0])
    }

    @After
    fun tearDown() {
    }
}