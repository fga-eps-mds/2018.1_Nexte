package com.nexte.nexte.MatchScene

import com.nexte.nexte.Entities.Challenge.ChallengeAdapterSpy
import com.nexte.nexte.Entities.Challenge.ChallengeManager
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

        //assert
        assertNotNull(this.interactor)
    }

    @Test
    fun testSetWorker(){
        //prepare
        val worker = MatchWorker()

        //call
        this.interactor?.worker = worker

        //call
        assertEquals(this.interactor?.worker, worker)
    }

    @Test
    fun testNullGetInfoMatches() {
        //prepare
        val matchData = MatchModel.MatchData(
                MatchModel.MatchPlayer("larissa", 1),
                MatchModel.MatchPlayer("larissa2", 1))
        val request = MatchModel.InitScene.Request(matchData)
        val presenter = this.interactor?.presenter
        this.interactor?.presenter = null

        //call
        this.interactor?.getInfoMatches(request)

        //assert
        assertEquals(this.mock?.passedHere, false)
        this.interactor?.presenter = presenter

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
    fun testInteractor(){
        val interactor = MatchInteractor()

        assertNotNull(interactor)
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

    @Test
    fun testGetMatchResultWithoutPresenter(){
        //prepare
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.ERROR)
        val presenter = this.interactor?.presenter
        this.interactor?.presenter = null
        mock?.response2 = null
        //call
        this.interactor?.getMatchResultResponse(response = response)

        //assert
        assertNull(mock?.response2)
        this.interactor?.presenter = presenter
    }

    @Test
    fun testGetMatchResult2WithoutPresenter(){
        //prepare
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)
        val presenter = this.interactor?.presenter
        this.interactor?.presenter = null
        mock?.response2 = null
        //call
        this.interactor?.getMatchResultResponse(response = response)

        //assert
        assertNull(mock?.response2)
        this.interactor?.presenter = presenter
    }

    @Test
    fun testDeclineMatchResultResponse(){
        val response = MatchModel.DeclineChallengeRequest
                .Response(MatchModel.DeclineChallengeRequest.Status.SUCCESS)
        this.interactor?.declineMatchResultResponse(response)
        assertEquals(this.mock?.responseDecline?.status, response.status)
    }

    @Test
    fun testDeclineMatchResult(){
        val request = MatchModel.DeclineChallengeRequest
                .Request("1")
        this.interactor?.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())
        this.interactor?.declineMatchResult(request)

        assertEquals(this.mockk?.response?.status, MatchModel.DeclineChallengeRequest.Status.SUCCESS)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

}

private class MMockMatchPresentationLogic: MatchPresentationLogic{
    override fun presentDeclineMatch(response: MatchModel.DeclineChallengeRequest.Response) {
        this.responseDecline = response
    }

    var responseDecline: MatchModel.DeclineChallengeRequest.Response? = null
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
    override fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response) {
        this.response = response
    }

    var response: MatchModel.DeclineChallengeRequest.Response? = null
    var mock: MMockMatchPresentationLogic? = null

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        if(response.status == MatchModel.SendMatchResult.Status.SUCESSED){
            mock?.presentSuccessMessageForMatchResult(response)
        }else{
            mock?.presentErrorMessageForMatchResult(response)
        }
    }
}