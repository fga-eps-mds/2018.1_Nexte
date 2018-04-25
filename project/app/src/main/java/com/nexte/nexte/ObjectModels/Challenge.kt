package com.nexte.nexte.ObjectModels

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

            class Game(val gameChallenger: Int,
                       val gameChallenged: Int)
        }
    }
}