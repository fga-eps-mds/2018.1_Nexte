package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.User.UserCategory.UserCategory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameRealmTest {

    @Before
    fun setUp() {}

    @Test
    fun successGameTest() {

        // Prepare
        val game = GameRealm(0, 6)

        // Asserts
        Assert.assertEquals("Game of Challenged is incorrect!", 6, game.gameChallenged)
        Assert.assertEquals("Game of Challenger is incorrect!", 0, game.gameChallenger)
    }

    @Test
    fun successSetMethodsTest() {

        // Prepare
        val game = GameRealm().apply {
            this.gameChallenged = 6
            this.gameChallenger = 0
        }

        // Asserts
        Assert.assertEquals("Game of Challenged is incorrect!", 6, game.gameChallenged)
        Assert.assertEquals("Game of Challenger is incorrect!", 0, game.gameChallenger)
    }

    @After
    fun tearDown() {}
}