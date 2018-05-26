package com.nexte.nexte.CommentsScene

import com.nexte.nexte.Entities.Comment.Comment
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
        val comment1 = Comment("456tpp", "hiago999", "Eita!", Date())
        val comment2 = Comment("jin6789", "helenagoulart", "Credito ou débito?", Date())
        val commentsList = mutableListOf(comment1, comment2)
        val response = CommentsModel.GetCommentsRequest.Response(commentsList)

        //call
        this.presenter?.presentComment(response)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun successPresentNewComment(){
        val comment = Comment("hjh777", "gabrielalbino2", "Iti Malia", Date())
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
        val comment2 = Comment("596leti", "leticiageografia", "Devolve meu sapato, Albino!", Date())
        val commentsList = Comment("596leti", "leticiageografia", "Devolve meu sapato, Albino!", Date())
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