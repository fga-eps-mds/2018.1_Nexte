package com.nexte.nexte.FeedScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FeedInteractorTest {

    private var mock: MockFeedPresentationLogic? = null
    private var interactor: FeedInteractor? = null

    @Before
    fun setUp() {
        this.mock = MockFeedPresentationLogic()
        this.interactor = FeedInteractor(presenter = mock)
    }

    @Test
    fun testFetchFeed() {
        //prepare
        val request = FeedModel.GetFeedActivities.Request()

        //call
        this.interactor?.fetchFeed(request = request)

        //assert
        assertNotNull(this.mock?.passedHere)
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun testFetchLikes(){
        //prepare
        val request = FeedModel.LikeAndUnlike.Request(identifier = "1")

        //call
        this.interactor?.fetchLikes(request = request)

        //assert
        assertNotNull(this.mock?.passedHere)
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
    }
}

private class MockFeedPresentationLogic: FeedPresentationLogic{
    var passedHere: Boolean? = null

    override fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response) {
        passedHere = null
        passedHere = true
    }

    override fun formatFeed(response: FeedModel.GetFeedActivities.Response) {
        passedHere = null
        passedHere = true
    }
}
