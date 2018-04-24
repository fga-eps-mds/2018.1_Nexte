package com.nexte.nexte.RankingScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RankingInteractorTest {

    private var interactor: RankingInteractor? = null
    private var mock: MockRankingPresentationLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingPresentationLogic()
        this.interactor = RankingInteractor(presenter = mock)
    }

    @Test
    fun testGetPlayersRanksForScene(){
        //prepare
        val request = RankingModel.Request()

        //call
        this.interactor?.getPlayersRanksForScene(request = request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockRankingPresentationLogic: RankingPresentationLogic{

    var passedHere = false

    override fun presentRanking(response: RankingModel.Response) {
        this.passedHere = true
    }
}