package com.nexte.nexte.LoginScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.HelpForRealm
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import com.nexte.nexte.Entities.User.UserManager

import org.junit.Assert.*
import org.junit.Test
import java.net.URL
import kotlin.concurrent.thread

class LoginWorkerTest: HelpForRealm() {

    private var worker: LoginWorker? = null
    private var updateLogicMock: MockUpdateLogic? = null
    private var jsonObject = JSONObject()
    private var userManager:  UserManager? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        super.setUpWithUserCategory()
        super.setUpRealm()
        this.userManager = UserManager(UserAdapterSpy())
        this.updateLogicMock = MockUpdateLogic()
        worker = LoginWorker()
        worker?.userManager = this.userManager
        worker?.updateLogic = this.updateLogicMock
    }

    @Test
    fun testSettersAndGetters(){
        //prepare  and call
        val updateLogic = this.worker?.updateLogic
        val userManager = this.worker?.userManager

        //assert
        assertEquals(worker?.updateLogic, updateLogic)
        assertEquals(worker?.userManager, userManager)
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
        val token = "d453243gfecwg4"
        val request = LoginModel.AccountKit.Request(token = token)

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
        val request = Request(Method.POST, "", url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        worker?.authenticateHandler?.invoke(request, response, result)

        //assert
        assertNotNull(updateLogicMock?.response1)
    }



//    @Test
//    fun testAuthenticateHandlerOnSuccess() {
//        //prepare
//        updateLogicMock?.response1 = null
//        val url = URL("http://www.randomsite.com/")
//        val request = Request(Method.GET, "",url)
//        val response = Response(url)
//
//        val jsonUser = JSONObject()
//        jsonUser.put("id", "1")
//        jsonUser.put("name", "teste")
//        jsonUser.put("profileImageURL", "www.lol.com.br")
//        jsonUser.put("nickname", "biel")
//        jsonUser.put("rankPosition", 1)
//        jsonUser.put("email", "biel@poc.br")
//        jsonUser.put("phone", "3232323232")
//        jsonUser.put("wins", 1)
//        jsonUser.put("loses", 1)
//        jsonUser.put("birthDate", "2018-01-07T00:00:00.000Z")
//        jsonUser.put("gender", "M")
//        jsonUser.put("category", 1)
//        jsonUser.put("status", 1)
//
//        val dataJson = JSONObject()
//
//        dataJson.put("user", jsonUser)
//        jsonObject.put("data", dataJson)
//
//        val json = Json(jsonObject.toString())
//        val result: Result<Json, FuelError> = Result.Success(json)
//
//        //call
//        thread { worker?.authenticateHandler?.invoke(request, response, result) }.join()
//
//        //assert
//        assertNotNull(updateLogicMock?.response1)
//
//        //reset
//        this.jsonObject = JSONObject()
//    }

    @Test
    fun testNullUpdateLogicForAccountKit(){
        //prepare
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        val request = LoginModel.AccountKit.Request("miugel")
        updateLogicMock?.response2 = null

        //call
        thread{  worker?.requestForAuth(request) }.join()

        //assert
        assertNull(updateLogicMock?.response2)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun testNullUpdateLogicForDefaultAuthentication(){
        //prepare
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        val request = LoginModel.Authentication.Request("miguel", "2343545")
        updateLogicMock?.response1 = null

        //call
        thread{  worker?.authenticateUser(request) }.join()

        //assert
        assertNull(updateLogicMock?.response1)

        //backup
        worker?.updateLogic = backup
    }


    @Test
    fun testRequestAuthenticateHandlerOnFailure() {
        //prepare
        updateLogicMock?.response1 = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        thread { worker?.authenticateHandler?.invoke(request, response, result) }.join()

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
        thread { worker?.requestAuthHandler?.invoke(request, response, result) }.join()

        //assert
        assertNotNull(updateLogicMock?.response2)
    }


//    @Test
//    fun testRequestAuthHandlerOnSuccess() {
//        //prepare
//        updateLogicMock?.response2 = null
//        val url = URL("http://www.randomsite.com/")
//        val request = Request(Method.GET, "",url)
//        val response = Response(url)
//        val json = Json(jsonObject.toString())
//        val result: Result<Json, FuelError> = Result.Success(json)
//
//        //call
//        thread { worker?.requestAuthHandler?.invoke(request, response, result) }.join()
//
//        //assert
//        assertNotNull(updateLogicMock?.response2)
//    }

    @Test
    fun testDefineBodyForAccountKitAuth() {
        val json = JSONObject()
        val token = "2e5tn4ugnreiu"

        val mockRequest = worker?.defineBodyForAccountKitAuth(token)

        json.put("fbAuthCode", token)


        assertEquals(mockRequest, json.toString())
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