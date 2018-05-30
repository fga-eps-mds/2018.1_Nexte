package com.nexte.nexte

import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserSingletonTest {

    var userType: UserType = UserType.MOCKED
    var loggedUserID: String = "9"

    @Before
    fun setUp() {
        this.userType = UserSingleton.userType
        this.loggedUserID = UserSingleton.loggedUserID
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
        //prepare
        val loggedUser: User = UserSingleton.loggedUser

        //call
        UserSingleton.setLoggedUser(identifier = "9", userType = UserType.MOCKED)
        val userInfo = UserSingleton.loggedUser

        //assert
        assertEquals(loggedUser.name, userInfo.name)
        assertEquals(loggedUser.rankingPosition, userInfo.rankingPosition)
        assertEquals(loggedUser.email, userInfo.email)
        assertEquals(loggedUser.gender, userInfo.gender)
        assertEquals(loggedUser.category, userInfo.category)
    }

    @After
    fun tearDown() {
    }
}