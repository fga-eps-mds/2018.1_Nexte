package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
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
        val request = CommentsModel.GetCommentsRequest.Request("1", "1")

        //assert
        assertNotNull(request)
        assertEquals(request.storyId, "1")
        assertEquals(request.tokenId, "1")
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
        val user1 = "123abc"
        val userId1 = "fulano123"
        val commentUser1 = "Mas cê ta brava?"
        val date1 = Date()

        val user2 = "456def"
        val userId2 = "ciclano456"
        val commentUser2 = "Legal ein"
        val date2 = Date()

        val comment1 = Comment(user1,
                userId1,
                commentUser1,
                date1)

        val comment2 = Comment(user2,
                               userId2,
                               commentUser2,
                               date2)

        val commentsList = mutableListOf(comment1, comment2)

        //call

        val response = CommentsModel.GetCommentsRequest.Response(commentsList)

        //assert
        assertEquals(comment1.id, response.comments[0].id)
        assertEquals(comment2.id, response.comments[1].id)

        assertEquals(comment1.userId, response.comments[0].userId)
        assertEquals(comment2.userId, response.comments[1].userId)

        assertEquals(comment1.comment, response.comments[0].comment)
        assertEquals(comment2.comment, response.comments[1].comment)

        assertEquals(comment1.date, response.comments[0].date)
        assertEquals(comment2.date, response.comments[1].date)
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
        val id1 = "1234"
        val userId1 = "lala123"
        val comment1 = "Muito bom galera"
        val date1 = Date()

        val comment = Comment(id1,
                userId1,
                comment1, date1)

        //call
        val response = CommentsModel.PublishCommentRequest.Response(comment)

        //assert
        assertEquals(comment.id, response.newComment.id)
        assertEquals(comment.userId, response.newComment.userId)
        assertEquals(comment.comment, response.newComment.comment)
        assertEquals(comment.date, response.newComment.date)
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
        val comment = Comment("haha123",
                "lili123",
                "Eae bro", Date())

        val newComment = Comment("hehe123",
                "lolo123",
                "Iih coé", Date())

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

        //assertF
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
        val id = "1"
        val idUser = "2"
        val date = Date()
        //call
        val comment = Comment(id, idUser, text, date)

        //assert
        assertEquals(text, comment.comment)
        assertEquals(date, comment.date)
        assertEquals(id, comment.id)
        assertEquals(idUser, comment.userId)

    }

    @Test
    fun testSetOnComments(){
        //prepare
        val text = "Jogo top!!!!"
        val date = Date()
        val id1 = "456789"
        val idUser1 = "456"

        //call
        val comment = Comment(id1, idUser1, text, date)

        comment.id = id1
        comment.comment = text
        comment.date = date
        comment.userId = idUser1

        //assert
        assertEquals(comment.comment, text)
        assertEquals(comment.id, id1)
        assertEquals(comment.userId, idUser1)
        assertEquals(comment.date, date)
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
    fun DeleteComgimentResponse() {
        //prepare

        val comment1 = Comment("hahaha",
                "lehaha",
                "Joga muito", Date())


        val commentsList = Comment("hahaha", "lehaha", "Joga muito", Date())

        //call

        val response = CommentsModel.DeleteCommentRequest.Response(commentsList)

        //assert
        assertEquals(comment1.id, response.delComments.id)

        assertEquals(comment1.userId, response.delComments.userId)

        assertEquals(comment1.comment, response.delComments.comment)

        assertEquals(comment1.date, response.delComments.date)


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