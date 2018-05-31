package com.nexte.nexte.Entities.Like
import io.realm.Realm
import io.realm.kotlin.where

class LikeAdapterRealm : LikeAdapter {

    var realm: Realm = Realm.getDefaultInstance()

    override fun get(identifier: String): Like? {

        val likeRealm = realm.where<LikeRealm>().equalTo("id", identifier).findFirst()
        return convertLikeRealmToLike(likeRealm!!)
    }

    override fun getAll(): List<Like> {

        val likeRealmResults = realm.where<LikeRealm>().findAll()
        return convertListLikeRealmToLikeList(likeRealmResults)
    }

    override fun updateOrInsert(like: Like): Like? {
        convertLikeToLikeRealm(like).let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return like
        }
    }


    override fun delete(identifier: String): Like? {

        return null
    }


    private fun convertLikeRealmToLike(likeRealm: LikeRealm) : Like {

        val id = likeRealm.id

        val userId = likeRealm.userId

        val date = likeRealm.date

        return Like(id = id, userId = userId, date = date)
    }

    private fun convertListLikeRealmToLikeList(likeRealm: List<LikeRealm>) : List<Like> {
        val likes: MutableList<Like> = mutableListOf()
        for(currentLikeRealm in likeRealm) {
            convertLikeRealmToLike(currentLikeRealm).let {
                likes.add(it)
            }
        }
        return likes.toList()
    }

    private fun convertLikeToLikeRealm(like: Like) : LikeRealm {

        val id = like.id

        val userId = like.id

        val date = like.date

        return LikeRealm(id = id, userId = userId, date = date)
    }
}
