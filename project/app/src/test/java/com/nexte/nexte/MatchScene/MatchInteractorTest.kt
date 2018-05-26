package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MatchInteractorTest {

    private var mock: MMockMatchPresentationLogic? = null
    private var interactor: MatchInteractor? = null
    private var mockk: MockkMatchUpdateWorkerLogic? = null

    @Before
    fun setUp() {
        this.mock = MMockMatchPresentationLogic()
        this.interactor = MatchInteractor(presenter = mock)
        this.mockk = MockkMatchUpdateWorkerLogic()
        this.mockk?.mock = mock
        this.interactor?.worker?.updateLogic = mockk
    }

    @Test
    fun testGetInfoMatches() {
        //prepare
        val matchData = MatchModel.MatchData(
                MatchModel.MatchPlayer("larissa", 1),
                MatchModel.MatchPlayer("larissa2", 1))
        val request = MatchModel.InitScene.Request(matchData)

        //call
        this.interactor?.getInfoMatches(request)

        //assert
        assertEquals(this.mock?.passedHere, true)

    }

    @Test
    fun testGetMatchResult(){
        val request = MatchModel.SendMatchResult.Request()

        this.interactor?.getMatchResult(request)

        assertEquals(MatchModel.SendMatchResult.Status.SUCESSED, mock?.response2?.status)
    }

    @Test
    fun testGetMatchResultSucess(){
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)

        this.interactor?.getMatchResultResponse(response = response)

        assertEquals(MatchModel.SendMatchResult.Status.SUCESSED, mock?.response2?.status)
    }

    @Test
    fun testGetMatchResultError(){
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.ERROR)

        this.interactor?.getMatchResultResponse(response = response)

        assertEquals(MatchModel.SendMatchResult.Status.ERROR, mock?.response2?.status)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

}

private class MMockMatchPresentationLogic: MatchPresentationLogic{

    var passedHere = false
    var response2 : MatchModel.SendMatchResult.Response? = null

    override fun presentMatch(response: MatchModel.InitScene.Response) {
        this.passedHere = true
    }

    override fun presentErrorMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        this.response2 = response
    }

    override fun presentSuccessMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        this.response2 = response
    }
}

private class MockkMatchUpdateWorkerLogic: MatchUpdateWorkerLogic{

    var mock: MMockMatchPresentationLogic? = null

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        if(response.status == MatchModel.SendMatchResult.Status.SUCESSED){
            mock?.presentSuccessMessageForMatchResult(response)
        }else{
            mock?.presentErrorMessageForMatchResult(response)
        }
    }
}