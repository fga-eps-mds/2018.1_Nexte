package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.*

class EditProfileInteractorTest: HelpForRealm() {

    var interactor: EditProfileInteractor? = null
    var mocker: EditProfilePresenterMock? = null


    @Before
    fun setUp(){
        super.setUpWithUser()
        interactor = EditProfileInteractor()
        mocker = EditProfilePresenterMock()
        interactor?.formatUserDataPresenter = mocker
        interactor?.formatErrorCodePresenter = mocker
    }

    @Test
    fun testGetProfileToEdit() {
        //prepare
        var requestTest = EditProfileModel.RecoverUserRequest.Request("Aleronupe", "1234f")

        //call
        this.interactor?.getProfileToEdit(requestTest)

        //assert
        assertEquals( true, this.mocker?.passedHere)
    }

    @Test
    fun testSetEditedProfile() {
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
        val requestTest = EditProfileModel.EditProfileRequest.Request(testUser,"654321")

        //call
        this.interactor?.setEditedProfile(requestTest)

        //assert
        assertEquals( true, this.mocker?.passedHere)
    }

    @Test
    fun successGetAndSetWorker() {
        //prepare
        val newWorker = EditProfileWorker()

        //call
        val worker = interactor?.worker
        interactor?.worker = newWorker

        //assert
        assertEquals(interactor?.worker, newWorker)
        assertNotNull(worker)
    }


    @After
    fun tearDown(){
        super.tearDownRealm()

        this.mocker = null
        this.interactor = null
    }


    class EditProfilePresenterMock: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic
    {
        var  passedHere = true

        override fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response) {
            passedHere = true
        }

        override fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response) {
            passedHere = true
        }
    }


}
