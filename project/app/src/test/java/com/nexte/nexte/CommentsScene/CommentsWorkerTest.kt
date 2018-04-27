package com.nexte.nexte.CommentsScene

import com.nexte.nexte.R
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
        val request = CommentsModel.Request("identifier")
        val player1 = CommentsModel.Player("Lorrany", R.mipmap.ic_launcher)
        val player4 = CommentsModel.Player("Letícia", R.mipmap.ic_launcher)
        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
                Date(),
                player1)
        val comment4 = CommentsModel.Comment("Uhuuul, lindas!!",
                Date(),
                player4)


        //call
        this.worker?.getCommentsData(request, {response ->

            //assert
            assertEquals(comment1.comment, response.comments[0].comment)
            assertEquals(comment4.comment, response.comments[3].comment)

            assertEquals(player1.name, response.comments[0].author.name)
            assertEquals(player4.name, response.comments[3].author.name)

            assertEquals(player1.photo, response.comments[0].author.photo)
            assertEquals(player4.photo, response.comments[3].author.photo)

        })
    }


    @After
    fun tearDown() {
        this.worker = null
    }
}