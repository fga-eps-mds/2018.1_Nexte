package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.Challenge.Challenge
import io.realm.RealmObject

open class GameRealm(var gameChallenger: Int = 0,
                     var gameChallenged: Int = 0): RealmObject() {

    constructor(game: Challenge.Stage.Played.Game) : this() {
        gameChallenger = game.gameChallenger
        gameChallenged = game.gameChallenged
    }
}