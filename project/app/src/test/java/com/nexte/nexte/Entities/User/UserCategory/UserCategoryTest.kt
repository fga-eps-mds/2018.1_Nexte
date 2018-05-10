package com.nexte.nexte.Entities.User.UserCategory

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserCategoryTest {

    @Before
    fun setUp() {}

    @Test
    fun successUserCategoryTest() {

        // Prepare
        val category = UserCategory("1", "First Class")

        // Asserts
        Assert.assertEquals("Id of category are incorrect!", category.id, "1")
        Assert.assertEquals("Name of category are incorrect!", category.name, "First Class")
    }

    @After
    fun tearDown() {}
}