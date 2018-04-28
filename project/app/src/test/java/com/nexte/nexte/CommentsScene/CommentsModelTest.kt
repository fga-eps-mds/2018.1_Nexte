package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentsModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun successRequest(){
        //prepare
        val testRequest = "identifier"

        //call
        val request = CommentsModel.Request(testRequest)

        //assert
        assertEquals(testRequest, request.request)
    }

    @Test
    fun successResponse(){
        //prepare
        val player1 = CommentsModel.Player("Larissa", 1)
        val player2 = CommentsModel.Player("Alexandre", 2)


        val comment1 = CommentsModel.Comment("Muito bom galera",
                                            Date(),
                                            player1)
        val comment2 = CommentsModel.Comment("Nossa, foi top mesmo",
                                            Date(),
                                            player2)
        val commentsList = mutableListOf(comment1, comment2)

        //call

        val response = CommentsModel.Response(commentsList)

        //assert
        assertEquals(player1.name, response.comments[0].author.name)
        assertEquals(player2.name, response.comments[1].author.name)

        assertEquals(player1.photo, response.comments[0].author.photo)
        assertEquals(player2.photo, response.comments[1].author.photo)

        assertEquals(comment1.comment, response.comments[0].comment)
        assertEquals(comment2.comment, response.comments[1].comment)

    }

    @Test
    fun successViewModel(){
        //prepare
        val namePlayer1 = "Larissa"
        val namePlayer2 = "Alexandre"

        val comment1 = "Muito bom galera"
        val comment2 = "Nossa, foi top mesmo"

        val dateComment1 = "11/04/1999"
        val dateComment2 = "12/15/1998"

        val profilePic1 = 1
        val profilePic2 = 2

        val commentFormatted1 = CommentsModel.CommentFormatted(comment1,
                                                               dateComment1,
                                                               namePlayer1,
                                                               profilePic1)
        val commentFormatted2 = CommentsModel.CommentFormatted(comment2,
                                                               dateComment2,
                                                               namePlayer2,
                                                               profilePic2)

        val commentsFormated = mutableListOf<CommentsModel.CommentFormatted>(commentFormatted1,
                                                                             commentFormatted2)

        //call
        val viewModel = CommentsModel.ViewModel(commentsFormated)

        //assert
        assertEquals(namePlayer1, viewModel.commentsFormatted[0].username)
        assertEquals(namePlayer2, viewModel.commentsFormatted[1].username)

        assertEquals(comment1, viewModel.commentsFormatted[0].comment)
        assertEquals(comment2, viewModel.commentsFormatted[1].comment)

        assertEquals(dateComment1, viewModel.commentsFormatted[0].commentDate)
        assertEquals(dateComment2, viewModel.commentsFormatted[1].commentDate)

        assertEquals(profilePic1, viewModel.commentsFormatted[0].profilePic)
        assertEquals(profilePic2, viewModel.commentsFormatted[1].profilePic)

        assertEquals(commentFormatted1, viewModel.commentsFormatted[0])
        assertEquals(commentFormatted2, viewModel.commentsFormatted[1])


    }

    @Test
    fun successPlayer() {
        //prepare
        val name = "userName"
        val photo = 1

        //call
        val player = CommentsModel.Player(name, photo)

        //assert
        assertEquals(name, player.name)
        assertEquals(photo, player.photo)

    }

    @Test
    fun successComment() {
        //prepare
        val text = "Jogo top!!!!"
        val date = Date()
        val name = "userName"
        val photo = 1
        val player = CommentsModel.Player(name, photo)

        //call
        val comment = CommentsModel.Comment(text, date, player)

        //assert
        assertEquals(text, comment.comment)
        assertEquals(date, comment.date)
        assertEquals(player, comment.author)
    }

    @Test
    fun successCommentFormatted() {
        //prepare
        val name = "userName"
        val comment = "Muito Bom!!"
        val commentDate = "25/04/2018"
        val pic = 1

        //call
        val commentFormatted = CommentsModel.CommentFormatted(comment, commentDate, name, pic)

        //assert
        assertEquals(name, commentFormatted.username)
        assertEquals(comment, commentFormatted.comment)
        assertEquals(commentDate, commentFormatted.commentDate)
        assertEquals(pic, commentFormatted.profilePic)
    }

    @After
    fun tearDown() {
    }
}