package com.nexte.nexte.Entities.User

import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class UserAdapterRealmTest {

    @Before
    fun setUp() {}

    @Test
    // TODO: Need to mock Realm.init() to can't call convertUserToUserRealm method
    fun convertUserToUserRealmTest() {
//        val userRealm = UserAdapterRealm().convertUserToUserRealm(mockUser("123"))
//        Assert.assertNotNull("User can't be null", userRealm)
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