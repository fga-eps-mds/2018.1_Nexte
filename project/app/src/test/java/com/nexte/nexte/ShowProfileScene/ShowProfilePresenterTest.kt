package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
class ShowProfilePresenterTest {

    private var mock: MockShowProfileDisplayLogic? = null
    private var presenter: ShowProfilePresenter? = null

    @Before
    fun setUp() {
        this.mock = MockShowProfileDisplayLogic()
        this.presenter = ShowProfilePresenter()
        this.presenter?.viewScene = mock
    }



    @Test
    fun successPresentUserProfile(){
        //prepare
        val response = ShowProfileModel.Response(user = User("13",
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
        ))
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Nick Cairo", rank = "#12", email = "cairo@nexte.com",  profileImage = R.drawable.profile_image9)

        //call
        this.presenter?.presentUserProfile(response = response)

        //assert
        assertEquals(formattedPlayer.name, this.mock?.formattedPlayer?.name)
        assertEquals(formattedPlayer.rank, this.mock?.formattedPlayer?.rank)
        assertEquals(formattedPlayer.email, this.mock?.formattedPlayer?.email)
    }


    @Test
    fun successPresentUserProfileCase2(){
        //prepare
        val response = ShowProfileModel.Response(user = User("13",
                "Nick Cairo",
                "1",
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
        ))
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "Nick Cairo", rank = "#12", email = "cairo@nexte.com",  profileImage = R.drawable.profile_image9)

        //call
        this.presenter?.presentUserProfile(response = response)

        //assert
        assertEquals(formattedPlayer.name, this.mock?.formattedPlayer?.name)
        assertEquals(formattedPlayer.rank, this.mock?.formattedPlayer?.rank)
        assertEquals(formattedPlayer.email, this.mock?.formattedPlayer?.email)
    }

    @Test
    fun testPresentUserProfileWithNullResponse() {
        //prepare
        val response = ShowProfileModel.Response(user = null)

        //call
        this.presenter?.presentUserProfile(response = response)

        //assert
        assertNotNull(mock?.formattedPlayer)
    }

    @Test
    fun failedPresentUserProfile() {
        //prepare
        val response = ShowProfileModel.Response(user = User("13",
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
        ))

        //call
        this.presenter?.viewScene = null
        this.presenter?.presentUserProfile(response = response)

        //assert
        assertNull(mock?.formattedPlayer)

    }

    @Test
    fun getViewScene() {
        //prepare
        val viewScene = presenter?.viewScene

        //assert
        assertEquals(viewScene, this.presenter?.viewScene)
    }

    @Test
    fun testCalculateWinnerInHeadToHead(){
        var points = mutableListOf(0, 0)
        val challenge = Challenge(id = "1", challengeDate = Date(), challengedId = "2", challengerId = "1",
                status = Challenge.Status.CONFIRMED, stage = Challenge.Stage.Played(setChallenger = 1,
                setChallenged = 0, detail = "", date = Date(), firstGame = Challenge.Stage.Played.Game(
                gameChallenger = 1, gameChallenged = 0),
                secondGame = null,thirdGame = null, fourthGame = null, fifthGame = null))
        val user = "1"
        val opponent = "2"

        points = presenter?.calculateWinnerInHeadToHead(points, challenge, user, opponent)!!

        assertEquals(points, mutableListOf(1, 0))
    }

    @Test
    fun testCalculateWinnerInHeadToHeadCase2(){
        var points = mutableListOf(0, 0)
        val challenge = Challenge(id = "1", challengeDate = Date(), challengedId = "2", challengerId = "1",
                status = Challenge.Status.CONFIRMED, stage = Challenge.Stage.Played(setChallenger = 0,
                setChallenged = 1, detail = "", date = Date(), firstGame = Challenge.Stage.Played.Game(
                gameChallenger = 1, gameChallenged = 0),
                secondGame = null,thirdGame = null, fourthGame = null, fifthGame = null))
        val user = "1"
        val opponent = "2"

        points = presenter?.calculateWinnerInHeadToHead(points, challenge, user, opponent)!!

        assertEquals(points[0], 0)
        assertEquals(points[1], 1)
    }

    @Test
    fun testCalculateWinnerInHeadToHeadCase3(){
        var points = mutableListOf(0, 0)
        val challenge = Challenge(id = "1", challengeDate = Date(), challengedId = "1", challengerId = "2",
                status = Challenge.Status.CONFIRMED, stage = Challenge.Stage.Played(setChallenger = 0,
                setChallenged = 1, detail = "", date = Date(), firstGame = Challenge.Stage.Played.Game(
                gameChallenger = 1, gameChallenged = 0),
                secondGame = null,thirdGame = null, fourthGame = null, fifthGame = null))
        val user = "1"
        val opponent = "2"

        points = presenter?.calculateWinnerInHeadToHead(points, challenge, user, opponent)!!

        assertEquals(points, mutableListOf(1, 0))
    }

    @Test
    fun testCalculateWinnerInHeadToHeadCase4(){
        var points = mutableListOf(0, 0)
        val challenge = Challenge(id = "1", challengeDate = Date(), challengedId = "1", challengerId = "2",
                status = Challenge.Status.CONFIRMED, stage = Challenge.Stage.Played(setChallenger = 1,
                setChallenged = 0, detail = "", date = Date(), firstGame = Challenge.Stage.Played.Game(
                gameChallenger = 1, gameChallenged = 0),
                secondGame = null,thirdGame = null, fourthGame = null, fifthGame = null))
        val user = "1"
        val opponent = "2"

        points = presenter?.calculateWinnerInHeadToHead(points, challenge, user, opponent)!!

        assertEquals(points, mutableListOf(0, 1))
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockShowProfileDisplayLogic: ShowProfileDisplayLogic{
    var formattedPlayer: ShowProfileModel.FormattedPlayer? = null

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        this.formattedPlayer = viewModel.playerInfo
    }
}