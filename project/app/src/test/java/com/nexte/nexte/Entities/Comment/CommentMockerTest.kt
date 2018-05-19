package com.nexte.nexte.Entities.Comment

import com.nexte.nexte.Entities.User.UserAdapterSpy
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CommentMockerTest {

    @Before
    fun setUp() {
        val commentAdapterSpy = CommentAdapterSpy()
        CommentMocker.commentAdapter = commentAdapterSpy
        CommentMocker.userAdapter = UserAdapterSpy()
    }

    @Test
    fun testGenerateComments(){

        val randomComments = CommentMocker.generateComments(1)

        Assert.assertEquals(1, randomComments.size)
        Assert.assertEquals("1", randomComments[0])
    }

    @After
    fun tearDown() {
    }
}