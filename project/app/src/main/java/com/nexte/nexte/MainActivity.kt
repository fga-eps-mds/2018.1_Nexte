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
import android.support.design.widget.BottomNavigationView

class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        // Setting up feed button listener

        showProfileButton.setOnClickListener {
            val intent = Intent(this, ShowProfileView::class.java)
            startActivity(intent)
        }

        challangeButton.setOnClickListener {
            val intent = Intent(this, ChallengeView::class.java)
            startActivity(intent)
        }

        // Ranking button

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }


        //Button to display Match scene
        matchButton.setOnClickListener {
            val intent = Intent(this, MatchView::class.java)
            intent.putExtra("identifier", "matchName")
            startActivity(intent)
        }
    }

    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener  { item ->
        when (item.itemId) {
            R.id.feed -> {val intent = Intent(this, FeedView::class.java)
                startActivity(intent)

            }           // include activity

            R.id.challenge -> {val intent = Intent(this, ChallengeView::class.java)
                startActivity(intent)

            }

            R.id.ranking -> {val intent = Intent(this, RankingView::class.java)
                startActivity(intent)

            }

        }
        true

    }

}
