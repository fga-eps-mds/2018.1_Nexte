package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class RankingViewTest {

    var view: RankingView? = null

    @Before
    fun setUp() {
        this.view = RankingView()
        this.view?.userManager = UserManager(UserAdapterSpy())
    }

    @Test
    fun testSetupRankingScene(){
        //prepare
        this.view?.setupRankingScene()

        //call

        //assert
        assertNotNull(this.view?.interactor)
        assertNotNull(this.view?.interactor?.presenter)
    }

    @Test
    fun testCreateGetPlayersRequest(){
        //prepare
        this.view?.setupRankingScene()
        val mock = MockRankingsPresentationLogic()
        this.view?.interactor?.presenter = mock

        //call
        thread { this.view?.createGetPlayersRequest() }.join()

        //assert
        assertNotNull(mock.response)
    }

    @After
    fun tearDown() {
    }
}

private class MockRankingsPresentationLogic: RankingPresentationLogic{

    var response: RankingModel.Response? = null

    override fun presentRanking(response: RankingModel.Response) {
        this.response = response
    }
}