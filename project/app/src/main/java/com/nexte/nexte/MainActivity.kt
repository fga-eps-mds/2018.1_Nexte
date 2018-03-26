package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.ShowProfile.ShowProfileDisplayLogic
import com.nexte.nexte.ShowProfile.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ShowProfileDisplayLogic {

    var interactor : ShowProfileBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupShowProfileScene()

        //testing if it is working
        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request(
                "gabrielalbino", "AUFDSASFSA321IEUNFDI23FIQ2F")
        interactor?.showProfile(showUserProfileRequest)
    }

    /*
    * SHOW PROFILE SCENE
    * */
    fun setupShowProfileScene(){
        var viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        viewScene.interactor = interactor
    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        textView.text = viewModel.message
    }

}
