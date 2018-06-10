package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.HelpForRealm
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*
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
            //assert
            assertNotNull(response)
        })

    }

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