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


    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

}

private class MockChallengePresentationLogic: FormatPlayersPresentationLogic{

    var passedHere = false

    override fun formatPlayersToChallenge(response: ChallengeModel.ShowRankingPlayersRequest.Response) {
        this.passedHere = true
    }
}