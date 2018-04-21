package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class FeedWorkerTest {

    var worker: FeedWorker? = null
    var feedList: MutableList<FeedModel.FeedActivity> = mutableListOf()

    @Before
    fun setUp() {
        this.worker = FeedWorker()
        this.feedList = FeedMocker.createFeedList()
    }

    @Test
    fun testFetchData(){
        //prepare
        val challenger2Name = "Letícia"
        val challenged3Set = 0
        val challenger5Name = "Larissa"
        val request = FeedModel.GetFeedActivities.Request()

        //call
        this.worker?.fetchFeedData(request = request, completion = {response ->
            //assert
            assertNotNull(response)
            assertEquals(response.feedActivities.size, 8)
            assertEquals(response.feedActivities[1].challenge.challenger.name, challenger2Name)
            assertEquals(response.feedActivities[2].challenge.challenged.set, challenged3Set)
            assertEquals(response.feedActivities[4].challenge.challenger.name, challenger5Name)
        })
    }

    @Test
    fun testManageLikes(){
        //prepare
        val identifier = "1"
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val request = FeedModel.LikeAndUnlike.Request(identifier = identifier)

        //call
        this.worker?.manageLikes(request = request, completion = {response ->
            //assert
            assertEquals(challenger1.name, response.likedActivity.challenge.challenger.name)
            assertEquals(challenger1.set, response.likedActivity.challenge.challenger.set)
            assertEquals(challenger1.photo, response.likedActivity.challenge.challenger.photo)
            assertEquals(challenged1.name, response.likedActivity.challenge.challenged.name)
            assertEquals(challenged1.set, response.likedActivity.challenge.challenged.set)
            assertEquals(challenged1.photo, response.likedActivity.challenge.challenged.photo)
            assertEquals(identifier, response.likedActivity.identifier)

        })
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}
