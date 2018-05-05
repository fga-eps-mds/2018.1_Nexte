package com.nexte.nexte.ChallengeScene

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChallengeInteractorTest {

    private var interactor: ChallengeInteractor?= null
    private var mock: MockChallengePresentationLogic?= null

    @Before
    fun setUp() {
        this.mock = MockChallengePresentationLogic()
        this.interactor = ChallengeInteractor()
        this.interactor?.presenter = mock
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

        //test
        interactor?.requestChallengedUser(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

}

private class MockChallengePresentationLogic: ChallengePresentationLogic{

    var passedHere = false

    override fun formatPlayersToChallenge(response: ChallengeModel.ShowRankingPlayersRequest.Response) {
        this.passedHere = true
    }

    override fun formatExpandedChallengedInfo(response: ChallengeModel.SelectPlayerForChallengeRequest.Response) {
        this.passedHere = true
    }

    override fun formatMessage(response: ChallengeModel.ChallengeButtonRequest.Response) {
        this.passedHere
    }
}