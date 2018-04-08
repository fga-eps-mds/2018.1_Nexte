package com.nexte.nexte.EditProfileScene

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nexte.nexte.R

interface EditProfileDisplayLogic {
    fun displayEditProfileState (viewModel: EditProfileModel.ViewModel)
}

class EditProfileView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
    }
}
