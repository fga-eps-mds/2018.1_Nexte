package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun successLoginModelRequest(){
        //prepare
        val userName = "luis-gustavo"
        val password = "123456"

        //call
        val request = LoginModel.Request(userName = "luis-gustavo", password = "123456")

        //assert
        assertEquals(userName, request.userName)
        assertEquals(password, request.password)
    }

    @Test
    fun successLoginModelResponse(){
        //prepare
        val tokenId = "hq7lwk13nvv31"
        val authenticationStatus = LoginModel.AuthenticationStatus.AUTHORIZED

        //call
        val response = LoginModel.Response(tokenId = "hq7lwk13nvv31",
                                           authenticateStatus = LoginModel.AuthenticationStatus.AUTHORIZED)

        //assert
        assertEquals(tokenId, response.tokenId)
        assertEquals(authenticationStatus, response.authenticateStatus)
    }

    @Test
    fun successLoginModelViewModel(){
        //prepare
        val message = "teste funcionando"

        //call
        val viewModel = LoginModel.ViewModel(message = "teste funcionando")

        //assert
        assertEquals(message, viewModel.message)
    }

    @Test
    fun sucessStatusToAuthentication() {
        //prepare
        val authorized = LoginModel.AuthenticationStatus.AUTHORIZED.value

        //assert
        assertEquals(authorized, LoginModel.AuthenticationStatus.AUTHORIZED.value)
    }

    @After
    fun tearDown() {
    }
}