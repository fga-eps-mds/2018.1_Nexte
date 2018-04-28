package com.nexte.nexte

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.ChallengeScene.*
import com.nexte.nexte.CommentsScene.*
import com.nexte.nexte.FeedScene.FeedView
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.RankingScene.RankingView
import com.nexte.nexte.ShowProfileScene.ShowProfileView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), LoginDisplayLogic,
                     ChallengeDisplayLogic, CommentsDisplayLogic {

    var loginInteractor: LoginBusinessLogic? = null
    var commentsInteractor: CommentsBusinessLogic? = null
    var challengeInteractor: ChallengeBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setupLoginScene() // Setup Login Scene
        this.setupCommentsScene() // Setup Comments Scene
        this.setupChallengeScene() // Setup Challenge Scene

        // Setting up feed button listener
        feedButton.setOnClickListener {
            val intent = Intent(this, FeedView::class.java)
            startActivity(intent)
        }

        showProfileButton.setOnClickListener {
            val intent = Intent(this, ShowProfileView::class.java)
            startActivity(intent)
        }

        rankingButton.setOnClickListener {
            val intent = Intent(this, RankingView::class.java)
            startActivity(intent)
        }

        // Testing if works the architecture
        val loginRequest: LoginModel.Request = LoginModel.Request("miguelpimentel", "123456")
        this.loginInteractor?.doAuthentication(loginRequest)

        //testing if it is working
        val alexandre: ChallengeModel.Player = ChallengeModel.Player("Alexandre Miguel", 1, 3, 4, "www.facebook.com")
        val helena: ChallengeModel.Player = ChallengeModel.Player("Helena Goulart", 1, 4, 3, "www.instagram.com")
        val challengeRequest: ChallengeModel.Request = ChallengeModel.Request(alexandre, helena, "FGA", "14:35", "15/12/2019")
        this.challengeInteractor?.sendChallenge(challengeRequest)

        // Testing if is working
        val commentsRequest: CommentsModel.Request = CommentsModel.Request("Gandalf vs Saruman", "Frodo_Bolseiro")
        commentsInteractor?.recentComments(commentsRequest)

        // Ranking button
        rankingButton.setOnClickListener {
            val intent = Intent(this, RankingView::class.java)
            startActivity(intent)
        }
    }

    /*
     *  LOGIN SCENE
     */

    // Setup all modules for exchange of data
    private fun setupLoginScene() {

        val viewController = this
        var date = Date(20)
        val interactor = LoginInteractor()
        val presenter = LoginPresenter()

        viewController.loginInteractor = interactor
        interactor.presenter = presenter
        presenter.viewController = viewController
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {

        // textView.text = viewModel.message
    }


    /*
    *  CHALLENGE SCENE
    */

    // Setup all modules for exchange of data
    private fun setupChallengeScene() {

        val viewChallenge = this
        val interactor = ChallengeInteractor()
        val presenter = ChallengePresenter()

        viewChallenge.challengeInteractor = interactor
        interactor.presenter = presenter
        presenter.viewChallenge = viewChallenge
    }

    override fun displayChallengeAnswer(viewModel: ChallengeModel.ViewModel) {

        // textView.text = viewModel.message
    }

    /*
     *  COMMENTS SCENE
     */

    private fun setupCommentsScene() {

        val viewController = this
        val interactor = CommentsInteractor()
        val presenter = CommentsPresenter()

        viewController.commentsInteractor = interactor
        interactor.presenter = presenter
        presenter.viewController = viewController
    }


    override fun displayComments(viewModel: CommentsModel.ViewModel) {
        // textView.text = viewModel.message
    }
}
