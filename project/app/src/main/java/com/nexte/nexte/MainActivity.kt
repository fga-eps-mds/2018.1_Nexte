package com.nexte.nexte


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.ShowProfileScene.ShowProfileView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.util.Log
import com.nexte.nexte.FeedScene.FeedFragment


class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_nav_view.setOnNavigationItemReselectedListener(mOnNavigationItemReselectedListener)
        this.bottom_nav_view.selectedItemId = R.id.profile


        loginButton.setOnClickListener {
            val intent = Intent(this, LoginView::class.java)
            startActivity(intent)
        }

    }

    private val mOnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemReselectedListener { item ->

        when(item.itemId) {
            R.id.profile -> {
                Log.e("Resultado do item reselecionado", item.itemId.toString())
            }
        }

    }
   private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener  { item ->

       Log.e("Resultado do item", item.itemId.toString())
        when (item.itemId) {
            R.id.feed -> {
                val feedFragment = FeedFragment().getInstance()
                openFragment(feedFragment)
                return@OnNavigationItemSelectedListener true

            }

            R.id.challenge -> {
//                val intent = Intent(this, ChallengeView::class.java)
//                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.ranking -> {
//                val intent = Intent(this, RankingView::class.java)
//                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }

            R.id.profile -> {
                val showProfileFragment = ShowProfileView.newInstance()
                openFragment(showProfileFragment)
                return@OnNavigationItemSelectedListener true

            } else -> {
                return@OnNavigationItemSelectedListener true
            }
        }


    }
    private fun openFragment(fragment: Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
