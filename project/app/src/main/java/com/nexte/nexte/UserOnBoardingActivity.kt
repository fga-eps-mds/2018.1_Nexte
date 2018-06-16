package com.nexte.nexte

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.user_onboarding_three.*

class UserOnBoardingActivity : AppCompatActivity() {

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
                1 -> inflater?.inflate(R.layout.user_onboarding_one, container, false)
                2 -> inflater?.inflate(R.layout.user_onboarding_two, container, false)
                3 -> inflater?.inflate(R.layout.user_onboarding_three, container, false)
                else -> {
                    throw IllegalArgumentException()
                }
            }
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            if(board == 3) {
                sentRequestButton.setOnClickListener {
                    this.activity.finish()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_on_boarding)
    }

}
