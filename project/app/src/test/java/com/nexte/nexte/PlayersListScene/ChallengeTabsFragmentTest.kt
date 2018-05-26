package com.nexte.nexte.PlayersListScene

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChallengeTabsFragmentTest {

    private var tabsFragment: ChallengeTabsFragment?= null
    private var mock: MockChallengeViewBusinessLogic?= null

    @Before
    fun setUp(){
        tabsFragment = ChallengeTabsFragment()
        mock = MockChallengeViewBusinessLogic()
        tabsFragment?.interactor = mock
    }

    @Test
    fun successGetPlayerInfo(){
        //prepare
        val playerRanking = 3
        val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(playerRanking)
        val expectedResult = true

        //call
        tabsFragment?.getPlayerInfo(request)

        //assert
        assertEquals(mock?.hasBeenCalled, expectedResult)
    }


    @Test
    fun testSetupChallengeScene(){
        //prepare //call
        this.tabsFragment?.setupChallengeScene()

        //assert
        assertNotNull(this.tabsFragment?.interactor)
    }

    @After
    fun tearDown(){
        mock = null
    }


    private class MockChallengeViewBusinessLogic : ChallengeBusinessLogic {

        var hasBeenCalled = false

        override fun requestChallengedUser(request: PlayersListModel.SelectPlayerForChallengeRequest.Request) {
           hasBeenCalled = true
        }

        override fun requestPlayersToChallenge(request: PlayersListModel.ShowRankingPlayersRequest.Request) {
            hasBeenCalled = true
        }

        override fun requestChallenger(request: PlayersListModel.ChallengeButtonRequest.Request) {
            hasBeenCalled = true
        }

    }
}