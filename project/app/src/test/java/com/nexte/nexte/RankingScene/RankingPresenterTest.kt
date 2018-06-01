package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class RankingPresenterTest {

    private var presenter: RankingPresenter? = null
    private var mock: MockRankingDisplayLogic? = null

    @Before
    fun setUp() {
        this.mock = MockRankingDisplayLogic()
        this.presenter = RankingPresenter(viewScene = mock)
    }

    @Test
    fun successPresentRanking(){
        //prepare
        val user1 = User(id = "1", name = "test1", status = User.Status.AVAILABLE, challengeSended = null,
        challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste@email.com",
        rankingPosition = 1, nickname = "test1", category = null, loses = 0, gender = User.Gender.MALE,
        profilePicture = "1", phone = "123456", wins = 1)
        val user2 = User(id = "2", name = "test2", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste2@email.com",
                rankingPosition = 2, nickname = "test2", category = null, loses = 1, gender = User.Gender.MALE,
                profilePicture = "2", phone = "1234567", wins = 1)


        val response = RankingModel.Response(users = arrayOf(user1, user2))

        //call
        this.presenter?.presentRanking(response = response)

        //assert
        assertEquals(this.mock?.players?.size, 2)
        assertEquals(this.mock?.players!![0].player.userName, user1.name)
        assertEquals(this.mock?.players!![0].player.userRankingPosition, user1.rankingPosition.toString())
        assertEquals(this.mock?.players!![0].player.userWins, "Vitórias: " + user1.wins.toString())
        assertEquals(this.mock?.players!![0].player.userEfficiency, "Aproveitamento: 100%")
        assertEquals(this.mock?.players!![0].player.userLastGame, "Último Jogo: Nenhum jogo")
        assertEquals(this.mock?.players!![0].player.userCategory, "")

        assertEquals(this.mock?.players!![1].player.userName, user2.name)
        assertEquals(this.mock?.players!![1].player.userRankingPosition,  user2.rankingPosition.toString())
        assertEquals(this.mock?.players!![1].player.userWins,"Vitórias: " +  user2.wins.toString())
        assertEquals(this.mock?.players!![1].player.userEfficiency, "Aproveitamento: 0%")
        assertEquals(this.mock?.players!![1].player.userLastGame, "Último Jogo: Nenhum jogo")
        assertEquals(this.mock?.players!![1].player.userCategory, "")
    }

    @Test
    fun successRankingPresenter(){
        //prepare and call
        val presenter = RankingPresenter()
        val newViewScene = RankingFragment()
        newViewScene.userManager = UserManager(UserAdapterSpy())
        presenter.viewScene = newViewScene
        val testViewScene = presenter.viewScene

        //assert
        assertNotNull(presenter)
        assertNotNull(testViewScene)
        assertEquals(testViewScene, newViewScene)
    }

    @Test
    fun successPresenter(){
        //prepare and call
        val presenter = RankingPresenter()

        //assert
        assertNotNull(presenter)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockRankingDisplayLogic: RankingDisplayLogic{

    var players: List<RankingModel.FormattedPlayerInfo> = listOf()

    override fun displayRankingInScreen(viewModel: RankingModel.ViewModel) {
        this.players = viewModel.formattedPlayers
    }
}