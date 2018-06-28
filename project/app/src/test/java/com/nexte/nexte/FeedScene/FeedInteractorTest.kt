package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FeedInteractorTest: HelpForRealm() {

    private var mock: MockFeedPresentationLogic? = null
    private var interactor: FeedInteractor? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.mock = MockFeedPresentationLogic()
        this.interactor = FeedInteractor(presenter = mock)
        this.interactor?.worker?.updateLogic = this.interactor
        this.interactor?.worker?.storyManager = StoryManager(StoryAdapterSpy())
    }

    @Test
    fun feedInteractorTest() {
        val testInteractor = FeedInteractor()

        assertNotNull(testInteractor)


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

    @Test
    fun testUpdateLikes(){
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
        super.tearDownRealm()
        this.mock = null
        this.interactor = null
    }
}

private class MockFeedPresentationLogic: FeedPresentationLogic, FeedWorkerUpdateLogic{
    var passedHere: Boolean? = null

    override fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response) {
        passedHere = true
    }

    override fun formatFeed(response: FeedModel.GetFeedActivities.Response) {
        passedHere = true
    }

    override fun updateFeed(response: FeedModel.GetFeedActivities.Response) {
        passedHere = true
    }

    override fun updateLikes(response: FeedModel.LikeAndUnlike.Response) {
        passedHere = true
    }
}
