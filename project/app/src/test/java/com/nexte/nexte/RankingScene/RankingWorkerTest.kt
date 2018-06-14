package com.nexte.nexte.RankingScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.R
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.net.URL
import java.util.*
import kotlin.concurrent.thread

class RankingWorkerTest : HelpForRealm() {

    var worker: RankingWorker? = null
    var mock: MockRankingWorkerUpdateLogic? = null
    private val jsonObject = JSONObject()

    @Before
    fun setUp() {
        super.setUpWithUser()
        this.worker = RankingWorker()
        this.mock = MockRankingWorkerUpdateLogic()
        this.worker?.updateLogic = mock
        this.worker?.userManager = UserManager(UserAdapterSpy())

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


        jsonObject.put("data", dataJson)
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
    fun testSorting(){
        val user1 = User("1",
                "André Rede",
                R.drawable.profile_image1.toString(),
                "André",
                Date(1987, 5, 15),
                3,
                "andre@nexte.com",
                "130",
                162,
                69,
                User.Gender.MALE,
                null,
                User.Status.AVAILABLE,
                null,
                null,
                emptyList()
        )
        val user2 = User("2",
        "Nova Jucá",
        R.drawable.profile_image2.toString(),
        "Nova",
        Date(1987, 5, 22),
        4,
        "nova@nexte.com",
        "130",
        165,
        63,
        User.Gender.MALE,
        null,
        User.Status.AVAILABLE,
        null,
        null,
        emptyList()
        )
        var unorderedList: List<User>? = listOf(user2, user1)
        unorderedList = worker?.sortListByRanking(unorderedList)

        assertEquals(unorderedList!![0].rankingPosition, 3)
        assertEquals(unorderedList[1].rankingPosition, 4)
    }

    @Test
    fun testHttpGetWithFailure(){
        //prepare
        mock?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        worker?.httpGetHandler?.invoke(request, response, result)

        //assert
        assertNull(mock?.response)
    }

    @Test
    fun testHttpGetWithSuccess(){
        //prepare
        mock?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.httpGetHandler?.invoke(request, response, result) }.join()

        //assert
        assertNotNull(mock?.response)
    }

    @Test
    fun testHttpGetWithoutUpdateLogic(){
        //prepare
        mock?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val backup = worker?.updateLogic
        worker?.updateLogic = null

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.httpGetHandler?.invoke(request, response, result) }.join()

        //assert
        assertNull(mock?.response)

        //backup
        worker?.updateLogic = backup
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
    fun successConvertJsonToListOfUsersCase1(){
        //call
        val users = worker?.convertJsonToListOfUsers(jsonObject)
        //assert
        assertNotNull(users)
    }

    @Test
    fun successConvertJsonToListOfUsersCase2(){
        //prepare
        val usersJsonArray = JSONArray()
        val dataJson = JSONObject()
        dataJson.put("users", usersJsonArray)
        val jsonObj = JSONObject()
        jsonObj.put("data", dataJson)
        //call
        val users = worker?.convertJsonToListOfUsers(jsonObject)
        //assert
        assertNotNull(users)
    }

    class MockRankingWorkerUpdateLogic: RankingWorkerUpdateLogic{
        var response: RankingModel.Response? = null

        override fun updateUsersInRanking(response: RankingModel.Response) {
            this.response = response
        }
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}
