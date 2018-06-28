package com.nexte.nexte.MatchScene

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import org.json.JSONObject
import java.util.*

/**
 * Created by leticia on 30/04/18.
 */

/**
 * Interface to define Response Logic of Match Class
 * that will be used to make the communication between worker and interactor
 */
interface MatchUpdateWorkerLogic {


    /**
     * Method that will be used to communicate with the presenter
     *
     * @param response contains the data about the status of the match result
     */
    fun getMatchResultResponse(response: MatchModel.SendMatchResult.Response)

    fun declineMatchResultResponse(response: MatchModel.DeclineChallengeRequest.Response)
}

/**
 * Class responsible to receive request, get Response from server and
 * call completion to send data for called class
 */
class MatchWorker {

    var updateLogic: MatchUpdateWorkerLogic? = null
    var challengeManager: ChallengeManager? = null
    val httpPatchHandler: (Request, Response, Result<Json, FuelError>) -> Unit = { _, _, result ->
        when (result) {
            is Result.Failure -> {
                println(result.getException())
            }

            is Result.Success -> {
                println(result.get())
            }
        }
    }

    /**
     * Function to get Match Data from server
     *
     * @param request String to identify the challenge to be passed
     * @param completion Challenge passed as a Response of the MatchData type
     */
    fun fetchMatchData (request: MatchModel.InitScene.Request, completion: (MatchModel.InitScene.Response) -> Unit) {

        val response = if(request.match == null) {
            MatchModel.InitScene.Response (this.generateMatchData())
        }
        else{
            MatchModel.InitScene.Response(request.match!!)
        }
        completion(response)
    }

    /**
     * Function that generates the Data to be added to Response, in order to simulate the action of
     * the server to get actual players. The function returns the format expected on response
     */
     fun generateMatchData() : MatchModel.MatchData{

        val challenger = MatchModel.MatchPlayer("Letícia",  R.mipmap.ic_launcher )

        val challenged = MatchModel.MatchPlayer("Alexandre Miguel", R.mipmap.ic_launcher )

        return MatchModel.MatchData(challenger, challenged, "1")
    }

    /**
     * Method responsible for saving the match result on the database
     *
     * @param request data that contains the request of the match result
     */
    fun generateMatchResult(request: MatchModel.SendMatchResult.Request) {
        val response = MatchModel.SendMatchResult.Response(
                MatchModel.SendMatchResult.Status.SUCESSED)
        val playedChallenge = challengeManager?.get(request.challengeId)

        if (playedChallenge != null) {
            val stage = Challenge.Stage.Played(1, 0, Date(),
                    Challenge.Stage.Played.Game(8, 4), null, null,
                    null, null, "Nothing")
            playedChallenge.stage = stage
            challengeManager?.update(playedChallenge)

            if(UserSingleton.userType == UserType.REAL) {
                val header = mapOf("Content-Type" to "application/json",
                        "Accept-Version" to "0.1.0")
                val url = "http://10.0.2.2:3000/challenges/" + playedChallenge.id
                val challengeJSON = createMatchPlayedJson(stage)
                Fuel.patch(url).header(header).body(challengeJSON.toString()).responseJson(httpPatchHandler)
            }
        }

        updateLogic?.getMatchResultResponse(response)

    }

    /**
     *  Function to decline a match
     *
     *  @param request the request of a challenge
     */
    fun declineMatch(request: MatchModel.DeclineChallengeRequest.Request){
        val deletedChallenge = challengeManager?.get(request.challengeId)
        var response: MatchModel.DeclineChallengeRequest.Response? = null

        if (deletedChallenge != null){
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.SUCCESS)
            val stage = Challenge.Stage.Canceled("Usuário não aceitou", date = Date(),
                    user = Challenge.UserType.CHALLENGER)
            deletedChallenge.stage = stage
            challengeManager?.update(deletedChallenge)

            if(UserSingleton.userType == UserType.REAL) {
                val header = mapOf("Content-Type" to "application/json",
                        "Accept-Version" to "0.1.0")
                val url = "http://10.0.2.2:3000/challenges/" + deletedChallenge.id
                val challengeJSON = createMatchCancelJson(stage, UserSingleton.loggedUserID)
                Fuel.patch(url).header(header).body(challengeJSON.toString()).responseJson(httpPatchHandler)
            }
        }else{
            response = MatchModel.DeclineChallengeRequest.Response(MatchModel.DeclineChallengeRequest
                    .Status.ERROR)
        }
        updateLogic?.declineMatchResultResponse(response)
    }

    /**
     * Method responsible for creating the JSON for canceled games
     *
     * @param stage stage of the challenge
     * @param user user id
     *
     * @return JSON for the canceled request
     */
    fun createMatchCancelJson(stage: Challenge.Stage, user: String): JSONObject{
        var returnJSON = JSONObject()
        val gameStage = stage as Challenge.Stage.Canceled

        val challengeJSON = JSONObject()
        challengeJSON.put("reason", gameStage.reason)
        challengeJSON.put("user", user)

        returnJSON.put("action", "cancel")
        returnJSON.put("challenge", challengeJSON)

        return returnJSON
    }

    /**
     * Method responsible for creating the JSON for played games
     *
     * @param stage stage of the challenge
     *
     * @return JSON for the played request
     */
    fun createMatchPlayedJson(stage: Challenge.Stage): JSONObject{
        var returnJSON = JSONObject()
        val gameStage = stage as Challenge.Stage.Played

        val firstGameJSON = JSONObject()
        firstGameJSON.put("challenger", gameStage.firstGame.gameChallenger)
        firstGameJSON.put("challenged", gameStage.firstGame.gameChallenged)

        val gamesJSON = JSONObject()
        gamesJSON.put("firstGame", firstGameJSON)

        val challengeJSON = JSONObject()
        challengeJSON.put("setChallenger", gameStage.setChallenger)
        challengeJSON.put("setChallenged", gameStage.setChallenged)
        challengeJSON.put("date", gameStage.date.toString())
        challengeJSON.put("games", firstGameJSON)

        returnJSON.put("action", "play")
        returnJSON.put("challenge", challengeJSON)

        return returnJSON
    }

}