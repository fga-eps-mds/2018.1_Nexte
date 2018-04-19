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
        val rankPosition = 2

        //call
        val player = RankingModel.Player(name = name, pictureURL = pictureURL, wins = wins, losses =
        losses, rankPosition = rankPosition)

        //assert
        assertEquals(name, player.name)
        assertEquals(pictureURL, player.pictureURL)
        assertEquals(wins, player.wins)
        assertEquals(losses, player.losses)
        assertEquals(rankPosition, player.rankPosition)
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
        val player1 = RankingModel.Player(name = "luis", pictureURL = 1, wins = 3, losses =
        1, rankPosition = 2)
        val player2 = RankingModel.Player(name = "teste", pictureURL = 2, wins = 2, losses =
        3, rankPosition = 3)

        //call
        val response = RankingModel.Response(players = arrayOf(player1, player2))

        //assert
        assertEquals(response.players.size, 2)
        assertEquals(response.players[0].name, "luis")
        assertEquals(response.players[0].pictureURL, 1)
        assertEquals(response.players[0].wins, 3)
        assertEquals(response.players[0].losses, 1)
        assertEquals(response.players[0].rankPosition, 2)
        assertEquals(response.players[1].name, "teste")
        assertEquals(response.players[1].pictureURL, 2)
        assertEquals(response.players[1].wins, 2)
        assertEquals(response.players[1].losses, 3)
        assertEquals(response.players[1].rankPosition, 3)
    }

    @Test fun successFormattedPlayer(){
        //prepare
        val userName = "luis"
        val userPictureURL = 1
        val userWins = "1"
        val userLosses =  "1"
        val userRankPosition = "1"

        //call
        val formattedPlayer = RankingModel.FormattedPlayer(userName = userName, userPictureURL = userPictureURL, userWins = userWins, userLosses = userLosses, userRankPosition = userRankPosition)

        //assert
        assertEquals(userName, formattedPlayer.userName)
        assertEquals(userPictureURL, formattedPlayer.userPictureURL)
        assertEquals(userWins, formattedPlayer.userWins)
        assertEquals(userLosses, formattedPlayer.userLosses)
        assertEquals(userRankPosition, formattedPlayer.userRankPosition)
    }

    @Test
    fun successFormattedPlayerInfo(){
        //prepare
        val userName = "luis"
        val userPictureURL = 1
        val userWins = "1"
        val userLosses =  "1"
        val userRankPosition = "1"
        val formattedPlayer = RankingModel.FormattedPlayer(userName = userName, userPictureURL = userPictureURL, userWins = userWins, userLosses = userLosses, userRankPosition = userRankPosition)
        val shouldDrawChild = true

        //call
        val formattedPlayerInfo = RankingModel.FormattedPlayerInfo(player = formattedPlayer, shouldDrawChild = shouldDrawChild)

        //assert
        assertEquals(formattedPlayer, formattedPlayerInfo.player)
        assertEquals(shouldDrawChild, formattedPlayerInfo.shouldDrawChild)

    }

    @Test
    fun successViewModel(){
        //prepare
        val formattedPlayer1 = RankingModel.FormattedPlayer(userName = "luis", userPictureURL = 1, userWins = "1", userLosses = "1", userRankPosition = "1")
        val formattedPlayer2 = RankingModel.FormattedPlayer(userName = "teste", userPictureURL = 2, userWins = "2", userLosses = "2", userRankPosition = "2")
        val formattedPlayerInfo1 = RankingModel.FormattedPlayerInfo(player = formattedPlayer1, shouldDrawChild = true)
        val formattedPlayerInfo2 = RankingModel.FormattedPlayerInfo(player = formattedPlayer2, shouldDrawChild = false)

        //call
        val viewModel = RankingModel.ViewModel(formattedPlayers = listOf(formattedPlayerInfo1, formattedPlayerInfo2))

        //assert
        assertEquals(viewModel.formattedPlayers.size, 2)
        assertEquals(viewModel.formattedPlayers[0], formattedPlayerInfo1)
        assertEquals(viewModel.formattedPlayers[1], formattedPlayerInfo2)
    }

    @After
    fun tearDown() {
    }
}