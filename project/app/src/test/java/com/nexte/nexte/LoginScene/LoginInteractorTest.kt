package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginInteractorTest {

    private var mock: MockLoginPresentationLogic? = null
    private var interactor: LoginInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockLoginPresentationLogic()
        this.interactor = LoginInteractor()
        this.interactor?.presenter = mock
    }

    @Test
    fun successDoAuthentication(){
        //prepare
        val request = LoginModel.Request(userName = "luis-gustavo", password = "123456")

        //call
        this.interactor?.doAuthentication(request = request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockLoginPresentationLogic: LoginPresentationLogic{
    var passedHere = false

    override fun presentLogin(response: LoginModel.Response){
        this.passedHere = true
        print(passedHere)
    }
}