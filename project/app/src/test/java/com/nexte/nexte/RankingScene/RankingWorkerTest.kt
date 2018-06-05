package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class RankingWorkerTest : HelpForRealm() {

    var worker: RankingWorker? = null
    var mock: MockRankingWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = RankingWorker()
        this.mock = MockRankingWorkerUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(UserAdapterSpy())
    }

    @Test
    fun testGetters(){
        //prepare  and call
        val updateLogic = worker?.updateLogic
        val userManager = worker?.userManager

        //assert
        assertEquals(worker?.updateLogic, updateLogic)
        assertEquals(worker?.userManager, userManager)

    }

    @Test
    fun testNullUpdateLogic(){
        //prepare
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        val request = RankingModel.Request()
        mock?.response = null
        //call
        worker?.getUsersInRanking(request)
        //assert
        assertNull(mock?.response)
        //backup
        worker?.updateLogic = backup
    }
//    @Test
//    fun testGetUsersInRanking(){
//        //prepare
//        val request = RankingModel.Request()
//
//        //call
//        thread { this.worker?.getUsersInRanking(request = request) }.join()
//
//        //assert
//        assertEquals(this.mock?.response?.users?.size, 9)
//        assertEquals(this.mock?.response?.users!![0].name, "User test")
//        assertEquals(this.mock?.response?.users!![3].wins, 0)
//        assertEquals(this.mock?.response?.users!![1].rankingPosition, 1)
//        assertEquals(this.mock?.response?.users!![4].category, null)
//        assertEquals(this.mock?.response?.users!![5].latestGames?.size, 0)
//        assertEquals(this.mock?.response?.users!![6].loses, 0)
//
//    }

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