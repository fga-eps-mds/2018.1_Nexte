package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.Player
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PlayersListPresenterTest {

    private var mock: MockChallengeDisplayLogic? = null
    private var presenter: PlayersListPresenter? = null

    @Before
    fun setUp(){
        this.mock = MockChallengeDisplayLogic()
        this.presenter = PlayersListPresenter()
        this.presenter?.viewChallenge = mock
    }

    @Test
    fun successFormatPlayersToChallenge() {
        //prepare
        val name = "Gabriel"
        val rankingPosition = 1
        val pictureAddress = "https://www.algumsite.com.br/algumaimagem.png"
        val email = "enggabrielalbino@gmail.com"
        val gender = "masculino"
        val club = "clubTop"
        val age = 19
        val password = "adoroPicole"
        val category = "profissional"
        val nameFormatted = "Gabriel"
        val rankingPositionFormatted = "#1"
        val pictureAddressFormatted = "https://www.algumsite.com.br/algumaimagem.png"

        val players = listOf(
                Player(name, rankingPosition, pictureAddress, email, gender, club, age, password,  category)
        )
        val playerFormatted = PlayersListModel.FormattedPlayer(nameFormatted, rankingPositionFormatted, pictureAddressFormatted)

        val response = PlayersListModel.ShowRankingPlayersRequest.Response(players)

        //call
        this.presenter?.formatPlayersToChallenge(response)

        //assert
        assertEquals(playerFormatted.name, this.mock?.formattedPlayersToShow?.get(0)?.name)
        assertEquals(playerFormatted.pictureAddress, this.mock?.formattedPlayersToShow?.get(0)?.pictureAddress)
        assertEquals(playerFormatted.rankingPosition, this.mock?.formattedPlayersToShow?.get(0)?.rankingPosition)
    }

    @Test
    fun successFormatExpandedChallengedInfo() {
        //prepare
        val response = PlayersListModel.SelectPlayerForChallengeRequest.Response(
                PlayersListModel.PlayerRankingDetails("larissa", 0,  0, 5)
        )

        //call
        this.presenter?.formatExpandedChallengedInfo(response)

        //assert
        assertEquals(mock?.formattedPlayerToPlayersList?.name, "larissa")
        assertEquals(mock?.formattedPlayerToPlayersList?.wins, "VITÓRIAS: 0")
        assertEquals(mock?.formattedPlayerToPlayersList?.loses, "DERROTAS: 0")
        assertEquals(mock?.formattedPlayerToPlayersList?.rankingPosition, "#5")
    }

    @Test
    fun successFormatMessage() {
        //prepare
        val match = MatchModel.MatchData(
                MatchModel.MatchPlayer("larissa", 1),
                MatchModel.MatchPlayer("larissa2", 1))
        val response = PlayersListModel.ChallengeButtonRequest.Response("larissa", match)

        //call
        this.presenter?.formatMatch(response)

        //assert
        assertNotNull(this.mock?.formattedMessageToShow)
    }

    @Test
    fun successGetViewChallenge(){
        //prepare and call
        val viewChallenge = PlayersListPresenter().viewChallenge

        //assert
        assertNull(viewChallenge)
    }

    @After
    fun tearDown(){
        this.mock = null
        this.presenter = null
    }
}


private class MockChallengeDisplayLogic: ChallengeDisplayLogic{

    var formattedPlayersToShow: List<PlayersListModel.FormattedPlayer>? = null
    var formattedPlayerToPlayersList: PlayersListModel.FormattedRankingDetails?= null
    var formattedMessageToShow: String?= null

    override fun displayPlayersToChallenge(viewModel: PlayersListModel.ShowRankingPlayersRequest.ViewModel) {
         this.formattedPlayersToShow = viewModel.formattedPlayer
    }

    override fun displayPlayerDetailedInfo(viewModel: PlayersListModel.SelectPlayerForChallengeRequest.ViewModel) {
        this.formattedPlayerToPlayersList = viewModel.challengedRankingDetails
    }

    override fun displayMessage(viewModel: PlayersListModel.ChallengeButtonRequest.ViewModel) {
        this.formattedMessageToShow = viewModel.messageForChallenger
    }

    override fun displayNoPlayersMessage(messageText: String) {
        this.formattedMessageToShow = messageText
    }
}