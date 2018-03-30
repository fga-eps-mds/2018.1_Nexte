package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.EditProfileScene.*
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.ShowProfileScene.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginDisplayLogic, ShowProfileDisplayLogic,
        EditProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null
    var loginInteractor: LoginBusinessLogic? = null
    var editProfileInteractor: EditProfileBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupShowProfileScene()
        this.setupLoginScene() // Setup Login Scene
        this.setupEditProfileScene()

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest)

        val editProfileRequest: EditProfileModel.Request = EditProfileModel.Request("lorranyfreire", "HDDGFRUH65752")
        this.editProfileInteractor?.getProfileToEdit(editProfileRequest)
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

    /*
     *  EDIT PROFILE SCENE
     */

    // Setup all modules for exchange of data
    private fun setupEditProfileScene() {

        val viewController = this
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()

        viewController.editProfileInteractor = interactor
        interactor.presenter = presenter
        presenter.view = viewController
    }

    // Print a message according with received data
    override fun displayEditProfileState(viewModel: EditProfileModel.ViewModel) {
        textView.text = viewModel.message
    }
}
