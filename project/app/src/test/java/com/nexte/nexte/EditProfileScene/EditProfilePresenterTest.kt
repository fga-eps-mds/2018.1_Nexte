package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

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
    fun presentProfileToEditTest() {

        // prepare
        val player = Player("",
                -1,
                "",
                "",
                "",
                "",
                -1,
                "",
                "")
        val expectedResult = true
        val response = EditProfileModel.RecoverUserRequest.Response(player)

        // call
        this.presenter?.presentProfileToEdit(response = response)

        // assert
       assertEquals(expectedResult, this.mock?.hasBeenHere)
    }

    @Test
    fun sendEditedProfileStatusTest() {

        // prepare
        val player: Player? = null
        val errorID = 1
        val response = EditProfileModel.EditProfileRequest.Response(errorID = errorID,
                newUser = player)

        // call
        this.presenter?.sendEditedProfileStatus(response = response)

        // assert
        assertEquals(this.mock?.hasBeenHere, true)
    }

    @Test
    fun sendEditProfileStatusTest2() {

        //prepare
        val player: Player? = null
        val errorID = 2
        val response = EditProfileModel.EditProfileRequest.Response(errorID = errorID,
                newUser = player)

        // call
        this.presenter?.sendEditedProfileStatus(response = response)


        // assert
        assertEquals(this.mock?.hasBeenHere, true)
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