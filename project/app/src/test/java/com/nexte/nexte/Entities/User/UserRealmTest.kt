package com.nexte.nexte.Entities.User

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class UserRealmTest {

    @Before
    fun setUp() {}

    @Test
    fun successUserRealmConstructorTest() {
        // Prepare
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
        val gender = User.Gender.MALE
        val category = null
        val status = User.Status.AVAILABLE
        val challengeSended = null
        val challengeReceived = null

        // Call
        val userRealm = UserRealm(id,
                name,
                profilePicture,
                nickname,
                birthdate,
                rankingPosition,
                email,
                phone,
                wins,
                loses,
                gender.name,
                category,
                status.name,
                challengeSended,
                challengeReceived)

        // Asserts
        Assert.assertEquals("User ID is invalid!", id, userRealm.id)
        Assert.assertEquals("User name is different!", name, userRealm.name)
        Assert.assertEquals("Nickname is invalid!", nickname, userRealm.nickname)
        Assert.assertEquals("Birthdate is incorrect!", birthdate, userRealm.birthDate)
        Assert.assertEquals("Ranking position is different!", rankingPosition, userRealm.rankingPosition)
        Assert.assertEquals("Email is incorrect!", email, userRealm.email)
        Assert.assertEquals("Phone is different!", phone, userRealm.phone)
        Assert.assertEquals("Wins are different!", wins, userRealm.wins)
        Assert.assertEquals("Loses are different!", loses, userRealm.loses)
        Assert.assertEquals("Gender is incorrect!", gender, User.Gender.valueOf(userRealm.gender))
        Assert.assertEquals("Status is incorrect!", status, User.Status.valueOf(userRealm.status))
        Assert.assertNull("Profile Picture isn't null!", userRealm.profilePicture)
        Assert.assertNull("Category isn't null!", userRealm.category)
        Assert.assertNull("Challenge Sended isn't null!", userRealm.challengeSended)
        Assert.assertNull("Challenge Received isn't null!", userRealm.challengeReceived)
    }

    @Test
    fun successUserRealmSetMethodsTest() {
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
        val gender = User.Gender.MALE
        val category = null
        val status = User.Status.AVAILABLE
        val challengeSended = null
        val challengeReceived = null

        // Call
        val userRealm = UserRealm().apply {
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
            this.gender = gender.name
            this.category = category
            this.status = status.name
            this.challengeSended = challengeSended
            this.challengeReceived = challengeReceived
        }

        // Asserts
        Assert.assertEquals("User ID is invalid!", id, userRealm.id)
        Assert.assertEquals("User name is different!", name, userRealm.name)
        Assert.assertEquals("Nickname is invalid!", nickname, userRealm.nickname)
        Assert.assertEquals("Birthdate is incorrect!", birthdate, userRealm.birthDate)
        Assert.assertEquals("Ranking position is different!", rankingPosition, userRealm.rankingPosition)
        Assert.assertEquals("Email is incorrect!", email, userRealm.email)
        Assert.assertEquals("Phone is different!", phone, userRealm.phone)
        Assert.assertEquals("Wins are different!", wins, userRealm.wins)
        Assert.assertEquals("Loses are different!", loses, userRealm.loses)
        Assert.assertEquals("Gender is incorrect!", gender, User.Gender.valueOf(userRealm.gender))
        Assert.assertEquals("Status is incorrect!", status, User.Status.valueOf(userRealm.status))
        Assert.assertNull("Profile Picture isn't null!", userRealm.profilePicture)
        Assert.assertNull("Category isn't null!", userRealm.category)
        Assert.assertNull("Challenge Sended isn't null!", userRealm.challengeSended)
        Assert.assertNull("Challenge Received isn't null!", userRealm.challengeReceived)
    }

    @After
    fun tearDown() {}
}