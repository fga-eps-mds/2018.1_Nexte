package com.nexte.nexte.Entities.User

import com.nexte.nexte.HelpForRealm
import io.realm.Realm
import io.realm.RealmResults
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class UserAdapterRealmTest: HelpForRealm() {

    var userAdapterRealm: UserAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithUser()
        userAdapterRealm = UserAdapterRealm()
    }

    @Test
    fun testGet(){
        val user = userAdapterRealm?.get("9")
        Assert.assertNotNull(user)
        Assert.assertEquals("Rafael Pardal", user?.name)
        Assert.assertEquals(30, user?.wins)
        Assert.assertEquals(23, user?.loses)
    }

    @Test
    fun testGetAll(){
        userAdapterRealm?.realm
        val users = userAdapterRealm?.getAll()

        Assert.assertNotNull(users)
    }

    @Test
    fun testUpdateOrInsert(){
        val id = "9"
        val name = "Rafael Pardal2"
        val profilePicture = null
        val nickname = "Pardal"
        val birthdate = Date(1990, 10, 12)
        val rankingPosition = 10
        val email = "pardal@nexte.com"
        val phone = "130"
        val wins = 30
        val loses = 23
        val gender = User.Gender.MALE
        val category = null
        val challengeSended = null
        val challengeReceived = null

        val user = mockUser("9")

        val userInserted = this.userAdapterRealm?.updateOrInsert(user)

        Assert.assertNotNull(userInserted)
    }

    @Test
    fun testDelete(){
        val user = this.userAdapterRealm?.delete("9")

        Assert.assertNull(user)
    }

    @Test
    fun testGetSetRealm() {
        this.userAdapterRealm?.realm = Realm.getDefaultInstance()
        org.junit.Assert.assertNotNull(this.userAdapterRealm?.realm)
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