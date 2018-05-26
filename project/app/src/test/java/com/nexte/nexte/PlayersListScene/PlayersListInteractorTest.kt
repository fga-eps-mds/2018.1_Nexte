package com.nexte.nexte.PlayersListScene

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PlayersListInteractorTest {

    private var interactor: PlayersListInteractor?= null
    private var mock: MockChallengePresentationLogic?= null

    @Before
    fun setUp() {
        this.mock = MockChallengePresentationLogic()
        this.interactor = PlayersListInteractor()
        this.interactor?.presenter = mock
    }

    @Test
    fun successTestConstructor(){
        //prepare and call
        val interactor = PlayersListInteractor()

        //assert
        assertNotNull(interactor)
    }

    @Test
    fun requestPlayersToChallengeTest(){
        //prepare
        val request = PlayersListModel.ShowRankingPlayersRequest.Request(6)
        val expectedResult = true

        //call
        interactor?.requestPlayersToChallenge(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successRequestChallengedUser(){
        //prepare
        val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(6)
        val expectedResult = true

        //call
        interactor?.requestChallengedUser(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successRequestMessageForChallenger() {
        //prepare
        val request = PlayersListModel.ChallengeButtonRequest.Request("larissa")
        val expectedResult = true

        //call
        interactor?.requestChallenger(request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @Test
    fun successGetWorker() {
        //prepare

        //call
        val worker = this.interactor?.worker

        //assert
        assertNotNull(worker)
    }

    @Test
    fun successSetWorker() {
        //prepare
        val worker = PlayersListWorker()

        //call
        this.interactor?.worker = worker

        //assert
        assertEquals(worker, this.interactor?.worker)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockChallengePresentationLogic: ChallengePresentationLogic{

    var passedHere = false

    override fun formatPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
        passedHere = true
    }

    override fun formatExpandedChallengedInfo(response: PlayersListModel.SelectPlayerForChallengeRequest.Response) {
        passedHere = true
    }

    override fun formatMatch(response: PlayersListModel.ChallengeButtonRequest.Response) {
        passedHere = true
    }

    override fun formatNoPlayersMessage() {
        passedHere = true
    }
}