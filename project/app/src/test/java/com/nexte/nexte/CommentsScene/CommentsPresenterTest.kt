package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentsPresenterTest {

    private var mock: MockCommentsDisplayLogic? = null
    private var presenter: CommentsPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockCommentsDisplayLogic()
        this.presenter = CommentsPresenter()
        this.presenter?.viewController = mock
    }

    @Test
    fun testNewComplaint(){
        val response = CommentsModel.ComplaintRequest.Response(0)

        //call
        this.presenter?.presentComplaint(response)

        //assert
        assertEquals(this.mock?.passedHere,true)
    }




    @After
    fun tearDown() {

        this.mock = null
        this.presenter = null
    }
}

private class MockCommentsDisplayLogic: CommentsDisplayLogic {
    var passedHere = false

    override fun displayComments(viewModel: CommentsModel.GetCommentsRequest.ViewModel){
        this.passedHere = true
    }

    override fun displayPublishedComment(viewModel: CommentsModel.PublishCommentRequest.ViewModel) {
        this.passedHere = true
    }

    override fun displayComplaintMessage(viewModel: CommentsModel.ComplaintRequest.ViewModel) {
        this.passedHere = true
    }

    override fun displayCommentsAfterDel(viewModel: CommentsModel.DeleteCommentRequest.ViewModel) {
        this.passedHere = true
    }

}