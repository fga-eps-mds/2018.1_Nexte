package com.nexte.nexte.LoginScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.net.URL
import kotlin.concurrent.thread

class LoginWorkerTest {

    private var worker: LoginWorker? = null
    private var updateLogicMock: MockUpdateLogic? = null
    private val jsonObject = JSONObject()

    @Before
    fun setUp() {
        worker = LoginWorker()
        this.updateLogicMock = MockUpdateLogic()
        this.worker?.updateLogic = updateLogicMock
    }

    @Test
    fun testAuthenticateUserTokenEmpty(){
        //prepare
        val userName = "luis-gustavo"
        val password = "123456"
        val request = LoginModel.Authentication.Request(userName = userName, password = password)

        //call
        thread { this.worker?.authenticateUser(request = request)}.join()

        //assert
        assertNull(this.updateLogicMock?.response1)
    }

    @Test
    fun testRequestForAuth() {
        //prepare
        val email = "helenaHtona@nexte.com"
        val phone = "123456"
        val token = "d453243gfecwg4"
        val request = LoginModel.AccountKit.Request(email = email, phone = phone, token = token)

        //call
        thread { this.worker?.requestForAuth(request = request) }.join()

        //assert
        assertNull(this.updateLogicMock?.response2)// It will never pass there
    }

    @Test
    fun testAuthenticateHandlerOnFailure() {
        //prepare
        updateLogicMock?.response1 = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        worker?.authenticateHandler?.invoke(request, response, result)

        //assert
        assertNotNull(updateLogicMock?.response1)
    }

    @Test
    fun testRequestAuthHandlerOnFailure() {
        //prepare
        updateLogicMock?.response2 = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        worker?.requestAuthHandler?.invoke(request, response, result)

        //assert
        assertNotNull(updateLogicMock?.response2)
    }

    @Test
    fun testAuthenticateHandlerOnSuccess() {
        //prepare
        updateLogicMock?.response1 = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.authenticateHandler?.invoke(request, response, result) }.join()

        //assert
        assertNotNull(updateLogicMock?.response1)
    }

    @Test
    fun testRequestAuthHandlerOnSuccess() {
        //prepare
        updateLogicMock?.response2 = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.requestAuthHandler?.invoke(request, response, result) }.join()

        //assert
        assertNotNull(updateLogicMock?.response2)
    }

    @Test
    fun testDefineBodyForAccountKitAuth() {
        val json = JSONObject()
        val phone = "333"
        val email = "eai.com"
        val token = "2e5tn4ugnreiu"

        val mockRequest = LoginModel.AccountKit.Request(email, phone, token = token)



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

class MockUpdateLogic: LoginWorkerUpdateLogic {

    var response1: LoginModel.Authentication.Response? = null
    var response2: LoginModel.AccountKit.Response? = null

    override fun authenticateUser(response: LoginModel.Authentication.Response) {
        this.response1 = response
    }

    override fun requestAuth(response: LoginModel.AccountKit.Response) {
        this.response2 = response
    }
}