package com.nexte.nexte.Entities.Challenge

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class ChallengeRealmTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testGetsAndSets(){
        var challengeRealm = ChallengeRealm()
        challengeRealm = ChallengeRealm(id = "1", challengedId = "2", challengerId = "2",
                status = "1", challegeDate = Date(), stageCancelledRealm = null, stagePlayedRealm = null)
        challengeRealm.id = "1"
        challengeRealm.challengedId = "2"
        challengeRealm.challengerId = "2"
        challengeRealm.status = "1"
        challengeRealm.challegeDate = Date()
        challengeRealm.stageCancelledRealm = null
        challengeRealm.stagePlayedRealm = null
        Assert.assertEquals(challengeRealm.id, "1")
        Assert.assertEquals(challengeRealm.challengedId, "2")
        Assert.assertEquals(challengeRealm.challengerId, "2")
        Assert.assertEquals(challengeRealm.status, "1")
        Assert.assertNull(challengeRealm.stageCancelledRealm)
        Assert.assertNull(challengeRealm.stagePlayedRealm)
    }

    @After
    fun tearDown() {
    }
}