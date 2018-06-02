package com.nexte.nexte

import org.junit.*
import org.junit.Assert.*


class UserSingletonTest: HelpForRealm() {

    @Before
    fun setUp() {
        super.setUpWithUser()
    }

    @Test
    fun successEnum() {
        //prepare
        val userTypeReal = UserType.REAL
        val userTypeMocked = UserType.MOCKED

        //assert
        Assert.assertNotNull("User type real is incorrect!", userTypeReal)
        Assert.assertNotNull("User type mocked is incorrect!", userTypeMocked)
    }

    @Test
    fun successSetLoggedUser(){
        //call
        UserSingleton.setLoggedUser(identifier = "9", userType = UserType.MOCKED)
        val userInfo = UserSingleton.loggedUser

        //assert
        assertEquals("9", userInfo.id)
        assertEquals(UserType.MOCKED, UserSingleton.userType)
    }

    @After
    fun tearDown() {
        super.tearDownRealm()
    }
}