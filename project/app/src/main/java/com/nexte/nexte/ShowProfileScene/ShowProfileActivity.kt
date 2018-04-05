package com.nexte.nexte.ShowProfileScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.MainActivity
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_profile.*

class ShowProfileActivity : AppCompatActivity(), ShowProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)
        setupShowProfileScene()

        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)

    }


    fun setupShowProfileScene(){

        val viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        viewScene.showProfileInteractor = interactor
    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        // textView.text = viewModel.message
        username.text = viewModel.name
        RankID.text = viewModel.rank
        club.text = viewModel.club
        number.text = viewModel.age
        email.text = viewModel.email


    }
}
