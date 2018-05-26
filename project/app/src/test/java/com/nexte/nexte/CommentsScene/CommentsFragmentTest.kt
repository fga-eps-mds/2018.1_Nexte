package com.nexte.nexte.CommentsScene

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CommentsFragmentTest {

    private var fragment: CommentsFragment?= null
    private var mock: CommentsInteractorMock?= null
    @Before
    fun setUp() {
        fragment = CommentsFragment()
        mock = CommentsInteractorMock(123)
        fragment?.interactor = mock
    }

    @After
    fun tearDown() {
        fragment = null
        mock = null
    }

    @Test
    fun getInteractor() {
        //prepare

        //call

        //assert
        assertEquals((fragment?.interactor as CommentsInteractorMock).id, mock?.id)
    }

    @Test
    fun setInteractor() {
        //prepare
        val newMock = CommentsInteractorMock(321)
        //call
        fragment?.interactor = newMock
        //assert
        assertEquals((fragment?.interactor as CommentsInteractorMock).id, newMock.id)
    }



    private class CommentsInteractorMock(var id: Int = 0) : CommentsBusinessLogic{
        var passedHere: Boolean = false

        override fun publishNewComment(request: CommentsModel.PublishCommentRequest.Request) {
            passedHere = true
        }

        override fun recentComments(request: CommentsModel.GetCommentsRequest.Request) {
            passedHere = true
        }

        override fun sendComplaint(request: CommentsModel.ComplaintRequest.Request) {
            passedHere = true
        }

        override fun deleteComment(request: CommentsModel.DeleteCommentRequest.Request) {
            passedHere = true
        }
    }
}