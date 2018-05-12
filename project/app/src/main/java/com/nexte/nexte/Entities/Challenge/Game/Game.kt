package com.nexte.nexte.Entities.Challenge.Game

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class GameRealm(@PrimaryKey var id: String = "",
                     var gameChallenger: Int = -1,
                     var gameChallenged: Int = -1): RealmObject()