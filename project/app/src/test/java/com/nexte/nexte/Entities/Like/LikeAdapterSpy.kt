package com.nexte.nexte.Entities.Like
import java.util.*

class LikeAdapterSpy: LikeAdapter {

    override fun delete(identifier: String): Like? {
        if (identifier == "1") {
            return mockLike()
        } else {
            return null
        }
    }

    override fun get(identifier: String): Like? {
        if (identifier == "1") {
            return mockLike()
        } else {
            return null
        }
    }

    override fun getAll(): List<Like> {
        val likeList: MutableList<Like> = mutableListOf(mockLike(), mockLike(), mockLike(), mockLike())
        return likeList.toList()
    }

    override fun updateOrInsert(like: Like): Like? {
        if (like.id == "1") {
            return mockLike()
        } else {
            return null
        }
    }

    private fun mockLike(): Like {
        val id: String? = "1"
        val userId: String? = "2"
        val date: Date? = Date()

        val like = Like(id, userId, date)

        return like
    }
}