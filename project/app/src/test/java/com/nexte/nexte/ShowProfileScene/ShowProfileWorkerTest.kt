package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Player
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ShowProfileWorkerTest {

    private var worker: ShowProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
    }

    @Test
    fun testGetUserProfileSuccess(){
        //prepare
        val request = ShowProfileModel.Request(username = "Robert Baptist")
        val player = User("14",
                "Robert Baptist",
                null,
                "Baptist",
                Date(1989, 12, 4),
                14,
                "baptist@nexte.com",
                "130",
                194,
                154,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )
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