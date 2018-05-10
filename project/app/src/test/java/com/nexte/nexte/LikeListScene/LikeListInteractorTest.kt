package com.nexte.nexte.LikeListScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListInteractorTest {

    private var mock: MockLikeListPresentationLogic? = null
    private var interactor: LikeListInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockLikeListPresentationLogic()
        this.interactor = LikeListInteractor(presenter = mock)
    }

    @Test
    fun testFetchDataToList(){
        //prepare
        val request = LikeListModel.Request(request = "akjsbdask")

        //call
        this.interactor?.fetchDataToList(request = request)
        request.request

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockLikeListPresentationLogic: LikeListPresentationLogic{

    var passedHere = false

    override fun formatLikeList(response: LikeListModel.Response) {
        this.passedHere = true
    }
}