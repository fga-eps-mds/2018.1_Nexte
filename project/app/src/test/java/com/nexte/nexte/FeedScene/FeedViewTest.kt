package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test


class FeedViewTest: HelpForRealm() {

    var view: FeedFragment? = null

    @Before
    fun setUp() {
        super.setUpWithUser()

        this.view = FeedFragment()
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
        this.view?.userManager = UserManager(UserAdapterSpy())
        this.view?.storyManager = StoryManager(StoryAdapterSpy())
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
        super.tearDownRealm()
    }
}

private class MockFeedsPresentationLogic: FeedPresentationLogic, FeedWorkerUpdateLogic{
    var getActivitiesResponse: FeedModel.GetFeedActivities.Response? = null
    var likeAndUnlikeResponse: FeedModel.LikeAndUnlike.Response? = null

    override fun formatFeed(response: FeedModel.GetFeedActivities.Response) {
        this.getActivitiesResponse = response
    }

    override fun updateViewActivity(response: FeedModel.LikeAndUnlike.Response) {
        this.likeAndUnlikeResponse = response
    }

    override fun updateFeed(response: FeedModel.GetFeedActivities.Response) {
        this.getActivitiesResponse = response
    }

    override fun updateLikes(response: FeedModel.LikeAndUnlike.Response) {
        this.likeAndUnlikeResponse = response
    }
}