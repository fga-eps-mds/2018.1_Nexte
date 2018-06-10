package com.nexte.nexte.PlayersListScene


import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class PlayersListInteractorTest: HelpForRealm() {

    private var mocker : MockPlayersListPresentationLogic? = null
    private var interactor : PlayersListInteractor? = null

    @Before
    fun setUp(){
        super.setUpWithUser()

        this.mocker = MockPlayersListPresentationLogic()
        this.interactor = PlayersListInteractor()
        this.interactor?.presenter = mocker
    }

    @After
    fun tearDown(){
        super.tearDownRealm()

        this.mocker = null
        this.interactor = null
    }

//    @Test
//    fun successRequestPlayersToChallenge(){
//        //prepare
//        val testRequest = PlayersListModel.ShowRankingPlayersRequest.Request(1)
//
//        //call
//        this.interactor?.requestPlayersToChallenge(testRequest)
//
//        //assert
//        assertEquals(true, this.mocker?.passedHere)
//    }

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
}