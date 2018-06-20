package com.nexte.nexte.PlayersListScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeAdapterSpy
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.UserSingleton
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.net.URL
import java.util.*
import kotlin.concurrent.thread

    @Suppress("DEPRECATION")
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
            this.worker?.fetchChallengedDetails(request) { response ->
                //assert
                assertNotNull(response.challengedPersonalDetails)
            }
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
            val request = PlayersListModel.ChallengeButtonRequest.Request("1")

            //call
            this.worker?.generateChallenge(request)

            //assert
            assertNotNull(this.updateLogicMocker?.response2?.challenge)
            assertNotNull(this.updateLogicMocker?.response2)
            assertEquals(this.updateLogicMocker?.response2?.challenge?.challenged?.name,"User test")
            assertEquals(this.updateLogicMocker?.response2?.challenge?.challenger?.name,UserSingleton.loggedUser.name)

        }

        @Test
        private fun jsonToUserListTest() {
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

        @Test
        fun testCreateChallengeJson(){
            val challenge = Challenge(id = "1", challengeDate = Date(2018, 5, 5),
                    status = Challenge.Status.WAITING, stage = Challenge.Stage.Scheduled(),
                    challengedId = "2", challengerId = "3")

            val jsonChallenge = this.worker?.createChallengeJson(challenge)
            this.worker?.httpPostHandler
            this.worker?.challengeManager
            this.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

            val challengeJson = JSONObject()
            challengeJson.put("challenger", challenge.challengerId)
            challengeJson.put("challenged", challenge.challengedId)
            challengeJson.put("date", challenge.challengeDate)
            val json = JSONObject()
            json.put("challenge", challengeJson)

            assertEquals(json.toString(), jsonChallenge.toString())

        }

        @Test
        fun testHttpPostWithFailure(){
            //prepare
            val url = URL("http://www.randomsite.com/")
            val request = Request(Method.GET, "",url)
            val response = Response(url)
            val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

            //call
            this.worker?.httpPostHandler?.invoke(request, response, result)

            //assert
            assertFalse(this.worker?.httpBool!!)
        }

        @Test
        fun testHttpPostWithSuccess(){
            //prepare

            val url = URL("http://www.randomsite.com/")
            val request = Request(Method.GET, "",url)
            val response = Response(url)
            val json = Json(jsonObject.toString())
            val result: Result<Json, FuelError> = Result.Success(json)

            //call
            this.worker?.httpPostHandler?.invoke(request, response, result)

            //assert
            assertTrue(this.worker?.httpBool!!)

        }

        @Test
        fun testCreateNewResponseFromServer(){
            //prepare

            val usersList = JSONArray()
            usersList.put(generateJSONUser(3))
            usersList.put(generateJSONUser(1))
            usersList.put(generateJSONUser(5))
            usersList.put(generateJSONUser(2))
            usersList.put(generateJSONUser(4))

            val dataJson = JSONObject()
            dataJson.put("users", usersList)

            val json = JSONObject()
            json.put("data", dataJson)

            this.worker?.createNewResponseFromServer(json)

            assertNotNull(this.updateLogicMocker?.response)
            assertEquals(this.updateLogicMocker?.response?.usersAbove?.size, 5)
            assertEquals(this.updateLogicMocker?.response?.usersAbove!![0].rankingPosition, 1)

        }

        // ESTE MÉTODO NÃO É UM TESTE
        // APENAS SEPAREI O PREPARE DE UM TESTE NESSE MÉTODO
        private fun generateJSONUser(rankingPosition: Int) : JSONObject {
            val challengeReceived = JSONObject()
            challengeReceived.put("sended", false)
            challengeReceived.put("id", "2")

            val challengeSended = JSONObject()
            challengeSended.put("sended", false)
            challengeSended.put("id", "1")


            val user1 = JSONObject()
            user1.put("id", "1")
            user1.put("lastestMatches", JSONArray())
            user1.put("profileImageURL", "123")
            user1.put("name", "123")
            user1.put("nickname", "123")
            user1.put("gender", "123")
            user1.put("category", 123)
            user1.put("email", "123")
            user1.put("phone", "123")
            user1.put("status", 1)
            user1.put("wins", 123)
            user1.put("loses", 123)
            user1.put("accountStatus", 123)
            user1.put("rankPosition", rankingPosition)
            user1.put("challengeSended", challengeSended)
            user1.put("challengeReceived", challengeReceived)

            return user1
        }


        @After
        fun tearDown() {
            super.tearDownRealm()
            this.worker = null
        }

        class PlayerListUpdateLogicMocker: PlayerListUpdateLogic {

            var response: PlayersListModel.ShowRankingPlayersRequest.Response? = null
            var hasBeenHere = false
            var response2: PlayersListModel.ChallengeButtonRequest.Response? = null

            override fun getPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
                this.hasBeenHere = true
                this.response = response
            }

            override fun generateChallengeReponse(response: PlayersListModel.ChallengeButtonRequest.Response) {
                this.response2 = response
            }
        }
    }