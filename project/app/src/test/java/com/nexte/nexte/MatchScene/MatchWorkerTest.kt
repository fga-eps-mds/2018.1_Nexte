package com.nexte.nexte.MatchScene

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeAdapterSpy
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.net.URL
import java.util.*
import kotlin.concurrent.thread


class MatchWorkerTest {
    private var worker: MatchWorker? = null
    private var mock: MockMatchUpdateWorkerLogic? = null

    @Before
    fun setUp() {
        this.worker = MatchWorker()
        mock = MockMatchUpdateWorkerLogic()
        this.worker?.updateLogic = mock
    }

    @Test
    fun testMatchWorker() {
        //prepare
        //call
        val testClass = MatchWorker()

        //assert
        assertNotNull(testClass)
    }

    @Test
    fun testFetchMatchDatas() {
        //prepare
        val match  = MatchModel.MatchData(
                MatchModel.MatchPlayer("larissa", 1),
                MatchModel.MatchPlayer("larissa2", 1), "1")
        val request = MatchModel.InitScene.Request(match)

        //calls
        worker?.fetchMatchData(request = request, completion = {response ->
            //asserts
            assertEquals("larissa", response.match.challenged.name)
            assertEquals("larissa2", response.match.challenger.name)
        })

    }

    @Test
    fun testFetchMatchDataWithNullMatch() {
        //prepare
        val request = MatchModel.InitScene.Request(null)

        //calls
        worker?.fetchMatchData(request = request, completion = {response ->
            //asserts
            assertEquals("Letícia", response.match.challenged.name)
            assertEquals("Alexandre Miguel", response.match.challenger.name)
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
    fun testGenerateMatchData(){
        //prepare and call
        val returnedValue = worker?.generateMatchData()
        //assert
        assertEquals("Letícia", returnedValue?.challenged?.name)
        assertEquals("Alexandre Miguel", returnedValue?.challenger?.name)
    }

    @Test
    fun testGenerateMatchResult(){
        //prepare
        val request = MatchModel.SendMatchResult.Request("1")

        //call
        this.worker?.generateMatchResult(request)

        //assert
        assertEquals(mock?.response?.status, MatchModel.SendMatchResult.Status.SUCESSED)
    }

    @Test
    fun testGenerateMatchResultWithUpdateLogic(){
        //prepare
        val request = MatchModel.SendMatchResult.Request("1")
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = null
        mock?.response = null
        //call
        this.worker?.generateMatchResult(request)

        //assert
        assertNull(mock?.response)
        this.worker?.updateLogic = updateLogic
    }

    @Test
    fun testDeclineMatchSuccess(){
        val request = MatchModel.DeclineChallengeRequest.Request("1")
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = updateLogic
        this.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        thread {this.worker?.declineMatch(request)}.join()

//        assertNotNull(this.mock?.responseDecline?.status)
    }

    @Test
    fun testDeclineMatchError(){
        val request = MatchModel.DeclineChallengeRequest.Request("2")
        val updateLogic = this.worker?.updateLogic
        this.worker?.updateLogic = updateLogic
        this.worker?.challengeManager = ChallengeManager(ChallengeAdapterSpy())

        this.worker?.declineMatch(request)

        assertNotNull(this.mock?.responseDecline)
    }

    @Test
    fun testCreateMatchPlayedJson(){
        var testJSON = JSONObject()

        val firstGameJSON = JSONObject()
        firstGameJSON.put("challenger", 1)
        firstGameJSON.put("challenged", 0)

        val gamesJSON = JSONObject()
        gamesJSON.put("firstGame", firstGameJSON)

        val challengeJSON = JSONObject()
        challengeJSON.put("setChallenger", 1)
        challengeJSON.put("setChallenged", 0)
        challengeJSON.put("date", Date().toString())
        challengeJSON.put("games", firstGameJSON)

        testJSON.put("action", "play")
        testJSON.put("challenge", challengeJSON)

        val stage = Challenge.Stage.Played(1, 0, Date(),
                Challenge.Stage.Played.Game(1, 0), null, null, null, null, "")

        val json = this.worker?.createMatchPlayedJson(stage)

        assertEquals(testJSON.toString(), json.toString())
    }

    @Test
    fun testCreateMatchCancelJson(){
        var testJSON = JSONObject()

        val challengeJSON = JSONObject()
        challengeJSON.put("reason", "123")
        challengeJSON.put("user", "123")

        testJSON.put("action", "cancel")
        testJSON.put("challenge", challengeJSON)

        val stage = Challenge.Stage.Canceled("123", Challenge.UserType.CHALLENGER, Date())

        val json = this.worker?.createMatchCancelJson(stage, "123")

        assertEquals(testJSON.toString(), json.toString())
    }

    @Test
    fun testHttpPostWithFailure(){
        //prepare
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val result: Result<Json, FuelError> = Result.error(FuelError(Exception("quero uma moto pra morrer antes dos 30")))

        //call
        this.worker?.httpPatchHandler?.invoke(request, response, result)

        //assert
    }

    @Test
    fun testHttpPostWithSuccess(){
        //prepare
        val jsonObject = JSONObject()
        val url = URL("http://www.randomsite.com/")
        val request = Request(Method.GET, "",url)
        val response = Response(url)
        val json = Json(jsonObject.toString())
        val result: Result<Json, FuelError> = Result.Success(json)

        //call
        this.worker?.httpPatchHandler?.invoke(request, response, result)

        //assert
    }


    @After
    fun tearDown() {
        this.worker = null
    }
}

private class MockMatchUpdateWorkerLogic: MatchUpdateWorkerLogic{
    override fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response) {
        this.responseDecline = response
    }

    var responseDecline: MatchModel.DeclineChallengeRequest.Response? = null
    var response: MatchModel.SendMatchResult.Response? = null

    override fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response) {
        this.response = response
    }
}
