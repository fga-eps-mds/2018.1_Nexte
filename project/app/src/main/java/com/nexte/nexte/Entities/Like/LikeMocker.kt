package com.nexte.nexte.Entities.Like
import java.util.*

object LikeMocker{

    private val likesMocks = listOf<Like>()
    private var likeId = 0
    var likeAdapter: LikeAdapter? = null


    private fun randomNumber(from: Int, to: Int): Int{
        val random = Random()
        println("BOUND: " + (to - from))

        val bound = to - from

        if (bound > 0){
            return random.nextInt(bound) + from
        }else{
            return from
        }
    }

    private fun generateRandomDate(): Date{
        val year = randomNumber(2013, 2018)
        val dayOfYear = randomNumber(1, 30)
        val monthOfYear = randomNumber(1, 12)
        val date = Date(year, monthOfYear, dayOfYear)
        return date
    }


    private fun getUserIds(): List<String?> {
        val users = LikeManager().getAll()
        val userIds = mutableListOf<String?>()

        for (likes in users){
            userIds.add(likes.id)
        }

        return userIds.toList()
    }

    fun generateLikes(): List<Like>{
        val likes = listOf<Like>()
        val userIds = getUserIds()
        var userIdIndex = 0

        for (like in likesMocks){
            val date = generateRandomDate()
            val id = (likeId++).toString()
            val userId = userIds[userIdIndex++]
        }

        return likes.toList()
    }


}