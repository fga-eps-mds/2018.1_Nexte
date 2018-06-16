package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
class ShowProfileModelTest {

    @Before
    fun setUp() {

    }

    @Test
    fun initShowProfile(){
        //prepare
        //call
        val testInit = ShowProfileModel()

        //assert
        assertNotNull(testInit)
    }

    @Test
    fun successRequest(){
        //prepare
        val userName = "luis-gustavo"

        //call
        val request = ShowProfileModel.Request("luis-gustavo")
        request.userId = userName

        //assert
        assertEquals(userName, request.userId)
    }

    @Test
    fun successResponse(){
        //prepare
        val player = User("13",
                "Nick Cairo",
                null,
                "Cairo",
                Date(1993, 3, 13),
                12,
                "cairo@nexte.com",
                "130",
                68,
                96,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )

        //call
        val response = ShowProfileModel.Response(user = player)
        response.user = player

        //assert
        assertNotNull(response.user)
        assertEquals(response.user, player)

    }

    @Test
    fun successViewModel(){
        //prepare
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Luis Gustavo", rank = "1", email = "luis@email.com",  profileImage = R.drawable.profile_image9)

        //call
        val viewModel = ShowProfileModel.ViewModel(playerInfo = formattedPlayer, formattedChallenges = listOf())
        val img = formattedPlayer.profileImage
        formattedPlayer.profileImage = 2
        viewModel.playerInfo = formattedPlayer

        //assert
        assertNotNull(viewModel.playerInfo)
        assertEquals(formattedPlayer, viewModel.playerInfo)
        assertEquals(formattedPlayer.name, viewModel.playerInfo.name)
        assertEquals(formattedPlayer.rank, viewModel.playerInfo.rank)
        assertEquals(formattedPlayer.email, viewModel.playerInfo.email)
        assertEquals(img, R.drawable.profile_image9)
        assertEquals(formattedPlayer.profileImage, 2)
    }

    @Test
    fun successFormattedPlayer(){
        //prepare
        val name = "Luis Gustavo"
        val rank = "1"
        val email = "luis@email.com"

        //call
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Luis Gustavo", rank = "1", email = "luis@email.com",  profileImage = R.drawable.profile_image9)

        formattedPlayer.email = email
        formattedPlayer.name = name
        formattedPlayer.rank = rank

        //assert
        assertEquals(name, formattedPlayer.name)
        assertEquals(rank, formattedPlayer.rank)
        assertEquals(email, formattedPlayer.email)
    }

    @Test
    fun successShowProfileModel() {
        //prepare

        //call
        val model = ShowProfileModel()

        //assert
        assertNotNull(model)
    }

    @Test
    fun testChallengeResult(){
        var challengeResultWO = ShowProfileModel.ChallengeResult.WO
        challengeResultWO = ShowProfileModel.ChallengeResult.WO
        var challengeResultLost = ShowProfileModel.ChallengeResult.LOST
        challengeResultLost = ShowProfileModel.ChallengeResult.LOST
        var challengeResultWON = ShowProfileModel.ChallengeResult.WON
        challengeResultWON = ShowProfileModel.ChallengeResult.WON

        assertEquals(challengeResultWO, ShowProfileModel.ChallengeResult.WO)
        assertEquals(challengeResultWON, ShowProfileModel.ChallengeResult.WON)
        assertEquals(challengeResultLost, ShowProfileModel.ChallengeResult.LOST)

    }

    @Test
    fun testFormattedChallenge(){
        val challengeDates = "12/03/2014"
        val setResult = "2x1"
        var gamesResults = "2x1"
        var headToHeadResults = "1x0"
        var opponentName = "luis"
        var opponentPictureUrl = "1"
        var opponentPictureAdress = 1
        var opponentColor = 2
        var challengeResult = ShowProfileModel.ChallengeResult.WO
        var formattedChallenge = ShowProfileModel.FormattedChallenge(
                challengeDates,setResult,gamesResults,headToHeadResults, opponentName,
                opponentPictureUrl, opponentPictureAdress, opponentColor, challengeResult
        )
        formattedChallenge.challengeDates = challengeDates
        formattedChallenge.setsResult = setResult
        formattedChallenge.gamesResults = gamesResults
        formattedChallenge.headToHeadResults = headToHeadResults
        formattedChallenge.opponentName = opponentName
        formattedChallenge.opponentPictureUrl = opponentPictureUrl
        formattedChallenge.opponentPictureAdress = opponentPictureAdress
        formattedChallenge.opponentColor = opponentColor
        formattedChallenge.challengeResult = challengeResult

        assertEquals(formattedChallenge.challengeDates, challengeDates)
        assertEquals(formattedChallenge.setsResult, setResult)
        assertEquals(formattedChallenge.gamesResults, gamesResults)
        assertEquals(formattedChallenge.headToHeadResults, headToHeadResults)
        assertEquals(formattedChallenge.opponentColor, opponentColor)
        assertEquals(formattedChallenge.opponentPictureAdress, opponentPictureAdress)
        assertEquals(formattedChallenge.opponentPictureUrl, opponentPictureUrl)
        assertEquals(formattedChallenge.opponentName, opponentName)
        assertEquals(formattedChallenge.challengeResult, challengeResult)
    }

    @After
    fun tearDown() {

    }
}