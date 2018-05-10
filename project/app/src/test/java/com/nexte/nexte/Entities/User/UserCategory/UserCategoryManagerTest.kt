package com.nexte.nexte.Entities.User.UserCategory

import org.junit.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserCategoryManagerTest {

    var userCategoryManager: UserCategoryManager? = null

    @Before
    fun setUp() {
        val userCategoryAdapterSpy = UserCategoryAdapterSpy()
        userCategoryManager = UserCategoryManager(userCategoryAdapterSpy)
    }

    @Test
    fun getAllUsersTest() {
        val userCategoryList = userCategoryManager!!.getAll()
        Assert.assertTrue("User Category list need to have count bigger or equals than 0.", userCategoryList.count() >= 0)
    }

    @Test
    fun getOneUserWithSuccessTest() {
        val userCategory = userCategoryManager!!.get("1")
        Assert.assertNotNull("Not null user category!", userCategory)
    }

    @Test
    fun getOneUserWithFailTest() {
        val userCategory = userCategoryManager!!.get("aasdas")
        Assert.assertNull("Null user!", userCategory)
    }

    @Test
    fun updateOrInsertSuccessTest() {
        val userCategory = userCategoryManager!!.update(UserCategory("1", "Category valid"))
        Assert.assertNotNull("User need to be valid in this case!", userCategory)
    }

    @Test
    fun updateOrInsertFailTest() {
        val userCategory = userCategoryManager!!.update(UserCategory("jasd", "Category invalid"))
        Assert.assertNull("User need to be null in this case!", userCategory)
    }

    @Test
    fun deleteSuccessTest() {
        val userCategory = userCategoryManager!!.delete("1")
        Assert.assertNotNull("Not null user!", userCategory)
    }

    @Test
    fun deleteFailTest() {
        val userCategory = userCategoryManager!!.delete("hshaexx")
        Assert.assertNull("Null user!", userCategory)
    }

    @Test
    fun getUserCategoryAdapterTest() {
        val userCategoryAdapter = userCategoryManager!!.userCategoryAdapter
        Assert.assertEquals("Adapter validate class!", userCategoryAdapter.javaClass, UserCategoryAdapterSpy::class.java)
    }

    @Test
    fun createInitialMockerTest() {
        val userCategoryList = userCategoryManager!!.createInitialMocker()
        Assert.assertTrue("User category list need to have count > 0!", userCategoryList.count() > 0)
    }

    @After
    fun tearDown() {}
}