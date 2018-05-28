package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.Player
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.concurrent.thread

class ShowProfileWorkerTest {

    private var worker: ShowProfileWorker? = null
    private var mock: MockShowProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
        this.mock = MockShowProfileWorker()
    }

    @Test
    fun testGetUserProfileSuccess(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        worker?.updateLogic = mock
        val request = ShowProfileModel.Request("Robert Baptist")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNotNull(this.mock?.response)
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

class MockShowProfileWorker: ShowProfileWorkerUpdateLogic {

    var response: ShowProfileModel.Response? = null

    override fun updateUserProfile(response: ShowProfileModel.Response) {
        this.response = response
    }
}