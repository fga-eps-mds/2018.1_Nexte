package com.nexte.nexte.PlayersListScene

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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.URL
import java.util.*
import kotlin.concurrent.thread

class PlayersListWorkerTest: HelpForRealm() {

    private var worker: PlayersListWorker? = null
    private var updateLogicMocker: PlayerListUpdateLogicMocker? = null
    private val jsonObject = JSONObject()

    @Before
    fun setUp(){
        super.setUpWithUser()
        this.updateLogicMocker = PlayerListUpdateLogicMocker()
        this.worker = PlayersListWorker()
        this.worker?.updateLogic = updateLogicMocker
        this.worker?.userManager = UserManager(UserAdapterSpy())
        this.jsonToUserListTest()
    }

    @Test
    fun successFetchChallengedDetails(){
        //prepare
        val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(1)

        //call
        this.worker?.fetchChallengedDetails(request, { response ->
            //assert
            assertNotNull(response.challengedPersonalDetails)
        })
    }

    // When update logic is null and is realizes a request
    @Test
    fun httpRequestTest(){
        //prepare
        updateLogicMocker?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val backup = worker?.updateLogic
        worker?.updateLogic = null

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.httpHandler?.invoke(request, response, result) }.join()

        //assert
        assertNull(updateLogicMocker?.response)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun testFetchUsersOnFailure() {
        //prepare
        updateLogicMocker?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        worker?.httpHandler?.invoke(request, response, result)

        //assert
        assertNull(updateLogicMocker?.response)
    }

    @Test fun testGetters() {
        val updateLogic = worker?.updateLogic
        val userManager = worker?.userManager

        //assert
        assertEquals(worker?.updateLogic, updateLogic)
        assertEquals(worker?.userManager, userManager)
    }

    @Test
    fun testFetchUsersOnSucess() {
        //prepare
        updateLogicMocker?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.httpHandler?.invoke(request, response, result) }.join()

        //assert
        assertNotNull(updateLogicMocker?.response)
    }

    @Test
    fun updateLogicNullTest() {
        //prepare
        val backup = worker?.updateLogic
        worker?.updateLogic = null
        val request = PlayersListModel.ShowRankingPlayersRequest.Request(1)
        updateLogicMocker?.response = null

        thread{ worker?.fetchPlayersToChallenge(request) }.join()  //call

        assertNull(updateLogicMocker?.response) //assert

        worker?.updateLogic = backup //backup
    }

    @Test
    fun httpRequestWhenUpdateLogicIsNull() {
        //prepare
        updateLogicMocker?.response = null
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val backup = worker?.updateLogic
        worker?.updateLogic = null

        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        thread { worker?.httpHandler?.invoke(request, response, result) }.join()

        //assert
        assertNull(updateLogicMocker?.response)

        //backup
        worker?.updateLogic = backup
    }

    @Test
    fun successFetchPlayersChallenge(){
        //prepare
        val request = PlayersListModel.ShowRankingPlayersRequest.Request(1)

        //call
        thread { this.worker?.fetchPlayersToChallenge(request = request) }.join()

        //assert
        assertEquals(true, this.updateLogicMocker?.hasBeenHere)
    }

    @Test
    fun successGenerateChallenge(){
        //prepare
        val request = PlayersListModel.ChallengeButtonRequest.Request("Mendelson")

        //call
        this.worker?.generateChallenge(request, { response ->
            //prepare
            val username = response.username
            val match = response.challenge

            //assert
            assertNotNull(match)
            assertNotNull(response)
            assertEquals(username,"Mendelson")
        })

    }

    @Test
    fun jsonToUserListTest() {
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


        jsonObject.put("data", dataJson)

        // call
        val users = worker?.convertJsonToListOfUsers(jsonObject)

        //assert
        assertNotNull(users)
    }

    @After
    fun tearDown() {
        super.tearDownRealm()
        this.worker = null
    }

    class PlayerListUpdateLogicMocker: PlayerListUpdateLogic {

        var response: PlayersListModel.ShowRankingPlayersRequest.Response? = null
        var hasBeenHere = false

        override fun getPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
            this.hasBeenHere = true
            this.response = response
        }
    }
}