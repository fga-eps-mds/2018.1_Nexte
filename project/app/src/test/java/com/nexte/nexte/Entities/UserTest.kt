package com.nexte.nexte.Entities

import com.nexte.nexte.Entities.User.User
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class UserTest {

    @Before
    fun setUp() {}

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val userRequest = User.ServerRequest.USER.request
        val usersRequest = User.ServerRequest.USERS.request
        val updateUserRequest = User.ServerRequest.UPDATE_USER.request
        val loginRequest = User.ServerRequest.LOGIN.request

        // Asserts
        Assert.assertEquals("User request is incorrect!", userRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Users request is incorrect!", usersRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Update user request is incorrect!", updateUserRequest.keys, setOf("route", "method"))
        Assert.assertEquals("Login request is incorrect!", loginRequest.keys, setOf("route", "method"))
    }

    @Test
    fun successStatusEnumTest() {

        // Prepare
        val statusAvailable = User.Status.AVAILABLE
        val statusInjured = User.Status.INJURED
        val statusUnavailable = User.Status.UNAVAILABLE

        // Asserts
        Assert.assertEquals("Status available are incorrect!", statusAvailable.value, "Available")
        Assert.assertEquals("Status injured are incorrect!", statusInjured.value, "Injured")
        Assert.assertEquals("Status unavailable are incorrect!", statusUnavailable.value, "Unavailable")
    }

    @Test
    fun successGenderEnumTest() {

        // Prepare
        val genderMale = User.Gender.MALE
        val genderFemale = User.Gender.FEMALE

        // Asserts
        Assert.assertEquals("Gender male are incorrect!", genderMale.value, "Male")
        Assert.assertEquals("Gender female are incorrect!", genderFemale.value, "Female")
    }



    @Test
    fun successUserConstructorTest() {
        // Prepate
        val id = "123"
        val name = "User test"
        val profilePicture = null
        val nickname = "uset"
        val birthdate = Date()
        val rankingPosition = 1
        val email = "uset@uset.com"
        val phone = "123"
        val wins = 0
        val loses = 0
        val gender = User.Gender.MALE.value
        val category = null
        val status = User.Status.AVAILABLE.value
        val challengeSended = null
        val challengeReceived = null

        // Call
        val user = User(id,
                name,
                profilePicture,
                nickname,
                birthdate,
                rankingPosition,
                email,
                phone,
                wins,
                loses,
                gender,
                category,
                status,
                challengeSended,
                challengeReceived)
//                RealmList())

        // Asserts
        Assert.assertEquals("User ID is invalid!", id, user.id)
        Assert.assertEquals("User name is different!", name, user.name)
        Assert.assertEquals("Nickname is invalid!", nickname, user.nickname)
        Assert.assertEquals("Birthdate is incorrect!", birthdate, user.birthDate)
        Assert.assertEquals("Ranking position is different!", rankingPosition, user.rankingPosition)
        Assert.assertEquals("Email is incorrect!", email, user.email)
        Assert.assertEquals("Phone is different!", phone, user.phone)
        Assert.assertEquals("Wins are different!", wins, user.wins)
        Assert.assertEquals("Loses are different!", loses, user.loses)
        Assert.assertEquals("Gender is incorrect!", gender, user.gender)
        Assert.assertEquals("Status is incorrect!", status, user.status)
        Assert.assertNull("Profile Picture isn't null!", user.profilePicture)
        Assert.assertNull("Category isn't null!", user.category)
        Assert.assertNull("Challenge Sended isn't null!", user.challengeSended)
        Assert.assertNull("Challenge Received isn't null!", user.challengeReceived)
//        Assert.assertEquals("Latest games aren't null!", 0, user.latestGames.count())
    }

    @Test
    fun successUserSetMethodsTest() {
        // Prepate
        val id = "123"
        val name = "User test"
        val profilePicture = null
        val nickname = "uset"
        val birthdate = Date()
        val rankingPosition = 1
        val email = "uset@uset.com"
        val phone = "123"
        val wins = 0
        val loses = 0
        val gender = User.Gender.MALE.value
        val category = null
        val status = User.Status.AVAILABLE.value
        val challengeSended = null
        val challengeReceived = null

        // Call
        val user = User().apply {
            this.id = id
            this.name = name
            this.profilePicture = profilePicture
            this.nickname = nickname
            this.birthDate = birthdate
            this.rankingPosition = rankingPosition
            this.email = email
            this.phone = phone
            this.wins = wins
            this.loses = loses
            this.gender = gender
            this.category = category
            this.status = status
            this.challengeSended = challengeSended
            this.challengeReceived = challengeReceived
        }

        // Asserts
        Assert.assertEquals("User ID is invalid!", id, user.id)
        Assert.assertEquals("User name is different!", name, user.name)
        Assert.assertEquals("Nickname is invalid!", nickname, user.nickname)
        Assert.assertEquals("Birthdate is incorrect!", birthdate, user.birthDate)
        Assert.assertEquals("Ranking position is different!", rankingPosition, user.rankingPosition)
        Assert.assertEquals("Email is incorrect!", email, user.email)
        Assert.assertEquals("Phone is different!", phone, user.phone)
        Assert.assertEquals("Wins are different!", wins, user.wins)
        Assert.assertEquals("Loses are different!", loses, user.loses)
        Assert.assertEquals("Gender is incorrect!", gender, user.gender)
        Assert.assertEquals("Status is incorrect!", status, user.status)
        Assert.assertNull("Profile Picture isn't null!", user.profilePicture)
        Assert.assertNull("Category isn't null!", user.category)
        Assert.assertNull("Challenge Sended isn't null!", user.challengeSended)
        Assert.assertNull("Challenge Received isn't null!", user.challengeReceived)
    }

    @Test
    fun successUserMatchesQuantityTest() {
        // Prepate
        val wins = 10
        val loses = 3
        val gender = User.Gender.MALE
        val status = User.Status.AVAILABLE

        // Call
        val user = User("", "", "", "", Date(), 0, "", "", wins, loses, gender.value, null, status.value, null, null)

        // Asserts
        Assert.assertEquals("Matches quantity are incorrect!", wins + loses, user.matches)
    }

    @After
    fun tearDown() {}
}