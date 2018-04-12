package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

interface EditProfileDisplayLogic {

    fun displayEditProfileState (viewModel: EditProfileModel.ViewModel)
}


class EditProfileView : AppCompatActivity() {

    private class PasswordWatcher(var view: EditProfileView) : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
            // Does nothing
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Does nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(this.view.passwordConfirmationTextEdit.text.trim().toString() == view.passwordTextEdit.text.trim().toString()) {
                view.passwordValidationTextView.text = "✓"
                view.passwordValidationTextView.setTextColor(Color.GREEN)
                view.updateProfileButton.isEnabled = true
            } else {
                view.passwordValidationTextView.text = "✕"
                view.passwordValidationTextView.setTextColor(Color.RED)
                view.updateProfileButton.isEnabled = false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        this.passwordConfirmationTextEdit.addTextChangedListener(PasswordWatcher(this))
        this.passwordTextEdit.addTextChangedListener(PasswordWatcher(this))
    }
}
