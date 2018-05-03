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
    fun successPresentComment() {
        //prepare
        val player1 = CommentsModel.Player("Larissa", 1)
        val player2 = CommentsModel.Player("Alexandre", 2)
        val comment1 = CommentsModel.Comment("Muito bom galera", Date(), player1)
        val comment2 = CommentsModel.Comment("Nossa, foi top mesmo", Date(), player2)
        val commentsList = mutableListOf(comment1, comment2)
        val response = CommentsModel.GetCommentsRequest.Response(commentsList)

        //call
        this.presenter?.presentComment(response)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun successPresentNewComment(){
        val player = CommentsModel.Player("Gabriel Albino", 2)
        val comment = CommentsModel.Comment("Show!", Date(), player)
        val response = CommentsModel.PublishCommentRequest.Response(comment)

        //call
        this.presenter?.presentNewComment(response)

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


}