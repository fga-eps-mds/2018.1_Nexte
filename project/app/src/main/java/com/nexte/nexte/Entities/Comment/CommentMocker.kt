package com.nexte.nexte.Entities.Comment
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapter
import com.nexte.nexte.Entities.User.UserManager
import java.util.*

object CommentMocker{

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

    private fun generateRandomDate(): Date {
        val year = randomNumber(2013, 2018)
        val dayOfYear = randomNumber(1, 30)
        val monthOfYear = randomNumber(1, 12)
        val date = Date(year, monthOfYear, dayOfYear)
        return date
    }

    private fun getCommentIds(amount: Int): List<String>{

        val commentsIds = mutableListOf<String>()
        for(counter in 0..amount - 1){
            commentsIds.add(commentsId++.toString())
        }

        return commentsIds.toList()
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
        for (counter in 0..usersAmount - 1){
            println(counter)
            usersIds.add(users[counter].id)
        }

        return usersIds
    }

    private fun getDates(amount: Int): List<Date>{
        val dates = mutableListOf<Date>()

        for (counter in 0..amount - 1){
            dates.add(generateRandomDate())
        }

        return dates
    }

    private fun getComments(amount: Int): List<String>{
        val comments = mutableListOf<String>()

        for(counter in 0..amount - 1){
            comments.add(commentTextMocks[counter])
        }

        return comments
    }

    fun generateComments(amount: Int): List<String>{
        val ids = getCommentIds(amount)
        val userIds = getUsersIds(amount)
        val dates = getDates(amount)
        val comments = getComments(amount)

        for (counter in 0..amount - 1){
            val comment = Comment(ids[counter], userIds[counter], comments[counter], dates[counter])
            allComments.add(comment)
        }

        return ids
    }
}