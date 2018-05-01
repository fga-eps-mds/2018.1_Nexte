package com.nexte.nexte.MatchScene

/**
 * Created by leticia on 01/05/18.
 */

interface MatchPresentationLogic {

    fun presentMatch (response: MatchModel.InitScene.Response)

}

class MatchPresenter : MatchPresentationLogic {

    override fun presentMatch(response: MatchModel.InitScene.Response) {
        var viewModel = MatchModel.InitScene.ViewModel(formatMatch(response.match))
    }

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