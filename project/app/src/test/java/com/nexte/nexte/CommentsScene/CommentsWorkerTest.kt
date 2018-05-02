package com.nexte.nexte.CommentsScene

import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentsWorkerTest {

    private var worker: CommentsWorker? = null

    @Before
    fun setUp() {
        this.worker = CommentsWorker()
    }

    @Test
    fun successGetCommentsData(){
        //prepare
        val request = CommentsModel.GetCommentsRequest.Request("identifier")
        val player1 = CommentsModel.Player("Lorrany", 1)
        val player4 = CommentsModel.Player("Letícia", 2)
        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
                Date(),
                player1)
        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
                Date(),
                player4)


        //call
        this.worker?.getCommentsData(request, {response ->

            //assert
            assertEquals(comment1.comment, response.comments[0].comment)
            assertEquals(comment4.comment, response.comments[3].comment)

            assertEquals(player1.name, response.comments[0].author.name)
            assertEquals(player4.name, response.comments[3].author.name)
        })
    }

    @Test
    fun successSetNewComment() {
        //prepare
        val comment = "Jogo Fantástico"
        val request = CommentsModel.PublishCommentRequest.Request(comment)
        val today = Date()
        val author = CommentsModel.Player(UserSingleton.getUserInformations().name, 3)
        val newComment = CommentsModel.Comment(comment, today, author)

        //call
        worker?.setNewComment(request, {response ->
            //assert
            assertEquals(response.newComment.author.name,newComment.author.name)
            assertEquals(response.newComment.comment, newComment.comment)
        })
    }


    @After
    fun tearDown() {
        this.worker = null
    }
}