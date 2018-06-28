package com.nexte.nexte.Entities.Comment

import com.nexte.nexte.Entities.Like.Like
import com.nexte.nexte.HelpForRealm
import io.realm.Realm
import junit.framework.Assert
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentAdapterRealmTest: HelpForRealm() {

    var commentAdapterRealm: CommentAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithComment()
        this.commentAdapterRealm = CommentAdapterRealm()
    }

    @Test
    fun testGet(){
        this.commentAdapterRealm?.realm
        val comment = this.commentAdapterRealm?.get("9")

        assertEquals(comment!!.id, "9")
        assertEquals(comment.userId, "1")
        assertEquals(comment.comment, "Jogo top demais")
    }

    @Test
    fun testGetAll(){
        commentAdapterRealm?.realm
        val comments = commentAdapterRealm?.getAll()

        Assert.assertNotNull(comments)
    }

    @Test
    fun testUpdateOrInsert(){
        val comment = Comment("123", "124141", "hahahaha", Date(1234512))

        val commentInserted = this.commentAdapterRealm?.updateOrInsert(comment)

        Assert.assertNotNull(commentInserted)
    }

    @Test
    fun testDelete(){
        val comment = this.commentAdapterRealm?.delete("9")

        Assert.assertNull(comment)
    }

    @Test
    fun testGetCommentsFromStory(){
        val comments = this.commentAdapterRealm?.getCommentsFromStory(listOf("1", "2", "3"))

        Assert.assertNotNull(comments)
    }

    @Test
    fun testGetSetRealm() {
        this.commentAdapterRealm?.realm = Realm.getDefaultInstance()
        org.junit.Assert.assertNotNull(this.commentAdapterRealm?.realm)
    }

    @Test
    fun testConvertCommentToCommentRealm(){
        val comment = Comment("1", "1", "1", Date())

        val commentRealm = this.commentAdapterRealm?.convertCommentToCommentRealm(comment)

        assertEquals(comment.id, commentRealm!!.id)
        assertEquals(comment.comment, commentRealm.comment)
        assertEquals(comment.userId, commentRealm.userId)
        assertEquals(comment.date, commentRealm.date)
    }

    @Test
    fun testConvertCommentRealmToComment(){
        val commentRealm = CommentRealm("1", "1", "1", Date())

        val comment = this.commentAdapterRealm?.convertCommentRealmToComment(commentRealm)

        assertEquals(comment!!.id, commentRealm.id)
        assertEquals(comment.comment, commentRealm.comment)
        assertEquals(comment.userId, commentRealm.userId)
        assertEquals(comment.date, commentRealm.date)
    }

    @Test
    fun testConvertListCommentRealmToComment(){
        val commentRealm = CommentRealm("1", "1", "1", Date())

        val comment = this.commentAdapterRealm?.convertListCommentRealmToCommentList(listOf(commentRealm, commentRealm))

        assertEquals(comment!!.size, 2)
        assertEquals(comment[0].id, commentRealm.id)
        assertEquals(comment[0].comment, commentRealm.comment)
        assertEquals(comment[0].userId, commentRealm.userId)
        assertEquals(comment[0].date, commentRealm.date)
    }

    @After
    fun tearDown() {
    }
}