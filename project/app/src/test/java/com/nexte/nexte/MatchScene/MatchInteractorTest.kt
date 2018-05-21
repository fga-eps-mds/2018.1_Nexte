package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MatchInteractorTest {

    private var mock: MMockMatchPresentationLogic? = null
    private var interactor: MatchBusinessLogic? = null

    @Before
    fun setUp() {
        this.mock = MMockMatchPresentationLogic()
        this.interactor = MatchInteractor(presenter = mock)
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

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

}

private class MMockMatchPresentationLogic: MatchPresentationLogic{

    var passedHere = false

    override fun presentMatch(response: MatchModel.InitScene.Response) {
        this.passedHere = true
    }
}