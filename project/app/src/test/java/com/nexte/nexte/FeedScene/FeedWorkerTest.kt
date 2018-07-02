package com.nexte.nexte.FeedScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.Story.StoryPlayer
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.net.URL
import java.util.*
import kotlin.concurrent.thread

class FeedWorkerTest: HelpForRealm() {

    var worker: FeedWorker? = null
    private var feedList: MutableList<FeedModel.FeedActivity> = mutableListOf()
    var mock: MockFeedUpdateLogic? = null
    var storyManager: StoryManager? = null
    var updateLogicFeed: FeedWorkerUpdateLogic? = null
    val jsonObject = JSONObject()

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = FeedWorker()
       // this.feedList = FeedMocker.createFeedList()
        this.mock = MockFeedUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.storyManager = StoryManager(StoryAdapterSpy())

        //set winner attributes
        val winner = JSONObject()
        winner.put("userID", "aleale")
        winner.put("setResult", 1)

        //set loser attributes
        val loser = JSONObject()
        loser.put("userID", "aleEEE")
        loser.put("setResult", 2)

        //use winner and loser to set challenge
        val challengeJson = JSONObject()
        challengeJson.put("challengeID", "Mito")
        challengeJson.put("winner", winner)
        challengeJson.put("loser", loser)

        //set array of comments with one comment
        val commentsIdsJsonArray = JSONArray()
        commentsIdsJsonArray.put(0, "comentario")

        //set array of likes with one like
        val likesIdsJsonArray = JSONArray()
        likesIdsJsonArray.put(0, "curti")

        //set feed with its attributes
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
        jsonObject.put("data", dataObject)
    }

    @Test
    fun testFetchDataSuccess(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()

        //call
        thread {
            worker?.fetchFeedData(request)
        }.join()
        //assert
        assertNotNull(this.mock?.response1)
        assertEquals(this.mock?.response1?.feedActivities?.size, 4)
    }

    @Test
    fun testFetchStoryManagerNull(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        val backup = worker?.storyManager
        worker?.storyManager = null
        //call
        thread {
            worker?.fetchFeedData(request)
        }.join()
        //assert
        assertNotNull(this.mock?.response1)
        assertEquals(this.mock?.response1?.feedActivities?.size, 0)

        //backup
        worker?.storyManager = backup
    }

    @Test
    fun testFetchDataCase3(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        //call
        thread {
            worker?.fetchFeedData(request)
        }.join()
        //assert
        assertNull(this.mock?.response1)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun testFetchFailHTTP(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()

        //call
        thread{ worker?.fetchFeedData(request) }.join()

        //assert
//        assertFalse(worker?.senderHTTP!!)
    }

    @Test
    fun testFetchSuccessHTTP(){
        //prepare
        val request = FeedModel.GetFeedActivities.Request()
        UserSingleton.userType = UserType.REAL

        //call
       thread {
           worker?.fetchFeedData(request)
       }.join()

        //assert
        assertTrue(worker?.senderHTTP!!)
    }

    @Test
    fun setSenderSuccess(){
        this.worker?.senderHTTP = false

        assertEquals(false, this.worker?.senderHTTP)
    }

    //    fun testJsonConvertJsonFeed(){
    //
    //        val feed = this.worker?.convertJsonStoryToStories(jsonObject)
    //
    //        val getDataJson = jsonObject["data"] as JSONObject
    //        val getFeedJson = getDataJson["feed"] as JSONArray
    //        val feedJson = getFeedJson.getJSONObject(0)
    //
    //        val getChallenge = feedJson["challenge"] as JSONObject
    //        val getWinner = getChallenge["winner"] as JSONObject
    //        val getLoser = getChallenge["loser"] as JSONObject
    //        val getComments = feedJson["comments"] as JSONArray
    //        val getLikes = feedJson["likes"] as JSONArray
    //
    //        //assert attributes after building Json objects
    //        assertEquals(feed!![0].id, feedJson["id"] as String)
    //
    //        assertEquals(feed[0].winner?.userId, getWinner["userID"] as String)
    //        assertEquals(feed[0].winner?.setResult, getWinner["setResult"] as Int)
    //
    //        assertEquals(feed[0].loser?.userId, getLoser["userID"] as String)
    //        assertEquals(feed[0].loser?.setResult, getLoser["setResult"] as Int)
    //
    //        assertEquals(feed[0].commentsId[0], getComments.getString(0))
    //        assertEquals(feed[0].likesId[0], getLikes.getString(0))
    //
    //
    //    }

    @Test
    fun testManageLikes(){
        //prepare
        val identifier = "1"
        val request = FeedModel.LikeAndUnlike.Request(identifier)

        val id: String? = "1"
        val winner: StoryPlayer? = StoryPlayer(userId = "1", setResult = 5)
        val loser: StoryPlayer? = StoryPlayer(userId = "2", setResult = 4)
        val date: Date? = Date()
        val commentsId: List<String> = listOf("A", "B","C")
        val likesId: List<String> = listOf( "2", "3")

        val storyComp = Story(id, winner, loser, date, commentsId, likesId)

        //call
        thread {this.worker?.manageLikes(request)}.join()

        //assert
        assertEquals(storyComp.likesId, this.mock?.response2?.likedActivity?.likesId)
        assertEquals(storyComp.commentsId, this.mock?.response2?.likedActivity?.commentsId)
        assertEquals(storyComp.id, this.mock?.response2?.likedActivity?.id)
    }

    @Test
    fun testInvokeFailure(){
        this.mock?.response1 = null
        val url = URL("http://www.youtube.com")
        val request = com.github.kittinunf.fuel.core.Request(com.github.kittinunf.fuel.core.Method.GET,
                                                             "",
                                                             url)
        val response = com.github.kittinunf.fuel.core.Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("An error occurred")))

        this.worker?.httpRequestHandler?.invoke(request, response, result)

        assertNull(this.mock?.response1)
    }

//    @Test
//    fun testInvokeSuccess(){
//        this.mock?.response1 = null
//
//        val url = URL("http://www.youtube.com")
//        val request = com.github.kittinunf.fuel.core.Request(com.github.kittinunf.fuel.core.Method.GET,
//                "",
//                url)
//        val response = com.github.kittinunf.fuel.core.Response(url)
//
//        val json = Json(jsonObject.toString())
//        val result: Result<Json, FuelError> = Result.Success(json)
//
//        this.worker?.httpRequestHandler?.invoke(request, response, result)
//
//        assertNotNull(this.mock?.response1)
//    }


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

//    @Test
//    fun setListFeedManagerEmpty(){
//        //prepare
//        val testList =  mutableListOf<FeedModel.FeedActivity>()
//        val listToCompare = FeedMocker.createFeedList()
//
//        //call
//        FeedManager.feedListMutable = testList
//
//        //assert
//        assertEquals(listToCompare[0].challenge.challenged.name, FeedManager.feedList[0].challenge.challenged.name)
//        assertEquals(listToCompare[0].challenge.challenger.name, FeedManager.feedList[0].challenge.challenger.name)
//        assertEquals(listToCompare[0].identifier, FeedManager.feedList[0].identifier)
//
//
//    }

//    @Test
//    fun setListFeedManager(){
//        //prepares
//        val challenger1 = FeedModel.FeedPlayer("Rafael", R.mipmap.ic_launcher, 234)
//        val challenged1 = FeedModel.FeedPlayer("Pedro", R.mipmap.ic_launcher, 352)
//        val likesList1: MutableList<FeedModel.FeedPlayer> = mutableListOf()
//        val challenge1 = FeedModel.FeedChallenge(challenger1, challenged1, Date())
//        val feedActivity1 = FeedModel.FeedActivity("246 F", challenge1, Date(), likesList1)
//
//        val challenger2 = FeedModel.FeedPlayer("Rafael", R.mipmap.ic_launcher, 234)
//        val challenged2 = FeedModel.FeedPlayer("Pedro", R.mipmap.ic_launcher, 352)
//        val likesList2: MutableList<FeedModel.FeedPlayer> = mutableListOf()
//        val challenge2 = FeedModel.FeedChallenge(challenger2, challenged2, Date())
//        val feedActivity2 = FeedModel.FeedActivity("212 F", challenge2, Date(), likesList2)
//
//        val listToTest = mutableListOf(feedActivity1, feedActivity2)
//
//        //call
//        FeedManager.feedListMutable = listToTest
//        FeedManager.feedList
//
//        //assert
//        assertEquals(listToTest, FeedManager.feedList)
//
//
//    }

//    @Test
//    fun addUserManager(){
//        //prepare
//        val challenger1 = FeedModel.FeedPlayer("Rafael", R.mipmap.ic_launcher, 234)
//        val challenged1 = FeedModel.FeedPlayer("Pedro", R.mipmap.ic_launcher, 352)
//        val likesList1: MutableList<FeedModel.FeedPlayer> = mutableListOf()
//        val challenge1 = FeedModel.FeedChallenge(challenger1, challenged1, Date())
//        val feedActivity1 = FeedModel.FeedActivity("246F", challenge1, Date(), likesList1)
//
//        //call
//        val testActivity = FeedManager.addAndRemoveUser(feedActivity1)
//
//        //assert
//        assertNotNull(testActivity?.likes?.find { it.name.equals(UserSingleton.loggedUser.name) })
//    }

//    @Test
//    fun removeUserManager(){
//        //prepare
//        val challenger1 = FeedModel.FeedPlayer("Rafael", R.mipmap.ic_launcher, 234)
//        val challenged1 = FeedModel.FeedPlayer("Pedro", R.mipmap.ic_launcher, 352)
//
//        val currentUser = FeedModel.FeedPlayer(UserSingleton.loggedUser.name, R.mipmap.ic_launcher, 3)
//        val likesList1 = mutableListOf(currentUser)
//        val challenge1 = FeedModel.FeedChallenge(challenger1, challenged1, Date())
//        val feedActivity1 = FeedModel.FeedActivity("246F", challenge1, Date(), likesList1)
//
//        //call
//        val testActivity = FeedManager.addAndRemoveUser(feedActivity1)
//
//        //assert
//        assertNull(testActivity?.likes?.find { it.name.equals(UserSingleton.loggedUser.name) })
//    }

    @After
    fun tearDown() {
        super.tearDownRealm()
        this.worker = null
    }
}

class MockFeedUpdateLogic: FeedWorkerUpdateLogic {

    var response1: FeedModel.GetFeedActivities.Response? = null
    var response2: FeedModel.LikeAndUnlike.Response? = null

    override fun updateFeed(response: FeedModel.GetFeedActivities.Response) {
        this.response1 = response
    }

    override fun updateLikes(response: FeedModel.LikeAndUnlike.Response) {
        this.response2 = response
    }
}
