package com.nexte.nexte.ShowProfileScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.net.URL
import kotlin.concurrent.thread

class ShowProfileWorkerTest : HelpForRealm() {

    private var worker: ShowProfileWorker? = null
    private var mock: MockShowProfileWorker? = null

    private val jsonObject = JSONObject()

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
        this.mock = MockShowProfileWorker()
        super.setUpWithUser()

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

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)
        jsonObject.put("data", dataJson)
    }

    @Test
    fun successConvertJsonToUser(){
        //call
        val user: User? = worker?.convertJsonUserToUser(jsonObject)
        println(user)
        //assert
        assertNotNull(user)
    }

    @Test
    fun testGetUserProfileSuccess(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = worker?.userManager
        worker?.updateLogic = mock
        val updateLogic = worker?.updateLogic
        val request = ShowProfileModel.Request("1")

        //call
        thread {
            this.worker?.getUserProfile(request = request)
        }.join()
        assertNotNull(updateLogic)
        assertNotNull(userManager)
        assertNotNull(this.mock?.response)
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
        worker?.httpRequestHandler?.invoke(request, response, result)

        //assert
        assertNull(mock?.response)
    }

    @Test
    fun testHttpGetWithSuccess(){
        //prepare
        mock?.response = null
        worker?.updateLogic = mock
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        worker?.httpRequestHandler?.invoke(request, response, result)

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
        thread { worker?.httpRequestHandler?.invoke(request, response, result) }.join()

        //assert
        assertNull(mock?.response)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun testGetUserProfileFailed(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = worker?.userManager
        worker?.updateLogic = mock
        val updateLogic = worker?.updateLogic
        val request = ShowProfileModel.Request("anythingtofail")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNotNull(updateLogic)
        assertNotNull(userManager)
        assertNotNull(this.mock?.response)
    }

    @Test
    fun successHttpRequestHandler(){

    }

    @Test
    fun successConvertJsonUserToUserCase1(){
        //prepare
        val jsonUser = JSONObject()

        val id = "123"
        val name = "gabriel"
        val profilePicture = "www.lol.com"
        val nickname = "bino"
        val rankingPosition = 2
        val email = "g@g.g"
        val phone = "5561999999999"
        val wins = 3
        val loses = 2
        val birthDate = "2018-01-07T00:00:00.000Z"
        val genderString = "M"
        val categoryInt = 1
        val statusInt = 0

        jsonUser.put("id", id)
        jsonUser.put("name", name)
        jsonUser.put("profileImageURL", profilePicture)
        jsonUser.put("nickname", nickname)
        jsonUser.put("rankPosition", rankingPosition)
        jsonUser.put("email", email)
        jsonUser.put("phone", phone)
        jsonUser.put("wins", wins)
        jsonUser.put("loses", loses)
        jsonUser.put("birthDate", birthDate)
        jsonUser.put("gender", genderString)
        jsonUser.put("category", categoryInt)
        jsonUser.put("status", statusInt)

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        var user: User? = null

        //call
        thread{ user = worker?.convertJsonUserToUser(jsonObject, UserCategoryAdapterSpy())}.join()

        //assert
        assertEquals(jsonUser["id"] as String, user?.id)
        assertEquals(jsonUser["name"] as String, user?.name)
        assertEquals(jsonUser["profileImageURL"] as String, user?.profilePicture)
        assertEquals(jsonUser["nickname"] as String, user?.nickname)
        assertEquals(jsonUser["rankPosition"] as Int, user?.rankingPosition)
        assertEquals(jsonUser["email"] as String, user?.email)
        assertEquals(jsonUser["phone"] as String, user?.phone)
        assertEquals(jsonUser["wins"] as Int, user?.wins)
        assertEquals(jsonUser["loses"] as Int, user?.loses)
        assertEquals(user?.gender, User.Gender.MALE)
        assertEquals(user?.status, User.Status.AVAILABLE)
    }

    @Test
    fun successConvertJsonUserToUserCase2(){
        //prepare
        val jsonUser = JSONObject()

        val id = "123"
        val name = "gabriel"
        val profilePicture = "www.lol.com"
        val nickname = "bino"
        val rankingPosition = 2
        val email = "g@g.g"
        val phone = "5561999999999"
        val wins = 3
        val loses = 2
        val birthDate = "2018-01-07T00:00:00.000Z"
        val genderString = "F"
        val categoryInt = 1
        val statusInt = 0

        jsonUser.put("id", id)
        jsonUser.put("name", name)
        jsonUser.put("profileImageURL", profilePicture)
        jsonUser.put("nickname", nickname)
        jsonUser.put("rankPosition", rankingPosition)
        jsonUser.put("email", email)
        jsonUser.put("phone", phone)
        jsonUser.put("wins", wins)
        jsonUser.put("loses", loses)
        jsonUser.put("birthDate", birthDate)
        jsonUser.put("gender", genderString)
        jsonUser.put("category", categoryInt)
        jsonUser.put("status", statusInt)

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        var user: User? = null

        //call
        thread{ user = worker?.convertJsonUserToUser(jsonObject, UserCategoryAdapterSpy())}.join()

        //assert
        assertEquals(jsonUser["id"] as String, user?.id)
        assertEquals(jsonUser["name"] as String, user?.name)
        assertEquals(jsonUser["profileImageURL"] as String, user?.profilePicture)
        assertEquals(jsonUser["nickname"] as String, user?.nickname)
        assertEquals(jsonUser["rankPosition"] as Int, user?.rankingPosition)
        assertEquals(jsonUser["email"] as String, user?.email)
        assertEquals(jsonUser["phone"] as String, user?.phone)
        assertEquals(jsonUser["wins"] as Int, user?.wins)
        assertEquals(jsonUser["loses"] as Int, user?.loses)
        assertEquals(user?.gender, User.Gender.FEMALE)
        assertEquals(user?.status, User.Status.AVAILABLE)
    }

    @Test
    fun successConvertJsonUserToUserCase3(){
        //prepare
        val jsonUser = JSONObject()

        val id = "123"
        val name = "gabriel"
        val profilePicture = "www.lol.com"
        val nickname = "bino"
        val rankingPosition = 2
        val email = "g@g.g"
        val phone = "5561999999999"
        val wins = 3
        val loses = 2
        val birthDate = "2018-01-07T00:00:00.000Z"
        val genderString = "F"
        val categoryInt = 1
        val statusInt = 1

        jsonUser.put("id", id)
        jsonUser.put("name", name)
        jsonUser.put("profileImageURL", profilePicture)
        jsonUser.put("nickname", nickname)
        jsonUser.put("rankPosition", rankingPosition)
        jsonUser.put("email", email)
        jsonUser.put("phone", phone)
        jsonUser.put("wins", wins)
        jsonUser.put("loses", loses)
        jsonUser.put("birthDate", birthDate)
        jsonUser.put("gender", genderString)
        jsonUser.put("category", categoryInt)
        jsonUser.put("status", statusInt)

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        var user: User? = null

        //call
        thread{ user = worker?.convertJsonUserToUser(jsonObject, UserCategoryAdapterSpy())}.join()

        //assert
        assertEquals(jsonUser["id"] as String, user?.id)
        assertEquals(jsonUser["name"] as String, user?.name)
        assertEquals(jsonUser["profileImageURL"] as String, user?.profilePicture)
        assertEquals(jsonUser["nickname"] as String, user?.nickname)
        assertEquals(jsonUser["rankPosition"] as Int, user?.rankingPosition)
        assertEquals(jsonUser["email"] as String, user?.email)
        assertEquals(jsonUser["phone"] as String, user?.phone)
        assertEquals(jsonUser["wins"] as Int, user?.wins)
        assertEquals(jsonUser["loses"] as Int, user?.loses)
        assertEquals(user?.gender, User.Gender.FEMALE)
        assertEquals(user?.status, User.Status.INJURED)
    }


    @Test
    fun successConvertJsonUserToUserCase4(){
        //prepare
        val jsonUser = JSONObject()

        val id = "123"
        val name = "gabriel"
        val profilePicture = "www.lol.com"
        val nickname = "bino"
        val rankingPosition = 2
        val email = "g@g.g"
        val phone = "5561999999999"
        val wins = 3
        val loses = 2
        val birthDate = "2018-01-07T00:00:00.000Z"
        val genderString = "F"
        val categoryInt = 1
        val statusInt = 2

        jsonUser.put("id", id)
        jsonUser.put("name", name)
        jsonUser.put("profileImageURL", profilePicture)
        jsonUser.put("nickname", nickname)
        jsonUser.put("rankPosition", rankingPosition)
        jsonUser.put("email", email)
        jsonUser.put("phone", phone)
        jsonUser.put("wins", wins)
        jsonUser.put("loses", loses)
        jsonUser.put("birthDate", birthDate)
        jsonUser.put("gender", genderString)
        jsonUser.put("category", categoryInt)
        jsonUser.put("status", statusInt)

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        var user: User? = null

        //call
        thread{ user = worker?.convertJsonUserToUser(jsonObject, UserCategoryAdapterSpy())}.join()

        //assert
        assertEquals(jsonUser["id"] as String, user?.id)
        assertEquals(jsonUser["name"] as String, user?.name)
        assertEquals(jsonUser["profileImageURL"] as String, user?.profilePicture)
        assertEquals(jsonUser["nickname"] as String, user?.nickname)
        assertEquals(jsonUser["rankPosition"] as Int, user?.rankingPosition)
        assertEquals(jsonUser["email"] as String, user?.email)
        assertEquals(jsonUser["phone"] as String, user?.phone)
        assertEquals(jsonUser["wins"] as Int, user?.wins)
        assertEquals(jsonUser["loses"] as Int, user?.loses)
        assertEquals(user?.gender, User.Gender.FEMALE)
        assertEquals(user?.status, User.Status.UNAVAILABLE)
    }

    @Test
    fun testGetUserProfileWithNullUserSuccess(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = null
        worker?.userManager = userManager
        val request = ShowProfileModel.Request("Robert Baptist")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNull(this.mock?.response)
    }

    @After
    fun tearDown(){
        super.tearDownRealm()
        this.worker = null
    }
}

class MockShowProfileWorker: ShowProfileWorkerUpdateLogic {

    var response: ShowProfileModel.Response? = null

    override fun updateUserProfile(response: ShowProfileModel.Response) {
        this.response = response
    }
}