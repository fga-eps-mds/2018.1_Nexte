package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class RankingInteractorTest {

    private var interactor: RankingInteractor? = null
    private var mock: MockRankingPresentationLogic? = null
    private var rankingUpdateLogicMock: MockRankWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingPresentationLogic()
        this.interactor = RankingInteractor(presenter = mock)
        this.interactor?.worker?.userManager = UserManager(UserAdapterSpy())
        this.rankingUpdateLogicMock = MockRankWorkerUpdateLogic()
        this.interactor?.worker?.updateLogic = rankingUpdateLogicMock
        this.rankingUpdateLogicMock?.mock = mock
    }

    @Test
    fun successRankingInteractor(){
        //prepare
        val newWorker = RankingWorker()

        // call
        val interactor = RankingInteractor()
        val oldWorker = interactor.worker
        interactor.worker = newWorker


        //assert
        assertNotNull(interactor)
        assertNotNull(oldWorker)
        assertEquals(interactor.worker, newWorker)
    }

//    @Test
//    fun testGetPlayersRanksForScene(){
//        //prepare
//        val newWorker = RankingWorker()
//        val request = RankingModel.Request()
//
//        //call
//        val interactor = RankingInteractor()
//        val oldWorker = interactor.worker
//        interactor.worker = newWorker
//        thread { this.interactor?.getPlayersRanksForScene(request = request) }.join()
//
//        //assert
//        assertEquals(this.mock?.passedHere, true)
//    }

    @Test
    fun successInteractor(){
        //prepare and call
        val interactor = RankingInteractor()

        //assert
        assertNotNull(interactor)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockRankingPresentationLogic: RankingPresentationLogic{

    var passedHere = false

    override fun presentRanking(response: RankingModel.Response) {
        this.passedHere = true
    }
}

private class MockRankWorkerUpdateLogic : RankingWorkerUpdateLogic{
    var mock: MockRankingPresentationLogic? = null

    override fun updateUsersInRanking(response: RankingModel.Response) {
        this.mock?.presentRanking(response)
    }
}