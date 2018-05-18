package com.nexte.nexte.Entities.Like
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapter
import com.nexte.nexte.Entities.User.UserManager
import java.util.*

object LikeMocker{

    val likesMocks = mutableListOf<Like>()
    private var likeId = 0
    var likeAdapter: LikeAdapter? = null
    var userAdapter: UserAdapter? = null


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

    private fun getUsersIds(usersAmount: Int) : List<String> {
        var users = listOf<User>()

        if(userAdapter == null){
            users = UserManager().getAll()
        }else{
            users = UserManager(userAdapter = userAdapter!!).getAll()
        }

        val usersIds = mutableListOf<String>()

        println(users.size)
        println(usersAmount)
        for (counter in 0..(usersAmount - 1)){
            println(counter)
            usersIds.add(users[counter].id)
        }

        return usersIds
    }

    private fun getLikeIds(amount: Int): List<String>{
        val likesIds = mutableListOf<String>()

        for (counter in 0..(amount - 1)){
            likesIds.add(likeId++.toString())
        }

        return likesIds
    }

    private fun getDates(amount: Int): List<Date>{
        val dates = mutableListOf<Date>()

        for (counter in 0..(amount - 1)){
            dates.add(generateRandomDate())
        }

        return dates
    }

    fun generateLikes(likesAmount: Int): List<String>{
        val ids = getLikeIds(likesAmount)
        val dates = getDates(likesAmount)
        val usersIds = getUsersIds(likesAmount)

        for (counter in 0..(usersIds.size - 1)){
            val like = Like(ids[counter], usersIds[counter], dates[counter])
            likesMocks.add(like)
        }

        return usersIds
    }


}