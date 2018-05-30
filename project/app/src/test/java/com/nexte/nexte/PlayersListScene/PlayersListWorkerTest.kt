package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class PlayersListWorkerTest{

    private var worker: PlayersListWorker? = null

    @Before
    fun setUp(){
        this.worker = PlayersListWorker()
    }

    @Test
    fun successFetchPlayersChallenge(){
        //prepare
        val request = PlayersListModel.ShowRankingPlayersRequest.Request(1)

        //call
        this.worker?.fetchPlayersToChallenge(request, { response ->
            //assert
            assertNotNull(response)
        })

    }

//    @Test
//    fun successFetchChallengedDetails(){
//        //prepare
//        val request = PlayersListModel.SelectPlayerForChallengeRequest.Request(2)
//
//        //call
//        this.worker?.fetchChallengedDetails(request, { response ->
//            //assert
//            assertNotNull(response.challengedPersonalDetails)
//        })
//
//    }

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
        this.worker = null

    }


}