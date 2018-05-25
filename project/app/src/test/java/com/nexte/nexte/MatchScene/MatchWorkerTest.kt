package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*


class MatchWorkerTest {
    private var worker: MatchWorker? = null
    private var mock: MockMatchUpdateWorkerLogic? = null

    @Before
    fun setUp() {
        this.worker = MatchWorker()
        mock = MockMatchUpdateWorkerLogic()
        this.worker?.updateLogic = mock
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

    @Test
    fun testGenerateMatchResult(){
        //prepare
        val request = MatchModel.SendMatchResult.Request()

        //call
        this.worker?.generateMatchResult(request)

        //assert
        assertEquals(mock?.response?.status, MatchModel.SendMatchResult.Status.SUCESSED)

    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

private class MockMatchUpdateWorkerLogic: MatchUpdateWorkerLogic{

    var response: MatchModel.SendMatchResult.Response? = null

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        this.response = response
    }
}
