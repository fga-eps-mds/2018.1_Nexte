package com.nexte.nexte


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nexte.nexte.LoginScene.*
import com.nexte.nexte.ShowProfileScene.ShowProfileFragment
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.nexte.nexte.FeedScene.FeedFragment
import com.nexte.nexte.RankingScene.RankingFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val feedFragment = FeedFragment().getInstance()
    private val challengeFragment = ChallengeTabsFragment().getInstance()
    private val rankingFragment = RankingFragment().getInstance()
    private val showProfileFragment = ShowProfileFragment().getInstance(UserSingleton.loggedUserID)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottom_navigation?.selectedItemId = R.id.feedNavigation
        disableShiftMode(bottom_navigation)

        val intent = Intent(this, LoginView::class.java)
        startActivity(intent)
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
        transaction.replace(R.id.main_frame_layout, fragment, tag)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    @SuppressLint("RestrictedApi")
    private fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView

                item.setShiftingMode(false)
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BNVHelper", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e)
        }

    }
}
