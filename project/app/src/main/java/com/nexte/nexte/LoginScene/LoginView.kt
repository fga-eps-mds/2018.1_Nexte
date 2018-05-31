package com.nexte.nexte.LoginScene

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R
import android.widget.Toast
import android.util.Log
import com.facebook.accountkit.*
import kotlinx.android.synthetic.main.activity_login_view.*
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.LoginType


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
 * @property interactor Interactor layer for send requests [FeedInteractor]
 */
class LoginView : AppCompatActivity(), LoginDisplayLogic {

    var interactor: LoginBusinessLogic? = null

    /**
     * On Create is a method that will setup this scene and call first Request and actions from UI
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AccountKit.initialize(this)
        setContentView(R.layout.activity_login_view)
        this.setup()

        login.setOnClickListener {
            createAuthenticationRequest()
        }
    }
    /**
     * On Acitvity Results is a method manager request and results provided
     *
     * @param savedInstanceState
     */   /**
     * On Create is a method that will setup this scene and call first Request and actions from UI
     *
     * @param savedInstanceState
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LoginModel.AccountKit.accountKit_code -> {
                this.getAccount()
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
    fun createAuthenticationRequest(){
        val account = userField.text.toString()
        val password = passwordField.text.toString()

        val request: LoginModel.Authentication.Request = LoginModel.Authentication.Request(account, password)
        this.interactor?.doAuthentication(request)
    }

    /**
     * Gets current account from Facebook Account Kit which include user's phone number
     */
    private fun getAccount() {
        AccountKit.getCurrentAccount(object : AccountKitCallback<Account> {
            override fun onSuccess(account: Account) {
                val phoneNumber = account.getPhoneNumber()
                val phoneNumberString = phoneNumber.toString()

                if(phoneNumberString !=  "") {
                    Log.i("Phone number:", phoneNumberString)
                    val request = LoginModel.AccountKit.Request("", phoneNumberString)
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
