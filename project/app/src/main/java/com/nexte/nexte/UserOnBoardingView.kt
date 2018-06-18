package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_user_on_boarding.*
import kotlinx.android.synthetic.main.user_onboarding_three.*
import android.support.v4.app.FragmentStatePagerAdapter

class UserOnBoardingView : AppCompatActivity() {

    class BoardingFragment : Fragment() {

        private var board : Int? = null

        fun getInstance(boardToShow: Int) : Fragment {
            val bundle = Bundle()
            bundle.putInt("board", boardToShow)
            val fragment = BoardingFragment()
            fragment.arguments = bundle

            return fragment
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            board = this.arguments.getInt("board")
            super.onCreate(savedInstanceState)
        }

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return when(board) {
                0 -> inflater?.inflate(R.layout.user_onboarding_one, container, false)
                1 -> inflater?.inflate(R.layout.user_onboarding_two, container, false)
                2 -> inflater?.inflate(R.layout.user_onboarding_three, container, false)
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            if(board == 2) {
                sentRequestButton.setOnClickListener {
                    this.activity.finish()
                }
                closeView.setOnClickListener{
                    this.activity.finish()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_on_boarding)
        userOnBoardingViewPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)
        tabDots.setupWithViewPager(userOnBoardingViewPager, true)
    }

    override fun onBackPressed() {
        if (userOnBoardingViewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            userOnBoardingViewPager.currentItem = userOnBoardingViewPager.currentItem - 1
        }
    }

    class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        companion object {
            const val numPages = 3
        }

        override fun getItem(position: Int): Fragment {
            return BoardingFragment().getInstance(position)
        }

        override fun getCount(): Int {
            return numPages
        }
    }

}
