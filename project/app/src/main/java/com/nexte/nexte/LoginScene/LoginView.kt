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

    fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel)
    fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel)
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
            LoginModel.AccountKit.ACCOUNTKIT_CODE -> {
                val loginResult = data?.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
                val request: LoginModel.AccountKit.Request =  LoginModel.AccountKit.Request(loginResult!!)
                this.interactor?.accountKitAuthentication(request)
            }
        }
    }

    override fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel) {
        val message: String = viewModel.message
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel) {
        val message: String  = viewModel.message
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

    fun loginPhoneNumber() {
        val intent = Intent(this, AccountKitActivity::class.java)
        val configBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configBuilder.build())
        startActivityForResult(intent, LoginModel.AccountKit.ACCOUNTKIT_CODE)
    }

    fun loginByEmail() {
        val intent  = Intent(this, AccountKitActivity::class.java)
        val builder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.TOKEN)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build())
        startActivityForResult(intent, LoginModel.AccountKit.ACCOUNTKIT_CODE)
    }

}
