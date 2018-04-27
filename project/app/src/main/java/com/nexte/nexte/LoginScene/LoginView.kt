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
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_login_view.*
import org.json.JSONObject

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

        val authenticationCode = 101
        if (requestCode == authenticationCode) {

            val loginResult = data!!.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
            var message = ""

            if (loginResult.error != null) {
                println("1 $loginResult?.error")
            } else if (loginResult.wasCancelled()) {
                message = "Login cancelled"
                println("2 $message")
            } else {
                if (loginResult.accessToken != null) {
                    message = "Sucess" + loginResult.accessToken
                    println("1 $message")
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

        val phoneRequestCode = 101
        this.startActivityForResult(intent, phoneRequestCode)
    }

    fun emailLogin(view: View) {

        val intent = Intent(this, AccountKitActivity::class.java)

        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.CODE)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build())

        val emailRequestCode = 101
        this.startActivityForResult(intent, emailRequestCode)
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
