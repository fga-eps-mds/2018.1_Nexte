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
    fun successFirstRequest(){
        //prepare

        //call
        val request = CommentsModel.GetCommentsRequest.Request()

        //assert
        assertNotNull(request)
    }

    @Test
    fun successSecondRequest(){
        //prepare
        val testRequest = "identifier"

        //call
        val request = CommentsModel.PublishCommentRequest.Request(testRequest)

        //assert
        assertEquals(testRequest, request.commentToPost)
    }

    @Test
    fun successSecondRequestSet(){
        //prepare
        val newTestRequest = "newComment"

        //call
        val request = CommentsModel.PublishCommentRequest.Request("comment")
        request.commentToPost = newTestRequest

        //assert
        assertEquals(request.commentToPost, newTestRequest)
    }


    @Test
    fun successFirstResponse(){
        //prepare
        val player1 = CommentsModel.Player("Larissa", 1)
        val player2 = CommentsModel.Player("Alexandre", 2)


        val comment1 = CommentsModel.Comment("Muito bom galera",
                                            Date(),
                                            player1, 1)
        val comment2 = CommentsModel.Comment("Nossa, foi top mesmo",
                                            Date(),
                                            player2, 2)
        val commentsList = mutableListOf(comment1, comment2)

        //call

        val response = CommentsModel.GetCommentsRequest.Response(commentsList)

        //assert
        assertEquals(player1.name, response.comments[0].author.name)
        assertEquals(player2.name, response.comments[1].author.name)

        assertEquals(player1.photo, response.comments[0].author.photo)
        assertEquals(player2.photo, response.comments[1].author.photo)

        assertEquals(comment1.comment, response.comments[0].comment)
        assertEquals(comment2.comment, response.comments[1].comment)

        assertEquals(comment1.commentId, response.comments[0].commentId)
        assertEquals(comment2.commentId, response.comments[1].commentId)
    }

    @Test
    fun successFirstResponseSet(){
        //prepare
        val player = CommentsModel.Player("Larissa", 1)
        val comment = mutableListOf(CommentsModel.Comment("Muito bom galera",
                Date(),
                player, 2))
        val newComment = mutableListOf(CommentsModel.Comment("Boa galera",
                Date(),
                player, 1))

        //call
        val response = CommentsModel.GetCommentsRequest.Response(comment)
        response.comments = newComment

        //assert
        assertEquals(response.comments, newComment)
    }

    @Test
    fun successTestConstructor(){
        //prepare and call
        val model = CommentsModel()
        val firstRequest = CommentsModel.GetCommentsRequest()
        val secondRequest = CommentsModel.PublishCommentRequest()
        val thirdRequest = CommentsModel.ComplaintRequest()
        //assert
        assertNotNull(model)
        assertNotNull(firstRequest)
        assertNotNull(secondRequest)
        assertNotNull(thirdRequest)
    }

    @Test
    fun successSecondResponse(){
        //prepare
        val player = CommentsModel.Player("Larissa", 1)
        val comment = CommentsModel.Comment("Muito bom galera",
                Date(),
                player, 3)

        //call
        val response = CommentsModel.PublishCommentRequest.Response(comment)

        //assert
        assertEquals(player.name, response.newComment.author.name)
        assertEquals(player.photo, response.newComment.author.photo)
        assertEquals(comment.comment, response.newComment.comment)
        assertEquals(comment.commentId, response.newComment.commentId)
    }

    @Test
    fun successTestPlayerSetter(){
        //prepare
        val player = CommentsModel.Player("Larissa", 1)
        val newName = "gabriel"
        val newPhoto = 2

        //call
        player.name = newName
        player.photo = newPhoto

        //assert
        assertEquals(player.name, newName)
        assertEquals(player.photo, newPhoto)
    }

    @Test
    fun successSecondResponseSet(){
        //prepare
        val player = CommentsModel.Player("Larissa", 1)
        val comment = CommentsModel.Comment("Muito bom galera",
                Date(),
                player, 2)
        val newComment = CommentsModel.Comment("Boa galera",
                Date(),
                player, 1)

        //call
        val response = CommentsModel.PublishCommentRequest.Response(comment)
        response.newComment = newComment

        //assert
        assertEquals(response.newComment, newComment)
    }

    @Test
    fun successSecondViewModel(){
        //prepare
        val namePlayer = "Larissa"
        val comment = "Muito bom galera"
        val dateComment = "11/04/1999"
        val profilePic = 1
        val commentFormatted = CommentsModel.CommentFormatted(comment,
                                                               dateComment,
                                                               namePlayer,
                                                               profilePic)

        //call
        val viewModel = CommentsModel.PublishCommentRequest.ViewModel(commentFormatted)

        //assert
        assertEquals(namePlayer, viewModel.newCommentFormatted.username)
        assertEquals(comment, viewModel.newCommentFormatted.comment)
        assertEquals(dateComment, viewModel.newCommentFormatted.commentDate)
        assertEquals(profilePic, viewModel.newCommentFormatted.profilePic)
    }

    @Test
    fun successSecondViewModelSet(){
        //prepare
        val newComments = CommentsModel.CommentFormatted("Test",
                "28/10/1998",
                "gabriel",
                1)

        //call
        val viewModel = CommentsModel.PublishCommentRequest.ViewModel(
                CommentsModel.CommentFormatted("comment",
                        "11/01/1990",
                        "teste",
                        1)
        )
        viewModel.newCommentFormatted = newComments

        //assert
        assertEquals(viewModel.newCommentFormatted, newComments)
    }


    @Test
    fun successFirstViewModel(){
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

        val commentsFormatted = mutableListOf(commentFormatted1,
                commentFormatted2)

        //call
        val viewModel = CommentsModel.GetCommentsRequest.ViewModel(commentsFormatted)

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
    fun successFirstViewModelSet(){
        //prepare
        val newComments = mutableListOf(CommentsModel.CommentFormatted("Test",
        "28/10/1998",
        "gabriel",
        1))

        //call
        val viewModel = CommentsModel.GetCommentsRequest.ViewModel(
                mutableListOf(CommentsModel.CommentFormatted("comment",
                        "11/01/1990",
                        "teste",
                        1))
        )
        viewModel.commentsFormatted = newComments

        //assert
        assertEquals(viewModel.commentsFormatted, newComments)
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
        val id = 1
        val player = CommentsModel.Player(name, photo)

        //call
        val comment = CommentsModel.Comment(text, date, player, id)

        //assert
        assertEquals(text, comment.comment)
        assertEquals(date, comment.date)
        assertEquals(player, comment.author)
        assertEquals(player.name, comment.author.name)
        assertEquals(player.photo, comment.author.photo)
        assertEquals(id, comment.commentId)
    }

    @Test
    fun testSetOnComments(){
        //prepare
        val text = "Jogo top!!!!"
        val date = Date()
        val name = "userName"
        val photo = 1
        val id = 1
        val player = CommentsModel.Player(name, photo)

        //call
        val comment = CommentsModel.Comment("Oloko meu", Date(), CommentsModel.Player(
                "gabs", 2
        ), 2)
        comment.author = player
        comment.comment = text
        comment.date = date
        comment.commentId = id
        //assert
        assertEquals(comment.comment, text)
        assertEquals(comment.author.photo, player.photo)
        assertEquals(comment.author.name, player.name)
        assertEquals(comment.date, date)
        assertEquals(comment.commentId, id)
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
        assertNotNull(commentFormatted)
        assertEquals(name, commentFormatted.username)
        assertEquals(comment, commentFormatted.comment)
        assertEquals(commentDate, commentFormatted.commentDate)
        assertEquals(pic, commentFormatted.profilePic)
    }

    @Test
    fun setComment(){
        //prepare
        val name = "userName"
        val comment = "Muito Bom!!"
        val commentDate = "25/04/2018"
        val pic = 1
        val newComment = "comment"
        //call
        val commentFormatted = CommentsModel.CommentFormatted(comment, commentDate, name, pic)
        commentFormatted.comment = newComment

        //assert
        assertEquals(newComment, commentFormatted.comment)
    }

    @Test
    fun setCommentDate(){
        //prepare
        val name = "userName"
        val comment = "Muito Bom!!"
        val commentDate = "25/04/2018"
        val pic = 1
        val newDate = "22/02/2009"
        //call
        val commentFormatted = CommentsModel.CommentFormatted(comment, commentDate, name, pic)
        commentFormatted.commentDate = newDate

        //assert
        assertEquals(newDate, commentFormatted.commentDate)
    }

    @Test
    fun setCommentUsername(){
        //prepare
        val name = "userName"
        val comment = "Muito Bom!!"
        val commentDate = "25/04/2018"
        val pic = 1
        val newUsername = "gabrielalbino"
        //call
        val commentFormatted = CommentsModel.CommentFormatted(comment, commentDate, name, pic)
        commentFormatted.username = newUsername

        //assert
        assertEquals(newUsername, commentFormatted.username)
    }

    @Test
    fun setCommentProfilePic(){
        //prepare
        val name = "userName"
        val comment = "Muito Bom!!"
        val commentDate = "25/04/2018"
        val pic = 1
        val newPic = 2
        //call
        val commentFormatted = CommentsModel.CommentFormatted(comment, commentDate, name, pic)
        commentFormatted.profilePic = newPic

        //assert
        assertEquals(newPic, commentFormatted.profilePic)
    }

    @Test
    fun successComplaintRequest(){
        //prepare
        val commentId = 3

        //call
        val request = CommentsModel.ComplaintRequest.Request(commentId)

        //assert
        assertEquals(request.commentID, commentId)
    }

    @Test
    fun successComplaintResponse(){
        //prepare
        val errorValue = 2

        //call
        val response = CommentsModel.ComplaintRequest.Response(errorValue)

        //assert
        assertEquals(response.serverResponse, errorValue)

    }

    @Test
    fun successComplaintViewModel(){
        //prepare
        val message = "Sucesso!"

        //call
        val commentsModel = CommentsModel.ComplaintRequest.ViewModel(message)

        //assert
        assertEquals(commentsModel.alertMessage, message)
    }

    @Test
    fun successComplaintSetters(){
        val message = "Sucesso!"
        val commentsModel = CommentsModel.ComplaintRequest.ViewModel("")
        val errorValue = 2
        val response = CommentsModel.ComplaintRequest.Response(0)
        val commentId = 3
        val request = CommentsModel.ComplaintRequest.Request(
                commentId
        )

        //call
        response.serverResponse = errorValue
        request.commentID = commentId
        commentsModel.alertMessage = message

        //assert
        assertEquals(response.serverResponse, errorValue)
        assertEquals(request.commentID, commentId)
        assertEquals(commentsModel.alertMessage, message)
    }

    @Test
    fun successDeleteComment() {
        //prepare
        //call

        val testDeleteComment = CommentsModel.DeleteCommentRequest()

        //assert

        assertNotNull(testDeleteComment)
    }

    @Test
    fun DeleteCommentRequest() {
        //prepare
        val position = 5

        //call
        val testRequest = CommentsModel.DeleteCommentRequest.Request(position)

        //assert
        assertEquals(position, testRequest.commentIdentifier)
    }

    @Test
    fun DeleteCommentResponse() {
        //prepare
        val player1 = CommentsModel.Player("Larissa", 1)
        val player2 = CommentsModel.Player("Alexandre", 2)


        val comment1 = CommentsModel.Comment("Muito bom galera",
                Date(),
                player1, 1)
        val comment2 = CommentsModel.Comment("Nossa, foi top mesmo",
                Date(),
                player2, 2)
        val commentsList = mutableListOf(comment1, comment2)

        //call

        val response = CommentsModel.DeleteCommentRequest.Response(commentsList)

        //assert
        assertEquals(player1.name, response.delComments[0].author.name)
        assertEquals(player2.name, response.delComments[1].author.name)

        assertEquals(player1.photo, response.delComments[0].author.photo)
        assertEquals(player2.photo, response.delComments[1].author.photo)

        assertEquals(comment1.comment, response.delComments[0].comment)
        assertEquals(comment2.comment, response.delComments[1].comment)

        assertEquals(comment1.commentId, response.delComments[0].commentId)
        assertEquals(comment2.commentId, response.delComments[1].commentId)


    }

    @Test
    fun DeleteCommentViewModel() {
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

        val commentsFormatted = mutableListOf(commentFormatted1,
                commentFormatted2)

        //call
        val viewModel = CommentsModel.DeleteCommentRequest.ViewModel(commentsFormatted)

        //assert
        assertEquals(namePlayer1, viewModel.delCommentsFormatted[0].username)
        assertEquals(namePlayer2, viewModel.delCommentsFormatted[1].username)

        assertEquals(comment1, viewModel.delCommentsFormatted[0].comment)
        assertEquals(comment2, viewModel.delCommentsFormatted[1].comment)

        assertEquals(dateComment1, viewModel.delCommentsFormatted[0].commentDate)
        assertEquals(dateComment2, viewModel.delCommentsFormatted[1].commentDate)

        assertEquals(profilePic1, viewModel.delCommentsFormatted[0].profilePic)
        assertEquals(profilePic2, viewModel.delCommentsFormatted[1].profilePic)

        assertEquals(commentFormatted1, viewModel.delCommentsFormatted[0])
        assertEquals(commentFormatted2, viewModel.delCommentsFormatted[1])
    }



    @After
    fun tearDown() {
    }
}