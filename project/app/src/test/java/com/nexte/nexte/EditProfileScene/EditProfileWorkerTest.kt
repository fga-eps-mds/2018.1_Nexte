package com.nexte.nexte.EditProfileScene

import org.junit.Before
import org.junit.After

/**
 * Created by miguelpimentel on 10/05/18.
 */

class EditProfileWorkerTest {

    private var worker: EditProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = EditProfileWorker()
    }


    @After
    fun tearDown() {
         this.worker = null
    }
}

//class LoginWorkerTest {
//
//    private var worker: LoginWorker? = null
//
//    @Before
//    fun setUp() {
//        worker = LoginWorker()
//    }
//
////    @Test
////    fun testAuthenticateUserTokenNotEmpty(){
////        //prepare
////        val userName = "miguelpimentel"
////        val password = "123456"
////        val request = LoginModel.Request(userName = userName, password = password)
////        val tokenId = "sd723gs261g2sv1234ss"
////
////        //call
////        this.worker?.authenticateUser(request = request, completion = { response ->
////
////            //assert
////            assertEquals(response.tokenId, tokenId)
////        })
////    }
//
////    @Test
////    fun testAuthenticateUserTokenEmpty(){
////        //prepare
////        val userName = "luis-gustavo"
////        val password = "123456"
////        val request = LoginModel.Request(userName = userName, password = password)
////        val tokenId = ""
////
////        //call
////        this.worker?.authenticateUser(request = request, completion = { response ->
////
////            //assert
////            assertEquals(response.tokenId, tokenId)
////        })
////    }
//
//    @After
//    fun tearDown() {
//        worker = null
//    }
//}