package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class FeedPresenterTest {

    private var mock: MockFeedDisplayLogic? = null
    private var presenter: FeedPresenter? = null

    @Before
    fun setUp() {
        this.mock = MockFeedDisplayLogic()
        this.presenter = FeedPresenter(viewScene = mock)
    }

    @Test
    fun testFormatFeed(){
        //prepare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val date = Date()
        val activity1 = FeedModel.FeedActivity(challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date), feedDate = date)
        val response = FeedModel.Response(feedActivities = arrayOf(activity1))

        //call
        this.presenter?.formatFeed(response = response)

        //assert
        assertEquals(challenger1.name, this.mock?.challengerName)
        assertEquals(challenger1.photo, this.mock?.challengerPhoto)
        assertEquals(challenger1.set.toString(), this.mock?.challengerSet)
        assertEquals(challenged1.name, this.mock?.challengedName)
        assertEquals(challenged1.photo, this.mock?.challengedPhoto)
        assertEquals(challenged1.set.toString(), this.mock?.challengedSet)
        assertEquals(true, this.mock?.isList)
        assertEquals(date.toString(), this.mock?.feedDate)
        assertEquals(1, this.mock?.size)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockFeedDisplayLogic: FeedDisplayLogic{

    var challengerName = ""
    var challengerSet = ""
    var challengerPhoto = -1
    var challengedName = ""
    var challengedSet = ""
    var challengedPhoto = -1
    var feedDate = ""
    var isList = false
    var size = -1

    override fun displayFeed(viewModel: FeedModel.ViewModel) {
        this.challengerName = viewModel.feedActivities[0].challengerName
        this.challengerSet = viewModel.feedActivities[0].challengerSets
        this.challengerPhoto = viewModel.feedActivities[0].challengerPhoto
        this.challengedName = viewModel.feedActivities[0].challengedName
        this.challengedSet = viewModel.feedActivities[0].challengedSets
        this.challengedPhoto = viewModel.feedActivities[0].challengedPhoto
        this.feedDate = viewModel.feedActivities[0].feedDate
        if (viewModel.feedActivities is List){
            this.isList = true
        }
        this.size = viewModel.feedActivities.size
    }
}