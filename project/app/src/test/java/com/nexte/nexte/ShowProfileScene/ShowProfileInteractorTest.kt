package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileInteractorTest {

    private var mock: MockShowProfilePresentationLogic? = null
    private var interactor: ShowProfileInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockShowProfilePresentationLogic()
        this.interactor = ShowProfileInteractor()
        this.interactor?.presenter = mock
    }

    @Test
    fun successShowProfile(){
        //prepare
        val request = ShowProfileModel.Request(username = "luis", tokenID = "asdkjabsd")
        val expectedResult = true

        //call
        this.interactor?.showProfile(request = request)

        //assert
        assertEquals(expectedResult, this.mock?.passedHere)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockShowProfilePresentationLogic: ShowProfilePresentationLogic{

    var passedHere = false

    override fun presentUserProfile(response: ShowProfileModel.Response){
        this.passedHere = true
    }
}