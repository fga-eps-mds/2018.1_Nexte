package com.nexte.nexte.LoginScene

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.R
import android.widget.Toast
import android.util.Log
import com.facebook.accountkit.*
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType
import com.nexte.nexte.UserOnBoardingView
import kotlinx.android.synthetic.main.activity_login_view.*
import android.preference.PreferenceManager
import android.support.v4.app.DialogFragment


/**
 * Interface to define Display Logic to LoginView Class that will receive information
 * from Presenter
 */
interface LoginDisplayLogic {

    /**
     * Display and acting from data provided from presenter
     *
     * @param viewModel data model provided from presenter
     */
    fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel)

    /**
     * Display and acting from data provided from presenter
     *
     * @param viewModel data model provided from presenter
     */
    fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel)
}

/**
 * Class that implements [LoginDisplayLogic] and its responsible to control feed screen
 *
 * @property interactor Interactor layer
 */
class LoginView : AppCompatActivity(), LoginDisplayLogic {

    var interactor: LoginBusinessLogic? = null

    /**
     * On Create is a method that will setup this scene and call first Request and actions from UI
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)
        this.setup()

        login.setOnClickListener { createAuthenticationRequest() }

        navigationLogin.setOnClickListener{ this.finish() }

        accountKitLogin.setOnClickListener {
            var dialog = AuthenticationDialogFragment()
            dialog.acitivity = this
            dialog.show(supportFragmentManager, "dssdd")
        }


        val prefs = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false)
        if (!previouslyStarted) {
            val edit = prefs.edit()
            edit.putBoolean(getString(R.string.pref_previously_started), java.lang.Boolean.TRUE)
            edit.apply()
            val intent = Intent(this, UserOnBoardingView::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() { this.finishAffinity() }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LoginModel.AccountKit.accountKit_code -> {
                Log.e("debug", "Here")
                val loginResult: AccountKitLoginResult = data!!.getParcelableExtra(AccountKitLoginResult.RESULT_KEY)
                this.handleLoginResult(loginResult)
            }
        }
    }

    override fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel) {
        val message: String = viewModel.message
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
        this.finish()
    }

    override fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel) {
        val message: String  = viewModel.message
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
        this.finish()
    }

    /**
     * Method responsible for creating the authetication request an passing it to the interactor
     */
    private fun createAuthenticationRequest(){
        val account = userField.text.toString()
        val password = passwordField.text.toString()

        val request: LoginModel.Authentication.Request = LoginModel.Authentication.Request(account, password)
        this.interactor?.doAuthentication(request)
    }

    /**
     * Gets current account from Facebook Account Kit which include user's phone number
     */
    private fun getAccount(token: String) {
        AccountKit.getCurrentAccount(object : AccountKitCallback<Account> {
            override fun onSuccess(account: Account) {
                val phoneNumber = account.phoneNumber
                val email = account.email
                val phoneNumberString = phoneNumber.toString()
                val emailString = email.toString()

                if(phoneNumberString !=  "") {
                    val request = LoginModel.AccountKit.Request(null, phoneNumberString, "dsfsd")
                    interactor?.accountKitAuthentication(request)
                }

                if(emailString != "") {
                    val request = LoginModel.AccountKit.Request(emailString, null, "dfsfd")
                    interactor?.accountKitAuthentication(request)
                }

            }

            override fun onError(error: AccountKitError) {
                Log.e("AccountKit", error.toString())
            }
        })
    }

    /**
     * Request login by phone - AccountKit
     */
    fun loginPhoneNumber() {
        val intent = Intent(this, AccountKitActivity::class.java)
        val configBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                AccountKitActivity.ResponseType.TOKEN)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configBuilder.build())
        startActivityForResult(intent, LoginModel.AccountKit.accountKit_code)
    }

    /**
     * Request login by email - AccountKit
     */
    fun loginByEmail() {
        val intent  = Intent(this, AccountKitActivity::class.java)
        val builder = AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                AccountKitActivity.ResponseType.TOKEN)
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build())
        startActivityForResult(intent, LoginModel.AccountKit.accountKit_code)
    }

    /*
     * Handle with Login Result
     */
    private fun handleLoginResult(loginResult: AccountKitLoginResult): String? {
        var message: String? = null

        if (loginResult.error != null) {
            Log.e("debug", "login error")
            message = loginResult.error?.errorType?.message
        } else if (loginResult.wasCancelled()) {
            Log.d("debug", "login cancelled")
            message = "login cancel"
        } else {
            if (loginResult.accessToken != null) {
                val accessToken = loginResult.authorizationCode
                getAccount(accessToken!!)
                Log.i("info", accessToken)
            } else {
                Log.e("debug", "access token null")
            }
        }
        return message
    }

    class AuthenticationDialogFragment: DialogFragment() {

        var acitivity: LoginView? = null

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Vamos entrar com o seu email ou telefone?")
                    .setItems(R.array.authenticationArray, DialogInterface.OnClickListener { _, which ->
                        when (which) {
                            0 -> { this.acitivity?.loginByEmail() }
                            1 -> { this.acitivity?.loginPhoneNumber() }
                        }
                    })
            return builder.create()
        }
    }

    /**
     * Method responsible for setup protocol between scenes
     */
    fun setup() {
        val view = this
        val interactor = LoginInteractor()
        val presenter = LoginPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.view = view
    }
}


