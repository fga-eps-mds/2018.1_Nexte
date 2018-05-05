package com.nexte.nexte.MatchScene

import com.nexte.nexte.ChallengeScene.ChallengeWorker
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
        val request = MatchModel.InitScene.Request("1234abcd")

        //calls
        worker?.fetchMatchData(request = request, completion = {response ->
        //asserts
            assertEquals("Letícia", response.match.challenged.name)
            assertEquals("Alexandre Miguel", response.match.challenger.name)
        })

    }

    @After
    fun tearDown() {
        this.worker = null
    }
}