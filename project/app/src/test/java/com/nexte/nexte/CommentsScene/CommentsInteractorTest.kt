package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test

class CommentsInteractorTest {

    private var mock: MockCommentsPresentationLogic? = null
    private var interactor: CommentsInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockCommentsPresentationLogic()
        this.interactor = CommentsInteractor()
        this.interactor?.presenter = mock
        this.interactor?.worker?.updateLogic = mock
    }

    @Test
    fun successRecentComments() {
        //prepare
        val request = CommentsModel.GetCommentsRequest.Request()

        //call
        this.interactor?.recentComments(request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }
    @Test
    fun successPublishNewComment() {
        //prepare
        val request = CommentsModel.PublishCommentRequest.Request(commentToPost = "Jogo Sensacional!")

        //call
        this.interactor?.publishNewComment(request)

        //assert
        assertEquals(this.mock?.passedHere, true)
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
        val request = CommentsModel.ComplaintRequest.Request(
                3
        )

        //call
        this.interactor?.sendComplaint(request)

        //assert
        assertEquals(true, mock?.passedHere)
    }

    @Test
    fun successDeleteComment(){
        //prepare
        val request = CommentsModel.DeleteCommentRequest.Request(
                3
        )

        //call
        this.interactor?.deleteComment(request)

        //assert
        assertEquals(true, mock?.passedHere)
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