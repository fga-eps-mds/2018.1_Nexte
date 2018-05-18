package com.nexte.nexte.Entities.Like

import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeRealm
import io.realm.Realm
import io.realm.kotlin.where

class LikeAdapterRealm : LikeAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): Like? {

        val likeRealm = realm.where<LikeRealm>().equalTo("id", identifier).findFirst()
        return convertLikeRealmToLike(LikeRealm)
    }

    override fun getAll(): List<Like> {

        val challengeRealmResults = realm.where<LikeRealm>().findAll()
        return convertListLikeRealmToLikeList(likeRealmResults)
    }

    private fun convertLikeRealmToLike(likeRealm: LikeRealm) : Like {

    }

}