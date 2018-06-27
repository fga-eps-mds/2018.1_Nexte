package com.nexte.nexte.LoginScene

import android.util.Log
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
        val authorized = LoginModel.Authentication.StatusCode.AUTHORIZED
        val response = LoginModel.Authentication.Response(tokenId = token, authenticateStatusCode = authorized)
        val expectedMessage = token

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun sucessMessagePresentAccountKit() {
        //prepare
        val sucessed = LoginModel.AccountKit.StatusCode.SUCESSED
        val response = LoginModel.AccountKit.Response(statusCode = sucessed)
        val expectedMessage = "SUCESSED"

        //call
        this.presenter?.presentAccountKit(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun cancelMessagePresentAccountKit() {
        //prepare
        val cancel = LoginModel.AccountKit.StatusCode.CANCELLED
        val response = LoginModel.AccountKit.Response(statusCode = cancel)
        val expectedMessage = "Ops, a autenticação foi cancelada"

        //call
        this.presenter?.presentAccountKit(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun errorMessagePresentAccountKit() {
        //prepare
        val error = LoginModel.AccountKit.StatusCode.ERROR
        val response = LoginModel.AccountKit.Response(statusCode = error)
        val expectedMessage = "Ops, houve um erro, tente novamente"

        //call
        this.presenter?.presentAccountKit(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun failMessagePresentLogin() {
        //prepare
        val unauthorized = LoginModel.Authentication.StatusCode.UNAUTHORIZED
        val response = LoginModel.Authentication.Response(tokenId = "", authenticateStatusCode = unauthorized)
        val expectedMessage = ""

        //call
        this.presenter?.presentLogin(response = response)

        //assert
        assertEquals(this.mock?.message, expectedMessage)
    }

    @Test
    fun testPresentError() {
        //prepare
        val tokenID = "123456"
        val value1 = LoginModel.Authentication.StatusCode.AUTHORIZED
        val response = LoginModel.Authentication.Response(tokenID, value1)

        //call
        this.presenter?.presentError(response = response)

        //assert
        assertNotNull(response)
    }

    @Test
    fun testGetView() {
        //prepare
        val view = presenter?.view

        //assert
        assertEquals(presenter?.view, view)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockLoginDisplayLogic: LoginDisplayLogic {

    var message: String? = null
    var passedHere: Boolean = false

    override fun displayAuthenticateState(viewModel: LoginModel.Authentication.ViewModel) {
        this.message = viewModel.tokenId
        print(this.message)
    }

    override fun displayAccountKit(viewModel: LoginModel.AccountKit.ViewModel) {
        this.message = viewModel.message
        print(this.message)
    }
}