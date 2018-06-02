package com.nexte.nexte.MatchScene

import com.nexte.nexte.Entities.Challenge.ChallengeAdapterSpy
import com.nexte.nexte.Entities.Challenge.ChallengeManager
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
    fun testFetchMatchDataWithNullMatch() {
        //prepare
        val request = MatchModel.InitScene.Request(null)

        //calls
        worker?.fetchMatchData(request = request, completion = {response ->
            //asserts
            assertEquals("Letícia", response.match.challenged.name)
            assertEquals("Alexandre Miguel", response.match.challenger.name)
        })

    }

    @Test
    fun successGetUpdateLogic(){
        //prepare and call
        val updateLogic = this.worker?.updateLogic

        //assert
        assertNotNull(updateLogic)
    }

    @Test
    fun testGenerateMatchData(){
        //prepare and call
        val returnedValue = worker?.generateMatchData()
        //assert
        assertEquals("Letícia", returnedValue?.challenged?.name)
        assertEquals("Alexandre Miguel", returnedValue?.challenger?.name)
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

    @Test
    fun testGenerateMatchResultWithUpdateLogic(){
        //prepare
        val request = MatchModel.SendMatchResult.Request()
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = null
        mock?.response = null
        //call
        this.worker?.generateMatchResult(request)

        //assert
        assertNull(mock?.response)
        this.worker?.updateLogic = updateLogic
    }

    @Test
    fun testDeclineMatchSuccess(){
        val request = MatchModel.DeclineChallengeRequest.Request("1")
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = updateLogic
        this.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        this.worker?.declineMatch(request)

        assertEquals(MatchModel.DeclineChallengeRequest
                .Status.SUCCESS, this.mock?.responseDecline?.status)
    }

    @Test
    fun testDeclineMatchError(){
        val request = MatchModel.DeclineChallengeRequest.Request("2")
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = updateLogic
        this.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        this.worker?.declineMatch(request)

        assertEquals(MatchModel.DeclineChallengeRequest
                .Status.ERROR, this.mock?.responseDecline?.status)
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

private class MockMatchUpdateWorkerLogic: MatchUpdateWorkerLogic{
    override fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response) {
        this.responseDecline = response
    }

    var responseDecline: MatchModel.DeclineChallengeRequest.Response? = null
    var response: MatchModel.SendMatchResult.Response? = null

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        this.response = response
    }
}
