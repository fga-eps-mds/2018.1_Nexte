package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import com.nexte.nexte.UserSingleton
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
    fun getUserProfileToEditTest() {

        // prepare
        val request = EditProfileModel.RecoverUserRequest.Request("gabrielalbino",
                "23892rncyeubec")

        // call
        this.worker?.getUserProfileToEdit(request = request, completion =  { response ->

            assertNotNull(response.user) // assert
        })
    }

    @Test
    fun editUserProfileTest() {

        // prepare//
        val player = Player("gabrielalbino",
                1,
                "imgur.com/nudh486d4",
                "enggabriel@gmail.com",
                "masculino",
                "ASCAD",
                35,
                "feioso",
                "Profissional")
        val request = EditProfileModel.EditProfileRequest.Request(user = player)
        
        // call
        this.worker?.editUserProfile(request = request, completion = { response ->

            // assert
            assertNotNull(response.newUser)
        })
    }



    @After
    fun tearDown() {
    }
}
