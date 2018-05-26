package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class RankingWorkerTest {

    var worker: RankingWorker? = null
    var mock: MockRankingWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        this.worker = RankingWorker()
        this.mock = MockRankingWorkerUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(UserAdapterSpy())
    }

    @Test
    fun testGetUsersInRanking(){
        //prepare
        val request = RankingModel.Request()

        //call
        thread { this.worker?.getUsersInRanking(request = request) }.join()

        //assert
        assertEquals(this.mock?.response?.users?.size, 9)
        assertEquals(this.mock?.response?.users!![0].name, "User test")
        assertEquals(this.mock?.response?.users!![3].wins, 0)
        assertEquals(this.mock?.response?.users!![1].rankingPosition, 1)
        assertEquals(this.mock?.response?.users!![4].category, null)
        assertEquals(this.mock?.response?.users!![5].latestGames?.size, 0)
        assertEquals(this.mock?.response?.users!![6].loses, 0)

    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

class MockRankingWorkerUpdateLogic: RankingWorkerUpdateLogic{
    var response: RankingModel.Response? = null

    override fun updateUsersInRanking(response: RankingModel.Response) {
        this.response = response
    }
}