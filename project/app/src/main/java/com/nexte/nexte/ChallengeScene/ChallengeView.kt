package com.nexte.nexte.ChallengeScene

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Interface to define Display Logic to ChallengeView Class that will
 * receive information from Presenter
 */
interface ChallengeDisplayLogic {

    fun displayChallengeAnswer (viewModel: ChallengeModel.ViewModel)
}

class Challenger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenger)
    }
}
