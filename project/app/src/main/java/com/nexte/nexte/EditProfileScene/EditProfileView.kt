package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

interface EditProfileDisplayLogic {

    fun displayEditProfileState (viewModel: EditProfileModel.ViewModel)
}

class EditProfileView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        this.passwordConfirmationTextEdit.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(p0: Editable?) {

                if(passwordConfirmationTextEdit.text.equals(passwordTextEdit.text)) {
                    passwordValidationTextView.text = "✓"
                    passwordValidationTextView.setTextColor(Color.GREEN)
                    updateProfileButton.isEnabled = true
                } else {
                    passwordValidationTextView.text = "✕"
                    passwordValidationTextView.setTextColor(Color.RED)
                    updateProfileButton.isEnabled = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { } // Does nothing

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { } // Does nothing
        })
    }


}
