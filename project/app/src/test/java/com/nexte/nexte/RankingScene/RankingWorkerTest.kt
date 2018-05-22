package com.nexte.nexte.RankingScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RankingWorkerTest {

    var worker: RankingWorker? = null

    @Before
    fun setUp() {
        this.worker = RankingWorker()
    }

    @Test
    fun testGetUsersInRanking(){
        //prepare
        val request = RankingModel.Request()

        //call
        this.worker?.getUsersInRanking(request = request, completion = { response ->
            //assert
            assertEquals(response.players.size, 29)
            assertEquals(response.players[0].name, "Helena")
            assertEquals(response.players[3].wins, 7)
            assertEquals(response.players[1].rankingPosition, 2)
            assertEquals(response.players[9].playerCategory, "Profissional")
            assertEquals(response.players[15].lastGame, "ontem")
            assertEquals(response.players[11].efficiency, "90%")
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}