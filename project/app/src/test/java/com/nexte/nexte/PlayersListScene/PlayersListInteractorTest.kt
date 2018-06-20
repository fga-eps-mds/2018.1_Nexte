package com.nexte.nexte.PlayersListScene


import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.concurrent.thread


class PlayersListInteractorTest: HelpForRealm() {

    private var mocker : MockPlayersListPresentationLogic? = null
    private var interactor : PlayersListInteractor? = null
    private var playerListUpdateLogicMock: PlayerListUpdateLogicMock? = null

    @Before
    fun setUp(){
        super.setUpWithUser()
        this.mocker = MockPlayersListPresentationLogic()
        this.interactor = PlayersListInteractor()
        this.interactor?.presenter = mocker
        this.interactor?.worker?.userManager = UserManager(UserAdapterSpy())
        this.playerListUpdateLogicMock = PlayerListUpdateLogicMock()
        this.interactor?.worker?.updateLogic = playerListUpdateLogicMock
        this.playerListUpdateLogicMock?.mock = mocker
    }

    @After
    fun tearDown(){
        super.tearDownRealm()

        this.mocker = null
        this.interactor = null
    }

    @Test
    fun successRequestPlayersToChallenge(){
        //prepare
        val testRequest = PlayersListModel.ShowRankingPlayersRequest.Request(1)

        //call
        thread { this.interactor?.requestPlayersToChallenge(testRequest) }.join()

        //assert
        assertEquals(true, this.mocker?.passedHere)
    }

    @Test
    fun successRankingInteractor(){
        //prepare
        val newWorker = PlayersListWorker()

        // call
        val interactor = PlayersListInteractor()
        val oldWorker = interactor.worker
        interactor.worker = newWorker


        //assert
        assertNotNull(interactor)
        assertNotNull(oldWorker)
        assertEquals(interactor.worker, newWorker)
    }

    @Test
    fun getPlayersToChallengeTest() {
        // prepare
        val users: List<User> = listOf()
        val response = PlayersListModel.ShowRankingPlayersRequest.Response(users)

        // call
        this.mocker?.formatPlayersToChallenge(response)

        // assert
        assertEquals(true, this.mocker?.passedHere)
    }


    @Test
    fun getPlayerToChallenge() {
        // prepare
        val users: List<User> = listOf()
        val response =  PlayersListModel.ShowRankingPlayersRequest.Response(users)

        // call
        thread { this.interactor?.getPlayersToChallenge(response = response) }.join()

        //assert
        assertEquals(this.mocker?.passedHere, true)
    }

    @Test
    fun getWorkerTest() {
        // prepare and call
        val worker = PlayersListWorker()
        assertNotNull(worker)
    }

    @Test
    fun requestChallenger(){
        //prepare
        val request = PlayersListModel.ChallengeButtonRequest.Request("1")

        //call
        this.interactor?.requestChallenger(request)

        //assert
        assertEquals(true, this.playerListUpdateLogicMock?.passedHere)
    }

    @Test
    fun requestChallengedUserTest() {

        // prepare
        val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(1)

        //call
        this.interactor?.requestChallengedUser(request = request)

        //assert
        assertEquals(this.mocker?.passedHere, true)
    }

    @Test
    fun successRequestChallengedUser(){
        //prepare
        val worker = PlayersListWorker()
        worker.userManager = UserManager(UserAdapterSpy())
        this.interactor?.worker = worker
        val testRequest = PlayersListModel.SelectPlayerForChallengeRequest.Request(1)

        //call
        this.interactor?.requestChallengedUser(testRequest)

        //assert
        assertEquals(true, this.mocker?.passedHere)
    }
}

private class MockPlayersListPresentationLogic : PlayersListPresentationLogic {

    var passedHere = false

    override fun formatExpandedChallengedInfo(response: PlayersListModel.SelectPlayerForChallengeRequest.Response) {
        passedHere = true
    }

    override fun formatMatch(response: PlayersListModel.ChallengeButtonRequest.Response) {
        passedHere = true
    }

    override fun formatNoPlayersMessage() {
        passedHere = true
    }

    override fun formatPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
        passedHere = true
    }
}

private class PlayerListUpdateLogicMock: PlayerListUpdateLogic {

    var mock: MockPlayersListPresentationLogic? = null
    var passedHere = false

    override fun getPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
        this.passedHere = true
        this.mock?.formatPlayersToChallenge(response)
    }

    override fun generateChallengeReponse(response: PlayersListModel.ChallengeButtonRequest.Response) {
        this.passedHere = true
    }
}