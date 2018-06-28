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
        val likeRealm = realm.where<LikeRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val like = convertLikeRealmToLike(likeRealm.first())
        likeRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return like
    }

    override fun getLikesFromStory(likesIds: List<String>): List<Like>? {
        var likesMutable = mutableListOf<Like>()
        for(likeId in likesIds){
            val likeRealm = realm.where<LikeRealm>().equalTo("id", likeId).findFirst()
            if (likeRealm != null) {
                convertLikeRealmToLike(likeRealm)?.let {
                    likesMutable.add(it)
                }
            } else {
                //Do nothing
            }
        }
        return likesMutable.toList()
    }


    fun convertLikeRealmToLike(likeRealm: LikeRealm?) : Like? {

        var like: Like? = null

        likeRealm?.let {
            val id = it.id
            val userId = it.userId
            val date = it.date
            like = Like(id = id, userId = userId, date = date)
        }
        return like
    }

    fun convertLikeToLikeRealm(like: Like?) : LikeRealm? {

        var likeRealm: LikeRealm? = null
        like?.let {
            val id = it.id
            val userId = it.id
            val date = it.date
            likeRealm = LikeRealm(id = id, userId = userId, date = date)
        }

        return likeRealm
    }

    fun convertListLikeRealmToLikeList(likeRealm: List<LikeRealm>) : List<Like> {
        val likes: MutableList<Like> = mutableListOf()

        if (likeRealm.size > 0) {
            for (currentLikeRealm in likeRealm) {
                convertLikeRealmToLike(currentLikeRealm)?.let {
                    likes.add(it)
                }
            }
        }
        return likes.toList()
    }
}
