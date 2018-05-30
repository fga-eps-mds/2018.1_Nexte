package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class EditProfileFragmentTest {

    var fragment: EditProfileFragment? = null

    @Before
    fun setUp() {
        this.fragment = EditProfileFragment()
    }

    @Test
    fun testSetupEditProfileScene(){
        //prepare  //call
        this.fragment?.setupEditProfileScene()

        //assert
        assertNotNull(this.fragment?.getUserInformationInteractor)
        assertNotNull(this.fragment?.editUserInformationInteractor)
    }

    @Test
    fun testPasswordWatcher(){
        //prepare
        val passwordWatcher = EditProfileFragment.PasswordWatcher(fragment = this.fragment!!)

        //call
        passwordWatcher.afterTextChanged(s = null)
        passwordWatcher.beforeTextChanged(s = null, start = 0, count = 0, after = 0)

        //assert
        assertNotNull(passwordWatcher)
    }

    @Test
    fun testGetProfileCreateRequest(){
        //prepare
        this.fragment?.setupEditProfileScene()
        val mock = MockGetProfileToEditBusinessLogic()
        this.fragment?.getUserInformationInteractor = mock

        //call
        this.fragment?.createGetProfileRequest()

        //assert
        mock.hasBeenHere = true
    }

    @Test
    fun testEditProfileCreateRequest(){
        //prepare
        this.fragment?.setupEditProfileScene()
        val mock = MockEditProfileBusinessLogic()
        this.fragment?.editUserInformationInteractor = mock

        //call
        this.fragment?.createEditProfileRequest(user = Player(password = "123456", age = 19, gender = "masc",
                name = "luis", club = "asdasdas", email = "asdasdasd", rankingPosition = 1,
                pictureAddress = "asd", category = "profissional"))

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