package com.nexte.nexte.ShowProfileScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.MainActivity
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_profile.*

class ShowProfileActivity : AppCompatActivity(), ShowProfileDisplayLogic {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)


    }

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        // textView.text = viewModel.message
        username.text = viewModel.player?.name

    }
}
