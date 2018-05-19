package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.Challenge.Challenge
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class PlayedRealmTest {

    @Before
    fun setUp() {}

    @Test
    fun successPlayedRealmTest() {

        // Prepare
        val playedRealm = PlayedRealm(3, 2, Date(),
                GameRealm(6, 0),
                GameRealm(2, 6),
                GameRealm(5, 7),
                GameRealm(6, 3),
                GameRealm(10, 8),
                "Jogão!")

        // Asserts
        Assert.assertEquals("Set challlenger is incorrect!", 3, playedRealm.setChallenger)
        Assert.assertEquals("Set challlenged is incorrect!", 2, playedRealm.setChallenged)
        Assert.assertNotNull("Date is null!", playedRealm.date)
        Assert.assertNotNull("First game is null!", playedRealm.firstGame)
        Assert.assertNotNull("Second game is null!", playedRealm.secondGame)
        Assert.assertNotNull("Third game is null!", playedRealm.thirdGame)
        Assert.assertNotNull("Fourth game is null!", playedRealm.fourthGame)
        Assert.assertNotNull("Fifth game is null!", playedRealm.fifthGame)
        Assert.assertEquals("Detail is invalid!", "Jogão!", playedRealm.detail)
    }

    @Test
    fun successSetMethodsTest() {

        // Prepare
        val playedRealm = PlayedRealm().apply {
            this.setChallenger = 3
            this.setChallenged = 2
            this.date = Date()
            this.firstGame = GameRealm(6, 0)
            this.secondGame = GameRealm(2, 6)
            this.thirdGame = GameRealm(5, 7)
            this.fourthGame = GameRealm(6, 3)
            this.fifthGame = GameRealm(10, 8)
            this.detail = "Jogão!"

        }

        // Asserts
        Assert.assertEquals("Set challlenger is incorrect!", 3, playedRealm.setChallenger)
        Assert.assertEquals("Set challlenged is incorrect!", 2, playedRealm.setChallenged)
        Assert.assertNotNull("Date is null!", playedRealm.date)
        Assert.assertNotNull("First game is null!", playedRealm.firstGame)
        Assert.assertNotNull("Second game is null!", playedRealm.secondGame)
        Assert.assertNotNull("Third game is null!", playedRealm.thirdGame)
        Assert.assertNotNull("Fourth game is null!", playedRealm.fourthGame)
        Assert.assertNotNull("Fifth game is null!", playedRealm.fifthGame)
        Assert.assertEquals("Detail is invalid!", "Jogão!", playedRealm.detail)
    }

    @Test
    fun successSecondConstructorTest() {

        // Prepare
        val played = Challenge.Stage.Played(3, 2, Date(),
                Challenge.Stage.Played.Game(6, 0),
                Challenge.Stage.Played.Game(2, 6),
                Challenge.Stage.Played.Game(5, 7),
                Challenge.Stage.Played.Game(6, 3),
                Challenge.Stage.Played.Game(10, 8),
                "Jogão!")

        // Call
        val playedRealm = PlayedRealm(played)

        // Asserts
        Assert.assertEquals("Set challlenger is incorrect!", 3, playedRealm.setChallenger)
        Assert.assertEquals("Set challlenged is incorrect!", 2, playedRealm.setChallenged)
        Assert.assertNotNull("Date is null!", playedRealm.date)
        Assert.assertNotNull("First game is null!", playedRealm.firstGame)
        Assert.assertNotNull("Second game is null!", playedRealm.secondGame)
        Assert.assertNotNull("Third game is null!", playedRealm.thirdGame)
        Assert.assertNotNull("Fourth game is null!", playedRealm.fourthGame)
        Assert.assertNotNull("Fifth game is null!", playedRealm.fifthGame)
        Assert.assertEquals("Detail is invalid!", "Jogão!", playedRealm.detail)
    }

    @After
    fun tearDown() {}
}