package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.nexte.nexte.FeedScene.FeedBusinessLogic
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.FeedScene.*
import kotlinx.android.synthetic.main.activity_login_view.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), LoginDisplayLogic, FeedDisplayLogic {

    var loginInteractor: LoginBusinessLogic? = null
    var feedInteractor: FeedBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupLoginScene() // Setup Login Scene
        this.setupFeedScene() // Setup Feed Scene

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest)

        val feedRequest: FeedModel.Request = FeedModel.Request ("leticia", "larissa")
        this.feedInteractor?.recentGames(feedRequest)

    }


    /*
     *  LOGIN SCENE
     */

    // Setup all modules for exchange of data
    private fun setupLoginScene() {

        val viewController = this
        val interactor = LoginIteractor()
        val presenter = LoginPresenter()

        viewController.loginInteractor = interactor
        interactor.presenter = presenter
        presenter.viewControler = viewController
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {

        textView.setText(viewModel.message)
    }


    /*
     *  FEED SCENE
     */

    // Setup all modules for exchange of data
    private fun setupFeedScene() {

        val viewController = this
        val interactor = FeedInteractor()
        val presenter = FeedPresenter()

        viewController.feedInteractor = interactor
        interactor.presenter = presenter
        presenter.viewController = viewController
    }

    // Print a message according with received data
    override fun displayRecentGames(viewModel: FeedModel.ViewModel) {

        textView.text = viewModel.message
    }
}
