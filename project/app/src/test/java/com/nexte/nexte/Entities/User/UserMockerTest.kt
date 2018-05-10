package com.nexte.nexte.Entities.User

import org.junit.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserMockerTest {

    @Before
    fun setUp() {}

    @Test
    fun generateUsersTest() {
        val userList = UserMocker.generateUsers()
        Assert.assertTrue("User list need to have count bigger or equals than 20.", userList.count() >= 20)
    }

    @After
    fun tearDown() {}
}