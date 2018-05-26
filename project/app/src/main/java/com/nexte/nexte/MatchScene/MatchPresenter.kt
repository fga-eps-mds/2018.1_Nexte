package com.nexte.nexte.MatchScene

/**
 * Created by leticia on 01/05/18.
 */

/**
 * Interface to define Presentation Logic to Match Class that
 * will be used to call this Interactor on other class layer
 */
interface MatchPresentationLogic {

    /**
     * Method responsible to format match data information and send to view
     *
     * @param response contains unformatted data received from [MatchModel]
     */
    fun presentMatch (response: MatchModel.InitScene.Response)

    /**
     * Method responsible for creating the appropriate message for
     * a success match result
     *
     * @param response contains the data about the status of the match result
     */
    fun presentSuccessMessageForMatchResult (response: MatchModel.SendMatchResult.Response)

    /**
     * Method responsible for creating the appropriate message for
     * a failed match result
     *
     * @param response contains the data about the status of the match result
     */
    fun presentErrorMessageForMatchResult (response: MatchModel.SendMatchResult.Response)
}

/**
 * Class needed to format response so the data can be displayed on activity at [MatchFragment]
 *
 * @property viewController Reference to the activity where data will be displayed on view
 */
class MatchPresenter(var viewController: MatchDisplayLogic? = null) : MatchPresentationLogic {

    override fun presentMatch(response: MatchModel.InitScene.Response) {

        val viewModel = MatchModel.InitScene.ViewModel(formatMatch(response.match))

        viewController?.displayMatch(viewModel)
    }

    /**
     * Function that formats data of players to be displayed on [MatchFragment]
     *
     * @param toFormat Data at unformatted stage that needs to be formatted
     */
    private fun formatMatch(toFormat : MatchModel.MatchData) : MatchModel.FormattedMatchData {
        val challengedName = toFormat.challenged.name
        val challengedPhoto = toFormat.challenged.photo
        val challengerName = toFormat.challenger.name
        val challengerPhoto = toFormat.challenger.photo


        val matchFormatted = MatchModel.FormattedMatchData(challengedName,
                                                           challengedPhoto,
                                                           challengerName,
                                                           challengerPhoto)
        return matchFormatted
    }

    override fun presentSuccessMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        val message = "Resultado do desafio enviado com sucesso!"
        val viewModel = MatchModel.SendMatchResult.ViewModel(message)
        this.viewController?.displayMatchResultMessage(viewModel)
    }

    override fun presentErrorMessageForMatchResult(response: MatchModel.SendMatchResult.Response) {
        val message = "Resultado do desafio não pode ser enviado. Por favor, tente novamente mais tarde."
        val viewModel = MatchModel.SendMatchResult.ViewModel(message)
        this.viewController?.displayMatchResultMessage(viewModel)
    }
}