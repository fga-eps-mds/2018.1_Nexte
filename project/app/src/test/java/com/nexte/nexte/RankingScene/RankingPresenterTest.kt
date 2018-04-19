package com.nexte.nexte.RankingScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RankingPresenterTest {

    private var presenter: RankingPresenter? = null
    private var mock: MockRankingDisplayLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingDisplayLogic()
        this.presenter = RankingPresenter(viewScene = mock)
    }

    @Test
    fun testPresentRanking(){
        //prepare
        val player1 = RankingModel.Player(name = "luis", pictureURL = 1, wins = 3, losses =
        1, rankPosition = 2)
        val player2 = RankingModel.Player(name = "teste", pictureURL = 2, wins = 2, losses =
        3, rankPosition = 3)
        val response = RankingModel.Response(players = arrayOf(player1, player2))

        //call
        this.presenter?.presentRanking(response = response)

        //assert
        assertEquals(this.mock?.players?.size, 2)
        assertEquals(this.mock?.players!![0].player.userName, player1.name)
        assertEquals(this.mock?.players!![0].player.userRankPosition, "#" + player1.rankPosition.toString())
        assertEquals(this.mock?.players!![0].player.userWins, "Vitórias: " + player1.wins.toString())
        assertEquals(this.mock?.players!![0].player.userLosses,"Derrotas: " + player1.losses.toString())
        assertEquals(this.mock?.players!![1].player.userName, player2.name)
        assertEquals(this.mock?.players!![1].player.userRankPosition, "#" + player2.rankPosition.toString())
        assertEquals(this.mock?.players!![1].player.userWins,"Vitórias: " +  player2.wins.toString())
        assertEquals(this.mock?.players!![1].player.userLosses,"Derrotas: " + player2.losses.toString())
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockRankingDisplayLogic: RankingDisplayLogic{

    var players: List<RankingModel.FormattedPlayerInfo> = listOf()

    override fun displayRankInScreen(viewModel: RankingModel.ViewModel) {
        this.players = viewModel.formattedPlayers
    }
}