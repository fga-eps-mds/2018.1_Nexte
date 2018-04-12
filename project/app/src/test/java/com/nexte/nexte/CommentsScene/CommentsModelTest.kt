package com.nexte.nexte.CommentsScene

import com.nexte.nexte.ShowProfileScene.ShowProfileModel
import com.nexte.nexte.ShowProfileScene.ShowProfileWorker
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CommentsModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun successRequest(){
        //prepare
        val game = "1"
        val user = "luis"

        //call
        val request = CommentsModel.Request(game = "1", user = "luis")

        //assert
        assertEquals(game, request.game)
        assertEquals(user, request.user)
    }

    @Test
    fun successResponse(){
        //prepare
        val comment = "Top"
        val userName = "luis"
        val linkUserProfilePicture = ""
        val commentTime = "15:30"
        val like = true

        //call
        val response = CommentsModel.Response(comment = comment, userName = userName, linkUserProfilePicture = linkUserProfilePicture, commentTime = commentTime, like = like)

        //assert
        assertEquals(comment, response.comment)
        assertEquals(userName, response.userName)
        assertEquals(linkUserProfilePicture, response.linkUserProfilePicture)
        assertEquals(commentTime, response.commentTime)
        assertEquals(like, response.like)
    }

    @Test
    fun successViewModel(){
        //prepare
        val message = "asidnbasi"

        //call
        val viewModel = CommentsModel.ViewModel(message = "asidnbasi")

        //assert
        assertEquals(message, viewModel.message)
    }

    @After
    fun tearDown() {
    }
}