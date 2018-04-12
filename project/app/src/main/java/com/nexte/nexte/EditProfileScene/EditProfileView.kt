package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
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
    }

    override fun displayEditedProfile(viewModel: EditProfileModel.SecondRequest.ViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayProfileToEdit(viewModel: EditProfileModel.FirstRequest.ViewModel) {

        this.username.text = viewModel.username
        this.RankingId.text = viewModel.ranking
        this.emailTextEdit.setText(viewModel.email, TextView.BufferType.EDITABLE)
        this.ageTextEdit.setText(viewModel.age, TextView.BufferType.EDITABLE)
        this.clubName.text = viewModel.club
    }

    private fun setupEditProfileScene() {
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()
        val view = this

        view.firstRequestInteractor = interactor
        view.secondRequestInteractor = interactor
        interactor.presenter = presenter
        presenter.firstView = view
        presenter.secondView = view
    }
}
