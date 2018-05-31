@file:Suppress("DEPRECATION")

package com.nexte.nexte.Entities.Comment
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapter
import com.nexte.nexte.Entities.User.UserManager
import java.util.*


/**
 * Singleton responsible for mock comment data
 */
object CommentMocker{

    /**
     * @property commentTextMocks contains a list of mock comments
     */
    private val commentTextMocks = listOf("Que jogo sensacional! " +
            "Muita coisa ruim aconteceu mas eu sei que muita coisa ainda " +
            "vai acontecer e por isso torço muito para que muita coisa" +
            " continue acontecendo porque no final é tudo o que importa," +
            " não é mesmo? Eu sei meu amigo." +
            " As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Tá na hora da revanche!" +
            " As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Você é muito pato",
            "Arregou! Torço muito para que muita coisa continue" +
            " acontecendo porque no final é tudo o que importa, não é mesmo?" +
            " Eu sei meu amigo. As coisas mudam" +
            " e tudo tem que mudar. A vida é assim!!!",
            "Eu Não imaginava esse placar. ",
            "Eu também acho isso. Eu sei meu amigo." +
            " As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Mais seriedade",
            "Não vou mais jogar contigo.",
            "Cala a boca!")
    var commentsId = 0
    var userAdapter: UserAdapter? = null
    var commentAdapter: CommentAdapter? = null
    var allComments = mutableListOf<Comment>()

    /**
     * Function to generate random numbers
     */
    private fun randomNumber(from: Int, to: Int): Int{
        val random = Random()
        println("BOUND: " + (to - from))

        val bound = to - from

        return if (bound > 0){
            random.nextInt(bound) + from
        }else{
            from
        }
    }

    /**
     * Function to generate random dates for all comments
     */
    private fun generateRandomDate(): Date {
        val year = randomNumber(2013, 2018)
        val dayOfYear = randomNumber(1, 30)
        val monthOfYear = randomNumber(1, 12)
        return Date(year, monthOfYear, dayOfYear)
    }

    /**
     * Function to generate comments Ids
     */
    private fun getCommentIds(amount: Int): List<String>{

        val commentsIds = mutableListOf<String>()
        for(counter in 0 until amount){
            commentsIds.add(commentsId++.toString())
        }

        return commentsIds.toList()
    }

    /**
     * Function to generate users Ids
     */
    private fun getUsersIds(usersAmount: Int) : List<String> {
        val users: List<User> = if(userAdapter == null){
            UserManager().getAll()
        }else{
            UserManager(userAdapter = userAdapter!!).getAll()
        }

        val usersIds = mutableListOf<String>()

        println(users.size)
        println(usersAmount)
        for (counter in 0 until usersAmount){
            println(counter)
            usersIds.add(users[counter].id)
        }

        return usersIds
    }

    /**
     * Function to generate a date for a comment
     */
    private fun getDates(amount: Int): List<Date>{
        val dates = mutableListOf<Date>()

        for (counter in 0 until amount){
            dates.add(generateRandomDate())
        }

        return dates
    }

    /**
     * Function to get content of a comment
     */
    private fun getComments(amount: Int): List<String>{
        val comments = mutableListOf<String>()

        for(counter in 0 until amount){
            comments.add(commentTextMocks[counter])
        }

        return comments
    }

    /**
     * Function to generate random data for comments which contains ids,
     * userIds, dates and a comment from a user
     */
    fun generateComments(amount: Int): List<String>{
        val ids = getCommentIds(amount)
        val userIds = getUsersIds(amount)
        val dates = getDates(amount)
        val comments = getComments(amount)

        for (counter in 0 until amount){
            val comment = Comment(ids[counter], userIds[counter], comments[counter], dates[counter])
            allComments.add(comment)
        }

        return ids
    }
}