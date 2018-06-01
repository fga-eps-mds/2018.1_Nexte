package com.nexte.nexte.EditProfileScene

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
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
class EditProfileFragment : Fragment(),
                        ShowProfileToEditDisplayLogic,
                        EditProfileDisplayLogic {

    var getUserInformationInteractor: GetProfileToEditBusinessLogic? = null
    var editUserInformationInteractor: EditProfileBusinessLogic? = null
    var errorMessageTextView: TextView? = null
    var passwordConfirmationTextEdit: EditText? = null
    var passwordTextEdit: EditText? = null
    var updateProfileButton: ImageButton? = null
    /**
     * Class responsible to define behavior of password validation (checking if password and confirmation match)
     */
    class PasswordWatcher(var fragment: EditProfileFragment) : TextWatcher {

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

            if(this.fragment.passwordConfirmationTextEdit?.text?.trim().toString() ==
               this.fragment.passwordTextEdit?.text?.trim().toString()) {
                fragment.passwordValidationTextView.text = "✓"
                fragment.passwordValidationTextView.setTextColor(Color.GREEN)
                fragment.updateProfileButton?.isEnabled = true
            } else {
                fragment.passwordValidationTextView.text = "✕"
                fragment.passwordValidationTextView.setTextColor(Color.RED)
                fragment.updateProfileButton?.isEnabled = false
            }
        }
    }

    fun getInstance(): EditProfileFragment {
        val editProfileFragment = EditProfileFragment()
        return editProfileFragment
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val newView = inflater?.inflate(R.layout.activity_edit_profile, container, false)
        this.errorMessageTextView = newView?.findViewById(R.id.errorMessageTextView)
        this.passwordConfirmationTextEdit = newView?.findViewById(R.id.passwordConfirmationTextEdit)
        this.passwordTextEdit = newView?.findViewById(R.id.passwordTextEdit)
        this.updateProfileButton = newView?.findViewById(R.id.updateProfileButton)

        super.onCreate(savedInstanceState)
        this.setupEditProfileScene()

        this.createEditProfileRequest(user = Player("", -1, "", "", "", "", -1, "", ""))

        this.passwordConfirmationTextEdit?.addTextChangedListener(PasswordWatcher(this))
        this.passwordTextEdit?.addTextChangedListener(PasswordWatcher(this))



        return newView

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        this.createGetProfileRequest()

        updateProfileButton?.setOnClickListener {

            val user = Player(username.text.toString(),
                    UserSingleton.getUserInformations().rankingPosition,
                    "",
                    emailTextEdit.text.trim().toString(),
                    "",
                    clubName.text.toString(),
                    ageTextEdit.text.trim().toString().toInt(),
                    passwordTextEdit?.text?.trim().toString(),
                    "")

            this.createEditProfileRequest(user = user)

        }
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Method responsible to get user information and display it
     *
     * @param viewModel contains formatted player data
     */
    override fun displayProfileToEdit(viewModel: EditProfileModel.RecoverUserRequest.ViewModel) {

        this.username.text = viewModel.playerToEdit.username
        this.emailTextEdit.setText(viewModel.playerToEdit.email, TextView.BufferType.EDITABLE)
        this.ageTextEdit.setText(viewModel.playerToEdit.age, TextView.BufferType.EDITABLE)
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
            this.activity.finish()
        } else {
            this.errorMessageTextView?.text = errorMessage
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
    fun createEditProfileRequest(user: Player){
        val editProfileRequest: EditProfileModel.EditProfileRequest.Request =
                EditProfileModel.EditProfileRequest.Request(user)

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
