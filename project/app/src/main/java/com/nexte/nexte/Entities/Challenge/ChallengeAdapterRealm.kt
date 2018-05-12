package com.nexte.nexte.Entities.Challenge

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

class ChallengeAdapterRealm: ChallengeAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun delete(identifier: String): Challenge? {

        val challengeRealm = realm.where<ChallengeRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val user = convertChallengeRealmToChallenge(challengeRealm.first())
        challengeRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return user
    }

    override fun getAll(): List<Challenge> {

        val challengeRealmResults = realm.where<ChallengeRealm>().findAll()
        return convertListChallengeRealmToChallengeList(challengeRealmResults)
    }


    override fun updateOrInsert(challenge: Challenge): Challenge? {

        convertChallengeToChallengeRealm(challenge)?.let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return challenge
        }

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
                    this.stageScheduledRealm = if (it.stage == Challenge.Stage.Scheduled) it.stage else null
                    this.stageCancelledRealm = if (it.stage == Challenge.Stage.Canceled) it.stage else null
                    this.stagePlayedRealm = if (it.stage == Challenge.Stage.Played) it.stage else null
            }
        }

        return challengeRealm
    }

    private fun convertChallengeRealmToChallenge(challengeRealm: ChallengeRealm): Challenge? {

        var challenge: Challenge? = null

        challengeRealm?.let {

            var stage: Challenge.Stage? = null

            if (challengeRealm.stagePlayedRealm != null) {
                challengeRealm.stagePlayedRealm?.let {

                    stage = Challenge.Stage.Played(it.setChallenger,
                            it.setChallenged,
                            it.date!!,
                            Challenge.Stage.Played.Game(it.firstGame!!.gameChallenger, it.firstGame!!.gameChallenged),
                            Challenge.Stage.Played.Game(it.secondGame!!.gameChallenger, it.secondGame!!.gameChallenged),
                            Challenge.Stage.Played.Game(it.thirdGame!!.gameChallenger, it.thirdGame!!.gameChallenged),
                            Challenge.Stage.Played.Game(it.fourthGame!!.gameChallenger, it.fourthGame!!.gameChallenged),
                            Challenge.Stage.Played.Game(it.fifthGame!!.gameChallenger, it.fifthGame!!.gameChallenged),
                            it.detail)
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