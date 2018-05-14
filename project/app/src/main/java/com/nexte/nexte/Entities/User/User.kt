package com.nexte.nexte.Entities.User

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import java.util.*

// ChallengeSended and ChallengeReceived have Challenge ID if are done or nil if don't
class User(val id: String,
           val name: String,
           val profilePicture: String?,
           val nickname: String,
           val birthDate: Date?,
           val rankingPosition: Int,
           val email: String,
           val phone: String,
           val wins: Int,
           val loses: Int,
           val gender: Gender,
           val category: UserCategory?,
           val status: Status,
           val challengeSended: Challenge?,
           val challengeReceived: Challenge?,
           var latestGames: List<Challenge>?) {

    val matches: Int
        get() = this.wins + this.loses


    // Enums
    enum class Gender(val identifier: Int, val value: String) {
        MALE(0, "Male"),
        FEMALE(1,"Female")
    }

    enum class Status(val identifier: Int, val value: String) {
        AVAILABLE(0, "Available"),
        INJURED(1, "Injured"),
        UNAVAILABLE(2, "Unavailable")
    }

    enum class ServerRequest(val request: Map<String, String>) {
        USERS(hashMapOf("route" to "users", "method" to "get")),
        USER(hashMapOf("route" to "user", "method" to "get")),
        UPDATE_USER(hashMapOf("route" to "user", "method" to "update")),
        LOGIN(hashMapOf("route" to "login", "method" to "post"))
    }
}

