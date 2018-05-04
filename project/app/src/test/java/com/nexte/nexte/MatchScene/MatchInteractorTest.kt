package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MatchInteractorTest {

    private var mock: MockMatchPresentationLogic? = null
    private var interactor: MatchBusinessLogic? = null

    @Before
    fun setUp() {
        this.mock = MockMatchPresentationLogic()
        this.interactor = MatchInteractor(presenter = mock)
    }

    @Test
    fun testGetInfoMatches() {
        //prepare
        val request = MatchModel.InitScene.Request("abcd123")

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

private class MockMatchPresentationLogic: MatchPresentationLogic{

    var passedHere = false

    override fun presentMatch(response: MatchModel.InitScene.Response) {
        this.passedHere = true
    }
}