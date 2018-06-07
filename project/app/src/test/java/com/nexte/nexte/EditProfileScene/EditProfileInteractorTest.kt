package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class EditProfileInteractorTest {

    var interactor: EditProfileInteractor? = null
    var mocker: MockerEditPresentationLogic? = null


    @Before
    fun setUp(){
        interactor = EditProfileInteractor()
        interactor?.formatUserDataPresenter = EditProfilePresenterMock()
        interactor?.formatErrorCodePresenter = EditProfilePresenterMock()
    }

    @Test
    fun getFormatUserDataPresenter() {
        //prepare
        var requestTest = EditProfileModel.RecoverUserRequest.Request("Aleronupe", "1234f")
        //call
        this.interactor?.formatUserDataPresenter
        this.interactor?.getProfileToEdit(requestTest)
        //assert
        assertEquals(mocker?.passedHere, true)
    }

    @Test
    fun setFormatUserDataPresenter() {

    }

    @Test
    fun getFormatErrorCodePresenter() {
    }

    @Test
    fun setFormatErrorCodePresenter() {
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

    @Test
    fun getProfileToEdit() {
        //prepare

        //call

    }

    @Test
    fun setEditedProfile() {
    }


    class EditProfilePresenterMock: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic
    {
        var  passedHere = false
        override fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response) {
            passedHere = true
        }

        override fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response) {
            passedHere = true
        }
    }

   class MockerEditPresentationLogic: EditProfileDisplayLogic {

        var passedHere = false

        override fun displayEditedProfile(viewModel: EditProfileModel.EditProfileRequest.ViewModel) {
            this.passedHere = true
        }
    }
}
