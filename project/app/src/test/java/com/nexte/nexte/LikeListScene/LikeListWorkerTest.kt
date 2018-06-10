package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.Like.LikeAdapterSpy
import com.nexte.nexte.Entities.Like.LikeManager
import com.nexte.nexte.Entities.Story.StoryAdapterSpy
import com.nexte.nexte.Entities.Story.StoryManager
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListWorkerTest {

    var worker: LikeListWorker? = null
    var mock: MockWorkersUpdateLogic? = null

    @Before
    fun setUp() {
        this.worker = LikeListWorker()
        this.mock = MockWorkersUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(userAdapter = UserAdapterSpy())
        this.worker?.storyManager = StoryManager(StoryAdapterSpy())
        this.worker?.likeManager = LikeManager(LikeAdapterSpy())
    }

    @Test
    fun testGetListLikesPlayers(){
        //prepare
        val request = LikeListModel.Request("1", "1")

        //call
        this.worker?.getListLikesPlayers(request = request)
        //assert

        assertEquals(this.mock?.response?.players?.size, 1)
        assertEquals(this.mock?.response?.players!![0].name, "User test")
    }

    @Test
    fun testJsonConvertJsonToListOfLikes(){
        val likeJson = JSONObject()
        likeJson.put("date", "2018-01-07T00:00:00.000Z")
        likeJson.put("id", "asdasd")
        likeJson.put("user", "asdasd")

        val likesJsonArray = JSONArray()
        likesJsonArray.put(likeJson)

        val dataObject = JSONObject()
        dataObject.put("likes",likesJsonArray)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataObject)

        println(jsonObject)

        val likes = this.worker?.convertJsonToListOfLikes(jsonObject)

        assertEquals(likes!![0].id, likeJson["id"] as String)
        assertEquals(likes!![0].userId, likeJson["user"] as String)
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

class MockWorkersUpdateLogic: WorkerUpdateLogic {

    var response: LikeListModel.Response? = null

    override fun updateUsers(response: LikeListModel.Response) {
        this.response = response
    }
}