package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginViewTest {

    var view: LoginView? = null

    @Before
    fun setUp() {
        this.view = LoginView()
    }

    @Test
    fun testSetup(){
        //prepare
        this.view?.setup()

        //call

        //assert
        assertNotNull(this.view?.interactor)
    }

    @After
    fun tearDown() {
    }
}

private class MockLoginBusinessLogic: LoginBusinessLogic{
    var request: LoginModel.Request? = null

    override fun doAuthentication(request: LoginModel.Request) {
        this.request = request
    }
}