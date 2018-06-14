package com.nexte.nexte.LikeListScene


import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
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
    fun testFormatPlayersCase1(){
        //prepare
        val userList = mutableListOf(User("1",
                "André Rede",
                R.drawable.profile_image1.toString(),
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        ))
        //call
        val returnedValue = presenter?.formatPlayers(userList)

        //assert
        assertEquals(returnedValue?.size, 1)
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
    fun testFormatLikeListWithNullViewList(){
        //prepare
        mock?.playersFormatted = mutableListOf()
        val backup = presenter?.viewList
        presenter?.viewList = null
        val response = LikeListModel.Response(listOf(User("1",
                "André Rede",
                R.drawable.profile_image1.toString(),
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )))
        //call
        presenter?.formatLikeList(response)

        //backup
        presenter?.viewList = backup
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