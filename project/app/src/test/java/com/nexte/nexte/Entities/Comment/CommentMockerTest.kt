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

        var commentId = CommentMocker.commentsId
        val randomComments = CommentMocker.generateComments(2)

        Assert.assertEquals(2, randomComments.size)
        Assert.assertEquals(commentId.toString(), randomComments[0])
        Assert.assertEquals((++commentId).toString(), randomComments[1])
    }

    @After
    fun tearDown() {
    }
}