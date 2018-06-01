package com.nexte.nexte.EditProfileScene

import org.junit.Before
import org.junit.After
import org.junit.*
import junit.framework.Assert.assertNotNull

class EditProfileWorkerTest {

    private var worker: EditProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = EditProfileWorker()
    }

    @Test
    fun getUserProfileToEditTestForAnInexistedUser() {

        // prepare
        val request = EditProfileModel.RecoverUserRequest.Request("gabrielalbino",
                "")

        // call
        this.worker?.getUserProfileToEdit(request = request, completion =  { response ->

            assertNotNull(response.user) // assert
        })
    }

    @Test
    fun getUserProfileToEditTestForAnExistedUser() {

        // prepare
        val request = EditProfileModel.RecoverUserRequest.Request("gabrielalbino",
                "")

        // call
        this.worker?.getUserProfileToEdit(request = request, completion =  { response ->

            assertNotNull(response.user) // assert
        })
    }

    @After
    fun tearDown() {
         this.worker = null
    }
}
