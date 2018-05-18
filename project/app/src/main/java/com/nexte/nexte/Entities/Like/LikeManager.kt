package com.nexte.nexte.Entities.Like

class LikeManager (private val likeAdapter: LikeAdapter = LikeAdapterRealm()) {

    fun get(identifier: String): Like? {
            return likeAdapter.get(identifier)
        }

        fun getAll(): List<Like> {
            return likeAdapter.getAll()
        }

        fun update(like: Like): Like? {
            return likeAdapter.updateOrInsert(like)
        }

    fun updateMany(likes: List<Like>): List<Like> {
        val newLikes: MutableList<Like> = mutableListOf()
        for (like in likes) {
            val updatedComment = likeAdapter.updateOrInsert(like)
            updatedComment?.let {
                newLikes.add(it)
            }
        }
        return newLikes.toList()
    }

    fun delete(identifier: String): Like? {
        return likeAdapter.delete(identifier)
    }

    fun createInitialMocker(): List<Like> {

        val likesInMocker = LikeMocker().generateLikes()
        val insertedLikes: MutableList<Like> = mutableListOf()

        for (like in likesInMocker) {

            val insertedLike = likeAdapter.updateOrInsert(like)
            insertedLike?.let {
                insertedLikes.add(it)
            }
        }

        return insertedLikes.toList()

    }




}