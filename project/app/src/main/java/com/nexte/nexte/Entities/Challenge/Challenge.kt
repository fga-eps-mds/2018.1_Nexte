package com.nexte.nexte.Entities.Challenge

import java.util.*

class Challenge(val id: String,
                val challengerId: String,
                val challengedId: String,
                val challengeDate: Date,
                val status: Status,
                val stage: Stage) {
    val winner: String?
        get() {
            if (stage is Stage.Played) {
                return if (stage.winner == UserType.CHALLENGER) challengerId else challengedId
            }
            return null
        }

    enum class Status { WAITING, CONFIRMED, PROCESSED }
    enum class UserType { CHALLENGER, CHALLENGED }

    sealed class Stage {
        class Scheduled: Stage()
        class Canceled(val reason: String,
                       val user: UserType,
                       val date: Date): Stage()
        class Played(val setChallenger: Int,
                     val setChallenged: Int,
                     val date: Date,
                     val firstGame: Game,
                     val secondGame: Game?,
                     val thirdGame: Game?,
                     val fourthGame: Game?,
                     val fifthGame: Game?,
                     val detail: String?): Stage() {

            val winner: UserType
                get() {
                    return if (setChallenger > setChallenged) UserType.CHALLENGER else UserType.CHALLENGED
                }

            val setsPlayed: Int
                get() = this.setChallenger + this.setChallenged

            class Game(val gameChallenger: Int?,
                       val gameChallenged: Int?)
        }
    }

    enum class ServerRequest(val request: Map<String, String>) {
        CHALLENGES(hashMapOf("route" to "challenges", "method" to "get")),
        SEND_CHALLENGE(hashMapOf("route" to "challenge", "method" to "post")),
        SEND_RESULT_CHALLENGE(hashMapOf("route" to "challenge", "method" to "post")),
        UPDATE_CHALLENGE(hashMapOf("route" to "edit-challenge", "method" to "update")),
        CANCEL_CHALLENGE(hashMapOf("route" to "cancel-challenge", "method" to "post"))
    }
}