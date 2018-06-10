package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*
import kotlin.concurrent.thread

class RankingWorkerTest : HelpForRealm() {

    var worker: RankingWorker? = null
    var mock: MockRankingWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = RankingWorker()
        this.mock = MockRankingWorkerUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(UserAdapterSpy())
    }

    @Test
    fun testGetters(){
        //prepare  and call
        val updateLogic = worker?.updateLogic
        val userManager = worker?.userManager

        //assert
        assertEquals(worker?.updateLogic, updateLogic)
        assertEquals(worker?.userManager, userManager)

    }

    @Test
    fun testNullUpdateLogic(){
        //prepare
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        val request = RankingModel.Request()
        mock?.response = null
        //call
        thread{
            worker?.getUsersInRanking(request)
        }.join()
        //assert
        assertNull(mock?.response)
        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun successConvertJsonToListOfUsers(){
        //prepare
        val jsonUser = JSONObject()
        jsonUser.put("id", "1")
        jsonUser.put("name", "teste")
        jsonUser.put("profileImageURL", "www.lol.com.br")
        jsonUser.put("nickname", "biel")
        jsonUser.put("rankPosition", 1)
        jsonUser.put("email", "biel@poc.br")
        jsonUser.put("phone", "3232323232")
        jsonUser.put("wins", 1)
        jsonUser.put("loses", 1)
        jsonUser.put("birthDate", "2018-01-07T00:00:00.000Z")
        jsonUser.put("gender", "M")
        jsonUser.put("category", 1)
        jsonUser.put("status", 1)

        val usersJsonArray = JSONArray()
        usersJsonArray.put(jsonUser)

        val dataJson = JSONObject()
        dataJson.put("users", usersJsonArray)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        //call
        val users = worker?.convertJsonToListOfUsers(jsonObject, UserCategoryAdapterSpy())
        //assert
        assertNotNull(users)
    }

//    @Test
//    fun testGetUsersInRanking(){
//        //prepare
//        val request = RankingModel.Request()
//
//        //call
//        thread { this.worker?.getUsersInRanking(request = request) }.join()
//
//        //assert
//        assertEquals(this.mock?.response?.users?.size, 9)
//        assertEquals(this.mock?.response?.users!![0].name, "User test")
//        assertEquals(this.mock?.response?.users!![3].wins, 0)
//        assertEquals(this.mock?.response?.users!![1].rankingPosition, 1)
//        assertEquals(this.mock?.response?.users!![4].category, null)
//        assertEquals(this.mock?.response?.users!![5].latestGames?.size, 0)
//        assertEquals(this.mock?.response?.users!![6].loses, 0)
//
//    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

class MockRankingWorkerUpdateLogic: RankingWorkerUpdateLogic{
    var response: RankingModel.Response? = null

    override fun updateUsersInRanking(response: RankingModel.Response) {
        this.response = response
    }
}