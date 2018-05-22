package com.nexte.nexte.Entities.Challenge

class ChallengeManager(val challengeAdapter: ChallengeAdapter = ChallengeAdapterRealm()) {

        fun get(identifier: String): Challenge? {
            return challengeAdapter.get(identifier)
        }

        fun getAll(): List<Challenge> {
            return challengeAdapter.getAll()
        }

        fun update(challenge: Challenge): Challenge? {
            return challengeAdapter.updateOrInsert(challenge)
        }

        fun updateMany(challenges: List<Challenge>): List<Challenge> {
            val newChallenges: MutableList<Challenge> = mutableListOf()
            for(challenge in challenges) {
                val updatedChallenges = challengeAdapter.updateOrInsert(challenge)
                updatedChallenges?.let {
                    newChallenges.add(it)
                }
            }
            return newChallenges.toList()
        }

        fun delete(identifier: String): Challenge? {
            return challengeAdapter.delete(identifier)
        }

        fun createInitialMocker(): List<Challenge> {
            val challengesInMocker = ChallengeMocker.generateChalleges()
            val insertedChallenges: MutableList<Challenge> = mutableListOf()
            for (challenge in challengesInMocker) {
                val insertedChallenge = challengeAdapter.updateOrInsert(challenge)
                insertedChallenge?.let {
                    insertedChallenges.add(it)
                }
            }
            return insertedChallenges.toList()
        }

}