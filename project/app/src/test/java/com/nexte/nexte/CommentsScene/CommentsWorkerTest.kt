package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentsWorkerTest {

    private var worker: CommentsWorker? = null

    @Before
    fun setUp() {
        this.worker = CommentsWorker()
    }

    @Test
    fun successGetCommentsData(){
        //prepare
        val request = CommentsModel.GetCommentsRequest.Request()

        val comment1 = Comment("123456",
                "le123",
                "Boa!!", 1)
        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
                Date(),
                player4, 4  )


        //call
        this.worker?.getCommentsData(request, {response ->

            //assert
            assertEquals(comment1.comment, response.comments[0].comment)
            assertEquals(comment4.comment, response.comments[3].comment)

            assertEquals(player1.name, response.comments[0].author.name)
            assertEquals(player4.name, response.comments[3].author.name)

            assertEquals(comment1.commentId, response.comments[0].commentId)
            assertEquals(comment4.commentId, response.comments[3].commentId)
        })
    }

    @Test
    fun successSetNewComment() {
        //prepare
        val comment = "Jogo Fantástico"
        val request = CommentsModel.PublishCommentRequest.Request(comment)
        val today = Date()
        val author = CommentsModel.Player(UserSingleton.getUserInformations().name, 3)
        val newComment = CommentsModel.Comment(comment, today, author, 5)

        //call
        worker?.setNewComment(request, {response ->
            //assert
            assertEquals(response.newComment.author.name,newComment.author.name)
            assertEquals(response.newComment.comment, newComment.comment)
            assertEquals(response.newComment.commentId, newComment.commentId)
        })
    }

    @Test
    fun successSetComplaint() {
        val request = CommentsModel.ComplaintRequest.Request(
                3
        )

        //call
        worker?.sendComplaint(request, {response ->
            //assert
            assertEquals(response.serverResponse,200)
        })
    }

    @Test
    fun successGetToDeletComment() {
        //prepare
        val player1 = CommentsModel.Player("Lorrany", R.mipmap.ic_launcher)
        val player2 = CommentsModel.Player("Alexandre", R.mipmap.ic_launcher)
        val player3 = CommentsModel.Player("Larissa", R.mipmap.ic_launcher)
        val player4 = CommentsModel.Player("Letícia", R.mipmap.ic_launcher)

        val comment1 = CommentsModel.Comment("Nossa, esse jogo foi topzera",
                Date(),
                player1, 1)
        val comment2 = CommentsModel.Comment("Boa galera, vocês arrasaram",
                Date(),
                player2, 2)
        val comment3 = CommentsModel.Comment("Isso mesmo, man. Que jogão",
                Date(),
                player3, 3)
        val comment4 = CommentsModel.Comment("Uhuuul, lindos!!",
                Date(),
                player4, 4)

        val originalList: MutableList<CommentsModel.Comment> = mutableListOf(
                comment1,
                comment2,
                comment3,
                comment4)

        val position = 1

        val requestToDel = CommentsModel.DeleteCommentRequest.Request(position)

        //call
        worker?.getToDeleteComment(requestToDel) {response ->
            //assert
            assertEquals(originalList[0].author.name, response.delComments[0].author.name )
            assertEquals(originalList[2].author.name, response.delComments[1].author.name )

            assertEquals(originalList[0].author.photo, response.delComments[0].author.photo )
            assertEquals(originalList[2].author.photo, response.delComments[1].author.photo )

            assertEquals(originalList[0].comment, response.delComments[0].comment )
            assertEquals(originalList[2].comment, response.delComments[1].comment )

            assertEquals(originalList[0].commentId, response.delComments[0].commentId )
            assertEquals(originalList[2].commentId, response.delComments[1].commentId )
        }


    }




    @After
    fun tearDown() {
        this.worker = null
    }
}