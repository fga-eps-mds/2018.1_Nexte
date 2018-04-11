package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginPresenterTest {

    private var mock: MockLoginDisplayLogic? = null
    private var presenter: LoginPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockLoginDisplayLogic()
        this.presenter = LoginPresenter()
        this.presenter?.viewControler = mock
    }

    @Test
    fun successMessagePresentLogin(){
        //prepare
        val response = LoginModel.Response(tokenId = "h1n3vv3u13ola")
        val expectedMessage = "Congratz! U get it"

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun failMessagePresentLogin(){
        //prepare
        val response = LoginModel.Response(tokenId =  "")
        val expectedMessage = "Something is wrong. Try again"

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockLoginDisplayLogic: LoginDisplayLogic{

    var message: String? = null

    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel){
        this.message = viewModel.message
        print(this.message)
    }
}