package com.nexte.nexte.CommentsScene

import android.content.Context
import android.content.Intent
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CommentsViewTest {

    private var view: CommentsView? = null
    private var mock: MockCommentPresentationLogic? = null

    @Before
    fun setUp() {
        this.view = CommentsView()
        this.mock = MockCommentPresentationLogic()
    }

    @Test
    fun testView(){
        //prepare
        val viewComments = CommentsView()
        //call

        //assert
        assertNotNull(viewComments)
    }


    @Test
    fun testSetUpCommentsScene(){
        //prepare //call
        this.view?.setUpCommentsScene()

        //assert
        assertNotNull(this.view?.interactor)
        assertNotNull(this.view?.interactor?.presenter)
    }

    @Test
    fun testCreateRequest(){
        //prepare
        this.view?.setUpCommentsScene()
        this.view?.interactor?.presenter = mock

        //call
        this.view?.createRequest()

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
    }
}

private class MockCommentPresentationLogic : CommentsPresentationLogic {
    var passedHere = false

    override fun presentComment(response: CommentsModel.Response) {
        this.passedHere = true
    }
}