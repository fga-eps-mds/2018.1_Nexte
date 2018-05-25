package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Player
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
    fun testGetUserProfileEmptyUser(){
        //prepare
        val request = ShowProfileModel.Request(username = "gabrielalbino")
        val player = Player("",
                -1,
                "",
                "",
                "",
                "",
                -1,
                "",
                "")

        //call
        this.worker?.getUserProfile(request = request, completion = { response ->
            //assert
            assertEquals(response.user?.name, player.name)
            assertEquals(response.user?.rankingPosition, player.rankingPosition)
            assertEquals(response.user?.email, player.email)
            assertEquals(response.user?.gender, player.gender)
            assertEquals(response.user?.category, player.category)
        })
    }

    @Test
    fun testGetUserProfileSuccess(){
        //prepare
        val request = ShowProfileModel.Request(username = "gabrielalbino")
        val player = Player("gabrielalbino",
                15,
                "imgur.com/nudh486d4",
                "enggabriel@gmail.com",
                "masculino",
                "ASCAD",
                19,
                "feioso",
                "Profissional")

        //call
        this.worker?.getUserProfile(request = request, completion = { response ->
            //assert
            assertEquals(response.user?.name, player.name)
            assertEquals(response.user?.rankingPosition, player.rankingPosition)
            assertEquals(response.user?.email, player.email)
            assertEquals(response.user?.gender, player.gender)
            assertEquals(response.user?.category, player.category)
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}