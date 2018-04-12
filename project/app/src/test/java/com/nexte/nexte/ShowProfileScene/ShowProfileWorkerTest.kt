package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileWorkerTest {

    private var worker: ShowProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
    }

    @Test
    fun testGetUserProfileTokenIdNotEmpty(){
        //prepare
        val request = ShowProfileModel.Request(username = "gabrielalbino", tokenID = "kjbdjh213")
        val player = ShowProfileModel.Player(name = "gabrielalbino", rankingPosition = 2, pictureAdress = "imgur.com/nudh486d4", email = "enggabriel@gmail.com", gender = "masculino", club = "ASCAD", age = 19)


        //call
        this.worker?.getUserProfile(request = request, completion = { response ->
            //assert
            assertEquals(response.user?.name, player.name)
            assertEquals(response.user?.rankingPosition, player.rankingPosition)
            assertEquals(response.user?.pictureAdress, player.pictureAdress)
            assertEquals(response.user?.email, player.email)
            assertEquals(response.user?.gender, player.gender)
            assertEquals(response.user?.club, player.club)
            assertEquals(response.user?.age, player.age)
        })
    }

    @Test
    fun testGetUserProfileTokenIdEmpty(){
        //prepare
        val request = ShowProfileModel.Request(username = "gabrielalbino", tokenID = "")
        val player = ShowProfileModel.Player(name = "", rankingPosition = -1, pictureAdress = "", email = "", gender = "", club = "", age = -1)


        //call
        this.worker?.getUserProfile(request = request, completion = { response ->
            //assert
            assertEquals(response.user?.name, player.name)
            assertEquals(response.user?.rankingPosition, player.rankingPosition)
            assertEquals(response.user?.pictureAdress, player.pictureAdress)
            assertEquals(response.user?.email, player.email)
            assertEquals(response.user?.gender, player.gender)
            assertEquals(response.user?.club, player.club)
            assertEquals(response.user?.age, player.age)
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}