package com.nexte.nexte.Entities.User

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

// ChallengeSended and ChallengeReceived have Challenge ID if are done or nil if don't
open class User(@PrimaryKey var id: String = "",
                var name: String = "",
                var profilePicture: String? = null,
                var nickname: String = "",
                var birthDate: Date? = null,
                var rankingPosition: Int = 0,
                var email: String = "",
                var phone: String = "",
                var wins: Int = 0,
                var loses: Int = 0,
                var gender: String = Gender.MALE.value,
                var category: UserCategory? = null,
                var status: String = Status.AVAILABLE.value,
                var challengeSended: String? = null,
                var challengeReceived: String? = null): RealmObject() {
//    var latestGames: RealmList<Challenge> = RealmList()

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

    enum class ServerRequest(val request: Map<String, String>) {
        USERS(hashMapOf("route" to "users", "method" to "get")),
        USER(hashMapOf("route" to "user", "method" to "get")),
        UPDATE_USER(hashMapOf("route" to "user", "method" to "update")),
        LOGIN(hashMapOf("route" to "login", "method" to "post"))
    }
}

