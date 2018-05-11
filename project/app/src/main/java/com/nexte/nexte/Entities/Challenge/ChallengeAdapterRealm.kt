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
                    this.status = it.status.name
                    this.stage =  it.stage.toString()
            }
        }

        return challengeRealm!!
    }

    private fun convertChallengeRealmToChallenge(challengeRealm: ChallengeRealm): Challenge? {

        var challenge: Challenge? = null

//        challengeRealm.let {
//            challenge = Challenge(it.id,
//                                  it.challengerId,
//                                  it.challengedId,
//                                  it.challegeDate,
//                                  Challenge.Status.CONFIRMED,
//                                  Challenge.Stage.Scheduled()
//            )
//        }

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