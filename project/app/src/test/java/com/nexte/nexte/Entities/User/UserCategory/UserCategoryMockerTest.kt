package com.nexte.nexte.Entities.User.UserCategory

import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserCategoryMockerTest {

    @Before
    fun setUp() {}

    @Test
    fun generateUsersTest() {
        val userCategoryList = UserCategoryMocker.generateUserCategories()
        Assert.assertTrue("User Category list need to have count bigger or equals than 5.", userCategoryList.count() >= 5)
    }

    @After
    fun tearDown() {}
}