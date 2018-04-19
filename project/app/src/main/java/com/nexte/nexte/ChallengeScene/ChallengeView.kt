package com.nexte.nexte.ChallengeScene

/**
 * Interface to define Display Logic to ChallengeView Class that will
 * receive information from Presenter
 */
interface ChallengeDisplayLogic {

    fun displayChallengeAnswer (viewModel: ChallengeModel.ViewModel)
}