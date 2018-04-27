package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before
import java.util.*
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
        val request = CommentsModel.Request("identifier")

        //call
        this.interactor?.recentComments(request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockCommentsPresentationLogic : CommentsPresentationLogic {
    var passedHere = false

    override fun presentComment(response: CommentsModel.Response) {
        this.passedHere = true
    }
}