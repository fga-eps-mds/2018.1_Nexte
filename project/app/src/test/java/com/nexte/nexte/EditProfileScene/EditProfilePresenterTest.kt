package com.nexte.nexte.EditProfileScene


import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*
import org.junit.Assert.*


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

    @Test
    fun testPresentProfileToEdit() {
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())
        val testResponse = EditProfileModel.RecoverUserRequest.Response(testUser)


        //call
        this.presenter?.presentProfileToEdit(testResponse)

        //assert
        assertEquals(true, this.mock?.hasBeenHere)

    }

    @Test
    fun testSendEditedProfileStatus() {
        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())
        val testResponse = EditProfileModel.EditProfileRequest.Response(1, testUser)


        //call
        this.presenter?.sendEditedProfileStatus(testResponse)

        //assert
        assertEquals(true, this.mock?.hasBeenHere)

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