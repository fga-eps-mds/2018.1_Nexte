package com.nexte.nexte.EditProfileScene


import org.junit.After
import org.junit.Before


class EditProfilePresenterTest {

    var mock: MockEditProfileDisplayLogic? = null
    var presenter: EditProfilePresenter? = null

    @Before
    fun setUp() {
        this.mock = MockEditProfileDisplayLogic()
        this.presenter = EditProfilePresenter()
        this.presenter?.viewToShowEditProfileError = mock
        this.presenter?.viewToShowUserInformation = mock
    }

    @After
    fun tearDown() {

    }
}


class MockEditProfileDisplayLogic: EditProfileDisplayLogic, ShowProfileToEditDisplayLogic {

    var hasBeenHere: Boolean = false

    override fun displayEditedProfile(viewModel: EditProfileModel.EditProfileRequest.ViewModel) {
        this.hasBeenHere = true
    }

    override fun displayProfileToEdit(viewModel: EditProfileModel.RecoverUserRequest.ViewModel) {
       this.hasBeenHere = true
    }
}