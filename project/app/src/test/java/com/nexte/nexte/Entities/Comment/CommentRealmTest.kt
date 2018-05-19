package com.nexte.nexte.Entities.Comment
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
        val comment = ""
        val date = Date()
        //call
        val commentRealm = CommentRealm(id, userId, comment, date)

        //assert
        Assert.assertEquals(id, commentRealm.id)
        Assert.assertEquals(userId, commentRealm.userId)
        Assert.assertEquals(comment, commentRealm.comment)
        Assert.assertEquals(date, commentRealm.date)
    }

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val commentsRealmRequest = CommentRealm.ServerRequest.COMMENTS.request
        val addCommentsRealmRequest = CommentRealm.ServerRequest.ADD_COMMENT.request
        val deleteCommentsRealmRequest = CommentRealm.ServerRequest.DELETE_COMMENT.request
        val reportCommentsRealmRequest = CommentRealm.ServerRequest.REPORT_COMMENT.request

        // Asserts
        Assert.assertEquals("Comment request is incorrect!", commentsRealmRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Add comment request is incorrect!", addCommentsRealmRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Delete comment request is incorrect!", deleteCommentsRealmRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Report comment request is incorrect!", reportCommentsRealmRequest.keys, setOf("route", "method"))
    }

    @Test
    fun testStoryRealmSet(){
        //prepare
        val id = "1"
        val userId = "1"
        val comment = ""
        val date = Date()

        //call
        val commentRealm = CommentRealm()
        commentRealm.id = id
        commentRealm.userId = userId
        commentRealm.comment = comment
        commentRealm.date = date

        //assert
        Assert.assertEquals(id, commentRealm.id)
        Assert.assertEquals(userId, commentRealm.userId)
        Assert.assertEquals(comment, commentRealm.comment)
        Assert.assertEquals(date, commentRealm.date)
    }

    @After
    fun tearDown() {
    }
}