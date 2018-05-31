package com.nexte.nexte.Entities.Challenge

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlayersListMockerTest {

    @Before
    fun setUp() {}

    @Test
    fun generateChallengeTest() {
        val challengeList = ChallengeMocker.generateChalleges()
        Assert.assertTrue("Challenge list need to have count bigger or equals than 40.", challengeList.count() >= 40)
    }

    @After
    fun tearDown() {}
}