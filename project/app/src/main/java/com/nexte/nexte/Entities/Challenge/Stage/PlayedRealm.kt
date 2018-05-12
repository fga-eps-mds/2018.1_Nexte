package com.nexte.nexte.Entities.Challenge.Stage

import com.nexte.nexte.Entities.Challenge.Game.GameRealm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class PlayedRealm(@PrimaryKey var id: String = "",
                       var setChallenger: Int = -1,
                       var setChallenged: Int = -1,
                       var date: Date? = null,
                       var firstGame: GameRealm? = null,
                       var secondGame: GameRealm? = null,
                       var thirdGame: GameRealm? = null,
                       var fourthGame: GameRealm? = null,
                       var fifthGame: GameRealm? = null,
                       var detail: String = ""): RealmObject()