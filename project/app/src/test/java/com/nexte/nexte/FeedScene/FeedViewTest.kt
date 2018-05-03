package com.nexte.nexte.FeedScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FeedViewTest {

    var view: FeedView? = null

    @Before
    fun setUp() {
        this.view = FeedView()
    }

    @Test
    fun testSetupFeedScene(){
        //prepare //call
        this.view?.setupFeedScene()

        //assert
        assertNotNull(this.view?.interactor)
        assertNotNull(this.view?.interactor?.presenter)
    }

    @Test
    fun testCreateGetActivitiesRequest(){
        //prepare
        this.view?.setupFeedScene()
        val mock = MockFeedsPresentationLogic()
        this.view?.interactor?.presenter = mock

        //call
        this.view?.createGetActivitiesRequest()

        //assert
        assertNotNull(mock.getActivitiesResponse)
    }

    @Test
    fun testSendLike(){
        //prepare
        this.view?.setupFeedScene()
        val mock = MockFeedsPresentationLogic()
        this.view?.interactor?.presenter = mock

        //call
        this.view?.sendLike(FeedModel.LikeAndUnlike.Request(identifier = "1"))

        //assert
        assertNotNull(mock.likeAndUnlikeResponse)
    }

    @After
    fun tearDown() {
    }
}

private class MockFeedsPresentationLogic: FeedPresentationLogic{
    var getActivitiesResponse: FeedModel.GetFeedActivities.Response? = null
    var likeAndUnlikeResponse: FeedModel.LikeAndUnlike.Response? = null

    override fun formatFeed(response: FeedModel.GetFeedActivities.Response) {
        this.getActivitiesResponse = response
    }

    override fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response) {
        this.likeAndUnlikeResponse = response
    }
}