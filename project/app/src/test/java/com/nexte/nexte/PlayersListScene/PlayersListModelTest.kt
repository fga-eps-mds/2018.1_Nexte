package com.nexte.nexte.PlayersListScene

import android.os.UserManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserMocker
import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.R
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class PlayersListModelTest {

    @Before
    fun setUp(){

    }

    @Test
    fun successShowRankingRequest(){
        //prepare
        val testInt = 2


        //call
        val testShowRankingRequestRequest = PlayersListModel.ShowRankingPlayersRequest.Request(testInt)
        testShowRankingRequestRequest.challengerRankingPosition = testInt

        //assert
        assertEquals(testInt, testShowRankingRequestRequest.challengerRankingPosition)
    }

    @Test
    fun successShowRankingResponse(){
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


        //call

        val testShowRankingRequestResponse = PlayersListModel.ShowRankingPlayersRequest.Response(testUsersList)


        //assert
        assertEquals(testUsersList, testShowRankingRequestResponse.usersAbove)
    }

    @Test
    fun successShowRankingViewModel(){
        //prepare
        val testFormatPlayer = PlayersListModel.FormattedPlayer("Gabriel Albino",
                                                             "2",
                                                             "identifier")
        val testPlayersFormattedList = listOf(testFormatPlayer)

        //call
        val testShowRankingRequestViewModel = PlayersListModel.ShowRankingPlayersRequest.ViewModel(
                testPlayersFormattedList)

        //assert
        assertEquals(testFormatPlayer, testShowRankingRequestViewModel.formattedPlayer[0])
    }

    ///////////////////////////////////////////////

    @Test
    fun successSelectPlayerRequest(){
        //prepare
        val testInt = 2


        //call
        val testSelectPlayerRequest = PlayersListModel.SelectPlayerForChallengeRequest.Request(testInt)
        testSelectPlayerRequest.challengedRankingPosition= testInt

        //assert
        assertEquals(testInt, testSelectPlayerRequest.challengedRankingPosition)
    }

    @Test
    fun successSelectPlayerResponse(){
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


        //call

        val testSelectPlayerRequest = PlayersListModel.SelectPlayerForChallengeRequest.Response(testUser)


        //assert
        assertEquals(testUser, testSelectPlayerRequest.challengedPersonalDetails)
    }

    @Test
    fun successSelectPlayerViewModel(){
        //prepare
        val testRankingDetails = PlayersListModel.FormattedRankingDetails("Daniel Maike",
                                                                          "4",
                                                                          "6",
                                                                          "1")


        //call
        val testSelectPlayerForChallenge = PlayersListModel.SelectPlayerForChallengeRequest.ViewModel(testRankingDetails)

        //assert
        assertEquals(testRankingDetails, testSelectPlayerForChallenge.challengedRankingDetails)
    }

    ///////////////////////////////////////////////

    @Test
    fun successChallengeButtonRequest(){
        //prepare
        val testChallenged = "Lorrany Freire"


        //call
        val testChallengeButtonRequest = PlayersListModel.ChallengeButtonRequest.Request(testChallenged)
        testChallengeButtonRequest.userChallenged = testChallenged

        //assert
        assertEquals(testChallenged, testChallengeButtonRequest.userChallenged)
    }

    @Test
    fun successChallengeButtonResponse(){
        //prepare
        val testUsername = "Letícia Meneses"
        val challenged = MatchModel.MatchPlayer("Letícia Meneses", 1)
        val challenger = MatchModel.MatchPlayer("Helena Goulart", 2)
        val testChallenge = MatchModel.MatchData(challenged, challenger)


        //call

        val testChallengeButtonResponse = PlayersListModel.ChallengeButtonRequest.Response(testUsername,
                                                                                           testChallenge)

        //assert
        assertEquals(testUsername,testChallengeButtonResponse.username)
        assertEquals(testChallenge.challenged, testChallengeButtonResponse.challenge.challenged)
        assertEquals(testChallenge.challenger, testChallengeButtonResponse.challenge.challenger)
    }

    @Test
    fun successChallengeButtonViewModel(){
        //prepare
        val testMessageForChallenge = "you did it"
        val testMatchMessage = "the match did it"
        val challenged = MatchModel.MatchPlayer("Letícia Meneses", 1)
        val challenger = MatchModel.MatchPlayer("Helena Goulart", 2)
        val testMatchData: MatchModel.MatchData?
        testMatchData = MatchModel.MatchData(challenged, challenger)

        //call
        val testChallengeButtonViewModel = PlayersListModel.ChallengeButtonRequest.ViewModel(testMessageForChallenge,
                                                                                             testMatchMessage,
                                                                                             testMatchData)
        testChallengeButtonViewModel.matchMessage = testMatchMessage
        testChallengeButtonViewModel.messageForChallenger = testMessageForChallenge
        testChallengeButtonViewModel.matchData?.challenger = challenger
        testChallengeButtonViewModel.matchData?.challenged = challenged


        //assert
        assertEquals(testMessageForChallenge, testChallengeButtonViewModel.messageForChallenger)
        assertEquals(testMatchMessage, testChallengeButtonViewModel.matchMessage)
        assertEquals(testMatchData, testChallengeButtonViewModel.matchData)
    }

    ////////////////////////////////////////

    @Test
    fun successFormattedPlayer(){
        //prepare
        val testName = "Mendelson"
        val testPosition = "2"
        val testPicture = "validToken"

        //call
        val testFormattedPlayer = PlayersListModel.FormattedPlayer(testName, testPosition, testPicture)
        testFormattedPlayer.name = testName
        testFormattedPlayer.rankingPosition = testPosition
        testFormattedPlayer.pictureAddress = testPicture

        //assert
        assertEquals(testName, testFormattedPlayer.name)
        assertEquals(testPosition, testFormattedPlayer.rankingPosition)
        assertEquals(testPicture, testFormattedPlayer.pictureAddress)
    }

    @Test
    fun successFormattedRankingDetails(){
        //prepare
        val testName = "Mendelson"
        val testPosition = "2"
        val testLosses = "0"
        val testWins = "3"

        //call
        val testFormattedRankingDetails = PlayersListModel.FormattedRankingDetails(testName,
                                                                                   testPosition,
                                                                                   testLosses,
                                                                                   testWins)
        testFormattedRankingDetails.name = testName
        testFormattedRankingDetails.rankingPosition = testPosition
        testFormattedRankingDetails.loses = testLosses
        testFormattedRankingDetails.wins = testWins

        //assert
        assertEquals(testName, testFormattedRankingDetails.name)
        assertEquals(testPosition, testFormattedRankingDetails.rankingPosition)
        assertEquals(testLosses, testFormattedRankingDetails.loses)
        assertEquals(testWins, testFormattedRankingDetails.wins)
    }

    @Test
    fun successPlayerRankingDetails(){
        //prepare
        val testName = "Mendelson"
        val testPosition = 2
        val testLosses = 0
        val testWins = 3

        //call
        val testPlayerRankingDetails = PlayersListModel.PlayerRankingDetails(testName,
                                                                             testWins,
                                                                             testLosses,
                                                                             testPosition)
        testPlayerRankingDetails.name = testName
        testPlayerRankingDetails.wins = testWins
        testPlayerRankingDetails.loses = testLosses
        testPlayerRankingDetails.rankingPosition = testPosition

        //assert
        assertEquals(testName, testPlayerRankingDetails.name)
        assertEquals(testPosition, testPlayerRankingDetails.rankingPosition)
        assertEquals(testLosses, testPlayerRankingDetails.loses)
        assertEquals(testWins, testPlayerRankingDetails.wins)
    }



    @After
    fun tearDown(){

    }


}
