package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.nexte.nexte.LoginScene.*
import kotlinx.android.synthetic.main.activity_login_view.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), LoginDisplayLogic {

    var interactor: LoginBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupLoginScene()

        val t: TextView = findViewById<TextView>(R.id.afuckingidentifier)
        t.setText("üsdcusdn")

        loginbutton.setOnClickListener {

            val request: LoginModel.Request = LoginModel.Request(usernametext.toString(),
                    passwordtext.toString())
            this.interactor?.doAuthentication(request)
        }
    }

    // Setup all modules for exchange of data
    private fun setupLoginScene() {

        val viewController = this
        val interactor = LoginIteractor()
        val presenter = LoginPresenter()

        viewController.interactor = interactor
        interactor.presenter = presenter
        presenter.viewControler = viewController
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {
        setContentView(R.layout.activity_main)

        if(viewModel.equals("")) {
            afuckingidentifier.setText("User not existed")
        }
    }
}
