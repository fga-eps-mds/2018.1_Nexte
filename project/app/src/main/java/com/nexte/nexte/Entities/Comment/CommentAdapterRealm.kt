package com.nexte.nexte.Entities.Comment
import com.nexte.nexte.Entities.Challenge.Challenge
import io.realm.Realm

class CommentAdapterRealm : CommentAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): Comment? {

        return null

    }

    override fun getAll(): List<Comment> {


    }

    override fun updateOrInsert(challenge: Challenge): Challenge? {

        return null

    }

    override fun delete(identifier: String): Comment? {

        return null

    }

}