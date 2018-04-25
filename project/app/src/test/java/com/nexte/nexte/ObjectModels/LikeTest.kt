package com.nexte.nexte.ObjectModels

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class LikeTest {

    @Before
    fun setUp() {}

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

    @After
    fun tearDown() {}
}