package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class EditProfileInteractorTest {

    var mock: MockEditProfilePresenter? = null
    var interactor: EditProfileInteractor? = null

    @Before
    fun setUp(){
        this.mock = MockEditProfilePresenter()
        interactor = EditProfileInteractor()
        interactor?.formatUserDataPresenter = mock
        interactor?.formatErrorCodePresenter = mock
    }

    @Test
    fun getProfileToEditTest() {

        // prepare
        val request = EditProfileModel.RecoverUserRequest.Request("gabrielalbino",
                "UHDASFUHADSUHF2828HJDDJFHA")
        // call
        this.interactor?.getProfileToEdit(request = request)

        // assert
        assertEquals(this.mock?.hasBeenHere, true)
    }

    @Test
    fun setEditedProfile() {

        //prepare
        val player = Player("gabrielalbino",
                1,
                "imgur.com/nudh486d4",
                "enggabriel@gmail.com",
                "masculino",
                "ASCAD",
                35,
                "feioso",
                "Profissional")
        val request = EditProfileModel.EditProfileRequest.Request(user = player)

        // call
        this.interactor?.setEditedProfile(request = request)

        // assert
        assertEquals(this.mock?.hasBeenHere, true)
    }

    @After
    fun tearDown(){
        this.interactor = null
        this.mock = null
    }


}

class MockEditProfilePresenter: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic {

    var  hasBeenHere = false

    override fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response) {
        this.hasBeenHere = true
    }

    override fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response) {
        this.hasBeenHere = true
    }
}
