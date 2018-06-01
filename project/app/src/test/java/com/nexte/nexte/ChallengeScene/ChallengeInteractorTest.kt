package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class ChallengeInteractorTest: HelpForRealm() {

    private var interactor: ChallengeInteractor?= null
    private var mock: MockChallengePresentationLogic?= null

    @Before
    fun setUp() {
        super.setUpWithUser()

        this.mock = MockChallengePresentationLogic()
        this.interactor = ChallengeInteractor()
        this.interactor?.presenter = mock
    }

    @Test
    fun successTestConstructor(){
        //prepare and call
        val interactor = ChallengeInteractor()

        //assert
        assertNotNull(interactor)
    }

    @Test
    fun requestPlayersToChallengeTest(){
        //prepare
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(6)
        val expectedResult = true

        //call
        interactor?.requestPlayersToChallenge(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successRequestChallengedUser(){
        //prepare
        val request = ChallengeModel.SelectPlayerForChallengeRequest.Request(6)
        val expectedResult = true

        //call
        interactor?.requestChallengedUser(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successRequestMessageForChallenger() {
        //prepare
        val request = ChallengeModel.ChallengeButtonRequest.Request("larissa")
        val expectedResult = true

        //call
        interactor?.requestChallenger(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successGetWorker() {
        //prepare

        //call
        val worker = this.interactor?.worker

        //assert
        assertNotNull(worker)
    }

    @Test
    fun successSetWorker() {
        //prepare
        val worker = ChallengeWorker()

        //call
        this.interactor?.worker = worker

        //assert
        assertEquals(worker, this.interactor?.worker)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
        super.tearDownRealm()
    }
}

private class MockChallengePresentationLogic: ChallengePresentationLogic{

    var passedHere = false

    override fun formatPlayersToChallenge(response: ChallengeModel.ShowRankingPlayersRequest.Response) {
        passedHere = true
    }

    override fun formatExpandedChallengedInfo(response: ChallengeModel.SelectPlayerForChallengeRequest.Response) {
        passedHere = true
    }

    override fun formatMatch(response: ChallengeModel.ChallengeButtonRequest.Response) {
        passedHere = true
    }

    override fun formatNoPlayersMessage() {
        passedHere = true
    }
}