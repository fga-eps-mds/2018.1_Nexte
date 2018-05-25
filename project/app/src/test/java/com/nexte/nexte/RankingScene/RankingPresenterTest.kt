package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class RankingPresenterTest {

    private var presenter: RankingPresenter? = null
    private var mock: MockRankingDisplayLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingDisplayLogic()
        this.presenter = RankingPresenter(viewScene = mock)
    }

    @Test
    fun successPresentRanking(){
        //prepare
        val player1 = User("13",
                "Nick Cairo",
                null,
                "Cairo",
                Date(1993, 3, 13),
                12,
                "cairo@nexte.com",
                "130",
                68,
                96,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )
        val player2 = User("14",
                "Robert Baptist",
                null,
                "Baptist",
                Date(1989, 12, 4),
                14,
                "baptist@nexte.com",
                "130",
                194,
                154,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )
        val response = RankingModel.Response(players = listOf(player1, player2))

        //call
        this.presenter?.presentRanking(response = response)

        //assert
        assertEquals(this.mock?.players?.size, 2)
        assertNotNull(this.mock?.players!![0])
        assertNotNull(this.mock?.players!![1])
    }

    @Test
    fun successRankingPresenter(){
        //prepare and call
        val presenter = RankingPresenter()
        val newViewScene = RankingFragment()
        presenter.viewScene = newViewScene
        val testViewScene = presenter.viewScene

        //assert
        assertNotNull(presenter)
        assertNotNull(testViewScene)
        assertEquals(testViewScene, newViewScene)
    }

    @Test
    fun successPresenter(){
        //prepare and call
        val presenter = RankingPresenter()

        //assert
        assertNotNull(presenter)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockRankingDisplayLogic: RankingDisplayLogic{

    var players: List<RankingModel.FormattedPlayerInfo> = listOf()

    override fun displayRankingInScreen(viewModel: RankingModel.ViewModel) {
        this.players = viewModel.formattedPlayers
    }
}