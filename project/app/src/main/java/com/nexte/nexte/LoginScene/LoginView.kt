package com.nexte.nexte.LoginScene

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.LoginType
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_login_view.*

interface LoginDisplayLogic {

    fun displayAuthenticateState(viewModel: LoginModel.ViewModel)
}



class LoginView : AppCompatActivity(), LoginDisplayLogic {

    var interactor: LoginBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        this.setup()

        AccountKit.initialize(this) // Account Kit

        requestAuthentication.setOnClickListener {

            val account = accountField.text.toString()
            val password = passwordField.text.toString()

            val request: LoginModel.Request = LoginModel.Request(account, password)
            this.interactor?.doAuthentication(request)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == LoginModel.AuthenticationStatus.FACEBOOK_REQUEST.value) {

            val loginResult = data!!.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
            var message = ""

            if (loginResult.error != null) {
            } else if (loginResult.wasCancelled()) {
                message = "Login cancelled"
            } else {
                if (loginResult.accessToken != null) {
                    message = "Sucess" + loginResult.accessToken
                } else {
                    println(loginResult.authorizationCode)
                }
            }
        }
    }

    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) { }

    fun phoneLogin(view: View) {

        val intent = Intent(this, AccountKitActivity::class.java)
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                                                                                          AccountKitActivity.ResponseType.CODE)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                        configurationBuilder.build())

        this.startActivityForResult(intent, LoginModel.AuthenticationStatus.FACEBOOK_REQUEST.value)
    }

    fun emailLogin(view: View) {

        val intent = Intent(this, AccountKitActivity::class.java)

        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.CODE)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build())

        this.startActivityForResult(intent, LoginModel.AuthenticationStatus.FACEBOOK_REQUEST.value)
    }

    // Private Methods

    private fun setup() {

        val view = this
        val interactor = LoginInteractor()
        val presenter = LoginPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.view = view
    }
}
