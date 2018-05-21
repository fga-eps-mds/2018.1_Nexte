package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Player
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileModelTest {

    @Before
    fun setUp() {

    }

    @Test
    fun initShowProfile(){
        //prepare
        //call
        val testInit = ShowProfileModel()

        //assert
        assertNotNull(testInit)
    }

    @Test
    fun successRequest(){
        //prepare
        val userName = "luis-gustavo"
        val tokenId = "akjbd2130as"

        //call
        val request = ShowProfileModel.Request(username = "luis-gustavo", tokenID = "akjbd2130as")
        request.tokenID = tokenId
        request.username = userName

        //assert
        assertEquals(userName, request.username)
        assertEquals(tokenId, request.tokenID)
    }

    @Test
    fun successResponse(){
        //prepare
        val player = Player(name = "Luis Gustavo", rankingPosition = 1, pictureAddress = "",
                email = "luis@email.com", gender = "Masculino", club = "Tenis House",
                age = 21, password = "123456", category = "")

        //call
        val response = ShowProfileModel.Response(user = player)
        response.user

        //assert
        assertNotNull(response.user)
        assertEquals(player, response.user)
        assertEquals(player.name, response.user?.name)
        assertEquals(player.rankingPosition, response.user?.rankingPosition)
        assertEquals(player.pictureAddress, response.user?.pictureAddress)
        assertEquals(player.email, response.user?.email)
        assertEquals(player.gender, response.user?.gender)
        assertEquals(player.club, response.user?.club)
        assertEquals(player.age, response.user?.age)
        assertEquals(player.password, response.user?.password)
        assertEquals(player.category, response.user?.category)
    }

    @Test
    fun successViewModel(){
        //prepare
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Luis Gustavo", rank = "1", club = "AASCD", email = "luis@email.com", age = "21")

        //call
        val viewModel = ShowProfileModel.ViewModel(playerInfo = formattedPlayer)
        viewModel.playerInfo.rank
        viewModel.playerInfo.name
        viewModel.playerInfo.email
        viewModel.playerInfo.club
        viewModel.playerInfo.age

        viewModel.playerInfo = formattedPlayer

        //assert
        assertNotNull(viewModel.playerInfo)
        assertEquals(formattedPlayer, viewModel.playerInfo)
        assertEquals(formattedPlayer.name, viewModel.playerInfo.name)
        assertEquals(formattedPlayer.rank, viewModel.playerInfo.rank)
        assertEquals(formattedPlayer.club, viewModel.playerInfo.club)
        assertEquals(formattedPlayer.email, viewModel.playerInfo.email)
        assertEquals(formattedPlayer.age, viewModel.playerInfo.age)
    }

    @Test
    fun successFormattedPlayer(){
        //prepare
        val name = "Luis Gustavo"
        val rank = "1"
        val club = "AASCD"
        val email = "luis@email.com"
        val age = "21"

        //call
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Luis Gustavo", rank = "1", club = "AASCD", email = "luis@email.com", age = "21")
        formattedPlayer.age = age
        formattedPlayer.club = club
        formattedPlayer.email = email
        formattedPlayer.name = name
        formattedPlayer.rank = rank

        //assert
        assertEquals(name, formattedPlayer.name)
        assertEquals(rank, formattedPlayer.rank)
        assertEquals(club, formattedPlayer.club)
        assertEquals(email, formattedPlayer.email)
        assertEquals(age, formattedPlayer.age)
    }

    @Test
    fun successShowProfileModel() {
        //prepare

        //call
        val model = ShowProfileModel()

        //assert
        assertNotNull(model)
    }

    @After
    fun tearDown() {

    }
}