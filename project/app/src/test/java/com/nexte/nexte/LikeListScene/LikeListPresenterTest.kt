package com.nexte.nexte.LikeListScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

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
        val name = "luis"
        val photo = 1
        val player = LikeListModel.Players(name = name, photo = photo)
        val response = LikeListModel.Response(players = mutableListOf(player))

        //call
        this.presenter?.formatLikeList(response = response)

        //assert
        assertEquals(this.mock?.playersFormatted?.size, 1)
        assertEquals(this.mock?.playersFormatted!![0].name, name)
        assertEquals(this.mock?.playersFormatted!![0].photo, photo)
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