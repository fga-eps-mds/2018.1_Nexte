package com.nexte.nexte.Entities.User

import java.util.*

class UserAdapterSpy: UserAdapter {

    override fun getAll(): List<User> {
        val userList: MutableList<User> = mutableListOf(mockUser(), mockUser(), mockUser(), mockUser(), mockUser(), mockUser(), mockUser(), mockUser(), mockUser())
        return userList.toList()
    }

    override fun get(identifier: String): User? {
        if (identifier == "1") {
            return mockUser()
        }
        else if(identifier == "2"){
            return mockAnotherUser()
        }
        else {
            return null
        }
    }

    override fun updateOrInsert(user: User): User? {
        if (user.id == "1") {
            return mockUser()
        } else {
            return null
        }
    }

    override fun delete(identifier: String): User? {
        if (identifier == "1") {
            return mockUser()
        }
        else {
            return null
        }
    }

    private fun mockUser(): User {
        val id = "1"
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
        val user = User(id, name, profilePicture, nickname, birthdate, rankingPosition, email, phone,
                wins, loses, gender, category, status, challengeSended, challengeReceived, emptyList())

        return user
    }

    private fun mockAnotherUser(): User {
        val id = "2"
        val name = "User New test"
        val profilePicture = null
        val nickname = "uset2yay"
        val birthdate = Date()
        val rankingPosition = 1
        val email = "use2t@uset_2.com"
        val phone = "321"
        val wins = 2
        val loses = 2
        val gender = User.Gender.FEMALE
        val category = null
        val status = User.Status.INJURED
        val challengeSended = null
        val challengeReceived = null

        // Call
        val user = User(id, name, profilePicture, nickname, birthdate, rankingPosition, email, phone,
                wins, loses, gender, category, status, challengeSended, challengeReceived, emptyList())

        return user
    }

}