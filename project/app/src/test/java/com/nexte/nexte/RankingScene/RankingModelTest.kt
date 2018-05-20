package com.nexte.nexte.RankingScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RankingModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun successPlayer(){
        //prepare
        val name = "luis"
        val pictureURL = 1
        val wins = 3
        val losses = 1
        val rankingPosition = 2
        val lastGame = "ontem"
        val efficiency = "90%"
        val playerCategory = "profissional"

        //call
        val player = RankingModel.Player(name = name,
                pictureURL = pictureURL,
                wins = wins,
                losses = losses,
                rankingPosition = rankingPosition,
                lastGame = lastGame,
                efficiency = efficiency,
                playerCategory = playerCategory)
        player.losses = losses
        player.name = name
        player.pictureURL = pictureURL
        player.wins = wins
        player.rankingPosition = rankingPosition
        player.lastGame = lastGame
        player.efficiency = efficiency
        player.playerCategory = playerCategory

        //assert
        assertEquals(name, player.name)
        assertEquals(pictureURL, player.pictureURL)
        assertEquals(wins, player.wins)
        assertEquals(losses, player.losses)
        assertEquals(rankingPosition, player.rankingPosition)
        assertEquals(efficiency, player.efficiency)
        assertEquals(playerCategory, player.playerCategory)
        assertEquals(lastGame, player.lastGame)
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
        val player1 = RankingModel.Player(name = "luis",
                pictureURL = 1,
                wins = 3,
                losses = 1,
                rankingPosition = 2,
                efficiency = "90%",
                playerCategory = "profissional",
                lastGame = "ontem")
        val player2 = RankingModel.Player(name = "teste",
                pictureURL = 2,
                wins = 2,
                losses = 3,
                rankingPosition = 3,
                efficiency = "80%",
                playerCategory = "intermediario",
                lastGame = "hoje")

        //call
        val response = RankingModel.Response(players = arrayOf(player1, player2))
        response.players = arrayOf(player1, player2)

        //assert
        assertEquals(response.players.size, 2)
        assertEquals(response.players[0].name, "luis")
        assertEquals(response.players[0].pictureURL, 1)
        assertEquals(response.players[0].wins, 3)
        assertEquals(response.players[0].rankingPosition, 2)
        assertEquals(response.players[0].efficiency, "90%")
        assertEquals(response.players[0].playerCategory, "profissional")
        assertEquals(response.players[0].lastGame, "ontem")
        assertEquals(response.players[1].name, "teste")
        assertEquals(response.players[1].pictureURL, 2)
        assertEquals(response.players[1].wins, 2)
        assertEquals(response.players[1].rankingPosition, 3)
        assertEquals(response.players[1].efficiency, "80%")
        assertEquals(response.players[1].playerCategory, "intermediario")
        assertEquals(response.players[1].lastGame, "hoje")
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