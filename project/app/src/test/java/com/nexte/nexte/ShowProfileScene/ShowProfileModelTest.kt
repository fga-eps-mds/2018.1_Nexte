package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileModelTest {

    @Before
    fun setUp() {

    }

    @Test
    fun successRequest(){
        //prepare
        val userName = "luis-gustavo"
        val tokenId = "akjbd2130as"

        //call
        val request = ShowProfileModel.Request(username = "luis-gustavo", tokenID = "akjbd2130as")

        //assert
        assertEquals(userName, request.username)
        assertEquals(tokenId, request.tokenID)
    }

    @Test
    fun successResponse(){
        //prepare
        val player = ShowProfileModel.Player(name = "Luis Gustavo", rankingPosition = 1, pictureAdress = "",
        email = "luis@email.com", gender = "Masculino", club = "Tenis House", age = 21)

        //call
        val response = ShowProfileModel.Response(user = player)

        //assert
        assertEquals(player, response.user)
        assertEquals(player.name, response.user?.name)
        assertEquals(player.rankingPosition, response.user?.rankingPosition)
        assertEquals(player.pictureAdress, response.user?.pictureAdress)
        assertEquals(player.email, response.user?.email)
        assertEquals(player.gender, response.user?.gender)
        assertEquals(player.club, response.user?.club)
        assertEquals(player.age, response.user?.age)
    }

    @Test
    fun successViewModel(){
        //prepare
        val name = "Luis"
        val rank = "Major"
        val club = "Tenis House"
        val email = "luis@email.com"
        val age = "21"

        //call
        val viewModel = ShowProfileModel.ViewModel(name = name, rank = rank, club = club, email = email, age = age)

        //assert
        assertEquals(name, viewModel.name)
        assertEquals(rank, viewModel.rank)
        assertEquals(club, viewModel.club)
        assertEquals(email, viewModel.email)
        assertEquals(age, viewModel.age)
    }

    @Test
    fun successPlayer(){
        //prepare
        val name = "Luis Gustavo"
        val rankingPosition = 1
        val pictureAdress = ""
        val email = "luis@email.com"
        val gender = "Masculino"
        val club = "Tenis House"
        val age = 21

        //call
        val player = ShowProfileModel.Player(name = "Luis Gustavo", rankingPosition = 1, pictureAdress = "",
                email = "luis@email.com", gender = "Masculino", club = "Tenis House", age = 21)

        //assert
        assertEquals(name, player.name)
        assertEquals(rankingPosition, player.rankingPosition)
        assertEquals(pictureAdress, player.pictureAdress)
        assertEquals(email, player.email)
        assertEquals(gender, player.gender)
        assertEquals(club, player.club)
        assertEquals(age, player.age)
    }

    @After
    fun tearDown() {

    }
}