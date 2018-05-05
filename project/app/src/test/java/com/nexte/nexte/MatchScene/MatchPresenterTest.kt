package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MatchPresenterTest {

    private var mock: MockMatchDisplayLogic? = null
    private var presenter: MatchPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockMatchDisplayLogic()
        this.presenter = MatchPresenter()
        this.presenter?.viewController = mock
    }

    @Test
    fun testPresentMatch() {
        //prepare
        val testFormattedMatchData = MatchModel.FormattedMatchData("Lele", 1, "Ale", 2, MatchModel.SetsNumber.One)
        val response = MatchModel.InitScene.Response(match = MatchModel.MatchData(challenged = MatchModel.MatchPlayer("Lele", 1),
                                                     challenger = MatchModel.MatchPlayer("Ale", 2), numberOfSets = MatchModel.SetsNumber.One ))

        //call
        this.presenter?.presentMatch(response = response)

        //assert
        assertEquals(testFormattedMatchData.challengedName, this.mock?.matchDataFormatted?.challengedName)
        assertEquals(testFormattedMatchData.challengedPhoto, this.mock?.matchDataFormatted?.challengedPhoto)
        assertEquals(testFormattedMatchData.challengerName, this.mock?.matchDataFormatted?.challengerName)
        assertEquals(testFormattedMatchData.challengerPhoto, this.mock?.matchDataFormatted?.challengerPhoto)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockMatchDisplayLogic : MatchDisplayLogic {

    var matchDataFormatted: MatchModel.FormattedMatchData? = null

    override fun displayMatch(viewModel: MatchModel.InitScene.ViewModel) {
        this.matchDataFormatted = viewModel.matchFormatted
    }
}