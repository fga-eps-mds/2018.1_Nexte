package com.nexte.nexte.LoginScene

import com.facebook.accountkit.AccountKitLoginResult
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LoginInteractorTest {

    private var mock: MockLoginPresentationLogic? = null
    private var interactor: LoginInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockLoginPresentationLogic()
        this.interactor = LoginInteractor()
        this.interactor?.presenter = mock
    }
    
    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
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