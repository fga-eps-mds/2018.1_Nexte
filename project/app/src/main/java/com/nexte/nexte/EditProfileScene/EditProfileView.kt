package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.nexte.nexte.Player
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

interface ShowProfileToEditDisplayLogic {

    fun displayProfileToEdit (viewModel: EditProfileModel.FirstRequest.ViewModel)
}

interface EditProfileDisplayLogic {

    fun displayEditedProfile (viewModel: EditProfileModel.SecondRequest.ViewModel)
}


class EditProfileView : AppCompatActivity(), ShowProfileToEditDisplayLogic, EditProfileDisplayLogic {

    var firstRequestInteractor: GetProfileToEditBusinessLogic? = null
    var secondRequestInteractor: EditProfileBusinessLogic? = null

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

        setupEditProfileScene()

        this.passwordConfirmationTextEdit.addTextChangedListener(PasswordWatcher(this))
        this.passwordTextEdit.addTextChangedListener(PasswordWatcher(this))

        val firstRequest: EditProfileModel.FirstRequest.Request = EditProfileModel.FirstRequest.Request(
                "gabrielalbino",
                "UHDASFUHADSUHF2828HJDDJFHA")

        firstRequestInteractor?.getProfileToEdit(firstRequest)

        updateProfileButton.setOnClickListener {

            val user = Player(username.text.toString(),
                    rankingID.text.removeRange(0, 1).toString().toInt(),
                    "",
                    emailTextEdit.text.trim().toString(),
                    "",
                    clubName.text.toString(),
                    ageTextEdit.text.trim().toString().toInt(),
                    passwordTextEdit.text.trim().toString())

            val secondRequest: EditProfileModel.SecondRequest.Request = EditProfileModel.SecondRequest.Request(user)

            secondRequestInteractor?.setEditedProfile(secondRequest)
        }
    }

    override fun displayProfileToEdit(viewModel: EditProfileModel.FirstRequest.ViewModel) {

        this.username.text = viewModel.username
        this.rankingID.text = viewModel.ranking
        this.emailTextEdit.setText(viewModel.email, TextView.BufferType.EDITABLE)
        this.ageTextEdit.setText(viewModel.age, TextView.BufferType.EDITABLE)
        this.clubName.text = viewModel.club
    }

    override fun displayEditedProfile(viewModel: EditProfileModel.SecondRequest.ViewModel) {

        val errorMessage = viewModel.errorMessage

        if(errorMessage == null) {
            this.finish()
        } else {
            this.errorMessageTextView.text = errorMessage
        }
    }


    private fun setupEditProfileScene() {
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()
        val view = this

        view.firstRequestInteractor = interactor
        view.secondRequestInteractor = interactor
        interactor.firstPresenter = presenter
        interactor.secondPresenter = presenter
        presenter.firstView = view
        presenter.secondView = view
    }
}
