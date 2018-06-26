package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapter
import com.nexte.nexte.Entities.User.UserAdapterRealm
import com.nexte.nexte.HelpForRealm
import junit.framework.Assert.*
import org.junit.Before
import org.junit.After
import org.junit.Assert
import org.junit.Test
import java.util.*

@Suppress("DEPRECATION")
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
        this.worker?.getUserProfileToEdit(request) { response ->

            //assert
            Assert.assertNotNull(response)
        }
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
        this.worker?.editUserProfile(request) { response ->
            //assert
            Assert.assertNotNull(response)
        }

    }

    @Test
    fun successGetUserProfileToEditCase2() {
        //prepare
        val request = EditProfileModel.RecoverUserRequest.Request("Alexandre", "wh4ts")

        //call
        this.worker?.getUserProfileToEdit(request) { response ->

            //assert
            Assert.assertNotNull(response)
        }
    }

    @Test
    fun successEditUserProfileCase2() {

        //prepare
        val backup = worker?.userAdapter
        worker?.userAdapter = null
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
        this.worker?.editUserProfile(request) { response ->
            //assert
            assertNotNull(response)
        }

        //backup
        worker?.userAdapter = backup
    }

    @Test
    fun getUserAdapter(){
        //prepare
        val oldUserAdapter = worker?.userAdapter

        //assert
        assertEquals(oldUserAdapter, worker?.userAdapter)
    }

    @Test
    fun setUserAdapter(){
        //prepare
        val newUserAdapter = UserAdapterRealm()
        //call
        worker?.userAdapter = newUserAdapter

        //assert
        assertEquals(newUserAdapter, worker?.userAdapter)
    }


    @After
    fun tearDown() {
        super.tearDownRealm()
    }
}
