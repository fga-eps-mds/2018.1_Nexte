package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.Like.LikeAdapter
import com.nexte.nexte.Entities.Like.LikeAdapterSpy
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
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
        this.worker?.storyManager = StoryManager(StoryAdapterSpy())
        this.worker?.likeManager = LikeManager(LikeAdapterSpy())
    }

    @Test
    fun testGetListLikesPlayers(){
        //prepare
        val request = LikeListModel.Request("1", "1")

        //call
        this.worker?.getListLikesPlayers(request = request)
        //assert

        assertEquals(this.mock?.response?.players?.size, 1)
        assertEquals(this.mock?.response?.players!![0].name, "User test")
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