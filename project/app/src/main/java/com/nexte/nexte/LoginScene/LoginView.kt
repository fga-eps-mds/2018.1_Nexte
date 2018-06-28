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
import android.content.Context
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

        login.setOnClickListener {  }

        navigationLogin.setOnClickListener{ this.finish() }

        accountKitLogin.setOnClickListener {
            var dialog = AuthenticationDialogFragment()
            dialog.acitivity = this
            dialog.show(supportFragmentManager, "Authenticate Dialog")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
//            LoginModel.AccountKit.accountKit_code -> {
//                val loginResult = data!!.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
//                println(loginResult)
//                if (loginResult?.error != null) {
//                    Log.d("Error", "login result $loginResult")
//                    if (loginResult.wasCancelled()){ Log.e("Error", "Authentication canceled") }
//                } else {
//                    val token = loginResult.accessToken!!.token
//                    Log.e("Auth code", token)
//                    this.createAccountKitRequest(token)
                }
            }
        }
    }

    override fun onBackPressed() { this.finishAffinity() }

    override fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel) {
        val message = "Sucess to authenticate"
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()

        this.finish()
    }

    override fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel) {
        if (viewModel.message == LoginModel.AccountKit.StatusCode.SUCESSED.toString()) {
            this.finish()
        } else {
            val message: String  = viewModel.message
            val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            toast.show()
        }

        this.finish()
    }

    /**
     * Method responsible for creating the authetication request an passing it to the interactor
     */
    private fun createAuthenticationRequest(){
        val account =  userField.text.toString() // Expected "ramires"
        val password = passwordField.text.toString() // Expected "test-nexte-ramires"

        val request: LoginModel.Authentication.Request = LoginModel.Authentication.Request(account, password)
        this.interactor?.doAuthentication(request)
    }

    /**
     * Method responsible for creating the accountKit request passing it to the interactor
     */
    private fun createAccountKitRequest(token: String) {
        val request: LoginModel.AccountKit.Request = LoginModel.AccountKit.Request(token)
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

    /**
     * Persist userId when authentication is sucessfully
     */
    fun saveUserIdentifier(id: String) {
        val sharedPreferences = getSharedPreferences("NexteAndroid", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("userId", id)
        editor.apply()
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
        interactor.worker.updateLogic = interactor
        presenter.view = view
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
}


