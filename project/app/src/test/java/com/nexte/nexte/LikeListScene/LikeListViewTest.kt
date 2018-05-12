package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListViewTest {

    var view: LikeListView? = null

    @Before
    fun setUp() {
        this.view = LikeListView()
    }

    @Test
    fun testSetUpLikeListScene(){
        //prepare
        this.view?.setUpLikeListScene(manager = UserManager(userAdapter = UserAdapterSpy()))

        //call

        //assert
        assertNotNull(this.view?.interactor)
        assertNotNull(this.view?.interactor?.presenter)
    }

    @Test
    fun testCreateFetchDataRequest(){
        //prepare
        this.view?.setUpLikeListScene(manager = UserManager(userAdapter = UserAdapterSpy()))
        val mock = MockLikeListsPresentationLogic()
        this.view?.interactor?.presenter = mock

        //call
        this.view?.createFetchDataRequest()

        //assert
        assertNotNull(mock.likeListResponse)
    }

    @After
    fun tearDown() {
    }
}

private class MockLikeListsPresentationLogic: LikeListPresentationLogic{
    var likeListResponse: LikeListModel.Response? = null

    override fun formatLikeList(response: LikeListModel.Response) {
        this.likeListResponse = response
    }
}