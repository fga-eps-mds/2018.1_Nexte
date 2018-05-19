package com.nexte.nexte.Entities.Story
import com.nexte.nexte.Entities.Comment.CommentAdapter
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Like.LikeAdapter
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import java.util.*
import com.nexte.nexte.Entities.User.UserAdapter

object StoryMocker{

    private val commentIds = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    private var likeId: Int = 0
    private var storiesId: Int = 0
    private val commentTextMocks = listOf("Que jogo sensacional! Muita coisa ruim aconteceu mas eu sei que muita coisa ainda vai acontecer e por isso torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Tá na hora da revanche! As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Você é muito pato",
            "Arregou! Torço muito para que muita coisa continue acontecendo porque no final é tudo o que importa, não é mesmo? Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Eu Não imaginava esse placar. ",
            "Eu também acho isso. Eu sei meu amigo. As coisas mudam e tudo tem que mudar. A vida é assim!!!",
            "Mais seriedade",
            "Não vou mais jogar contigo.",
            "Cala a boca!")
    var userAdapter: UserAdapter? = null
    var likeAdapter: LikeAdapter? = null
    var commentAdapter: CommentAdapter? = null

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

    private fun generateRandomWinnerAndLoser(): List<User>{
        //val users = if (userAdapter == null) UserManager().getAll() else UserManager(userAdapter = userAdapter)

        var users = listOf<User>()
        if (userAdapter == null){
            users = UserManager().getAll()
        }else{
            users = UserManager(userAdapter = userAdapter!!).getAll()
        }

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

    private fun generateRandomCommentId(): String{
        val commentIndex = randomNumber(0, commentTextMocks.size - 1)
        val commentId = commentIds[commentIndex]
        return commentId
    }

    private fun generateRandomLikeId(): String{
        val likeId = likeId++
        return likeId.toString()
    }

    private fun generateRandomStory(): Story{
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
        val commentsAmount = randomNumber(0, 9)
        var commentsId = listOf<String>()

        if(commentAdapter == null){
            commentsId = CommentManager().createCommentsIds(commentsAmount)
        }else{
            commentsId = CommentManager(commentAdapter = commentAdapter!!).createCommentsIds(commentsAmount)
        }

        //Likes
        val likesAmount = randomNumber(0, 6)
        var likesId = listOf<String>()

        if(likeAdapter == null){
            likesId = LikeManager().createLikesIds(likesAmount)
        }else{
            likesId = LikeManager(likeAdapter = likeAdapter!!).createLikesIds(likesAmount)
        }


        val story = Story(id = storyId.toString(), winner = winner, loser = loser, date = date, commentsId = commentsId, likesId = likesId)
        return story
    }
}