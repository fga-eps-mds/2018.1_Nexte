package com.nexte.nexte.LoginScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginWorkerTest {

    private var worker: LoginWorker? = null

    @Before
    fun setUp() {
        worker = LoginWorker()
    }

    @Test
    fun testAuthenticateUserTokenNotEmpty(){
        //prepare
        val userName = "miguelpimentel"
        val password = "123456"
        val request = LoginModel.Authentication.Request(userName = userName, password = password)
        val tokenId = "sd723gs261g2sv1234ss"

        //call
        this.worker?.authenticateUser(request = request, completion = { response ->

            //assert
            assertEquals(response.tokenId, tokenId)
        })
    }

    @Test
    fun testAuthenticateUserTokenEmpty(){
        //prepare
        val userName = "luis-gustavo"
        val password = "123456"
        val request = LoginModel.Authentication.Request(userName = userName, password = password)
        val tokenId = ""

        //call
        this.worker?.authenticateUser(request = request, completion = { response ->

            //assert
            assertEquals(response.tokenId, tokenId)
        })
    }

    @Test
    fun testRequestForAuth(){
        //prepare
        val email = "helenaHtona@nexte.com"
        val phone = "123456"
        val statusCode = "SUCESS"
        val request = LoginModel.AccountKit.Request(email = email, phone = phone)

        //call
        this.worker?.requestForAuth(request= request, completion= { response ->

            //assert
            assertEquals(response.statusCode, statusCode)
        })
    }


    @After
    fun tearDown() {
        worker = null
    }
}