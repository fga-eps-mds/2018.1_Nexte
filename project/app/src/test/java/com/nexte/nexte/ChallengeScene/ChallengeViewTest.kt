package com.nexte.nexte.ChallengeScene

import android.test.AndroidTestCase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChallengeViewTest {

    private var view: ChallengeView?= null
    private var mock: MockChallengeViewBusinessLogic?= null

    @Before
    fun setUp(){
        view = ChallengeView()
        mock = MockChallengeViewBusinessLogic()
        view?.interactor = mock
    }

    @Test
    fun successGetPlayerInfo(){
        //prepare
        val playerRank = 3
        val request = ChallengeModel.SelectPlayerForChallengeRequest.Request(playerRank)
        val expectedResult = true

        //call
        view?.getPlayerInfo(request)

        //assert
        assertEquals(mock?.hasBeenCalled, expectedResult)
    }

    @Test fun sucessGetPlayerToChallenge(){
        //prepare
        val expectedResult = true

        //call
        view?.getPlayerToChallenge()

        //assert
        assertEquals(mock?.hasBeenCalled, expectedResult)
    }

    @Test
    fun testSetupChallengeScene(){
        //prepare //call
        this.view?.setupChallengeScene()

        //assert
        assertNotNull(this.view?.interactor)
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
    }
}