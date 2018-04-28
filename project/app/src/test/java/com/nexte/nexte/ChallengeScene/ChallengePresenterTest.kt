package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChallengePresenterTest {

    private var mock: MockShowPlayersToChallengeDisplayLogic? = null
    private var presenter: ChallengePresenter? = null

    @Before
    fun setUp(){
        this.mock = MockShowPlayersToChallengeDisplayLogic()
        this.presenter = ChallengePresenter()
        this.presenter?.viewChallenge = mock
    }

    @Test
    fun formatPlayersToChallenge() {
        //prepare
        val name = "Gabriel"
        val rankingPosition = 1
        val pictureAddress = "https://www.algumsite.com.br/algumaimagem.png"
        val email = "enggabrielalbino@gmail.com"
        val gender = "masculino"
        val club = "clubTop"
        val age = 19
        val password = "adoroPicole"
        val nameFormatted = "Gabriel"
        val rankingPositionFormatted = "#1"
        val pictureAddressFormatted = "https://www.algumsite.com.br/algumaimagem.png"

        val players = listOf(
                Player(name, rankingPosition, pictureAddress, email, gender, club, age, password)
        )
        val playerFormatted = ChallengeModel.FormattedPlayer(nameFormatted, rankingPositionFormatted, pictureAddressFormatted)

        val response = ChallengeModel.ShowRankingPlayersRequest.Response(players)

        //call
        this.presenter?.formatPlayersToChallenge(response)

        //assert
        assertEquals(playerFormatted.name, this.mock?.formattedPlayersToShow?.get(0)?.name)
        assertEquals(playerFormatted.pictureAddress, this.mock?.formattedPlayersToShow?.get(0)?.pictureAddress)
        assertEquals(playerFormatted.rankingPosition, this.mock?.formattedPlayersToShow?.get(0)?.rankingPosition)
    }

    @After
    fun tearDown(){
        this.mock = null
        this.presenter = null
    }
}


private class MockShowPlayersToChallengeDisplayLogic: ShowPlayersToChallengeDisplayLogic{
    var formattedPlayersToShow: List<ChallengeModel.FormattedPlayer>? = null
    var formattedPlayerToChallenge: ChallengeModel.FormattedRankingDetails?= null

    override fun displayPlayersToChallenge(viewModel: ChallengeModel.ShowRankingPlayersRequest.ViewModel) {
         this.formattedPlayersToShow = viewModel.formattedPlayer
    }

    override fun displayPlayerDetailedInfo(viewModel: ChallengeModel.SelectPlayerForChallengeRequest.ViewModel) {
        this.formattedPlayerToChallenge = viewModel.challengedRankingDetails
    }
}