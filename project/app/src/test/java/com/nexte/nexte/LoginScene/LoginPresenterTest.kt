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
        this.presenter?.view = mock
    }

    @Test
    fun successMessagePresentLogin() {
        //prepare
        val token = "1820uf09183h9d12db092ed9has9d1j020hf90aasfjialuch"
        val authorized = LoginModel.AuthenticationStatus.AUTHORIZED
        val response = LoginModel.Response(tokenId = token, authenticateStatus = authorized)
        val expectedMessage = "Congratz! U get it"

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun failMessagePresentLogin() {
        //prepare
        val unauthorized = LoginModel.AuthenticationStatus.UNAUTHORIZED
        val response = LoginModel.Response(tokenId = "", authenticateStatus = unauthorized)
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

private class MockLoginDisplayLogic: LoginDisplayLogic {

    var message: String? = null

    override fun displayAuthenticateState(viewModel: LoginModel.ViewModel) {
        this.message = viewModel.message
        print(this.message)
    }
}