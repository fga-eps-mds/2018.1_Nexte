package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class RankingFragmentTest {

    var fragment: RankingFragment? = null

    @Before
    fun setUp() {
        this.fragment = RankingFragment()
    }

    @Test
    fun testSetupRankingScene(){
        //prepare
        this.fragment?.setupRankingScene()

        //call

        //assert
        assertNotNull(this.fragment?.interactor)
        assertNotNull(this.fragment?.interactor?.presenter)
    }

    @Test
    fun testCreateGetPlayersRequest(){
        //prepare
        this.fragment?.setupRankingScene()
        val mock = MockRankingsPresentationLogic()
        this.fragment?.interactor?.presenter = mock

        //call
        this.fragment?.createGetPlayersRequest()

        //assert
        assertNotNull(mock.response)
    }

    @After
    fun tearDown() {
    }
}

private class MockRankingsPresentationLogic: RankingPresentationLogic{

    var response: RankingModel.Response? = null

    override fun presentRanking(response: RankingModel.Response) {
        this.response = response
    }
}