package com.nexte.nexte.LoginScene

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.LoginType
import com.nexte.nexte.R
import android.util.Log
import android.widget.Toast
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

        btnLoginPhonenumber.setOnClickListener {
            this.loginPhoneNumber()
        }

        btnLoginFacebook.setOnClickListener {
            this.loginByEmail()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_PHONE_NUMBER -> {
                Log.d("debug", "user request phone number")

                // Interactor

                val loginResult = data?.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)

                this.interactor

//                var toastMsg: String?
//
//                if (loginResult != null) {
//                    Log.d("debug", "login result ${loginResult.accessToken}")
//                    toastMsg = handleLoginResult(loginResult)
//                } else {
//                    toastMsg = "Something gets wrong"
//                }
//
//                showMessage(toastMsg)

            }
            RC_FACEBOOK_ACCOUNT -> {  }
        }
    }
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {

        val message: String = viewModel.message
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    /**
     * Method responsible for creating the authetication request an passing it to the interactor
     */
//    fun createAuthenticationRequest(){
//        val account = accountField.text.toString()
//        val password = passwordField.text.toString()
//
//        val request: LoginModel.Request = LoginModel.Request(account, password)
//        this.interactor?.doAuthentication(request)
//    }


    fun setup() {
        val view = this
        val interactor = LoginInteractor()
        val presenter = LoginPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.view = view
    }


    // Account Kit

    companion object {
        val RC_PHONE_NUMBER = 1
        val RC_FACEBOOK_ACCOUNT = 2
    }

    fun loginPhoneNumber() {
        val intent = Intent(this, AccountKitActivity::class.java)
        val configBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN)

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configBuilder.build())
        startActivityForResult(intent, RC_PHONE_NUMBER)
    }

    fun loginByEmail() {
        val intent  = Intent(this, AccountKitActivity::class.java)
        val builder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.TOKEN)

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build())
        startActivityForResult(intent, RC_PHONE_NUMBER)
    }


    // Interactor ->  Worker

    private fun handleLoginResult(loginResult: AccountKitLoginResult): String? {
        var msg: String? = null

        if (loginResult.error != null) {
            Log.e("debug", "login error")
            msg = loginResult?.error?.errorType?.message
        } else if (loginResult.wasCancelled()) {
            Log.d("debug", "login cancelled")
            msg = "login cancel"
        } else {
            if (loginResult.accessToken != null) {
                val accessToken = loginResult.accessToken
                val accountId = accessToken?.accountId
                if (accountId != null) {
                    msg = "success $accountId"
                    Log.d("debug", "acces token ${accessToken.token}")
                } else {
                    Log.e("debug", "account id null")
                }
            } else {
                Log.e("debug", "access token null")
            }
        }

        return msg
    }

    // Presenter// View Controller

    private fun showMessage(msg: String?) {
        if (msg != null) {
            Log.d("debug", "message $msg")
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
