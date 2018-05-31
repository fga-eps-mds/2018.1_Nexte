package com.nexte.nexte.Entities.Comment
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class CommentManagerTest {

    var commentManager: CommentManager? = null

    @Before
    fun setUp() {
        CommentMocker.commentAdapter = CommentAdapterSpy()
        commentManager = CommentManager(commentAdapter = CommentAdapterSpy())
    }

    @Test
    fun testCreateInitialMocker(){
        val comments = commentManager?.createInitialMocker()

        Assert.assertEquals(comments is List<Comment>, true)
        Assert.assertTrue("List of comments have size between 0 and 20", comments!!.size >= 0 && comments.size <= 20)
    }

    @Test
    fun testGetSucess(){
        val identifier = "1"

        val comment = commentManager?.get(identifier)

        Assert.assertEquals(comment?.id, "1")
        Assert.assertEquals(comment?.userId, "2")
        Assert.assertEquals(comment?.comment, "")
    }

    @Test
    fun testGetNull(){
        val identifier = "2"

        val comment = commentManager?.get(identifier)

        Assert.assertNull(comment)
    }

    @Test
    fun testGetAll(){
        val comments = commentManager?.getAll()

        Assert.assertTrue("List of comments has the lenght of 4", comments!!.size == 4)
    }

    @Test
    fun testUpdate(){
        val comments = Comment(id = "1", userId = "2", comment = "3", date = Date())

        val commentsUpdated = commentManager?.update(comment = comments)

        Assert.assertEquals(commentsUpdated?.id, "1")
        Assert.assertEquals(commentsUpdated?.userId, "2")
        Assert.assertEquals(commentsUpdated?.comment, "")
    }

    @Test
    fun testUpdateNull(){
        val comments = Comment(id = "2", userId = "3", comment = "4", date = Date())

        val commentsUpdated = commentManager?.update(comment = comments)

        Assert.assertNull(commentsUpdated)
    }

    @Test
    fun testUpdateMany(){
        val comments = listOf(Comment(id = "1", userId = "2", comment = "3", date = Date()))

        val commentList = commentManager?.updateMany(comments = comments)

        Assert.assertEquals(commentList!![0].id, "1")
        Assert.assertEquals(commentList[0].userId, "2")
        Assert.assertEquals(commentList[0].comment, "")
    }

    @Test
    fun testUpdateManyNull(){
        val comments = listOf(Comment(id = "2", userId = "3", comment = "4", date = Date()))

        val commentList = commentManager?.updateMany(comments = comments)

        Assert.assertEquals(commentList?.size, 0)
    }

    @After
    fun tearDown() {
    }
}