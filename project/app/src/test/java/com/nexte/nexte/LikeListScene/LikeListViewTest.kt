package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListViewTest {

    var fragment: LikeListFragment? = null

    @Before
    fun setUp() {
        this.fragment = LikeListFragment()
    }

    @Test
    fun testSetUpLikeListScene(){
        //prepare
        this.fragment?.setUpLikeListScene(manager = UserManager(userAdapter = UserAdapterSpy()))

        //assert
        assertNotNull(this.fragment?.interactor)
        assertNotNull(this.fragment?.interactor?.presenter)
    }

    @Test
    fun testCreateFetchDataRequest(){
        //prepare
        this.fragment?.setUpLikeListScene(manager = UserManager(userAdapter = UserAdapterSpy()))
        val mock = MockLikeListsPresentationLogic()
        this.fragment?.interactor?.presenter = mock

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