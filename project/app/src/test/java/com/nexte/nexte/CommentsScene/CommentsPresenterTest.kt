package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

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
    fun successPresentComment(){
        //prepare
        val response = CommentsModel.Response(comment = "Muito bom", userName = "luis", linkUserProfilePicture = "", commentTime = "14:20", like = true)
        val expectedMessage = "Um comentário existente"

        //call
        this.presenter?.presentComment(response = response)

        //assert
        assertEquals(expectedMessage, this.mock?.message)
    }

    @Test
    fun failPresentComment(){
        //prepare
        val response = CommentsModel.Response(comment = "", userName = "luis", linkUserProfilePicture = "", commentTime = "14:20", like = true)
        val expectedMessage = "Não há comentário"

        //call
        this.presenter?.presentComment(response = response)

        //assert
        assertEquals(expectedMessage, this.mock?.message)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockCommentsDisplayLogic: CommentsDisplayLogic{
    var message: String = "123"

    override fun displayComments(viewModel: CommentsModel.ViewModel){
        this.message = viewModel.message!!
    }
}