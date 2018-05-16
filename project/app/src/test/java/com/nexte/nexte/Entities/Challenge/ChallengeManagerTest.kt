package com.nexte.nexte.Entities.Challenge

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class ChallengeManagerTest {
    var challengeManager: ChallengeManager? = null

    @Before
    fun setUp() {
        val challengeAdapterSpy = ChallengeAdapterSpy()
        challengeManager = ChallengeManager(challengeAdapterSpy)
    }

    @Test
    fun getAllChallengeTest() {
        val challengeList = challengeManager!!.getAll()
        Assert.assertTrue("Challenge list need to have count bigger or equals than 0.", challengeList.count() >= 0)
    }

    @Test
    fun getOneChallengeWithSuccessTest() {
        val challenge = challengeManager!!.get("1")
        Assert.assertNotNull("Not null challenge!", challenge)
    }

    @Test
    fun getOneChallengeWithFailTest() {
        val challenge = challengeManager!!.get("aasdas")
        Assert.assertNull("Null challenge!", challenge)
    }

    @Test
    fun updateOrInsertSuccessTest() {
        val challenge = challengeManager!!.update(mockChallenge("1"))
        Assert.assertNotNull("Challenge need to be valid in this case!", challenge)
    }

    @Test
    fun updateOrInsertFailTest() {
        val challenge = challengeManager!!.update(mockChallenge("xxxx"))
        Assert.assertNull("Challenge need to be null in this case!", challenge)
    }

    @Test
    fun update2ChallengesWithSuccessTest() {

        val challenge1 = mockChallenge("1")
        val challenge2 = mockChallenge("1")
        val challengeList = listOf(challenge1, challenge2)
        val challengeListUpdated = challengeManager!!.updateMany(challengeList)

        Assert.assertTrue("Challenge updated list with valid identifier challenges, need to have same count of original challenge list.", challengeListUpdated.count() == challengeList.count())
    }

    @Test
    fun update2ChallengeWithPartialSuccessTest() {

        val challenge1 = mockChallenge("1")
        val challenge2 = mockChallenge("invalid")
        val challengeList = listOf(challenge1, challenge2)
        val challengeListUpdated = challengeManager!!.updateMany(challengeList)

        Assert.assertTrue("Challenge updated list with partial valid identifier challenges, need to have count of valid challenge list.", challengeListUpdated.count() == 1)
    }

    @Test
    fun update2ChallengeWithFailTest() {

        val challenge1 = mockChallenge("invalid1")
        val challenge2 = mockChallenge("invalid2")
        val challengeList = listOf(challenge1, challenge2)
        val challengeListUpdated = challengeManager!!.updateMany(challengeList)

        Assert.assertTrue("Challenge updated list with invalid identifier challenges, need to have count 0.", challengeListUpdated.count() == 0)
    }

    @Test
    fun deleteSuccessTest() {
        val challenge = challengeManager!!.delete("1")
        Assert.assertNotNull("Not null challenge!", challenge)
    }

    @Test
    fun deleteFailTest() {
        val challenge = challengeManager!!.delete("hshaexx")
        Assert.assertNull("Null challenge!", challenge)
    }

    @Test
    fun getChallengeAdapterTest() {
        val challengeAdapter = challengeManager!!.challengeAdapter
        Assert.assertEquals("Not null challenge adapter!", challengeAdapter.javaClass, ChallengeAdapterSpy::class.java)
    }

    @Test
    fun createInitialMockerTest() {
        val challengeList = challengeManager!!.createInitialMocker()
        Assert.assertTrue("Challenge list need to have count > 0!", challengeList.count() > 0)
    }

    @After
    fun tearDown() {}

    // Auxiliar function to mock user
    private fun mockChallenge(identifier: String): Challenge {
        val id = identifier
        val challengerId = "1"
        val challengedId = "1"
        val challengeDate = Date()
        val status = Challenge.Status.CONFIRMED
        val stage = Challenge.Stage.Scheduled()

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        return challenge
    }
}