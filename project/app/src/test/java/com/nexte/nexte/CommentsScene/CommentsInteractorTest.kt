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

class CommentsInteractorTest {

    private var mock: MockCommentsPresentationLogic? = null
    private var interactor: CommentsInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockCommentsPresentationLogic()
        this.interactor = CommentsInteractor()
        this.interactor?.presenter = mock
        this.interactor?.worker?.updateLogic = mock
        this.interactor?.worker?.commentsManager = CommentManager(CommentAdapterSpy())
        this.interactor?.worker?.storyManager = StoryManager(StoryAdapterSpy())
    }

    @Test
    fun successRecentComments() {
        //prepare
        val request = CommentsModel.GetCommentsRequest.Request("1", "1")

        //call
        thread{this.interactor?.recentComments(request )}.join()

        //assert
        assertEquals(this.mock?.passedHere, true)
    }
    @Test
    fun successPublishNewComment() {
        //prepare
        val request = CommentsModel.PublishCommentRequest.Request("Jogo Sensacional!", "1")

        //call
        this.interactor?.publishNewComment(request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun failedUpdateComment(){
        //prepare
        val backup = interactor?.presenter
        interactor?.presenter = null
        //call
        interactor?.updateComment(CommentsModel.GetCommentsRequest.Response(mutableListOf()))
        //assert
        assertEquals(mock?.passedHere, false)
        //backup
        interactor?.presenter = backup
    }

    @Test
    fun failedUpdateNewComment(){
        //prepare
        val backup = interactor?.presenter
        interactor?.presenter = null
        //call
        interactor?.updateNewComment(CommentsModel.PublishCommentRequest.Response(Comment("", "", "", Date())))
        //assert
        assertEquals(mock?.passedHere, false)
        //backup
        interactor?.presenter = backup
    }
    @Test
    fun failedUpdateDeleteComment(){
        //prepare
        val backup = interactor?.presenter
        interactor?.presenter = null
        val mockComment = Comment("1", "1", "", Date())
        val mutList = mutableListOf(mockComment)
        //call
        interactor?.updateDeleteComment(CommentsModel.DeleteCommentRequest.Response(mutList))
        //assert
        assertEquals(mock?.passedHere, false)
        //backup
        interactor?.presenter = backup
    }

    @Test
    fun successUpdateComment(){
        //prepare and call
        interactor?.updateComment(CommentsModel.GetCommentsRequest.Response(mutableListOf()))
        //assert
        assertEquals(mock?.passedHere, true)
    }

    @Test
    fun successGetWorkerTest(){
        //prepare and call
        val expectedWorker = this.interactor?.worker

        //assert
        assertEquals(expectedWorker, this.interactor?.worker)
    }

    @Test
    fun successTestConstructor(){
        //prepare and call
        val interactor = CommentsInteractor()

        //assert
        assertNotNull(interactor)
    }

    @Test
    fun successSetWorkerTest(){
        //prepare
        val newWorker = CommentsWorker()

        //call
        this.interactor?.worker = newWorker

        //assert
        assertEquals(newWorker, interactor?.worker)
    }

    @Test
    fun successSendComplaint(){
        //prepare
        val request = CommentsModel.ComplaintRequest.Request(3, "1")

        //call
        this.interactor?.sendComplaint(request)

        //assert
        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun successDeleteComment(){
        //prepare
        val request = CommentsModel.DeleteCommentRequest.Request(
                108, "1")

        //call
        this.interactor?.deleteComment(request)

        //assert
        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun updateSendComplain() {
        val serverResponse = 1
        val response= CommentsModel.ComplaintRequest.Response(serverResponse)

        this.interactor?.updateSendComplaint(response)

        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun updateNewComment() {
        //prepare
        val id: String? = "1"
        val userId: String? = "2"
        val comment: String? = ""
        val date: Date? = Date()
        val comments = Comment(id, userId, comment, date)

        val response = CommentsModel.PublishCommentRequest.Response(comments)

        this.interactor?.updateNewComment(response)

        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun updateDeleteComment() {
        //prepare
        val id: String? = "1"
        val userId: String? = "2"
        val comment: String? = ""
        val date: Date? = Date()
        val comments = Comment(id, userId, comment, date)
        val mutList = mutableListOf(comments)

        val response = CommentsModel.DeleteCommentRequest.Response(mutList)

        this.interactor?.updateDeleteComment(response)

        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun testGetPresenter() {
        val presenter = this.interactor?.presenter

        //assert
        assertNotNull(presenter)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockCommentsPresentationLogic : CommentsPresentationLogic, CommentsWorkerUpdateLogic {
    var passedHere = false

    override fun updateSendComplaint(response: CommentsModel.ComplaintRequest.Response) {
        this.passedHere = true
    }

    override fun updateNewComment(response: CommentsModel.PublishCommentRequest.Response) {
        this.passedHere = true
    }

    override fun updateDeleteComment(response: CommentsModel.DeleteCommentRequest.Response) {
        this.passedHere = true
    }

    override fun updateComment(response: CommentsModel.GetCommentsRequest.Response) {
        this.passedHere = true
    }
    override fun presentComment(response: CommentsModel.GetCommentsRequest.Response) {
        this.passedHere = true
    }

    override fun presentNewComment(response: CommentsModel.PublishCommentRequest.Response) {
        this.passedHere = true
    }

    override fun presentComplaint(response: CommentsModel.ComplaintRequest.Response) {
        this.passedHere = true
    }

    override fun presentPositionToDelete(response: CommentsModel.DeleteCommentRequest.Response) {
        this.passedHere = true
    }
}