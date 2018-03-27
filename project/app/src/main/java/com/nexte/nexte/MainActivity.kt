package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.nexte.nexte.EditProfileScene.*
import com.nexte.nexte.LoginScene.*
import kotlinx.android.synthetic.main.activity_login_view.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), LoginDisplayLogic, EditProfileDisplayLogic {

    var loginInteractor: LoginBusinessLogic? = null
    var editProfileInteractor: EditProfileBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupLoginScene() // Setup Login Scene
        this.setupEditProfileScene()

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest)

        val editProfileRequest: EditProfileModel.Request = EditProfileModel.Request("lorllranyfreire")
        this.editProfileInteractor?.getProfileToEdit(editProfileRequest)
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
        textView.setText(viewModel.message)
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
