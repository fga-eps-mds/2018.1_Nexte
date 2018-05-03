package com.nexte.nexte.CommentsScene

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.nexte.nexte.R
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CommentsViewTest {

    private var view: CommentsView?= null
    private var mock: CommentsInteractorMock?= null
    @Before
    fun setUp() {
        view = CommentsView()
        mock = CommentsInteractorMock(123)
        view?.interactor = mock
    }

    @After
    fun tearDown() {
        view = null
        mock = null
    }

    @Test
    fun getInteractor() {
        //prepare

        //call

        //assert
        assertEquals((view?.interactor as CommentsInteractorMock).id, mock?.id)
    }

    @Test
    fun setInteractor() {
        //prepare
        val newMock = CommentsInteractorMock(321)
        //call
        view?.interactor = newMock
        //assert
        assertEquals((view?.interactor as CommentsInteractorMock).id, newMock.id)
    }

    @Test
    fun setUpCommentsScene() {
        //prepare
        //call
        view?.setUpCommentsScene()
        //assert
        assertNotNull(view?.interactor)
        assertNotNull((view?.interactor as CommentsInteractor).presenter)
        assertNotNull(((view?.interactor as CommentsInteractor)
                .presenter as CommentsPresenter).viewController)
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
            this.passedHere = true
        }
    }

}
