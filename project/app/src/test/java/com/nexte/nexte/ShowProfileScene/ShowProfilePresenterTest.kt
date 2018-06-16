package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
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
        this.presenter?.userManager = UserManager(UserAdapterSpy())
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
    fun getOpponentChallengedSuccess(){
        //prepare
        val challengeTest = Challenge("1",
                "1",
                "13",
                Date(2017, 11, 19),
                Challenge.Status.CONFIRMED,
                Challenge.Stage.Played(
                        1,
                        0,
                        Date(2017, 11, 25),
                        Challenge.Stage.Played.Game(8, 4),
                        null, null, null, null, null
                )
        )
        val userTest =  User("13",
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

        val returnTest = this.presenter?.userManager?.get("1")

        //call
        val testReturn = this.presenter?.getOponent(challengeTest, userTest)

        //assert
        assertEquals(returnTest?.name, testReturn?.name)
        assertEquals(returnTest?.id, testReturn?.id)
        assertEquals(returnTest?.birthDate, testReturn?.birthDate)
    }

    @Test
    fun getOpponentChallengerSuccess(){
        //prepare
        val challengeTest = Challenge("1",
                "13",
                "1",
                Date(2017, 11, 19),
                Challenge.Status.CONFIRMED,
                Challenge.Stage.Played(
                        1,
                        0,
                        Date(2017, 11, 25),
                        Challenge.Stage.Played.Game(8, 4),
                        null, null, null, null, null
                )
        )

        val userTest =  User("13",
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

        val returnTest = this.presenter?.userManager?.get("1")

        //call
        val testReturn = this.presenter?.getOponent(challengeTest, userTest)

        //assert
        assertEquals(returnTest?.name, testReturn?.name)
        assertEquals(returnTest?.id, testReturn?.id)
        assertEquals(returnTest?.birthDate, testReturn?.birthDate)
    }

    @Test
    fun getNumberOfSetsNull(){
        //prepare
        val challengeTest = Challenge("1",
                "13",
                "15",
                Date(2017, 11, 19),
                Challenge.Status.CONFIRMED,
                Challenge.Stage.Played(
                        1,
                        0,
                        Date(2017, 11, 25),
                        Challenge.Stage.Played.Game(null, null),
                        null, null, null, null, null
                )
        )
        val gamePlayedTest = challengeTest.stage as Challenge.Stage.Played

        //call
        val testReturn = this.presenter?.getNumberOfSets(gamePlayedTest.firstGame)

        //assert
        assertEquals(testReturn, "")

    }

    @Test
    fun getNumberOfSetsSuccess(){
        //prepare
        val challengeTest = Challenge("1",
                "13",
                "15",
                Date(2017, 11, 19),
                Challenge.Status.CONFIRMED,
                Challenge.Stage.Played(
                        1,
                        0,
                        Date(2017, 11, 25),
                        Challenge.Stage.Played.Game(8, 4),
                        null, null, null, null, null
                )
        )
        val gamePlayedTest = challengeTest.stage as Challenge.Stage.Played

        //call
        val testReturn = this.presenter?.getNumberOfSets(gamePlayedTest.firstGame)

        //assert
        assertEquals(testReturn, "8/4  ")

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