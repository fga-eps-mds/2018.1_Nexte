package com.nexte.nexte.Entities.User.UserCategory

import com.nexte.nexte.Entities.User.UserCategory.UserCategory
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

    @Test
    fun successUserCategorySetMethodsTest() {

        // Prepare
//        val category = UserCategory().apply {
//            this.id = "1"
//            this.name = "First Class"
//        }

        // Asserts
//        Assert.assertEquals("Id of category are incorrect!", "1", category.id)
//        Assert.assertEquals("Name of category are incorrect!", "First Class", category.name)
    }

    @After
    fun tearDown() {}
}