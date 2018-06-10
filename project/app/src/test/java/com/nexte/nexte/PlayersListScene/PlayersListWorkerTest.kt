package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.json.JSONArray
import org.json.JSONObject
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.concurrent.thread

class PlayersListWorkerTest: HelpForRealm() {

    private var worker: PlayersListWorker? = null
    private var updateLogicMocker: PlayerListUpdateLogicMocker? = null

    @Before
    fun setUp(){
        super.setUpWithUser()
        this.updateLogicMocker = PlayerListUpdateLogicMocker()
        this.worker = PlayersListWorker()
        this.worker?.updateLogic = updateLogicMocker
        this.worker?.userManager = UserManager(UserAdapterSpy())
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

//    @Test
//    fun jsonToUserListTest() {
//        //prepare
//        val userJson = JSONObject()
//        userJson.put("id", "1")
//        userJson.put("name", "Rafael Pardal")
//        userJson.put("profileImageURL", "9842u349h")
//        userJson.put("nickname", "100")
//        userJson.put("birthDate", "22-05-1987")
//        userJson.put("rankPosition", 100)
//        userJson.put("email", "5454623")
//        userJson.put("phone", "436453")
//        userJson.put("wins", 2)
//        userJson.put("loses", 3)
//        userJson.put("gender", "M")
//        userJson.put("category", 1)
//        userJson.put("status", 1)
//
//        val usersJson = JSONArray()
//        usersJson.put(userJson)
//
//        val dataObject = JSONObject()
//        dataObject.put("users", usersJson)
//
//        val jsonObject = JSONObject()
//        jsonObject.put("data", dataObject)
//
//        //call
//        val users = this.worker?.convertJsonToListOfUsers(jsonObject)
//
//        //assert
//        assertNotNull(users)
//    }

    @After
    fun tearDown(){
        super.tearDownRealm()
        this.worker = null
    }

    class PlayerListUpdateLogicMocker: PlayerListUpdateLogic {

        var hasBeenHere = false

        override fun getPlayersToChallenge(response: PlayersListModel.ShowRankingPlayersRequest.Response) {
            this.hasBeenHere = true
        }
    }

}