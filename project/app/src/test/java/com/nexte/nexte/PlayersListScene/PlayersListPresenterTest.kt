package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.MatchScene.MatchModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
class PlayersListPresenterTest{

    private var mock : MockPlayersListDisplayLogic? = null
    private var presenter : PlayersListPresenter? = null

    @Before
    fun setUp(){
        this.mock = MockPlayersListDisplayLogic()
        this.presenter = PlayersListPresenter()
        this.presenter?.viewChallenge = mock

    }

    @Test
    fun successFormatPlayersToChallenge() {
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())
        val testUsersList = listOf(testUser)

        val testResponse = PlayersListModel.ShowRankingPlayersRequest.Response(testUsersList)

        //call
        this.presenter?.formatPlayersToChallenge(testResponse)

        //assert
        assertEquals(true, this.mock?.passedHere)
    }


    @Test
    fun successFormatExpandedChallengedInfo() {
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())

        val testResponse = PlayersListModel.SelectPlayerForChallengeRequest.Response(testUser)

        //call
        this.presenter?.formatExpandedChallengedInfo(testResponse)

        //assert
        assertEquals(true, this.mock?.passedHere)
    }

    @Test
    fun successFormatMatch() {
        //prepare
        val testUsername = "Letícia Meneses"
        val challenged = MatchModel.MatchPlayer("Letícia Meneses", 1)
        val challenger = MatchModel.MatchPlayer("Helena Goulart", 2)
        val testChallenge = MatchModel.MatchData(challenged, challenger)

        val testResponse = PlayersListModel.ChallengeButtonRequest.Response(testUsername, testChallenge)

        //call
        this.presenter?.formatMatch(testResponse)

        //assert
        assertEquals(true, this.mock?.passedHere)
    }

    @Test
    fun successFormatNoPlayersMessage() {
        //prepare
        //call
        this.presenter?.formatNoPlayersMessage()

        //assert
        assertEquals(true, this.mock?.passedHere)
    }

    @After
    fun tearDown(){
        this.mock = null
        this.presenter = null

    }


    private class MockPlayersListDisplayLogic: PlayersListDisplayLogic {

        var passedHere = false

        override fun displayMessage(viewModel: PlayersListModel.ChallengeButtonRequest.ViewModel) {
            passedHere = true

        }

        override fun displayNoPlayersMessage(messageText: String) {
            passedHere = true
        }

        override fun displayPlayerDetailedInfo(viewModel: PlayersListModel.SelectPlayerForChallengeRequest.ViewModel) {
            passedHere = true
        }

        override fun displayPlayersToChallenge(viewModel: PlayersListModel.ShowRankingPlayersRequest.ViewModel) {
            passedHere = true
        }

    }
}