package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListWorkerTest {

    var worker: LikeListWorker? = null
    var mock: MockWorkersUpdateLogic? = null


    @Before
    fun setUp() {
        this.worker = LikeListWorker()
        this.mock = MockWorkersUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(userAdapter = UserAdapterSpy())
    }

    @Test
    fun testGetListLikesPlayers(){
        //prepare
        val requestMessage = "1"
        val request = LikeListModel.Request(request = requestMessage)

        //call
        this.worker?.getListLikesPlayers(request = request)
        //assert

        assertEquals(this.mock?.response?.players?.size, 1)
        assertEquals(this.mock?.response?.players!![0].name, "User test")
        assertNotNull(request)
    }

    @Test
    fun getUpdate() {
        //prepare and call
        val userManager = worker?.userManager
        val updateLogic = worker?.updateLogic
        //assert
        assertEquals(worker?.updateLogic, updateLogic)
        assertEquals(worker?.userManager, userManager)

    }

        @After
    fun tearDown() {
        this.worker = null
    }
}

class MockWorkersUpdateLogic: WorkerUpdateLogic {

    var response: LikeListModel.Response? = null

    override fun updateUsers(response: LikeListModel.Response) {
        this.response = response
    }
}