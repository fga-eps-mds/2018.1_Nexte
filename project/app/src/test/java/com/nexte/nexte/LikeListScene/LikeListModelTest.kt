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
    fun initLikeList(){
        //prepare
        //call
        val testInit = LikeListModel()

        //assert
        assertNotNull(testInit)
    }

    @Test
    fun testRequest(){
        //prepare
        val requestMessage = "123"

        //call
        val request = LikeListModel.Request(request = requestMessage)
        request.request = requestMessage

        //assert
        assertEquals(requestMessage, request.request)
    }

    @Test
    fun testPlayer(){
        //prepare
        val name = "luis"
        val photo = 1

        //call
        val player = LikeListModel.Players(name = name, photo = photo)
        player.name = name
        player.photo = photo

        //assert
        assertEquals(name, player.name)
        assertEquals(photo, player.photo)
    }

    @Test
    fun testPlayersFormatted(){
        //prepare
        val name = "luis"
        val photo = 1

        //call
        val playersFormatted = LikeListModel.PlayersFormatted(name = name, photo = photo)
        playersFormatted.name = name
        playersFormatted.photo = photo

        //assert
        assertEquals(name, playersFormatted.name)
        assertEquals(photo, playersFormatted.photo)
    }

    @Test
    fun testResponse(){
        //prepare
        val name = "luis"
        val photo = 1
        val player = LikeListModel.Players(name = name, photo = photo)

        //call
        val response = LikeListModel.Response(players = mutableListOf(player))
        response.players = mutableListOf(player)

        //assert
        assertEquals(response.players.size, 1)
        assertEquals(player, response.players[0])
    }

    @Test
    fun testViewModel(){
        //prepare
        val name = "luis"
        val photo = 1
        val playersFormatted = LikeListModel.PlayersFormatted(name = name, photo = photo)

        //call
        val viewModel = LikeListModel.ViewModel(playersFormatted = mutableListOf(playersFormatted))
        playersFormatted.photo = photo
        playersFormatted.name = name

        //assert
        assertEquals(viewModel.playersFormatted.size, 1)
        assertEquals(viewModel.playersFormatted[0], playersFormatted)
    }

    @Test
    fun successLikeListModel() {
        //prepare

        //call
        val model = LikeListModel()

        //assert
        assertNotNull(model)
    }

    @After
    fun tearDown() {
    }
}