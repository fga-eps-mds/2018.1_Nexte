package com.nexte.nexte.LikeListScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListWorkerTest {

    var worker: LikeListWorker? = null

    @Before
    fun setUp() {
        this.worker = LikeListWorker()
    }

    @Test
    fun testGetListLikesPlayers(){
        //prepare
        val requestMessage = "123"
        val request = LikeListModel.Request(request = requestMessage)

        //call
        this.worker?.getListLikesPlayers(request = request, completion = { response ->
            //assert
            assertEquals(response.players.size, 4)
            assertEquals(response.players[0].name, "Alexandre")
            assertEquals(response.players[1].time, String())
        })


    }

    @After
    fun tearDown() {
        this.worker = null
    }
}