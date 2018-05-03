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
    }

    @Test
    fun successRecentComments() {
        //prepare
        val request = CommentsModel.GetCommentsRequest.Request("identifier")

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
                CommentsModel.CommentFormatted(
                        "Boa!", "27/12/1239", "gabriel", 2
                )
        )

        //call
        this.interactor?.sendComplaint(request)

        //assert
        assertEquals(true, mock?.passedHere)
    }
    
    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockCommentsPresentationLogic : CommentsPresentationLogic {
    var passedHere = false

    override fun presentComment(response: CommentsModel.GetCommentsRequest.Response) {
        this.passedHere = true
    }

    override fun presentNewComment(response: CommentsModel.PublishCommentRequest.Response) {
        this.passedHere = true
    }

    override fun presentComplaint(response: CommentsModel.ComplaintRequest.Response) {
        this.passedHere = true
    }
}