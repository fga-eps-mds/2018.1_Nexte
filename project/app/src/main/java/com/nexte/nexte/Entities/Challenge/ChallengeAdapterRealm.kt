package com.nexte.nexte.Entities.Challenge

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class ChallengeAdapterRealm: ChallengeAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun delete(identifier: String): Challenge? {

        val challengeRealm = realm.where<ChallengeRealm>().equalTo("id", identifier).findFirst()
        return convertChallengeRealmToChallenge(challengeRealm!!)
    }

    override fun getAll(): List<Challenge> {

        val challengeRealmResults = realm.where<ChallengeRealm>().findAll()
        return convertListChallengeRealmToChallengeList(challengeRealmResults)
    }

    override fun updateOrInsert(challenge: Challenge): Challenge? {

        val challenge: Challenge? = null
        return challenge
    }

    override fun get(identifier: String): Challenge? {
        val challenge: Challenge? = null
        return challenge
    }

    private fun convertChallengeToChallengeRealm(challenge: Challenge): ChallengeRealm? {

        var challengeRealm: ChallengeRealm? = null

        challenge.let {
            challengeRealm = ChallengeRealm().apply {
                    this.id = it.id
                    this.challengerId = it.challengerId
                    this.challengedId = it.challengedId
                    this.challegeDate = it.challengeDate
            }
        }

        return challengeRealm!!
    }

    private fun convertChallengeRealmToChallenge(challengeRealm: ChallengeRealm): Challenge? {

        var challenge: Challenge? = null

        challengeRealm?.let {

            var status: Challenge.Status? = null
            var stage: Challenge.Stage? = null

            if (challengeRealm.stagePlayedRealm != null) {
                challengeRealm.stagePlayedRealm?.let {
                    stage = Challenge.Stage.Played(it.setChallenger,
                            it.setChallenged,
                            it.date!!,
                            Challenge.Stage.Played.Game()
                }
            } else if (challengeRealm.stageCancelledRealm != null) {
                challengeRealm.stageCancelledRealm?.let {
                    stage = Challenge.Stage.Canceled(it.reason,
                            Challenge.UserType.valueOf(it.userType),
                            it.date!!)
                }
            } else {
                  stage = Challenge.Stage.Scheduled()
            }

            challenge = Challenge(it.id,
                    it.challengerId,
                    it.challengedId,
                    it.challegeDate,
                    Challenge.Status.valueOf(it.status),
                    stage!!)
        }

        return challenge
    }

    private fun convertListChallengeRealmToChallengeList(challengeRealmResults: RealmResults<ChallengeRealm>): List<Challenge> {

        val challenges: MutableList<Challenge> = mutableListOf()

        for(challengeRealm in challengeRealmResults) {
            convertChallengeRealmToChallenge(challengeRealm)?.let {

            }
        }

        return challenges
    }

}