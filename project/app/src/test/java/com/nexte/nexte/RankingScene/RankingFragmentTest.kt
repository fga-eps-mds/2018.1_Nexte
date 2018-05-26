package com.nexte.nexte.RankingScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

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