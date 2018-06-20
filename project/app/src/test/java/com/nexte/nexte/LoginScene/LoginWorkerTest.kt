package com.nexte.nexte.LoginScene

import org.json.JSONObject
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

//    @Test
//    fun testAuthenticateUserTokenNotEmpty(){
//        //prepare
//        val userName = "miguelpimentel"
//        val password = "123456"
//        val request = LoginModel.Authentication.Request(userName = userName, password = password)
//        val tokenId = "sd723gs261g2sv1234ss"
//
//        //call
//        this.worker?.authenticateUser(request = request, completion = { response ->
//
//            //assert
//            assertEquals(response.tokenId, tokenId)
//        })
//    }

    //    @Test
//    fun testAuthenticateUserTokenEmpty(){
//        //prepare
//        val userName = "luis-gustavo"
//        val password = "123456"
//        val request = LoginModel.Authentication.Request(userName = userName, password = password)
//        val tokenId = ""
//
//        //call
//        this.worker?.authenticateUser(request = request, completion = { response ->
//
//            //assert
//            assertEquals(response.tokenId, tokenId)
//        })
//    }
//
//    @Test
//    fun testRequestForAuth() {
//        //prepare
//        val email = "helenaHtona@nexte.com"
//        val phone = "123456"
//        val statusCode = "SUCESS"
//        val request = LoginModel.AccountKit.Request(email = email, phone = phone)
//
//        //call
//        this.worker?.requestForAuth(request = request, completion = { response ->
//
//            //assert
//            assertEquals(response.statusCode, statusCode)
//        })

        @Test
        fun testDefineBodyForAccountKitAuth() {
            val json = JSONObject()
            val phone = "333"
            val email = "eai.com"

            val mockRequest = LoginModel.AccountKit.Request(email, phone)



            json.put("phone", phone)
            json.put("password", "bla")


            assertEquals(mockRequest.email, email)


        }
    @Test
    fun testDefineBodyForAccountKitAuthPhoneNotNull(){
        val phone = "123456789"
        val email = "email@email.com"

        val json = this.worker?.defineBodyForAccountKitAuth(phone, email)

        val jsonObject = JSONObject()
        jsonObject.put("phone", phone)
        jsonObject.put("password", "test-nexte-ramires")

        assertEquals(json.toString(), jsonObject.toString())
    }

    @Test
    fun testDefineBodyForAccountKitAuthPhoneNull(){
        val phone = null
        val email = "email@email.com"

        val json = this.worker?.defineBodyForAccountKitAuth(phone, email)

        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", "test-nexte-ramires")

        assertEquals(json.toString(), jsonObject.toString())
    }

    @After
    fun tearDown() {
        worker = null
    }

}