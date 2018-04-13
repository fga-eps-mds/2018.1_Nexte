package com.nexte.nexte.LoginScene

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login_view.*
import kotlinx.android.synthetic.main.activity_login_view.view.*
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.LoginType
import com.nexte.nexte.R

interface LoginDisplayLogic {

    fun displayAuthenticateState(viewModel: LoginModel.ViewModel)
}


class LoginView : AppCompatActivity(), LoginDisplayLogic {

    var interactor: LoginBusinessLogic? = null
    val requestCode: Int = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        this.setup()

        AccountKit.initialize(this) // Account Kit

        val request: LoginModel.Request = LoginModel.Request("Miguel", "123456")
        this.interactor?.doAuthentication(request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101) {

            var loginResult = data?.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY) as AccountKitLoginResult
            var message = ""

           if (loginResult.error != null) {

           } else if (loginResult.wasCancelled()) {

           } else {

               if(loginResult.accessToken != null) { }
               else { }
           }
        }


//
        }

    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) { }

    fun phoneLogin(view: View) {

        val intent = Intent(this, AccountKitActivity::class.java)
        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                                                                                          AccountKitActivity.ResponseType.CODE)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                        configurationBuilder.build())

        this.startActivityForResult(intent, 101)
    }

    fun emailLogin(view: View) {

        val intent = Intent(this, AccountKitActivity::class.java)

        val configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.CODE)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build())

        this.startActivityForResult(intent, 101)
    }

    // Private Methods

    private fun setup() {

        val view = this
        val interactor = LoginIteractor()
        val presenter = LoginPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewControler = view
    }
}
