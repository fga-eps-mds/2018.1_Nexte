package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CommentsWorkerTest {

    private var worker: CommentsWorker? = null

    @Before
    fun setUp() {
        this.worker = CommentsWorker()
    }

    @Test
    fun successGetCommentsData(){
        //prepare
        val request = CommentsModel.Request(game = "Gandalf vs Saruman", user = "Frodo_Bolseiro")
        val comment = "Esse jogo foi digno da terra média"
        val userName = "Frodo_do_condado"
        val linkUserProfilePicture = "https://www.nexte.com.br/pictures/user/frodo_do_condado/avatar156x156.jpg"
        val commentTime = "16:50"
        val like = true

        //call
        this.worker?.getCommentsData(request = request, completion = {response ->
            //assert
            assertEquals(comment, response.comment)
            assertEquals(userName, response.userName)
            assertEquals(linkUserProfilePicture, response.linkUserProfilePicture)
            assertEquals(commentTime, response.commentTime)
            assertEquals(like, response.like)
        })
    }

    @Test
    fun failGetCommentsData(){
        //prepare
        val request = CommentsModel.Request(game = "Frodo vs Gollum", user = "Frodo_Bolseiro")
        val comment = ""
        val userName = ""
        val linkUserProfilePicture = ""
        val commentTime = ""
        val like = false


        //call
        this.worker?.getCommentsData(request = request, completion = {response ->
            //assert
            assertEquals(comment, response.comment)
            assertEquals(userName, response.userName)
            assertEquals(linkUserProfilePicture, response.linkUserProfilePicture)
            assertEquals(commentTime, response.commentTime)
            assertEquals(like, response.like)
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}