package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.Player
import org.json.JSONObject
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
        val userManager = worker?.userManager
        worker?.updateLogic = mock
        val updateLogic = worker?.updateLogic
        val request = ShowProfileModel.Request("Robert Baptist")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNotNull(updateLogic)
        assertNotNull(userManager)
        assertNotNull(this.mock?.response)
    }

    @Test
    fun testGetUserProfileFailed(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = worker?.userManager
        worker?.updateLogic = mock
        val updateLogic = worker?.updateLogic
        val request = ShowProfileModel.Request("anythingtofail")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNotNull(updateLogic)
        assertNotNull(userManager)
        assertNotNull(this.mock?.response)
    }


    @Test
    fun testGetUserProfileWithNullUserSuccess(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = null
        worker?.userManager = userManager
        val request = ShowProfileModel.Request("Robert Baptist")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNull(this.mock?.response)
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