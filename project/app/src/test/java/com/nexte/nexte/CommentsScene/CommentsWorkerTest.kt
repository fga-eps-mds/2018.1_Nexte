package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Comment.CommentAdapterSpy
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
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
        this.mock = MockCommentsWorkerUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.commentsManager = CommentManager(CommentAdapterSpy())
        this.worker?.storyManager = StoryManager(StoryAdapterSpy())
    }

    @Test
    fun successGetCommentsData(){
        //prepare
        val user1 = "1"
        val userId1 = "2"
        val commentUser1 = ""
        val date1 = Date()

        val user2 = "1"
        val userId2 = "2"
        val commentUser2 = ""
        val date2 = Date()

        val comment1 = Comment(user1,
                userId1,
                commentUser1,
                date1)

        val comment2 = Comment(user2,
                userId2,
                commentUser2,
                date2)

        val request = CommentsModel.GetCommentsRequest.Request("1", "1")

        //call
        this.worker?.getCommentsData(request)

        assertEquals(comment1.id, this.mock?.response1?.comments!![0].id)
        assertEquals(comment2.id, this.mock?.response1?.comments!![1].id)

        assertEquals(comment1.userId, this.mock?.response1?.comments!![0].userId)
        assertEquals(comment2.userId, this.mock?.response1?.comments!![1].userId)

        assertEquals(comment1.comment, this.mock?.response1?.comments!![0].comment)
        assertEquals(comment2.comment, this.mock?.response1?.comments!![1].comment)


    }

    @Test
    fun successSetNewComment() {
        //prepare
        val comment = ""
        val request = CommentsModel.PublishCommentRequest.Request(comment)
        val today = Date()
        val id = "1"
        val userId = "2"
        val newComment = Comment(id, userId, comment, today)

        //call
        worker?.setNewComment(request)
        //assert
        assertEquals(this.mock?.response2?.newComment?.id, newComment.id)
        assertEquals(this.mock?.response2?.newComment?.userId, newComment.userId)
        assertEquals(this.mock?.response2?.newComment?.comment, newComment.comment)


    }

    @Test
    fun successSetComplaint() {
        val request = CommentsModel.ComplaintRequest.Request(
                3
        )

        //call
        worker?.sendComplaint(request)

        //assert
        assertEquals(this.mock?.response3?.serverResponse,200)

    }

    @Test
    fun successGetToDeleteComment() {
        //prepare

        val comment1 = Comment("1",
                "2",
                "", Date())


        val requestToDel = CommentsModel.DeleteCommentRequest.Request(1)

        //call
        thread {this.worker?.getToDeleteComment(requestToDel)}.join()


        //assert
        assertEquals(comment1.id, this.mock?.response4?.delComments?.id)
        assertEquals(comment1.userId, this.mock?.response4?.delComments?.userId)
        assertEquals(comment1.comment, this.mock?.response4?.delComments?.comment)


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
            println(response)
            println(response.newComment.id)
        }


        override fun updateSendComplaint(response: CommentsModel.ComplaintRequest.Response) {
            this.response3 = response
        }

        override fun updateDeleteComment(response: CommentsModel.DeleteCommentRequest.Response) {
            this.response4 = response
        }
    }

}