package com.nexte.nexte.RankingScene


import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
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
    fun successTestFormatPlayerWithoutCategory(){
        //prepare
        val user1 = User(id = "1", name = "test1", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste@email.com",
                rankingPosition = 1, nickname = "test1", category = UserCategory("1", "teste") , loses = 0, gender = User.Gender.MALE,
                profilePicture = "1", phone = "123456", wins = 1)
        val list = listOf(user1)
        //call
        val returnedValue = presenter?.formatPlayers(list.toTypedArray())

        //assert
        assertEquals(returnedValue?.get(0)?.player?.userCategory, "teste")

    }

    @Test
    fun successTestFormatPlayerWithoutList(){
        //prepare

        val list: Array<User>? = arrayOf()
        //call
        val returnedValue = presenter?.formatPlayers(list)

        //assert
        assertEquals(returnedValue?.size, 0)

    }

    @Test
    fun successCalculatePlayerLastGameCase1(){
        //prepare
        val lastestGame: List<Challenge>? = null
        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, Date())
        //assert
        assertEquals(returnedValue, "Nenhum jogo")
    }

    @Test
    fun successCalculatePlayerLastGameCase2(){
        //prepare
        val lastestGame = listOf(
                Challenge("1", "1", "2", Date(),
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, Date())
        //assert
        assertEquals(returnedValue, "hoje")
    }

    @Test
    fun successCalculatePlayerLastGameCase3(){
        //prepare
        val todayDate = Date()
        val yesterdayDate = Date(todayDate.year, todayDate.month, todayDate.day+4)

        val lastestGame = listOf(
                Challenge("1", "1", "2", yesterdayDate,
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call

        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, todayDate)
        //assert
        assertEquals(returnedValue, "ontem")
    }

    @Test
    fun successCalculatePlayerLastGameCase4(){
        //prepare
        val todayDate = Date()
        val twoDaysAgo = Date(todayDate.year, todayDate.month, todayDate.day-2)
        val lastestGame = listOf(
                Challenge("1", "1", "2", twoDaysAgo,
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, todayDate)
        //assert
        assertEquals(returnedValue, "" + (todayDate.day - twoDaysAgo.day) + " days")
    }

    @Test
    fun successCalculatePlayerLastGameCase5(){
        //prepare
        val todayDate = Date()
        val lastMonth = Date(todayDate.year, todayDate.month-1, todayDate.day)
        val lastestGame = listOf(
                Challenge("1", "1", "2", lastMonth,
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, todayDate)
        //assert
        assertEquals(returnedValue, "" + (todayDate.month - lastMonth.month) + " months")
    }

    @Test
    fun successCalculatePlayerLastGameCase6(){
        //prepare
        val todayDate = Date()
        val lastYear = Date(todayDate.year-1, todayDate.month, todayDate.day)
        val lastestGame = listOf(
                Challenge("1", "1", "2", lastYear,
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, todayDate)
        //assert
        assertEquals(returnedValue,  "" + (todayDate.year - lastYear.year) + " years")
    }

    @Test
    fun successCalculatePlayerLastGameCase7(){
        //prepare
        val lastestGame:List<Challenge>? = listOf()
        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, Date())
        //assert
        assertEquals(returnedValue, "Nenhum jogo")
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
    fun successGetChallengerManager(){
        //prepare and call
        val challengerManager = presenter?.challengeManager

        //assert
        assertEquals(challengerManager, presenter?.challengeManager)
    }

    @Test
    fun successCalculatePlayerEfficiency(){
        //prepare
        val wins = 0
        val losses = 0

        //call
        val efficiency = presenter?.calculatePlayerEfficiency(wins, losses)

        //assert
        assertEquals(efficiency, "0%")
    }

    @Test
    fun successCalculatePlayerEfficiencyWithGames(){
        //prepare
        val wins = 3
        val losses = 5
        val allGames = wins+losses

        //call
        val efficiency = presenter?.calculatePlayerEfficiency(wins, losses)

        //assert
        assertEquals(efficiency, "" + (wins/allGames*oneHundredPercent).toString() + "%")
    }

    @Test
    fun successPresentRankingWithNullViewScene(){
        //prepare
        val backup = presenter?.viewScene
        presenter?.viewScene = null
        mock?.players = listOf()
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
        presenter?.presentRanking(response)
        //assert
        assertEquals(mock?.players?.size, 0)
        //backup
        presenter?.viewScene = backup
    }

    companion object {
        const val oneHundredPercent = 100
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