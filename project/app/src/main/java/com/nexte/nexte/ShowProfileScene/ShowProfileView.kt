package com.nexte.nexte.ShowProfileScene

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.EditProfileScene.EditProfileView
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_show_profile.*

interface ShowProfileDisplayLogic {

    /* This function turns data avaliable of impression on screen */

    fun displayProfile(viewModel : ShowProfileModel.ViewModel)
}

/* This class implements ShowProfileDisplayLogic,
    printing on screen the user data */

class ShowProfileView : AppCompatActivity(), ShowProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null // Receives request exemple

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)
        setupShowProfileScene()

        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)

        editProfileButton.setOnClickListener {

            val intent = Intent(this, EditProfileView::class.java)

            startActivity(intent)
        }
    }

    override fun onResume() {

        super.onResume()

        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)
    }


    fun setupShowProfileScene() {

        val viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        viewScene.showProfileInteractor = interactor

    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {

        username.text = viewModel.name
        RankingID.text = viewModel.rank
        club.text = viewModel.club
        age.text = viewModel.age
        email.text = viewModel.email

    }
}
