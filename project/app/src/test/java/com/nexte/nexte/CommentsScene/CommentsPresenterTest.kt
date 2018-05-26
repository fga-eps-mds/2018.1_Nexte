package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Comment.CommentAdapterSpy
import com.nexte.nexte.Entities.Comment.CommentManager
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
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
        this.presenter?.userManager = UserManager(UserAdapterSpy())

    }

    @Test
    fun successPresentComment() {
        //prepare
        val comment1 = Comment("1", "1", "", Date())
        val comment2 = Comment("1", "1", "", Date())
        val commentsList = mutableListOf(comment1, comment2)
        val response = CommentsModel.GetCommentsRequest.Response(commentsList)

        //call
        this.presenter?.presentComment(response)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun successPresentNewComment(){
        val comment = Comment("1", "1", "", Date())
        val response = CommentsModel.PublishCommentRequest.Response(comment)

        //call
        this.presenter?.presentNewComment(response)

        //assert
        assertEquals(this.mock?.passedHere,true)
    }

    @Test
    fun testNewComplaint(){
        val response = CommentsModel.ComplaintRequest.Response(0)

        //call
        this.presenter?.presentComplaint(response)

        //assert
        assertEquals(this.mock?.passedHere,true)
    }

    @Test
    fun testDeleteComment(){
        //prepare
        val comment2 = Comment("1", "1", "", Date())
        val commentsList = Comment(comment2.id, comment2.userId, comment2.comment, Date())
        val response = CommentsModel.DeleteCommentRequest.Response(commentsList)

        //call
        this.presenter?.presentPositionToDelete(response)

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