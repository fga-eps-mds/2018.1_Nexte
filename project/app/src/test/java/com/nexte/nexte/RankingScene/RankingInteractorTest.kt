package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RankingInteractorTest {

    private var interactor: RankingInteractor? = null
    private var mock: MockRankingPresentationLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingPresentationLogic()
        this.interactor = RankingInteractor(presenter = mock)
        this.interactor?.worker?.userManager = UserManager(UserAdapterSpy())
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

    @Test
    fun testGetPlayersRanksForScene(){
        //prepare
        val request = RankingModel.Request()

        //call
        this.interactor?.getPlayersRanksForScene(request = request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

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