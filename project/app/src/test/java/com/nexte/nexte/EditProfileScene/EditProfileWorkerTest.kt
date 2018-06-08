package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.HelpForRealm
import org.junit.Before
import org.junit.After
import org.junit.Assert
import org.junit.Test
import java.util.*

class EditProfileWorkerTest: HelpForRealm() {

    private var worker: EditProfileWorker? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = EditProfileWorker()
    }

    @Test
    fun successGetUserProfileToEdit() {
        //prepare
        val request = EditProfileModel.RecoverUserRequest.Request("Alexandre", "wh4ts")

        //call
        this.worker?.getUserProfileToEdit(request, { response ->

            //assert
            Assert.assertNotNull(response)
        })
    }

    @Test
    fun successEditUserProfile() {

        //prepare
        val testUser = User("1",
                "André Rede",
                null,
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList())
        val request = EditProfileModel.EditProfileRequest.Request(testUser,"654321")

        //call
        this.worker?.editUserProfile(request, { response ->
            //assert
            Assert.assertNotNull(response)
        })

    }


    @After
    fun tearDown() {
        super.tearDownRealm()
    }
}
