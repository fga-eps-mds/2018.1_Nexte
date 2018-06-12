package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Before
import java.util.*

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
        val user1 = User(id = "1", name = "test1", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste@email.com",
                rankingPosition = 1, nickname = "test1", category = null, loses = 0, gender = User.Gender.MALE,
                profilePicture = "1", phone = "123456", wins = 1)
        val user2 = User(id = "2", name = "test2", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste2@email.com",
                rankingPosition = 2, nickname = "test2", category = null, loses = 1, gender = User.Gender.MALE,
                profilePicture = "2", phone = "1234567", wins = 1)

        //call

        val response = RankingModel.Response(users = arrayOf(user1, user2))
        response.users = arrayOf(user1, user2)

        //assert
        assertEquals(response.users.size, 2)
        assertEquals(response.users[0].name, "test1")
        assertEquals(response.users[0].profilePicture, "1")
        assertEquals(response.users[0].wins, 1)
        assertEquals(response.users[0].rankingPosition, 1)
        assertEquals(response.users[0].latestGames?.size, 0)
        assertEquals(response.users[0].category, null)
        assertEquals(response.users[0].phone, "123456")

        assertEquals(response.users[1].name, "test2")
        assertEquals(response.users[1].profilePicture, "2")
        assertEquals(response.users[1].wins, 1)
        assertEquals(response.users[1].rankingPosition, 2)
        assertEquals(response.users[1].latestGames?.size, 0)
        assertEquals(response.users[1].category, null)
        assertEquals(response.users[1].phone, "1234567")
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
        val id = "1"

        //call
        val formattedPlayer = RankingModel.FormattedPlayer(userName = userName,
                userPictureURL = userPictureURL,
                userWins = userWins,
                userRankingPosition = userRankingPosition,
                userCategory = userCategory,
                userEfficiency = userEfficiency,
                userLastGame = userLastGame,
                id = id)
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
                userCategory = userCategory,
                id = "1")
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
                userLastGame = "ontem",
                id = "1")
        val formattedPlayer2 = RankingModel.FormattedPlayer(userName = "teste",
                userPictureURL = 2,
                userWins = "2",
                userRankingPosition = "2",
                userEfficiency = "80%",
                userLastGame = "hoje",
                userCategory = "intermediario",
                id = "1")
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