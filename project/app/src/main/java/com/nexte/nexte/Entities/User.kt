package com.nexte.nexte.Entities

import java.util.*

// ChallengeSended and ChallengeReceived have Challenge ID if are done or nil if don't
class User(val id: String,
           val name: String,
           val profilePicture: String?,
           val nickname: String,
           val birthDate: Date,
           val rankingPosition: Int,
           val email: String,
           val phone: String,
           val wins: Int,
           val loses: Int,
           val gender: Gender,
           val category: Category?,
           val status: Status,
           val challengeSended: String?,
           val challengeReceived: String?,
           val latestGames: List<String>?) {

    val matches: Int
        get() = this.wins + this.loses


    // Enums
    enum class Gender(val value: String) {
        MALE("Male"),
        FEMALE("Female")
    }

    enum class Status(val value: String) {
        AVAILABLE("Available"),
        INJURED("Injured"),
        UNAVAILABLE("Unavailable")
    }

    class Category(val id: String,
                   val name: String)

    enum class ServerRequest(val request: Map<String, String>) {
        USERS(hashMapOf("route" to "users", "method" to "get")),
        USER(hashMapOf("route" to "user", "method" to "get")),
        UPDATE_USER(hashMapOf("route" to "user", "method" to "update")),
        LOGIN(hashMapOf("route" to "login", "method" to "post"))
    }
}

