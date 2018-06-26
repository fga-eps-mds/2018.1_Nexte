package com.nexte.nexte.Entities.Comment

import com.nexte.nexte.HelpForRealm
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