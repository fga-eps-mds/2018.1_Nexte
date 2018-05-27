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
        //call
        this.worker?.getUserProfile(request = request, completion = { response ->
            //assert
            assertNotNull(response.user)
        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}