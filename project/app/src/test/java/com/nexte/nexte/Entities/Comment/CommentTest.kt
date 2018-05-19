package com.nexte.nexte.Entities.Comment
import io.realm.RealmList
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class LikeTest {

    var comments: List<String> = RealmList()

    @Before
    fun setup(){
        comments = listOf("1", "2", "3")
    }

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val commentsRequest = Comment.ServerRequest.COMMENTS.request
        val addCommentRequest = Comment.ServerRequest.ADD_COMMENT.request
        val deleteCommentRequest = Comment.ServerRequest.DELETE_COMMENT.request
        val reportCommentRequest = Comment.ServerRequest.REPORT_COMMENT.request

        // Asserts
        Assert.assertEquals("Comment request is incorrect!", commentsRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Add comment request is incorrect!", addCommentRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Delete comment request is incorrect!", deleteCommentRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Report comment request is incorrect!", reportCommentRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successCommentConstructorTest() {

        // Prepare
        val id = "1234567890"
        val userId = "0987654321"
        val comment = "Comment"
        val date = Date()

        // Call
        val comments = Comment(id, userId, comment, date)

        // Asserts
        Assert.assertEquals(id, comments.id)
        Assert.assertEquals(userId, comments.userId)
        Assert.assertEquals(comment, comments.comment)
        Assert.assertEquals(date, comments.date)
    }

    @Test
    fun successStorySetMethodsTest() {

        // Prepare
        val id = "1234567890"
        val userId = "0987654321"
        val comment = "Comment"
        val date = Date()

        // Call
        val comments = Comment()
        comments.id = id
        comments.userId = userId
        comments.comment = comment
        comments.date = date

        // Asserts
        Assert.assertNotNull(comments)
        Assert.assertEquals("Id of comment is incorrect!", id, comments.id)
        Assert.assertEquals("Id of user is incorrect!", userId, comments.userId)
        Assert.assertEquals("Comment is incorrect!", comment, comments.comment)
        Assert.assertEquals("This date is incorrect!", date, comments.date)
    }

    @After
    fun tearDown() {
        comments = RealmList()
    }
}


