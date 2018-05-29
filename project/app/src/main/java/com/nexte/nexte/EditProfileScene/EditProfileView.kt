package com.nexte.nexte.EditProfileScene

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Player
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_edit_profile.*

/**
 * Interface responsible to define methods used to get user information data
 * from presenter and display it
 */
interface ShowProfileToEditDisplayLogic {

    fun displayProfileToEdit (viewModel: EditProfileModel.RecoverUserRequest.ViewModel)
}

/**
 * Interface responsible to define methods used to get error message
 * from presenter and display it
 */
interface EditProfileDisplayLogic {

    fun displayEditedProfile (viewModel: EditProfileModel.EditProfileRequest.ViewModel)
}

/**
 * Class that implements [ShowProfileToEditDisplayLogic] and [EditProfileDisplayLogic]
 *
 * @property getUserInformationInteractor get user information from interactor
 * @property editUserInformationInteractor edit user information from interactor
 */
class EditProfileView : AppCompatActivity(),
                        ShowProfileToEditDisplayLogic,
                        EditProfileDisplayLogic {

    var getUserInformationInteractor: GetProfileToEditBusinessLogic? = null
    var editUserInformationInteractor: EditProfileBusinessLogic? = null

    /**
     * Class responsible to define behavior of password validation (checking if password and confirmation match)
     */
    class PasswordWatcher(var view: EditProfileView) : TextWatcher {

        /**
         * This method does nothing but is necessary
         */
        override fun afterTextChanged(s: Editable?) {}

        /**
         * This method does nothing but is necessary
         */
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        /**
         * Method that validates and setup the new password
         */
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            if(this.view.passwordConfirmationTextEdit.text.trim().toString() ==
               this.view.passwordTextEdit.text.trim().toString()) {
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

    /**
     * Method called on scene creation and responsible to show initial user information for edition
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setupEditProfileScene()

        this.passwordConfirmationTextEdit.addTextChangedListener(PasswordWatcher(this))
        this.passwordTextEdit.addTextChangedListener(PasswordWatcher(this))

        this.createGetProfileRequest()

        updateProfileButton.setOnClickListener {

            val user = UserSingleton.loggedUser
            val password: String = passwordTextEdit.text.trim().toString()
            val newUser: User = User(user.id, user.name, user.profilePicture,
                    user.nickname, user.birthDate, user.rankingPosition, emailTextEdit.text.trim().toString(),
                    user.phone,  user.wins, user.loses, user.gender, user.category, user.status, user.challengeSended,
                    user.challengeReceived, user.latestGames)

            this.createEditProfileRequest(user = newUser, password = password)
        }
    }

    /**
     * Method responsible to get user information and display it
     *
     * @param viewModel contains formatted player data
     */
    override fun displayProfileToEdit(viewModel: EditProfileModel.RecoverUserRequest.ViewModel) {

        this.username.text = viewModel.playerToEdit.username
        this.rankingID.text = viewModel.playerToEdit.ranking
        this.emailTextEdit.setText(viewModel.playerToEdit.email, TextView.BufferType.EDITABLE)
        this.clubName.text = viewModel.playerToEdit.club
    }

    /**
     * Method responsible to get edit profile error and display it
     *
     * @param viewModel contains formatted error message
     */
    override fun displayEditedProfile(viewModel: EditProfileModel.EditProfileRequest.ViewModel) {

        val errorMessage = viewModel.errorMessage

        if(errorMessage == null) {
            this.finish()
        } else {
            this.errorMessageTextView.text = errorMessage
        }
    }
    /**
     * Method responsible for creating the get profile request and passing it to the interactor
     */
    fun createGetProfileRequest(){
        val recoverUserRequest: EditProfileModel.RecoverUserRequest.Request =
                EditProfileModel.RecoverUserRequest.Request(
                        "gabrielalbino",
                        "UHDASFUHADSUHF2828HJDDJFHA")

        getUserInformationInteractor?.getProfileToEdit(recoverUserRequest)
    }

    /**
     * Method responsible for creating the edit profile request and passing it to the interactor
     */
    fun createEditProfileRequest(user: User, password: String){
        val editProfileRequest: EditProfileModel.EditProfileRequest.Request =
                EditProfileModel.EditProfileRequest.Request(user, password)

        editUserInformationInteractor?.setEditedProfile(editProfileRequest)
    }



    /**
     * Method responsible to set all the references in this scene
     */
    fun setupEditProfileScene() {
        val interactor = EditProfileInteractor()
        val presenter = EditProfilePresenter()
        val view = this

        view.getUserInformationInteractor = interactor
        view.editUserInformationInteractor = interactor
        interactor.formatUserDataPresenter = presenter
        interactor.formatErrorCodePresenter = presenter
        presenter.viewToShowUserInformation = view
        presenter.viewToShowEditProfileError = view
    }
}
