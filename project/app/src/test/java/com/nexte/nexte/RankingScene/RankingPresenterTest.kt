package com.nexte.nexte.RankingScene


import android.graphics.Color
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
    fun successTestFormatPlayerWithNullCategory(){
        //prepare
        val user1 = User(id = "1", name = "test1", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste@email.com",
                rankingPosition = 1, nickname = "test1", category = null , loses = 0, gender = User.Gender.MALE,
                profilePicture = null, phone = "123456", wins = 1)
        val list = listOf(user1)
        //call
        val returnedValue = presenter?.formatPlayers(list.toTypedArray())

        //assert
        assertEquals(returnedValue?.get(0)?.player?.userCategory, "")

    }

    @Test
    fun successTestFormatPlayerWithNullManager(){
        //prepare
        val user1 = User(id = "1", name = "test1", status = User.Status.AVAILABLE, challengeSended = null,
                challengeReceived = null, latestGames = listOf(), birthDate = Date(), email = "teste@email.com",
                rankingPosition = 1, nickname = "test1", category = null , loses = 0, gender = User.Gender.MALE,
                profilePicture = null, phone = "123456", wins = 1)
        val list = listOf(user1)
        val backup = presenter?.challengeManager
        presenter?.challengeManager = null
        //call
        val returnedValue = presenter?.formatPlayers(list.toTypedArray())

        //assert
        assertEquals(returnedValue?.get(0)?.player?.userLastGame, "Último Jogo: Nenhum jogo")
        //backup
        presenter?.challengeManager = backup
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
    fun successTestFormatPlayerWithNullList(){
        //prepare

        val list: Array<User>? = null
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
        val todayDate = Date(2018, 3, 20)
        val yesterdayDate = Date(2018, 3, 19)

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
        val todayDate = Date(2018, 5, 20)
        val twoDaysAgo = Date(todayDate.year, todayDate.month, 18)
        val lastestGame = listOf(
                Challenge("1", "1", "2", twoDaysAgo,
                        Challenge.Status.CONFIRMED, Challenge.Stage.Canceled("sla bixo",
                        Challenge.UserType.CHALLENGED, Date()))
        )

        //call
        val returnedValue = presenter?.calculatePlayerLastGame(lastestGame, todayDate)
        //assert
        assertEquals(returnedValue, "2 days")
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

    @Test
    fun testGetPlayerLastFiveGamesArray(){

        val userId = "5"
        val challenge1 = Challenge("19","5","6",Date(2017, 11, 27),
                                    Challenge.Status.CONFIRMED, Challenge.Stage.Played(2,1,
                                    Date(2017, 11, 30),
                                    Challenge.Stage.Played.Game(6, 4),
                                    Challenge.Stage.Played.Game(1, 6),
                                    Challenge.Stage.Played.Game(10, 7),
                                    null, null, null))
        val challenge2 = Challenge("19","6","5",Date(2017, 11, 27),
                Challenge.Status.CONFIRMED, Challenge.Stage.Played(2,1,
                Date(2017, 11, 30),
                Challenge.Stage.Played.Game(6, 4),
                Challenge.Stage.Played.Game(1, 6),
                Challenge.Stage.Played.Game(10, 7),
                null, null, null))
        val challenge3 = null
        val challenge4 = Challenge("19","6","5",Date(2017, 11, 27),
                Challenge.Status.CONFIRMED, Challenge.Stage.Played(2,1,
                Date(2017, 11, 30),
                Challenge.Stage.Played.Game(6, 4),
                Challenge.Stage.Played.Game(1, 6),
                Challenge.Stage.Played.Game(10, 7),
                null, null, null))
        val challenge5 = Challenge("19","6","5",Date(2017, 11, 27),
                Challenge.Status.CONFIRMED, Challenge.Stage.Played(2,1,
                Date(2017, 11, 30),
                Challenge.Stage.Played.Game(6, 4),
                Challenge.Stage.Played.Game(1, 6),
                Challenge.Stage.Played.Game(10, 7),
                null, null, null))
        val latestGames = listOf(challenge1, challenge2, challenge3, challenge4, challenge5)

        val colors = this.presenter?.getPlayerLastFiveGamesArray(latestGames, userId)

        assertEquals(Color.GREEN, colors!![0])
        assertEquals(Color.RED, colors[1])
        assertEquals(Color.GRAY, colors[2])
        assertEquals(Color.RED, colors[3])
        assertEquals(Color.RED, colors[4])


    }

    @Test
    fun testGetPlayerLastFiveGamesArrayAllNull(){

        val userId = "5"

        val colors = this.presenter?.getPlayerLastFiveGamesArray(null, userId)

        assertEquals(Color.GRAY, colors!![0])
        assertEquals(Color.GRAY, colors[1])
        assertEquals(Color.GRAY, colors[2])
        assertEquals(Color.GRAY, colors[3])
        assertEquals(Color.GRAY, colors[4])


    }

    @Test
    fun testCheckIfUserWonAllLatestFiveGamesWonAll(){

        val latestGames = mutableListOf(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)

        val colors = this.presenter?.checkIfUserWonAllLatestFiveGames(latestGames)

        assertEquals(Color.YELLOW, colors!![0])
        assertEquals(Color.YELLOW, colors[1])
        assertEquals(Color.YELLOW, colors[2])
        assertEquals(Color.YELLOW, colors[3])
        assertEquals(Color.YELLOW, colors[4])

    }

    @Test
    fun testCheckIfUserWonAllLatestFiveGamesNotWolAll(){

        val latestGames = mutableListOf(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.RED)

        val colors = this.presenter?.checkIfUserWonAllLatestFiveGames(latestGames)

        assertEquals(Color.GREEN, colors!![0])
        assertEquals(Color.GREEN, colors[1])
        assertEquals(Color.GREEN, colors[2])
        assertEquals(Color.GREEN, colors[3])
        assertEquals(Color.RED, colors[4])

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