package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserMocker
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
            assertEquals(response.players.size, UserMocker.generateUsers().size)
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}