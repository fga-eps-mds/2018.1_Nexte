package com.nexte.nexte


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.ShowProfileScene.ShowProfileFragment
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Button
import com.nexte.nexte.FeedScene.FeedFragment
import com.nexte.nexte.RankingScene.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var loginButton: Button?= null

    private val feedFragment = FeedFragment().getInstance()
    private val challengeFragment = ChallengeTabsFragment().getInstance()
    private val rankingFragment = RankingFragment().getInstance()
    private val showProfileFragment = ShowProfileFragment().getInstance(UserSingleton.loggedUser.name)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = this.findViewById(R.id.loginButton)

        bottom_navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_navigation?.selectedItemId = R.id.feedNavigation
        loginButton?.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener  { item ->

        when (item.itemId) {
            R.id.feedNavigation -> {
                openFragment(feedFragment, "feed")
                return@OnNavigationItemSelectedListener true
            }

            R.id.challengeNavigation -> {
                openFragment(challengeFragment, "challenge")
                return@OnNavigationItemSelectedListener true
            }

            R.id.rankingNavigation -> {
                openFragment(rankingFragment, "ranking")
                return@OnNavigationItemSelectedListener true
            }

            R.id.profileNavigation -> {
                openFragment(showProfileFragment, "profile")
                return@OnNavigationItemSelectedListener true

            } else -> {
            return@OnNavigationItemSelectedListener true
          }
        }
      }

    private fun openFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frame_layout,fragment, tag)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}
