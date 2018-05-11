package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ChallengeModelTest{
    @Before
    fun setUp(){

    }

    @Test
    fun successFormattedRankingDetails(){
        //prepare
        val name = "Gabriel"
        val wins = "3"
        val loses = "6"
        val rankingPosition = "#1"

        //call
        val playerFormatted = ChallengeModel.FormattedRankingDetails(name, wins, loses, rankingPosition)
        playerFormatted.rankingPosition = rankingPosition
        playerFormatted.wins = wins
        playerFormatted.loses = loses
        playerFormatted.name = name

        //assert
        assertEquals(playerFormatted.loses, loses)
        assertEquals(playerFormatted.name, name)
        assertEquals(playerFormatted.rankingPosition, rankingPosition)
        assertEquals(playerFormatted.wins, wins)
    }

    @Test
    fun sucessFormattedPlayer(){
        //prepare
        val name = "Gabriel"
        val rankingPosition = "#1"
        val pictureAddress = "https://www.www.com/www.jpg"

        //call
        val playerFormatted = ChallengeModel.FormattedPlayer(name, rankingPosition, pictureAddress)
        playerFormatted.pictureAddress = pictureAddress
        playerFormatted.name = name
        playerFormatted.rankingPosition = rankingPosition

        //assert
        assertEquals(playerFormatted.rankingPosition, rankingPosition)
        assertEquals(playerFormatted.name, name)
        assertEquals(playerFormatted.pictureAddress, pictureAddress)
    }

    @Test
    fun successFirstRequest(){
        //prepare
        val ranking = 5

        //call
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(ranking)
        request.challengerRankingPosition = ranking

        //assert
        assertEquals(request.challengerRankingPosition, ranking)
    }

    @Test
    fun successSecondRequest(){
        //prepare
        val ranking = 5

        //call
        val request = ChallengeModel.SelectPlayerForChallengeRequest.Request(ranking)
        request.challengedRankingPosition = ranking

        //assert
        assertEquals(request.challengedRankingPosition, ranking)
    }

    @Test
    fun successThirdRequest(){
        //prepare
        val user = "larissa"

        //call
        val request = ChallengeModel.ChallengeButtonRequest.Request(user)
        request.userChallenged = user

        //assert
        assertEquals(request.userChallenged, user)
    }

    @Test
    fun successFirstResponse(){
        //prepare
        val name = "Gabriel"
        val rankingPosition = 5
        val pictureAddress = "https://www.algumsite.com.br/algumaimagem.png"
        val email = "enggabrielalbino@gmail.com"
        val gender = "masculino"
        val club = "clubTop"
        val age = 19
        val password = "adoroPicole"

        val players = listOf(
                Player(name, rankingPosition, pictureAddress, email, gender, club, age, password)
        )

        //call
        val response = ChallengeModel.ShowRankingPlayersRequest.Response(players)
        response.usersAbove = players

        //assert
        assertEquals(response.usersAbove[0].rankingPosition, rankingPosition)
        assertEquals(response.usersAbove[0].name, name)
        assertEquals(response.usersAbove[0].pictureAddress, pictureAddress)
        assertEquals(response.usersAbove[0].email, email)
        assertEquals(response.usersAbove[0].gender, gender)
        assertEquals(response.usersAbove[0].club, club)
        assertEquals(response.usersAbove[0].age, age)
        assertEquals(response.usersAbove[0].password, password)
    }

    @Test
    fun successSecondResponse(){
        //prepare
        val name = "Gabriel"
        val wins = 3
        val loses = 6
        val rankingPosition = 1

        val player = ChallengeModel.PlayerRankingDetails(
                name, wins, loses, rankingPosition
        )

        //call
        player.loses = loses
        player.name = name
        player.rankingPosition = rankingPosition
        player.wins

        val response = ChallengeModel.SelectPlayerForChallengeRequest.Response(player)
        response.challengedPersonalDetails = player

        //assert
        assertEquals(response.challengedPersonalDetails.loses, loses)
        assertEquals(response.challengedPersonalDetails.name, name)
        assertEquals(response.challengedPersonalDetails.rankingPosition, rankingPosition)
        assertEquals(response.challengedPersonalDetails.wins, wins)
    }

    @Test
    fun successThirdResponse(){
        //prepare
        val user = "larissa"

        //call
        val response = ChallengeModel.ChallengeButtonRequest.Response(user)
        response.username = user

        //assert
        assertEquals(response.username, user)
    }

    @Test
    fun successFirstViewModel(){
        //prepare
        val name = "Gabriel"
        val rankingPosition = "#1"
        val pictureAddress = "https://www.www.com/www.jpg"
        val playerFormatted = ChallengeModel.FormattedPlayer(name, rankingPosition, pictureAddress)

        val playersFormatted = listOf(playerFormatted)

        //call
        val viewModel = ChallengeModel.ShowRankingPlayersRequest.ViewModel(playersFormatted)
        viewModel.formattedPlayer = playersFormatted

        //assert
        assertEquals(viewModel.formattedPlayer.size, playersFormatted.size)
        assertEquals(viewModel.formattedPlayer[0].pictureAddress, pictureAddress)
        assertEquals(viewModel.formattedPlayer[0].name, name)
        assertEquals(viewModel.formattedPlayer[0].rankingPosition, rankingPosition)
    }


    @Test
    fun successSecondViewModel(){
        //prepare
        val name = "Gabriel"
        val wins = "3"
        val loses = "6"
        val rankingPosition = "#1"
        val playerFormatted = ChallengeModel.FormattedRankingDetails(name, wins, loses, rankingPosition)

        //call
        val viewModel = ChallengeModel.SelectPlayerForChallengeRequest.ViewModel(playerFormatted)
        viewModel.challengedRankingDetails = playerFormatted

        //assert
        assertEquals(viewModel.challengedRankingDetails.wins, wins)
        assertEquals(viewModel.challengedRankingDetails.loses, loses)
        assertEquals(viewModel.challengedRankingDetails.rankingPosition, rankingPosition)
        assertEquals(viewModel.challengedRankingDetails.name, name)
    }

    @Test
    fun successThirdViewModel(){
        //prepare
        val message = "Sucesso"

        //call
        val viewModel = ChallengeModel.ChallengeButtonRequest.ViewModel(message)
        viewModel.messageForChallenger = message

        //assert
        assertEquals(viewModel.messageForChallenger, message)
    }

    @Test
    fun successChallengeModel() {
        //prepare

        //call
        val model = ChallengeModel()

        //assert
        assertNotNull(model)
    }

    @Test
    fun successSetFormattedPlayer() {
        //prepare
        val name = "larissa"
        val rankingPosition = "5"
        val pictureAddress = ""
        val formattedPlayer = ChallengeModel.FormattedPlayer("albino", "6", "a")

        //call
        formattedPlayer.name = name
        formattedPlayer.rankingPosition = rankingPosition
        formattedPlayer.pictureAddress = pictureAddress

        //assert
        assertEquals(formattedPlayer.name, name)
        assertEquals(formattedPlayer.rankingPosition, rankingPosition)
        assertEquals(formattedPlayer.pictureAddress, pictureAddress)
    }

    @Test
    fun successChallengeButtonRequest() {
        //prepare

        //call
        val model = ChallengeModel.ChallengeButtonRequest()

        //assert
        assertNotNull(model)
    }

    @Test
    fun successShowRankingPlayersRequest() {
        //prepare

        //call
        val model = ChallengeModel.ShowRankingPlayersRequest()

        //assert
        assertNotNull(model)
    }

    @After
    fun tearDown(){

    }
}