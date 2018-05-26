package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.concurrent.thread


class CommentsWorkerTest {

    private var worker: CommentsWorker? = null
    var mock: MockCommentsWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        this.worker = CommentsWorker()
    }

    @Test
    fun successGetCommentsData(){
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

        val request = CommentsModel.GetCommentsRequest.Request()

        //call
        thread { this.worker?.getCommentsData(request)}.join()

            assertEquals(comment1.id, this.mock?.response1?.comments!![0].id)
            assertEquals(comment2.id, this.mock?.response1?.comments!![1].id)

            assertEquals(comment1.userId, this.mock?.response1?.comments!![0].userId)
            assertEquals(comment2.userId, this.mock?.response1?.comments!![1].userId)

            assertEquals(comment1.comment, this.mock?.response1?.comments!![0].comment)
            assertEquals(comment2.comment, this.mock?.response1?.comments!![1].comment)

            assertEquals(comment1.date, this.mock?.response1?.comments!![0].date)
            assertEquals(comment2.date, this.mock?.response1?.comments!![1].date)

    }

    @Test
    fun successSetNewComment() {
        //prepare
        val comment = "Jogo Fantástico"
        val request = CommentsModel.PublishCommentRequest.Request(comment)
        val today = Date()
        val author = CommentsModel.Player(UserSingleton.getUserInformations().name, 3)
        val newComment = Comment("54633jp", "angelo", "eae", Date())

        //call
        thread{worker?.setNewComment(request)}.join()
            //assert
            assertEquals(this.mock?.response2?.newComment?.id, newComment.id)
            assertEquals(this.mock?.response2?.newComment?.userId, newComment.userId)
            assertEquals(this.mock?.response2?.newComment?.comment, newComment.comment)
            assertEquals(this.mock?.response2?.newComment?.date, newComment.date)


    }

    @Test
    fun successSetComplaint() {
        val request = CommentsModel.ComplaintRequest.Request(
                3
        )

        //call
        thread{ worker?.sendComplaint(request)}.join()

        //assert
        assertEquals(this.mock!!.response3!!.serverResponse,200)

    }

    @Test
    fun successGetToDeleteComment() {
        //prepare

        val comment1 = Comment("hahaha",
                "lehaha",
                "Joga muito", Date())


        val requestToDel = CommentsModel.DeleteCommentRequest.Request(2)

        //call
        thread {this.worker?.getToDeleteComment(requestToDel)}.join()


            //assert
            assertEquals(comment1.id, this.mock?.response4?.delComments?.id)
            assertEquals(comment1.userId, this.mock?.response4?.delComments?.userId)
            assertEquals(comment1.comment, this.mock?.response4?.delComments?.comment)
            assertEquals(comment1.date, this.mock?.response4?.delComments?.date)



    }
            @After
            fun tearDown() {
                this.worker = null
            }

            class MockCommentsWorkerUpdateLogic : CommentsWorkerUpdateLogic {
                var response1: CommentsModel.GetCommentsRequest.Response? = null
                var response2: CommentsModel.PublishCommentRequest.Response? = null
                var response3: CommentsModel.ComplaintRequest.Response? = null
                var response4: CommentsModel.DeleteCommentRequest.Response? = null

                override fun updateComment(response: CommentsModel.GetCommentsRequest.Response) {
                    this.response1 = response
                }

                override fun updateNewComment(response: CommentsModel.PublishCommentRequest.Response) {
                    this.response2 = response
                }

                override fun updateSendComplaint(response: CommentsModel.ComplaintRequest.Response) {
                    this.response3 = response
                }

                override fun updateDeleteComment(response: CommentsModel.DeleteCommentRequest.Response) {
                    this.response4 = response
                }
            }

    }