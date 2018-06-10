package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.FeedMocker
import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.Story.StoryPlayer
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.R
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class FeedWorkerTest: HelpForRealm() {

    var worker: FeedWorker? = null
    var feedList: MutableList<FeedModel.FeedActivity> = mutableListOf()
    var mock: MockFeedUpdateLogic? = null
    var storyManager: StoryManager? = null
    var updateLogicFeed: FeedWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = FeedWorker()
        this.feedList = FeedMocker.createFeedList()
        this.mock = MockFeedUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.storyManager = StoryManager(StoryAdapterSpy())
    }

    @Test
    fun testFetchDataCase1(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        //call
        worker?.fetchFeedData(request)

        //assert
        assertNotNull(this.mock?.response)
        assertEquals(this.mock?.response?.feedActivities?.size, 4)
    }

    @Test
    fun testFetchDataCase2(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        val backup = worker?.storyManager
        //call
        worker?.storyManager = null
        worker?.fetchFeedData(request)

        //assert
        assertNotNull(this.mock?.response)
        assertEquals(this.mock?.response?.feedActivities?.size, 0)

        //backup
        worker?.storyManager = backup
    }

    @Test
    fun testFetchDataCase3(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        val backup = worker?.updateLogic
        //call
        worker?.updateLogic = null
        worker?.fetchFeedData(request)

        //assert
        assertNull(this.mock?.response)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun testJsonConvertJsonFeed(){

        val winner = JSONObject()
        winner.put("userID", "aleale")
        winner.put("setResult", 1)

        val loser = JSONObject()
        loser.put("userID", "aleEEE")
        loser.put("setResult", 2)

        val challengeJson = JSONObject()
        challengeJson.put("challengeID", "Mito")
        challengeJson.put("winner", winner)
        challengeJson.put("loser", loser)

        val commentsIdsJsonArray = JSONArray()
        commentsIdsJsonArray.put(0, "comentario")

        val likesIdsJsonArray = JSONArray()
        likesIdsJsonArray.put(0, "curti")

        val feedJson = JSONObject()
        feedJson.put("id", "Ohooh")
        feedJson.put("challenge", challengeJson)
        feedJson.put("comments", commentsIdsJsonArray)
        feedJson.put("likes", likesIdsJsonArray)
        feedJson.put("date", "2008-12-12T0000:00:00.0")

        val feedJsonArray = JSONArray()
        feedJsonArray.put(feedJson)

        val dataObject = JSONObject()
        dataObject.put("feed", feedJsonArray)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataObject)

        println(jsonObject)

        val feed = this.worker?.convertJsonStoryToStories(jsonObject)

        val getChallenge = feedJson["challenge"] as JSONObject
        val getWinner = getChallenge["winner"] as JSONObject
        val getLoser = getChallenge["loser"] as JSONObject
        val getComments = feedJson["comments"] as JSONArray
        val getLikes = feedJson["likes"] as JSONArray

        assertEquals(feed!![0].id, feedJson["id"] as String)

        assertEquals(feed[0].winner?.userId, getWinner["userID"] as String)
        assertEquals(feed[0].winner?.setResult, getWinner["setResult"] as Int)

        assertEquals(feed[0].loser?.userId, getLoser["userID"] as String)
        assertEquals(feed[0].loser?.setResult, getLoser["setResult"] as Int)

        assertEquals(feed[0].commentsId[0], getComments.getString(0))
        assertEquals(feed[0].likesId[0], getLikes.getString(0))

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

    @Test
    fun successGetUpdateLogic(){
        //prepare and call
        val updateLogic = this.worker?.updateLogic

        //assert
        assertNotNull(updateLogic)
    }

    @Test
    fun successStoryManager(){
        //prepare and call
        val storyManager = this.worker?.storyManager
        //assert
        assertNotNull(storyManager)
    }

    @After
    fun tearDown() {
        super.tearDownRealm()
        this.worker = null
    }
}

class MockFeedUpdateLogic: FeedWorkerUpdateLogic {

    var response: FeedModel.GetFeedActivities.Response? = null

    override fun updateFeed(response: FeedModel.GetFeedActivities.Response) {
        this.response = response
    }
}
