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

}

/**
 * Class needed to format response so the data can be displayed on activity at [MatchView]
 *
 * @property viewController Reference to the activity where data will be displayed on view
 */
class MatchPresenter(var viewController: MatchDisplayLogic? = null) : MatchPresentationLogic {

    override fun presentMatch(response: MatchModel.InitScene.Response) {

        var viewModel = MatchModel.InitScene.ViewModel(formatMatch(response.match))

        viewController?.displayMatch(viewModel)
    }

    /**
     * Function that formats data of players to be displayed on [MatchView]
     *
     * @param toFormat Data at unformatted stage that needs to be formatted
     */
    private fun formatMatch(toFormat : MatchModel.MatchData) : MatchModel.FormattedMatchData {
        var challengedName = toFormat.challenged.name
        var challengedPhoto = toFormat.challenged.photo
        var challengerName = toFormat.challenger.name
        var challengerPhoto = toFormat.challenger.photo


        val matchFormatted = MatchModel.FormattedMatchData(challengedName,
                                                           challengedPhoto,
                                                           challengerName,
                                                           challengerPhoto)
        return matchFormatted
    }

}