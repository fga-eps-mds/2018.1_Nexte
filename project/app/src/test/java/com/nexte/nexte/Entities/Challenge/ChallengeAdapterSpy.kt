package com.nexte.nexte.Entities.Challenge

import java.util.*

class ChallengeAdapterSpy: ChallengeAdapter {

    override fun getAll(): List<Challenge> {
        val challengeList: MutableList<Challenge> = mutableListOf(mockChallenge(), mockChallenge(), mockChallenge(), mockChallenge())
        return challengeList.toList()
    }

    override fun get(identifier: String): Challenge? {
        if (identifier == "1") {
            return mockChallenge()
        } else {
            return null
        }
    }

    override fun updateOrInsert(challenge: Challenge): Challenge? {
        if (challenge.id == "1") {
            return mockChallenge()
        } else {
            return null
        }
    }

    override fun delete(identifier: String): Challenge? {
        if (identifier == "1") {
            return mockChallenge()
        } else {
            return null
        }
    }

    override fun getUserPlayedGames(userdId: String): List<Challenge> {
        return listOf(mockChallenge())
    }

    override fun getLastFiveChallenges(userdId: String): List<Challenge> {
        return listOf()
    }

    private fun mockChallenge(): Challenge {
        val id = "1"
        val challengerId = "1"
        val challengedId = "2"
        val challengeDate = Date()
        val status = Challenge.Status.CONFIRMED
        val stage = Challenge.Stage.Played(
                1,
                0,
                Date(2017, 11, 25),
                Challenge.Stage.Played.Game(8, 4),
                null, null, null, null, null
        )

        // Call
        val challenge = Challenge(id, challengerId, challengedId, challengeDate, status, stage)

        return challenge
    }
}