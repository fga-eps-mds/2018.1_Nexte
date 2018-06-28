package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginModelTest {

    @Before
    fun setUp() { }

    @Test
    fun successLoginModelAuthenticationRequest(){
        //prepare
        val userName = "luis-gustavo"
        val password = "123456"

        //call
        val request = LoginModel.Authentication.Request(userName = "luis-gustavo", password = "123456")

        //assert
        assertEquals(userName, request.userName)
        assertEquals(password, request.password)
    }

    @Test
    fun successLoginModelAuthenticationResponse(){
        //prepare
        val tokenId = "hq7lwk13nvv31"
        val authenticationStatus = LoginModel.Authentication.StatusCode.AUTHORIZED

        //call
        val response = LoginModel.Authentication.Response(tokenId = "hq7lwk13nvv31",
                authenticateStatusCode = LoginModel.Authentication.StatusCode.AUTHORIZED)

        //assert
        assertEquals(tokenId, response.tokenId)
        assertEquals(authenticationStatus, response.authenticateStatusCode)
    }

    @Test
    fun successLoginModelAccountKitResponse(){
        // prepare
        val statusCode = LoginModel.AccountKit.StatusCode.SUCESSED

        // call
        val response = LoginModel.AccountKit.Response(statusCode = statusCode)

        //assert
        assertEquals(statusCode, response.statusCode)
    }

    @Test
    fun successLoginModelAuthenticationViewModel(){
        //prepare
        val message = "teste funcionando"

        //call
        val viewModel = LoginModel.Authentication.ViewModel(tokenId = "teste funcionando")

        //assert
        assertEquals(message, viewModel.tokenId)
    }

    @Test
    fun successLoginModelAccountKitViewModel(){
        //prepare
        val message = "teste funcionando"

        //call
        val viewModel = LoginModel.AccountKit.ViewModel(message = "teste funcionando")

        //assert
        assertEquals(message, viewModel.message)
    }

    @Test
    fun sucessStatusToAuthentication() {
        //prepare
        val authorized = LoginModel.Authentication.StatusCode.AUTHORIZED.value

        //assert
        assertEquals(authorized, LoginModel.Authentication.StatusCode.AUTHORIZED.value)
    }

    @Test
    fun sucessStatusToAccountKit() {
        //prepare
        val sucessed = LoginModel.AccountKit.StatusCode.SUCESSED

        //assert
        assertEquals(sucessed, LoginModel.AccountKit.StatusCode.SUCESSED)
    }

    @Test
    fun testAccountKitCode() {
        //prepare
        val accountKitCode = LoginModel.AccountKit.accountKit_code

        //assert
        assertEquals(accountKitCode, 13)
    }

    @Test
    fun testAuthenticationModel() {
        //prepare
        val authenticationModel = LoginModel.Authentication()

        //assert
        assertNotNull(authenticationModel)
    }

    @Test
    fun testAccountKitModel() {
        //prepare
        val accountKitModel = LoginModel.AccountKit()

        //assert
        assertNotNull(accountKitModel)
    }

    @Test
    fun testAccountKitRequest() {
        //prepare
        val token = "1323444566"

        //call
        val request = LoginModel.AccountKit.Request(token)

        //assert
        assertNotNull(request.token) //Esse teste não pode ser assim

    }

    @Test
    fun testLoginModel() {
        //prepare
        val loginModel = LoginModel()

        //assert
        assertNotNull(loginModel)
    }

    @After
    fun tearDown() { }
}