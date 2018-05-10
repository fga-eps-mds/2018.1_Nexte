package com.nexte.nexte.Entities.User

import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class UserManagerTest {
    var userManager: UserManager? = null

    @Before
    fun setUp() {
        val userAdapterSpy = UserAdapterSpy()
        userManager = UserManager(userAdapterSpy)
    }

    @Test
    fun getAllUsersTest() {
        val userList = userManager!!.getAll()
        Assert.assertTrue("User list need to have count bigger or equals than 0.", userList.count() >= 0)
    }

    @Test
    fun getOneUserWithSuccessTest() {
        val user = userManager!!.get("1")
        Assert.assertNotNull("Not null user!", user)
    }

    @Test
    fun getOneUserWithFailTest() {
        val user = userManager!!.get("aasdas")
        Assert.assertNull("Null user!", user)
    }

    @Test
    fun updateOrInsertSuccessTest() {
        val user = userManager!!.update(mockUser("1"))
        Assert.assertNotNull("User need to be valid in this case!", user)
    }

    @Test
    fun updateOrInsertFailTest() {
        val user = userManager!!.update(mockUser("xxxx"))
        Assert.assertNull("User need to be null in this case!", user)
    }

    @Test
    fun update2UsersWithSuccessTest() {

        val user1 = mockUser("1")
        val user2 = mockUser("1")
        val userList = listOf(user1, user2)
        val userListUpdated = userManager!!.updateMany(userList)

        Assert.assertTrue("User updated list with valid identifier users, need to have same count of original user list.", userListUpdated.count() == userList.count())
    }

    @Test
    fun update2UsersWithPartialSuccessTest() {

        val user1 = mockUser("1")
        val user2 = mockUser("invalid")
        val userList = listOf(user1, user2)
        val userListUpdated = userManager!!.updateMany(userList)

        Assert.assertTrue("User updated list with partial valid identifier users, need to have count of valid user list.", userListUpdated.count() == 1)
    }

    @Test
    fun update2UsersWithFailTest() {

        val user1 = mockUser("invalid1")
        val user2 = mockUser("invalid2")
        val userList = listOf(user1, user2)
        val userListUpdated = userManager!!.updateMany(userList)

        Assert.assertTrue("User updated list with invalid identifier users, need to have count 0.", userListUpdated.count() == 0)
    }

    @Test
    fun deleteSuccessTest() {
        val user = userManager!!.delete("1")
        Assert.assertNotNull("Not null user!", user)
    }

    @Test
    fun deleteFailTest() {
        val user = userManager!!.delete("hshaexx")
        Assert.assertNull("Null user!", user)
    }

    @Test
    fun getUserAdapterTest() {
        val userAdapter = userManager!!.userAdapter
        Assert.assertEquals("Not null user!", userAdapter.javaClass, UserAdapterSpy::class.java)
    }

    @Test
    fun createInitialMockerTest() {
        val userList = userManager!!.createInitialMocker()
        Assert.assertTrue("User list need to have count > 0!", userList.count() > 0)
    }

    @After
    fun tearDown() {}

    // Auxiliar function to mock user
    private fun mockUser(identifier: String): User {
        val name = "User test"
        val profilePicture = null
        val nickname = "uset"
        val birthdate = Date()
        val rankingPosition = 1
        val email = "uset@uset.com"
        val phone = "123"
        val wins = 0
        val loses = 0
        val gender = User.Gender.MALE
        val category = null
        val status = User.Status.AVAILABLE
        val challengeSended = null
        val challengeReceived = null

        // Call
        val user = User(identifier, name, profilePicture, nickname, birthdate, rankingPosition, email, phone,
                wins, loses, gender, category, status, challengeSended, challengeReceived, emptyList())

        return user
    }
}