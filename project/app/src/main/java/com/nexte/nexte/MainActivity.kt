package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.ShowProfile.ShowProfileDisplayLogic
import com.nexte.nexte.ShowProfile.*
import kotlinx.android.synthetic.main.activity_main.*
import com.nexte.nexte.LoginScene.*


class MainActivity : AppCompatActivity(), LoginDisplayLogic, ShowProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null
    var loginInteractor: LoginBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupShowProfileScene()
        this.setupLoginScene() // Setup Login Scene

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest)

        //testing if it is working
        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request(
                "gabrielalbino", "AUFDSASFSA321IEUNFDI23FIQ2F")
        showProfileInteractor?.showProfile(showUserProfileRequest)
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
        viewScene.showProfileInteractor = interactor
    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        textView.text = viewModel.message
    }

    /*
     *  LOGIN SCENE
     */

    // Setup all modules for exchange of data
    private fun setupLoginScene() {

        val viewController = this
        val interactor = LoginIteractor()
        val presenter = LoginPresenter()

        viewController.loginInteractor = interactor
        interactor.presenter = presenter
        presenter.viewControler = viewController
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {
        textView.text = viewModel.message
    }

}
