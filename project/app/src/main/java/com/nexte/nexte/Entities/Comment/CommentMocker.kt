package com.nexte.nexte.Entities.Comment

import com.nexte.nexte.Entities.User.UserManager
import java.util.*

class CommentMocker{

    private val commentTextMocks = listOf("Que jogo sensacional! Muita coisa ruim aconteceu mas eu sei que muita coisa ainda vai acontecer e por isso torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Tá na hora da revanche! As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Você é muito pato",
            "Arregou! Torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Eu Não imaginava esse placar. ",
            "Eu também acho isso. Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Mais seriedade",
            "Não vou mais jogar contigo.",
            "Cala a boca!")
    private var commentsId = 0

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

    private fun getUserIds(): List<String>{
        val users = UserManager().getAll()
        val userIds = mutableListOf<String>()

        for (user in users){
            userIds.add(user.id)
        }

        return userIds.toList()
    }

    fun generateComments(): List<Comment>{
        val commentsMutable = mutableListOf<Comment>()
        val userIds = getUserIds()
        var userIdIndex = 0

        for (commentMessage in commentTextMocks){
            val date = generateRandomDate()
            val id = (commentsId++).toString()
            val userId = userIds[userIdIndex++]
            val comment = Comment(date = date, id = id, userId = userId, comment = commentMessage)
            commentsMutable.add(comment)
        }

        return commentsMutable.toList()
    }
}