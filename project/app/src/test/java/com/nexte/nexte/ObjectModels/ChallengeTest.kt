package com.nexte.nexte.ObjectModels

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class ChallengeTest {
    @Before
    fun setUp() {}

    @Test
    fun successStatusEnumTest() {

        // Prepare
        val statusWaiting = Challenge.Status.WAITING
        val statusConfirmed = Challenge.Status.CONFIRMED
        val statusProcessed = Challenge.Status.PROCESSED


        // Asserts
        Assert.assertNotNull("Status waiting is incorrect!", statusWaiting)
        Assert.assertNotNull("Status confirmed is incorrect!", statusConfirmed)
        Assert.assertNotNull("Status processed is incorrect!", statusProcessed)
    }

    @Test
    fun successUserTypeEnumTest() {

        // Prepare
        val userTypeChallenger = Challenge.UserType.CHALLENGER
        val userTypeChallenged = Challenge.UserType.CHALLENGED


        // Asserts
        Assert.assertNotNull("User type challenger is incorrect!", userTypeChallenger)
        Assert.assertNotNull("User type challenged is incorrect!", userTypeChallenged)
    }

    @Test
    fun successChallengeStagePlayedGameTest() {

        // Prepare
        val game = Challenge.Stage.Played.Game(1, 2)

        // Asserts
        Assert.assertEquals("Game of Challenger is incorrect!", game.gameChallenger, 1)
        Assert.assertEquals("Game of Challenged is incorrect!", game.gameChallenged, 2)
    }

    @Test
    fun successStatusChallengeScheduledConstructorTest() {
        // Call
        val challengeScheduled = Challenge.Stage.Scheduled()

        // Assert
        Assert.assertNotNull("Fail to constructor scheduled challenge!", challengeScheduled)
    }

    @Test
    fun successStatusChallengeCanceledConstructorTest() {
        // Prepare
        val reason = ""
        val userType = Challenge.UserType.CHALLENGER
        val date = Date()

        // Call
        val challengeCanceled = Challenge.Stage.Canceled(reason, userType, date)

        // Assert
        Assert.assertNotNull("Fail to constructor canceled challenge!", challengeCanceled)
        Assert.assertEquals("Cancel reason is incorrect!", reason, challengeCanceled.reason)
        Assert.assertEquals("User type is different!", userType, challengeCanceled.user)
        Assert.assertEquals("Date is invalid!", date, challengeCanceled.date)
    }

    @Test
    fun successStatusChallengePlayedConstructorTest() {
        // Prepare
        val setChallenger = 1
        val setChallenged = 0
        val date = Date()
        val firstGame = Challenge.Stage.Played.Game(7, 5)
        val secondGame = null
        val thirdGame = null
        val fourthGame = null
        val fifthGame = null
        val detail = null

        // Call
        val challengePlayed = Challenge.Stage.Played(setChallenger, setChallenged, date, firstGame, secondGame, thirdGame, fourthGame, fifthGame, detail)

        // Assert
        Assert.assertNotNull("Fail to constructor played challenge!", challengePlayed)
        Assert.assertEquals("Set challenger is incorrect!", setChallenger, challengePlayed.setChallenger)
        Assert.assertEquals("Set challenged is incorrect!", setChallenged, challengePlayed.setChallenged)
        Assert.assertEquals("Date is invalid!", date, challengePlayed.date)
        Assert.assertEquals("First game is different!", firstGame, challengePlayed.firstGame)
        Assert.assertEquals("Second game is different!", secondGame, challengePlayed.secondGame)
        Assert.assertEquals("Third game is different!", thirdGame, challengePlayed.thirdGame)
        Assert.assertEquals("Fourth game is different!", fourthGame, challengePlayed.fourthGame)
        Assert.assertEquals("Fifth game is different!", fifthGame, challengePlayed.fifthGame)
        Assert.assertEquals("Detail is incorrect!", detail, challengePlayed.detail)
    }

    @Test
    fun successStatusChallengePlayedChallengerWinnerTest() {
        // Prepare
        val setChallenger = 1
        val setChallenged = 0
        val firstGame = Challenge.Stage.Played.Game(7, 5)

        // Call
        val challengePlayed = Challenge.Stage.Played(setChallenger, setChallenged, Date(), firstGame, null, null, null, null, null)

        // Assert
        Assert.assertNotNull("Fail to constructor played challenge!", challengePlayed)
        Assert.assertEquals("Winner incorrect!", challengePlayed.winner, Challenge.UserType.CHALLENGER)
        Assert.assertEquals("Count of sets played is incorrect!", challengePlayed.setsPlayed, 1)
    }

    @Test
    fun successStatusChallengePlayedChallengedWinnerTest() {
        // Prepare
        val setChallenger = 0
        val setChallenged = 1
        val firstGame = Challenge.Stage.Played.Game(5, 7)

        // Call
        val challengePlayed = Challenge.Stage.Played(setChallenger, setChallenged, Date(), firstGame, null, null, null, null, null)

        // Assert
        Assert.assertNotNull("Fail to constructor played challenge!", challengePlayed)
        Assert.assertEquals("Winner incorrect!", challengePlayed.winner, Challenge.UserType.CHALLENGED)
        Assert.assertEquals("Count of sets played is incorrect!", challengePlayed.setsPlayed, 1)
    }

    @Test
    fun successStatusChallengePlayedQuantityOfSetsTest() {
        // Prepare
        val setChallenger = 2
        val setChallenged = 1
        val firstGame = Challenge.Stage.Played.Game(5, 7)
        val secondGame = Challenge.Stage.Played.Game(6, 3)
        val thirdGame = Challenge.Stage.Played.Game(6, 2)

        // Call
        val challengePlayed = Challenge.Stage.Played(setChallenger, setChallenged, Date(), firstGame, secondGame, thirdGame, null, null, null)

        // Assert
        Assert.assertNotNull("Fail to constructor played challenge!", challengePlayed)
        Assert.assertEquals("Count of sets played is incorrect!", challengePlayed.setsPlayed, 3)
    }

    @Test
    fun successChallengeConstructorTest() {
        // Prepare
        val id = "123"
        val challengerId = "123"
        val challengedId = "321"
        val challengeDate = Date()
        val status = Challenge.Status.PROCESSED
        val stage = Challenge.Stage.Scheduled()

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        // Assert
        Assert.assertNotNull("Fail to constructor challenge!", challenge)
        Assert.assertEquals("Challenge ID is incorrect!", "123", challenge.id)
        Assert.assertEquals("Challenger ID is incorrect!", "123", challenge.challengerId)
        Assert.assertEquals("Challenged ID is incorrect!", "321", challenge.challengedId)
        Assert.assertEquals("Date is invalid!", challengeDate, challenge.challengeDate)
        Assert.assertEquals("Status of challenge is different!", Challenge.Status.PROCESSED, challenge.status)
        Assert.assertEquals("Stage changed!", stage, challenge.stage)
    }

    @Test
    fun successChallengeWinnerChallengerTest() {
        // Prepare
        val id = "123"
        val challengerId = "123"
        val challengedId = "321"
        val challengeDate = Date()
        val status = Challenge.Status.PROCESSED
        val stage = this.createChallengePlayed(Challenge.UserType.CHALLENGER)

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        // Assert
        Assert.assertNotNull("Fail to constructor challenge!", challenge)
        Assert.assertEquals("Winner is incorrect", "123", challenge.winner)
    }

    @Test
    fun successChallengeWinnerChallengedTest() {
        // Prepare
        val id = "123"
        val challengerId = "123"
        val challengedId = "321"
        val challengeDate = Date()
        val status = Challenge.Status.PROCESSED
        val stage = this.createChallengePlayed(Challenge.UserType.CHALLENGED)

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        // Assert
        Assert.assertNotNull("Fail to constructor challenge!", challenge)
        Assert.assertEquals("Winner is incorrect", "321", challenge.winner)
    }

    @Test
    fun successChallengeWithoutWinnerTest() {
        // Prepare
        val id = "123"
        val challengerId = "123"
        val challengedId = "321"
        val challengeDate = Date()
        val status = Challenge.Status.PROCESSED
        val stage = this.createChallengeCanceled()

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        // Assert
        Assert.assertNotNull("Fail to constructor challenge!", challenge)
        Assert.assertEquals("Winner is incorrect", null, challenge.winner)
    }


    fun createChallengePlayed(userType: Challenge.UserType): Challenge.Stage.Played {
        var setChallenger: Int
        var setChallenged: Int
        var firstGame: Challenge.Stage.Played.Game
        if (userType == Challenge.UserType.CHALLENGER) {
            setChallenger = 1
            setChallenged = 0
            firstGame = Challenge.Stage.Played.Game(7, 5)
        } else {
            setChallenger = 0
            setChallenged = 1
            firstGame = Challenge.Stage.Played.Game(5, 7)
        }
        return Challenge.Stage.Played(setChallenger, setChallenged, Date(), firstGame, null, null, null, null, null)
    }

    fun createChallengeCanceled(): Challenge.Stage.Canceled {
        val reason = ""
        val userType = Challenge.UserType.CHALLENGER
        val date = Date()

        return Challenge.Stage.Canceled(reason, userType, date)
    }


    @After
    fun tearDown() {}
}