package com.nexte.nexte.Entities.Challenge

import io.realm.Realm

class ChallengeAdapterRealm: ChallengeAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun delete(identifier: String): Challenge? {

        val challengerRealm = realm.where<ChallengeRealm>().equalTo("id", identifier).findFirst()
        return Challenge()
    }

    override fun getAll(): List<Challenge> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateOrInsert(challenge: Challenge): Challenge? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(identifier: String): Challenge? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun convertChallengeToChallengeRealm(challenge: Challenge): ChallengeRealm? {

    }

    private fun convertChallengeRealmToChallenge(challengeRealm: ChallengeRealm): Challenge? {

    }
}