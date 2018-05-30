package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class EditProfileViewTest {

    var view: EditProfileView? = null

    @Before
    fun setUp() {
        this.view = EditProfileView()
    }

    @Test
    fun testSetupEditProfileScene(){
        //prepare  //call
        this.view?.setupEditProfileScene()

        //assert
        assertNotNull(this.view?.getUserInformationInteractor)
        assertNotNull(this.view?.editUserInformationInteractor)
    }

    @Test
    fun testPasswordWatcher(){
        //prepare
        val passwordWatcher = EditProfileView.PasswordWatcher(view = this.view!!)

        //call
        passwordWatcher.afterTextChanged(s = null)
        passwordWatcher.beforeTextChanged(s = null, start = 0, count = 0, after = 0)

        //assert
        assertNotNull(passwordWatcher)
    }

    @Test
    fun testGetProfileCreateRequest(){
        //prepare
        this.view?.setupEditProfileScene()
        val mock = MockGetProfileToEditBusinessLogic()
        this.view?.getUserInformationInteractor = mock

        //call
        this.view?.createGetProfileRequest()

        //assert
        mock.hasBeenHere = true
    }

    @Test
    fun testEditProfileCreateRequest(){
        //prepare
        this.view?.setupEditProfileScene()
        val mock = MockEditProfileBusinessLogic()
        this.view?.editUserInformationInteractor = mock

        //call
        this.view?.createEditProfileRequest(user = UserSingleton.loggedUser, password = "senha")

        //assert
        mock.hasBeenHere = true
    }

    @After
    fun tearDown() {
    }
}

private class MockGetProfileToEditBusinessLogic: GetProfileToEditBusinessLogic{
    var hasBeenHere = false

    override fun getProfileToEdit(request: EditProfileModel.RecoverUserRequest.Request) {
        this.hasBeenHere = true
    }
}

private class MockEditProfileBusinessLogic: EditProfileBusinessLogic{
    var hasBeenHere = false

    override fun setEditedProfile(request: EditProfileModel.EditProfileRequest.Request) {
        this.hasBeenHere = true
    }

}