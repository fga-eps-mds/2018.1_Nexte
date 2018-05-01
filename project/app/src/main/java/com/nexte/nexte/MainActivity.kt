package com.nexte.nexte

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.FeedScene.FeedView
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.RankingScene.RankingView
import com.nexte.nexte.ShowProfileScene.ShowProfileView
import com.nexte.nexte.ChallengeScene.ChallengeView
import com.nexte.nexte.MatchScene.MatchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginDisplayLogic {

    private var loginInteractor: LoginBusinessLogic? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        challangeButton.setOnClickListener {
            val intent = Intent(this, ChallengeView::class.java)
            startActivity(intent)
        }

        // Ranking button
        rankingButton.setOnClickListener {
            val intent = Intent(this, RankingView::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }

        naoaguentomais.setOnClickListener {
            val intent = Intent(this, MatchView::class.java)
            startActivity(intent)
        }
    }

    // Print a message according with received data
    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) { }

}
