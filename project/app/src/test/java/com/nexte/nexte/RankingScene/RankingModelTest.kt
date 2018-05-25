package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class RankingModelTest {

    @Before
    fun setUp() {
    }


    @Test
    fun successRequest(){
        //prepare //call
        val request = RankingModel.Request()

        //assert
        assertNotNull(request)
    }

    @Test
    fun successResponse(){
        //call
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

        //call
        val response = RankingModel.Response(players = listOf(player1, player2))
        response.players = listOf(player1, player2)

        //assert
        assertEquals(response.players.size, 2)
        assertEquals(response.players[0], player1)
        assertEquals(response.players[1], player2)
    }

    @Test fun successFormattedPlayer(){
        //prepare
        val userName = "luis"
        val userPictureURL = 1
        val userWins = "1"
        val userRankingPosition = "1"
        val userCategory = "profissional"
        val userEfficiency = "90%"
        val userLastGame = "ontem"

        //call
        val formattedPlayer = RankingModel.FormattedPlayer(userName = userName,
                userPictureURL = userPictureURL,
                userWins = userWins,
                userRankingPosition = userRankingPosition,
                userCategory = userCategory,
                userEfficiency = userEfficiency,
                userLastGame = userLastGame)
        formattedPlayer.userName = userName
        formattedPlayer.userPictureURL = userPictureURL
        formattedPlayer.userRankingPosition = userRankingPosition
        formattedPlayer.userWins = userWins
        formattedPlayer.userCategory = userCategory
        formattedPlayer.userLastGame = userLastGame
        formattedPlayer.userEfficiency = userEfficiency

        //assert
        assertEquals(userName, formattedPlayer.userName)
        assertEquals(userPictureURL, formattedPlayer.userPictureURL)
        assertEquals(userWins, formattedPlayer.userWins)
        assertEquals(userRankingPosition, formattedPlayer.userRankingPosition)
    }

    @Test
    fun successFormattedPlayerInfo(){
        //prepare
        val userName = "luis"
        val userPictureURL = 1
        val userWins = "1"
        val userRankingPosition = "1"
        val userLastGame = "ontem"
        val userEfficiency = "90%"
        val userCategory = "profissional"
        val formattedPlayer = RankingModel.FormattedPlayer(userName = userName,
                userPictureURL = userPictureURL,
                userWins = userWins,
                userRankingPosition = userRankingPosition,
                userLastGame = userLastGame,
                userEfficiency = userEfficiency,
                userCategory = userCategory)
        val shouldDrawChild = true

        //call
        val formattedPlayerInfo = RankingModel.FormattedPlayerInfo(player = formattedPlayer,
                shouldDrawChild = shouldDrawChild)
        formattedPlayerInfo.player = formattedPlayer
        formattedPlayerInfo.shouldDrawChild = shouldDrawChild

        //assert
        assertEquals(formattedPlayer, formattedPlayerInfo.player)
        assertEquals(shouldDrawChild, formattedPlayerInfo.shouldDrawChild)

    }

    @Test
    fun successViewModel(){
        //prepare
        val formattedPlayer1 = RankingModel.FormattedPlayer(userName = "luis",
                userPictureURL = 1,
                userWins = "1",
                userRankingPosition = "1",
                userCategory = "profissional",
                userEfficiency = "90%",
                userLastGame = "ontem")
        val formattedPlayer2 = RankingModel.FormattedPlayer(userName = "teste",
                userPictureURL = 2,
                userWins = "2",
                userRankingPosition = "2",
                userEfficiency = "80%",
                userLastGame = "hoje",
                userCategory = "intermediario")
        val formattedPlayerInfo1 = RankingModel.FormattedPlayerInfo(player = formattedPlayer1, shouldDrawChild = true)
        val formattedPlayerInfo2 = RankingModel.FormattedPlayerInfo(player = formattedPlayer2, shouldDrawChild = false)

        //call
        val viewModel = RankingModel.ViewModel(formattedPlayers = listOf(formattedPlayerInfo1, formattedPlayerInfo2))
        viewModel.formattedPlayers = listOf(formattedPlayerInfo1, formattedPlayerInfo2)

        //assert
        assertEquals(viewModel.formattedPlayers.size, 2)
        assertEquals(viewModel.formattedPlayers[0], formattedPlayerInfo1)
        assertEquals(viewModel.formattedPlayers[1], formattedPlayerInfo2)
    }

    @Test
    fun successRankingModel() {
        //prepare

        //call
        val model = RankingModel()

        //assert
        assertNotNull(model)
    }

    @After
    fun tearDown() {
    }
}