package com.nexte.nexte.ShowProfileScene

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
import kotlin.concurrent.thread

class ShowProfileWorkerTest : HelpForRealm() {

    private var worker: ShowProfileWorker? = null
    private var mock: MockShowProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = ShowProfileWorker()
        this.mock = MockShowProfileWorker()
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