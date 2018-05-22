package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
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

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        //RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
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
        assertEquals(this.mock?.response?.players?.size, 9)
        assertEquals(this.mock?.response?.players!![0].name, "User test")
        assertEquals(this.mock?.response?.players!![3].wins, 0)
        assertEquals(this.mock?.response?.players!![1].rankingPosition, 1)
        assertEquals(this.mock?.response?.players!![4].playerCategory, "")
        assertEquals(this.mock?.response?.players!![5].lastGame, "Nenhum jogo")
        assertEquals(this.mock?.response?.players!![6].efficiency, "100%")

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