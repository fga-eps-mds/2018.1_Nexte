package com.nexte.nexte.LoginScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login_view.*
import kotlinx.android.synthetic.main.activity_login_view.view.*
import kotlinx.android.synthetic.main.activity_main.*

interface LoginDisplayLogic {

    fun displayAuthenticateState(viewModel: LoginModel.ViewModel)
}

//class LoginView : AppCompatActivity(), LoginDisplayLogic {
//
//    var interactor: LoginBusinessLogic? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//
//
////        var passwordText: EditText =  findViewById<EditText>(R.id.passwordtext)
////        var usernameText: EditText = findViewById<EditText>(R.id.usernametext)
////        var signInButton: Button = findViewById<Button>(R.id.loginbutton)
//
////        loginbutton.setOnClickListener {
////
////            val request: LoginModel.Request = LoginModel.Request(usernametext.toString(),
////                                                                 passwordtext.toString())
////            this.interactor?.doAuthentication(request)
////
////        }
//    }
//
//    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {
//
//    }
//}
