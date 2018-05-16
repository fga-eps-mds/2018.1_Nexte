package com.nexte.nexte.EditProfileScene

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class EditProfileInteractorTest {

    var interactor: EditProfileInteractor?= null

    @Before
    fun setUp(){
        interactor = EditProfileInteractor()
        interactor?.formatUserDataPresenter = EditProfilePresenterMock()
        interactor?.formatErrorCodePresenter = EditProfilePresenterMock()
    }

    @Test
    fun getFormatUserDataPresenter() {

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

    @After
    fun tearDown(){
        interactor = null
    }

    class EditProfilePresenterMock: ShowProfileToEditPresentationLogic, SendEditedProfileDataPresentationLogic
    {
        var  passedHere = false;
        override fun presentProfileToEdit(response: EditProfileModel.RecoverUserRequest.Response) {
            passedHere = true
        }

        override fun sendEditedProfileStatus(response: EditProfileModel.EditProfileRequest.Response) {
            passedHere = true
        }
    }

}