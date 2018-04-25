package com.nexte.nexte.ObjectModels

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class CommentTest {
    @Before
    fun setUp() {}

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val addCommentRequest = Comment.ServerRequest.ADD_COMMENT.request
        val commentsRequest = Comment.ServerRequest.COMMENTS.request
        val deleteCommentRequest = Comment.ServerRequest.DELETE_COMMENT.request
        val reportCommentRequest = Comment.ServerRequest.REPORT_COMMENT.request

        // Asserts
        Assert.assertEquals("Add comment request is incorrect!", addCommentRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Comments request is incorrect!", commentsRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Delete comment request is incorrect!", deleteCommentRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Report comment request is incorrect!", reportCommentRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successCommentConstructorTest() {

        // Prepare
        val id = "1234567890"
        val userId = "1234567890"
        val date = Date()
        val commentText = "This is a comment text for test!"

        // Call
        val comment = Comment(id, userId, commentText, date)

        // Asserts
        Assert.assertEquals(id, comment.id)
        Assert.assertEquals(userId, comment.userId)
        Assert.assertEquals(commentText, comment.comment)
        Assert.assertEquals(date, comment.date)
    }

    @After
    fun tearDown() {}
}