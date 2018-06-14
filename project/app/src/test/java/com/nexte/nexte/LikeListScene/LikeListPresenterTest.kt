package com.nexte.nexte.LikeListScene


import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class LikeListPresenterTest {

    private var mock: MockLikeListDisplayLogic? = null
    private var presenter: LikeListPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockLikeListDisplayLogic()
        this.presenter = LikeListPresenter(viewList = mock)
    }

    @Test
    fun testFormatLikeList(){
        //prepare

        val response = LikeListModel.Response(listOf())

        //call
        this.presenter?.formatLikeList(response = response)


        //assert
        assertEquals(this.mock?.playersFormatted?.size, 0)
    }

    @Test
    fun testConstructorLikeListPresenter() {
        //prepare and call
        val presenter = LikeListPresenter()

        //assert
        assertNotNull(presenter)
    }

    @Test
    fun testGetViewList() {
        //prepare
        val viewList = presenter?.viewList

        //assert
        assertEquals(presenter?.viewList, viewList)
    }

    @Test
    fun testPlayerFormattedNotNull() {
        this.mock?.playersFormatted

        assertNotNull(this.mock?.playersFormatted)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockLikeListDisplayLogic: LikeListDisplayLogic{

    var playersFormatted: MutableList<LikeListModel.PlayersFormatted> = mutableListOf()

    override fun displayLikeList(viewModel: LikeListModel.ViewModel) {
        this.playersFormatted = viewModel.playersFormatted
    }
}