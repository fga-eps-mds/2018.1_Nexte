package com.nexte.nexte.MatchScene

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.nexte.nexte.Entities.Challenge.ChallengeAdapterSpy
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class MatchFragmentTest {

    var view: MatchFragment? = null
    var mock: MockerMatchUpdateWorkerLogic? = null

    @Before
    fun setUp() {
        view = MatchFragment()
        mock = MockerMatchUpdateWorkerLogic()
    }

    @Test
    fun testGetInstanceNotNull(){
        val challenged = MatchModel.MatchPlayer("123", 1)
        var challenger = MatchModel.MatchPlayer("321", 2)
        val challenge = MatchModel.MatchData(challenged, challenger)

        thread {
            assertNotNull(this.view?.getInstance(challenge))
        }.join()

    }

    @Test
    fun testGetInstanceNull(){
        val challenge = null

        thread {
            assertNull(this.view?.getInstance(challenge))
        }.join()
    }

    @Test
    fun testOnCreate() {
        //prepare
        val bundle = Bundle()

        thread {
            //call
            view?.onCreate(bundle)

            //assert
            assertNotNull(view?.challenged)

        }.join()
    }

    @Test
    fun declineMatch() {

        this.view?.interactor = MatchInteractor()
        this.view?.interactor?.worker?.updateLogic = mock
        this.view?.interactor?.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        this.view?.declineMatch()

        assertEquals(this.mock?.response?.status, MatchModel.DeclineChallengeRequest.Status.SUCCESS)
    }

    @Test
    fun displayDeclineMatchSuccess() {
        val viewModel = MatchModel.DeclineChallengeRequest.ViewModel(MatchModel.DeclineChallengeRequest.Status.SUCCESS, "")

        thread {
            this.view?.displayDeclineMatch(viewModel)
        }.join()
    }

    @Test
    fun displayDeclineMatchFail() {
        val viewModel = MatchModel.DeclineChallengeRequest.ViewModel(MatchModel.DeclineChallengeRequest.Status.ERROR, "")

        thread {
            this.view?.displayDeclineMatch(viewModel)
        }.join()
    }

    @Test
    fun sendMatchResultTest() {
        this.view?.interactor = MatchInteractor()
        this.view?.interactor?.worker?.updateLogic = mock
        this.view?.interactor?.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        this.view?.sendMatchResult()

        assertEquals(this.mock?.responseMatch?.status, MatchModel.SendMatchResult.Status.SUCESSED)
    }

    @Test
    fun setUpMatchSceneTest() {
        this.view?.setUpMatchScene()

        assertNotNull(this.view?.interactor)
        assertNotNull(this.view?.interactor?.presenter)
        assertNotNull(this.view?.interactor?.worker?.updateLogic)


    }

    @Test
    fun updateSetsNumberTest() {
        var setsNumber = MatchModel.SetsNumber.One
        this.view?.updateSetsNumber(setsNumber)

        assertEquals(setsNumber, this.view?.numberOfSets)
    }

    @After
    fun tearDown() {
    }
}

class MockerMatchUpdateWorkerLogic: MatchUpdateWorkerLogic {

    var response: MatchModel.DeclineChallengeRequest.Response? = null
    var responseMatch: MatchModel.SendMatchResult.Response? = null

    override fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response) {
        this.response = response
    }

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        this.responseMatch = response
    }
}
