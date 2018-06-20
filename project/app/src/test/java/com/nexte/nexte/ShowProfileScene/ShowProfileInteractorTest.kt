package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class ShowProfileInteractorTest {

    private var mock: MockShowProfilePresentationLogic? = null
    private var interactor: ShowProfileInteractor? = null
    private var mock2: MockShowProfileUpdateLogic? = null

    @Before
    fun setUp() {
        this.mock = MockShowProfilePresentationLogic()
        this.interactor = ShowProfileInteractor()
        this.interactor?.presenter = mock
        this.mock2 = MockShowProfileUpdateLogic()
    }

    @Test
    fun successShowProfile(){
        //prepare
        this.interactor?.worker = ShowProfileWorker()
        this.interactor?.worker?.updateLogic = mock2
        this.interactor?.worker?.userManager = UserManager(UserAdapterSpy())
        val request = ShowProfileModel.Request("luis")
        val expectedResult = true
        val presenter = this.interactor?.presenter

        //call
        thread { this.interactor?.showProfile(request = request) }.join()

        //assert
        assertNotNull(presenter)
        assertEquals(expectedResult, this.mock2?.passedHere)
    }

    @Test
    fun testUpdateUserProfile(){
        //prepare
        val response = ShowProfileModel.Response(User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null))

        //call
        interactor?.updateUserProfile(response)

        //assert
        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun testUpdateUserProfileWithoutPresenter(){
        //prepare
        val response = ShowProfileModel.Response(User("", "", "", "", null, -1,
                "", "", -1, -1, User.Gender.FEMALE, UserCategory("", ""),
                User.Status.UNAVAILABLE,null, null, null))
        val backup = this.interactor?.presenter
        this.interactor?.presenter = null
        //call
        interactor?.updateUserProfile(response)

        //assert
        assertEquals(false, mock?.passedHere)

        //backup
        this.interactor?.presenter = backup
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockShowProfilePresentationLogic: ShowProfilePresentationLogic {

    var passedHere = false

    override fun presentUserProfile(response: ShowProfileModel.Response) {
        this.passedHere = true
    }

}

private class MockShowProfileUpdateLogic: ShowProfileWorkerUpdateLogic{

    var passedHere = false

    override fun updateUserProfile(response: ShowProfileModel.Response) {
        this.passedHere = true
    }
}