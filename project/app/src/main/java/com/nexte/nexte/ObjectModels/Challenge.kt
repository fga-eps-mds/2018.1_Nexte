package com.nexte.nexte.ObjectModels

import java.util.*

data class Challenge(val id: String,
                     val challengerId: String,
                     val challengedId: String,
                     val challengeDate: Date,
                     val status: Status,
                     val stage: Stage) {
    val winner: String
        get() {
            return ""
        }

    enum class Status { WAITING, CONFIRMED, PROCESSED }
    enum class UserType { CHALLENGER, CHALLENGED }

    sealed class Stage {
        class Scheduled()
        class Canceled(val reason: String,
                       val user: UserType,
                       val date: Date)
        class Played(val winner: UserType,
                     val setChallenger: Int,
                     val setChallenged: Int,
                     val date: Date,
                     val firstGame: Game,
                     val secondGame: Game?,
                     val thirdGame: Game?,
                     val fourthGame: Game?,
                     val fifthGame: Game?,
                     val detail: String?) {

            val setsPlayed: Int
                get() = this.setChallenger + this.setChallenged

            data class Game(val gameChallenger: Int,
                            val gameChallenged: Int)
        }
    }
}