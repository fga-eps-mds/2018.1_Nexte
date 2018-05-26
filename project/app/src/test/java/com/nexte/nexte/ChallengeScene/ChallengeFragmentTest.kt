package com.nexte.nexte.ChallengeScene

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChallengeFragmentTest {

    private var fragment: ChallengeFragment?= null
    private var mock: MockChallengeViewBusinessLogic?= null

    @Before
    fun setUp(){
        fragment = ChallengeFragment()
        mock = MockChallengeViewBusinessLogic()
        fragment?.interactor = mock
    }

    @Test
    fun successGetPlayerInfo(){
        //prepare
        val playerRanking = 3
        val request = ChallengeModel.SelectPlayerForChallengeRequest.Request(playerRanking)
        val expectedResult = true

        //call
        fragment?.getPlayerInfo(request)

        //assert
        assertEquals(mock?.hasBeenCalled, expectedResult)
    }


    @Test
    fun testSetupChallengeScene(){
        //prepare //call
        this.fragment?.setupChallengeScene()

        //assert
        assertNotNull(this.fragment?.interactor)
    }

    @After
    fun tearDown(){
        mock = null
    }


    private class MockChallengeViewBusinessLogic : ChallengeBusinessLogic {

        var hasBeenCalled = false

        override fun requestChallengedUser(request: ChallengeModel.SelectPlayerForChallengeRequest.Request) {
           hasBeenCalled = true
        }

        override fun requestPlayersToChallenge(request: ChallengeModel.ShowRankingPlayersRequest.Request) {
            hasBeenCalled = true
        }

        override fun requestChallenger(request: ChallengeModel.ChallengeButtonRequest.Request) {
            hasBeenCalled = true
        }

    }
}