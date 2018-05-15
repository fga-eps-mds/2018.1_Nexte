package com.nexte.nexte.Entities.Story
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import java.util.*
import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Like.Like

object StoryMocker{

    private val commentIds = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private val commentTextMocks = listOf("Que jogo sensacional! Muita coisa ruim aconteceu mas eu sei que muita coisa ainda vai acontecer e por isso torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Tá na hora da revanche! As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Você é muito pato",
            "Arregou! Torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Eu Não imaginava esse placar. ",
            "Eu também acho isso. Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Mais seriedade",
            "Não vou mais jogar contigo.",
            "Cala a boca!")
    private var likeId: Int = 0
    private var storiesId: Int = 0

    fun generateRandomStories(): List<Story>{
        val storiesAmount = randomNumber(0, 20)
        val storiesMutable = mutableListOf<Story>()
        for(counter in 0..storiesAmount){
            val story = generateRandomStory()
            storiesMutable.add(story)
        }
        val stories = storiesMutable.toList()
        return stories
    }

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

    fun generateRandomWinnerAndLoser(): List<User>{
        val users = UserManager().getAll()

        val winnerIndex = randomNumber(0, users.size - 1)
        var loserIndex = randomNumber(0, users.size - 1)
        while(winnerIndex == loserIndex){
            loserIndex = randomNumber(0, users.size - 1)
        }

        val winner = users[winnerIndex]
        val loser = users[loserIndex]

        val winnerAndLoser = listOf(winner, loser)
        return winnerAndLoser
    }

//    fun generateRandomComment(): Comment{
//        val commentIndex = randomNumber(0, commentTextMocks.size - 1)
//        val commentText = commentTextMocks[commentIndex]
//
//        val users = UserManager().getAll()
//        val userIndex = randomNumber(0, users.size - 1)
//        val userId = users[userIndex].id
//
//        val commentId = commentIds[commentIndex]
//
//        val date = generateRandomDate()
//
//        val comment = Comment(comment = commentText, id = commentId, userId = userId, date = date)
//        return comment
//    }
    fun generateRandomCommentId(): String{
        val commentIndex = randomNumber(0, commentTextMocks.size - 1)
        val commentId = commentIds[commentIndex]
        return commentId
    }

//    fun generateRandomLike(): Like{
//        val date = generateRandomDate()
//
//        val users = UserManager().getAll()
//        val userIndex = randomNumber(0, users.size - 1)
//        val userId = users[userIndex].id
//
//        val likeId = likeId++
//
//        val like = Like(date = date, id = likeId.toString(), userId = userId)
//
//        return like
//    }
    fun generateRandomLikeId(): String{
        val likeId = likeId++
        return likeId.toString()
    }

    fun generateRandomStory(): Story{
        //Id
        val storyId = storiesId++

        //Winner
        val winnerAndLoser = generateRandomWinnerAndLoser()
        val winnerUser = winnerAndLoser[0]
        val winnerResultRange = randomNumber(1, 5)
        val winner = StoryPlayer(userId = winnerUser.id, setResult = winnerResultRange)

        //Loser
        val loserUser = winnerAndLoser[1]
        val loserResultRange = randomNumber(0, (winnerResultRange - 1))
        val loser = StoryPlayer(userId = loserUser.id, setResult = loserResultRange)

        //Date
        val date = generateRandomDate()

        //Comments
//        val commentsAmount = randomNumber(0, 9)
//        val commentsMutable = mutableListOf<Comment>()
//        for (counter in 0..commentsAmount){
//            val comment = generateRandomComment()
//            commentsMutable.add(comment)
//        }
//        val comments = commentsMutable.toList()
        val commentsAmount = randomNumber(0, 9)
        val commentsMutable = mutableListOf<String>()
        for (counter in 0..commentsAmount){
            val comment = generateRandomCommentId()
            commentsMutable.add(comment)
        }
        val commentsId = commentsMutable.toList()

        //Likes
//        val likesAmount = randomNumber(0, 6)
//        val likesMutable = mutableListOf<Like>()
//        for (counter in 0..likesAmount){
//            val like = generateRandomLike()
//            likesMutable.add(like)
//        }
//        val likes = likesMutable.toList()
        //Likes
        val likesAmount = randomNumber(0, 6)
        val likesMutable = mutableListOf<String>()
        for (counter in 0..likesAmount){
            val like = generateRandomLikeId()
            likesMutable.add(like)
        }
        val likesId = likesMutable.toList()

        val story = Story(id = storyId.toString(), winner = winner, loser = loser, date = date, commentsId = commentsId, likesId = likesId)
        return story
    }
}