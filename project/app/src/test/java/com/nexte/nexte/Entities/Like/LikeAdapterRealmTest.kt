package com.nexte.nexte.Entities.Like

import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class LikeAdapterRealmTest: HelpForRealm() {

    var likeAdapterRealm : LikeAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithLike()
        this.likeAdapterRealm = LikeAdapterRealm()
    }

    @Test
    fun testGet(){
        this.likeAdapterRealm?.realm
        val like = this.likeAdapterRealm?.get("9")

        assertEquals("9", like!!.id)
        assertEquals("1", like.userId)
    }

    @Test
    fun testConvertLikeToLikeRealm(){
        val like = Like("1", "1", Date())

        val likeRealm = this.likeAdapterRealm?.convertLikeToLikeRealm(like)

        assertEquals(like.id, likeRealm!!.id)
        assertEquals(like.userId, likeRealm.userId)
        assertEquals(like.date, likeRealm.date)
    }

    @Test
    fun testConvertLikeRealmToLike(){
        val likeRealm = LikeRealm("1", "1", Date())

        val like = this.likeAdapterRealm?.convertLikeRealmToLike(likeRealm)

        assertEquals(likeRealm.id, like!!.id)
        assertEquals(likeRealm.userId, like.userId)
        assertEquals(likeRealm.date, like.date)
    }

    @Test
    fun testConvertListLikeRealmToLikeList(){
        val likeRealm = LikeRealm("1", "1", Date())

        val likes = this.likeAdapterRealm?.convertListLikeRealmToLikeList(listOf(likeRealm, likeRealm))

        assertEquals(likes!!.size , 2)
        assertEquals(likes[0].id, likeRealm.id)
        assertEquals(likes[0].userId, likeRealm.userId)
        assertEquals(likes[0].date, likeRealm.date)
    }

    @After
    fun tearDown() {
    }
}