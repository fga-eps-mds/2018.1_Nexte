package com.nexte.nexte.LikeListScene


import com.nexte.nexte.Entities.User.User
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

        //call
        val request = LikeListModel.Request("1", "2")
        request.tokenId = "1"
        request.storyId = "2"

        //assert
        assertEquals(request.tokenId, "1")
        assertEquals(request.storyId, "2")
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

        //call
        val response = LikeListModel.Response(listOf())
        response.players = listOf()

        //assert
        assertEquals(response.players.size, 0)
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