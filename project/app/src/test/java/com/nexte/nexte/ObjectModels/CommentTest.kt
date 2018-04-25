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