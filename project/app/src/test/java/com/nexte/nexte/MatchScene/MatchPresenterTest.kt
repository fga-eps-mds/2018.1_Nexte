package com.nexte.nexte.MatchScene

import org.junit.Before
import org.junit.Test

class MatchPresenterTest {

    private var mock: MockMatchDisplayLogic? = null
    private var presenter: MatchPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockMatchDisplayLogic()
        this.presenter = MatchPresenter(viewList = mock)
    }

    @Test
    fun testPresentMatch() {
        //prepare
        var challengedName = "Lele"
        var challengedPhoto = 1
        var challengerName = "Ale"
        var challengerPhoto = 2
        val response = MatchModel.InitScene.Response(dataMatc)

        //call
        var testMatchFormatted = MatchModel.FormattedMatchData()
    }
}