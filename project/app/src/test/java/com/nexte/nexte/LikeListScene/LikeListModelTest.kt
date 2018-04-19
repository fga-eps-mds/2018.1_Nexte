package com.nexte.nexte.LikeListScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testRequest(){
        //prepare
        val requestMessage = "123"

        //call
        val request = LikeListModel.Request(request = requestMessage)

        //assert
        assertEquals(requestMessage, request.request)
    }

    @Test
    fun testPlayer(){
        //prepare
        val name = "luis"
        val photo = 1
        val time = "AASC"

        //call
        val player = LikeListModel.Players(name = name, photo = photo, time = time)

        //assert
        assertEquals(name, player.name)
        assertEquals(photo, player.photo)
        assertEquals(time, player.time)
    }

    @Test
    fun testPlayersFormatted(){
        //prepare
        val name = "luis"
        val photo = 1
        val time = "AASC"

        //call
        val playersFormatted = LikeListModel.PlayersFormatted(name = name, photo = photo, time = time)

        //assert
        assertEquals(name, playersFormatted.name)
        assertEquals(photo, playersFormatted.photo)
        assertEquals(time, playersFormatted.time)
    }

    @Test
    fun testResponse(){
        //prepare
        val name = "luis"
        val photo = 1
        val time = "AASC"
        val player = LikeListModel.Players(name = name, photo = photo, time = time)

        //call
        val response = LikeListModel.Response(players = mutableListOf(player))

        //assert
        assertEquals(response.players.size, 1)
        assertEquals(player, response.players[0])
    }

    @Test
    fun testViewModel(){
        //prepare
        val name = "luis"
        val photo = 1
        val time = "AASC"
        val playersFormatted = LikeListModel.PlayersFormatted(name = name, photo = photo, time = time)

        //call
        val viewModel = LikeListModel.ViewModel(playersFormatted = mutableListOf(playersFormatted))

        //assert
        assertEquals(viewModel.playersFormatted.size, 1)
        assertEquals(viewModel.playersFormatted[0], playersFormatted)
    }

    @After
    fun tearDown() {
    }
}