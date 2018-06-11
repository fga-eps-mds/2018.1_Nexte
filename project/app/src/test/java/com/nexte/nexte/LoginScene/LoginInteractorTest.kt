package com.nexte.nexte.LoginScene

import com.facebook.accountkit.AccountKitLoginResult
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.RxThreadFactory
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//@RunWith(RobolectricTestRunner::class)
class LoginInteractorTest {

    private var mock: MockLoginPresentationLogic? = null
    private var interactor: LoginInteractor? = null
    private var worker: LoginPresentationLogic? = null

    @Before
    fun setUp() {
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

    @Test
    fun testDoAuthentication() {
        //prepare

        val request: LoginModel.Authentication.Request

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
    var passedHere = false

    override fun presentLogin(response: LoginModel.Authentication.Response){
        this.passedHere = true
        print(passedHere)
    }

    override fun presentError(response: LoginModel.Authentication.Response) {
        this.passedHere = true
        print(passedHere)
    }

    override fun presentAccountKit(response: LoginModel.AccountKit.Response) {
        this.passedHere = true
        print(passedHere)
    }
}