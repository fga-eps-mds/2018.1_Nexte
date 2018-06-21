package com.nexte.nexte.LoginScene

import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import kotlin.concurrent.thread

class LoginInteractorTest: HelpForRealm() {

    private var mock: MockLoginPresentationLogic? = null
    private var interactor: LoginInteractor? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.mock = MockLoginPresentationLogic()
        this.interactor = LoginInteractor()
        this.interactor?.presenter = mock

    }

    @Test
    fun successSetWorkerTest(){
        //prepare
        val newWorker = LoginWorker()

        //call
        this.interactor?.worker = newWorker

        //assert
        assertEquals(newWorker, interactor?.worker)
    }
    
    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }

    @Test
    fun testGetWorker() {
        // prepare and call
        val worker = interactor?.worker

        assertEquals(interactor?.worker, worker)
    }

    @Test
    fun testGetPresenter() {
        // prepare and call
        val presenter = interactor?.presenter

        assertEquals(interactor?.presenter, presenter)
    }

    @Test
    fun testAuthenticateUserAuthorized(){
        val responseStatus = LoginModel.Authentication.StatusCode.AUTHORIZED
        val response = LoginModel.Authentication.Response("1", responseStatus)


        this.interactor?.authenticateUser(response)

        assertTrue(this.mock?.passedHere!!)
    }

    @Test
    fun testAuthenticateUserNotAuthorized(){
        val responseStatus = LoginModel.Authentication.StatusCode.UNAUTHORIZED
        val response = LoginModel.Authentication.Response("1", responseStatus)


        this.interactor?.authenticateUser(response)

        assertTrue(this.mock?.passedHere!!)
    }

    @Test
    fun testRequestAuth(){
        val responseStatus = LoginModel.AccountKit.StatusCode.SUCESSED
        val response = LoginModel.AccountKit.Response(responseStatus)

        this.interactor?.requestAuth(response)

        assertTrue(this.mock?.passedHere!!)
    }

    @Test
    fun testDoAuthentication(){
        val request = LoginModel.Authentication.Request("123", "123")

        thread { this.interactor?.doAuthentication(request)}.join()

        assertNull(this.mock?.passedHere)
    }

    @Test
    fun testAccountKitAuthentication(){
        val request = LoginModel.AccountKit.Request("123", "123")

        thread{this.interactor?.accountKitAuthentication(request)}.join()

        assertNull(this.mock?.passedHere)
    }

//    @Test
//    fun testSetWorker() {
//        //prepare and call
//        val worker = LoginWorker()
//
//        assertEquals(interactor?.worker, worker)
//    }

//    @Test
//    fun testAccountKitAuth() {
//        // prepare
//        var email = "helenaHtona@nexte.com"
//        var phone = "123456"
//        val value = LoginModel.AccountKit.StatusCode.SUCESSED
//        var request = LoginModel.AccountKit.Request(email, phone)
//        var response1 = LoginModel.AccountKit.Response(value)
//
//        // call
//        interactor?.worker?.requestForAuth(request) { response ->
//            // search
//            assertEquals(interactor?.presenter?.presentAccountKit(response), response1)
//        }
//    }
//
//    @Test
//    fun testDoAuthentication() {
//        // prepare
//        val user = "fulano"
//        val password = "123456"
//        val request = LoginModel.Authentication.Request(user, password)
//        val tokenID = "123456"
//        val value1 = LoginModel.Authentication.StatusCode.AUTHORIZED
//        val response = LoginModel.Authentication.Response(tokenID, value1)
//        val responseStatus = response.authenticateStatusCode
//
//        // call
//        interactor?.worker.au
//    }

}

private class MockLoginPresentationLogic: LoginPresentationLogic{
    var passedHere: Boolean? = null

    override fun presentLogin(response: LoginModel.Authentication.Response){
        this.passedHere = true
    }

    override fun presentError(response: LoginModel.Authentication.Response) {
        this.passedHere = true
    }

    override fun presentAccountKit(response: LoginModel.AccountKit.Response) {
        this.passedHere = true
    }
}