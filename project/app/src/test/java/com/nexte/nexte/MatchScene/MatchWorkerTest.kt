package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class MatchWorkerTest {
    private var worker: MatchWorker? = null

    @Before
    fun setUp() {
        this.worker = MatchWorker()
    }

    @Test
    fun testFetchMatchDatas() {
        //prepare
        val match  = MatchModel.MatchData(
                MatchModel.MatchPlayer("larissa", 1),
                MatchModel.MatchPlayer("larissa2", 1))
        val request = MatchModel.InitScene.Request(match)

        //calls
        worker?.fetchMatchData(request = request, completion = {response ->
        //asserts
            assertEquals("larissa", response.match.challenged.name)
            assertEquals("larissa2", response.match.challenger.name)
        })

    }

    @After
    fun tearDown() {
        this.worker = null
    }
}