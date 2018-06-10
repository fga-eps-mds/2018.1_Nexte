package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserCategory.UserCategoryManager
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.Player
import org.json.JSONObject
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class ShowProfileWorkerTest : HelpForRealm() {

    private var worker: ShowProfileWorker? = null
    private var mock: MockShowProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
        this.mock = MockShowProfileWorker()
        super.setUpWithUser()
        super.setUpRealm()
    }

    @Test
    fun testGetUserProfileSuccess(){
        //prepare
        worker?.userManager = UserManager(UserAdapterSpy())
        val userManager = worker?.userManager
        worker?.updateLogic = mock
        val updateLogic = worker?.updateLogic
        val request = ShowProfileModel.Request("Robert Baptist")

        //call
        thread { this.worker?.getUserProfile(request = request) }.join()

        assertNotNull(updateLogic)
        assertNotNull(userManager)
        assertNotNull(this.mock?.response)
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
    fun successConvertJsonUserToUser(){
        //prepare
        val jsonUser = JSONObject()

        val id = "1"
        val name = "gabriel"
        val profilePicture = "www.lol.com"
        val nickname = "bino"
        val rankingPosition = 2
        val email = "g@g.g"
        val phone = "5561999999999"
        val wins = 3
        val loses = 2
        val birthDate = Date(1987, 5, 15)
        val genderString = "M"
        val categoryInt = 1
        val statusInt = 0

        jsonUser.put("id", id)
        jsonUser.put("name", name)
        jsonUser.put("profilePicture", profilePicture)
        jsonUser.put("nickname", nickname)
        jsonUser.put("rankingPosition", rankingPosition)
        jsonUser.put("email", email)
        jsonUser.put("phone", phone)
        jsonUser.put("wins", wins)
        jsonUser.put("loses", loses)
        jsonUser.put("birthDate", birthDate.toString())
        jsonUser.put("gender", genderString)
        jsonUser.put("category", categoryInt)
        jsonUser.put("status", statusInt)

        val dataJson = JSONObject()
        dataJson.put("user", jsonUser)

        val jsonObject = JSONObject()
        jsonObject.put("data", dataJson)
        var user: User? = null
        var userToCompare: User? = null
        thread{
            //call
            user = worker?.convertJsonUserToUser(jsonObject)
        }.join()
        //assert
        assertEquals(jsonUser["id"] as String, user?.id)
        assertEquals(jsonUser["name"] as String, user?.name)
        assertEquals(jsonUser["profilePicture"] as String, user?.profilePicture)
        assertEquals(jsonUser["nickname"] as String, user?.nickname)
        assertEquals(jsonUser["rankingPosition"] as Int, user?.rankingPosition)
        assertEquals(jsonUser["email"] as String, user?.email)
        assertEquals(jsonUser["phone"] as String, user?.phone)
        assertEquals(jsonUser["wins"] as Int, user?.wins)
        assertEquals(jsonUser["loses"] as Int, user?.loses)
        assertEquals(jsonUser["birthDate"] as String, user?.birthDate.toString())
        assertEquals(jsonUser["gender"] as String, User.Gender.MALE)
        assertEquals(jsonUser["category"] as String, "Primeira Classe")
        assertEquals(jsonUser["status"] as String, User.Status.AVAILABLE)
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
    fun tearDown() {
        this.worker = null
    }
}

class MockShowProfileWorker: ShowProfileWorkerUpdateLogic {

    var response: ShowProfileModel.Response? = null

    override fun updateUserProfile(response: ShowProfileModel.Response) {
        this.response = response
    }
}