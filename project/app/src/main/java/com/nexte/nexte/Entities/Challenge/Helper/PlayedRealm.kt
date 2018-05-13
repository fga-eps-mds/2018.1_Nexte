package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.Challenge.Challenge
import io.realm.RealmObject
import java.util.*

open class PlayedRealm(var setChallenger: Int = 0,
                       var setChallenged: Int = 0,
                       var date: Date? = null,
                       var firstGame: GameRealm? = null,
                       var secondGame: GameRealm? = null,
                       var thirdGame: GameRealm? = null,
                       var fourthGame: GameRealm? = null,
                       var fifthGame: GameRealm? = null,
                       var detail: String? = ""): RealmObject() {

    constructor(played: Challenge.Stage.Played): this() {
        setChallenger = played.setChallenger
        setChallenged = played.setChallenged
        date = played.date
        firstGame = GameRealm(played.firstGame)
        played.secondGame?.let {
            secondGame = GameRealm(it)
        }
        played.thirdGame?.let {
            thirdGame = GameRealm(it)
        }
        played.fourthGame?.let {
            fourthGame = GameRealm(it)
        }
        played.fifthGame?.let {
            fifthGame = GameRealm(it)
        }
        detail = played.detail
    }
}