package com.nexte.nexte


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.ShowProfileScene.ShowProfileFragment
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.widget.Button
import com.nexte.nexte.ChallengeScene.ChallengeView
import com.nexte.nexte.FeedScene.FeedFragment
import com.nexte.nexte.RankingScene.RankingFragment

class MainActivity : AppCompatActivity() {

    private var bottomNavView: BottomNavigationView?= null
    private var loginButton: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView =this.findViewById(R.id.bottom_nav_view)
        loginButton = this.findViewById(R.id.loginButton)

        bottomNavView?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavView?.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener)
        bottomNavView?.selectedItemId = R.id.profile
        loginButton?.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }
    }

    private val mOnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemReselectedListener { item ->

        when(item.itemId) {
            R.id.profile -> {
                /* Do Nothing*/
            }
        }

    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener  { item ->

        when (item.itemId) {
            R.id.feed -> {
                val feedFragment = FeedFragment().getInstance()
                openFragment(feedFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.challenge -> {
                val intent = Intent(this, ChallengeView::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.ranking -> {
                val rankingFragment = RankingFragment().getInstance()
                openFragment(rankingFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.profile -> {
                val showProfileFragment = ShowProfileFragment().getInstance(UserSingleton.getUserInformations().name)
                openFragment(showProfileFragment)
                return@OnNavigationItemSelectedListener true

            } else -> {
            return@OnNavigationItemSelectedListener true
        }
        }


    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

