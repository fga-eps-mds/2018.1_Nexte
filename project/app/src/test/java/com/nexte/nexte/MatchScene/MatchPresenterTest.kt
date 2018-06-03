package com.nexte.nexte.MatchScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MatchPresenterTest {

    private var mock: MockMatchDisplayLogic? = null
    private var presenter: MatchPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockMatchDisplayLogic()
        this.presenter = MatchPresenter()
        this.presenter?.viewController = mock

        //assert
        assertNotNull(this.presenter)
    }

    @Test
    fun testPresentMatch() {
        //prepare
        val testFormattedMatchData = MatchModel.FormattedMatchData("Lele", 1, "Ale", 2)
        val response = MatchModel.InitScene.Response(match = MatchModel.MatchData(challenged = MatchModel.MatchPlayer("Lele", 1),
                                                     challenger = MatchModel.MatchPlayer("Ale", 2)))
        //call
        this.presenter?.presentMatch(response = response)

        //assert
        assertEquals(testFormattedMatchData.challengedName, this.mock?.matchDataFormatted?.challengedName)
        assertEquals(testFormattedMatchData.challengedPhoto, this.mock?.matchDataFormatted?.challengedPhoto)
        assertEquals(testFormattedMatchData.challengerName, this.mock?.matchDataFormatted?.challengerName)
        assertEquals(testFormattedMatchData.challengerPhoto, this.mock?.matchDataFormatted?.challengerPhoto)
    }

    @Test
    fun testPresentSuccessMessageForMatchResult(){
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)

        this.presenter?.presentSuccessMessageForMatchResult(response)

        assertEquals(this.mock?.viewModel?.message, "Resultado do desafio enviado com sucesso!")
    }

    @Test
    fun successGetViewController(){
        //assert and call
        val viewController = presenter?.viewController

        //assert
        assertEquals(viewController, presenter?.viewController)
    }

    @Test
    fun testPresentErrorMessageForMatchResult(){
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.ERROR)

        this.presenter?.presentErrorMessageForMatchResult(response)

        assertEquals(this.mock?.viewModel?.message, "Resultado do desafio não pode ser enviado. Por favor, tente novamente mais tarde.")
    }

    @Test
    fun testPresentErrorMessageForMatchResultWithoutViewController(){
        //prepare
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.ERROR)
        val viewController = this.presenter?.viewController
        this.presenter?.viewController = null
        this.mock?.viewModel = null

        //call
        this.presenter?.presentErrorMessageForMatchResult(response)

        //assert
        assertNull(this.mock?.viewModel)
        this.presenter?.viewController = viewController
    }

    @Test
    fun testPresentSuccessMessageForMatchResultWithoutViewController(){
        //prepare
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)
        val viewController = this.presenter?.viewController
        this.presenter?.viewController = null
        this.mock?.viewModel = null

        //call
        this.presenter?.presentSuccessMessageForMatchResult(response)

        //assert
        assertNull(this.mock?.viewModel)
        this.presenter?.viewController = viewController
    }

    @Test
    fun testPresenter(){
        val presenter = MatchPresenter()

        assertNotNull(presenter)
    }

    @Test
    fun testPresentMatchWithoutViewController(){
        //prepare
        val response = MatchModel.InitScene.Response(match = MatchModel.MatchData(challenged = MatchModel.MatchPlayer("Lele", 1),
                challenger = MatchModel.MatchPlayer("Ale", 2)))
        val viewModel = this.presenter?.viewController
        this.presenter?.viewController = null
        this.mock?.matchDataFormatted = null

        //call
        this.presenter?.presentMatch(response = response)

        //assert
        assertNull(this.mock?.matchDataFormatted)
        this.presenter?.viewController = viewModel
    }

    @Test
    fun testPresentDeclineMatchSuccess(){
        val response = MatchModel.DeclineChallengeRequest.
                Response(MatchModel.DeclineChallengeRequest.Status.SUCCESS)
        this.presenter?.presentDeclineMatch(response)

        assertEquals(this.mock?.viewModelDecline?.message, "")
        assertEquals(this.mock?.viewModelDecline?.status, MatchModel.DeclineChallengeRequest.Status.SUCCESS)
    }

    @Test
    fun testPresentDeclineMatchError(){
        val response = MatchModel.DeclineChallengeRequest.
                Response(MatchModel.DeclineChallengeRequest.Status.ERROR)
        this.presenter?.presentDeclineMatch(response)

        assertEquals(this.mock?.viewModelDecline?.message, "Não foi possível cancelar esse desafio!")
        assertEquals(this.mock?.viewModelDecline?.status, MatchModel.DeclineChallengeRequest.Status.ERROR)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockMatchDisplayLogic : MatchDisplayLogic {


    var matchDataFormatted: MatchModel.FormattedMatchData? = null
    var viewModel: MatchModel.SendMatchResult.ViewModel? = null
    var viewModelDecline: MatchModel.DeclineChallengeRequest.ViewModel? = null

    override fun displayMatch(viewModel: MatchModel.InitScene.ViewModel) {
        this.matchDataFormatted = viewModel.matchFormatted
    }

    override fun displayMatchResultMessage(viewModel: MatchModel.SendMatchResult.ViewModel) {
        this.viewModel = viewModel
    }

    override fun displayDeclineMatch(viewModel: MatchModel.DeclineChallengeRequest.ViewModel) {
        this.viewModelDecline = viewModel
    }
}