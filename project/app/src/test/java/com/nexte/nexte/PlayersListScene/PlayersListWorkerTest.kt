package com.nexte.nexte.PlayersListScene

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class PlayersListWorkerTest {

    private var worker: PlayersListWorker? = null

    @Before
    fun setUp() {
        this.worker = PlayersListWorker()
    }

    @Test
    fun fetchPlayersToChallenge() {
        //prepare
        val request = PlayersListModel.ShowRankingPlayersRequest.Request(6)
        val request2 = PlayersListModel.ShowRankingPlayersRequest.Request(2)

        //call
        worker?.fetchPlayersToChallenge(request){ response->
            val players = response.usersAbove
            //assert
            assertEquals(players.size, 5)
        }

        //call
        worker?.fetchPlayersToChallenge(request2){ response->
            val players = response.usersAbove
            //assert
            assertEquals(players.size, 1)
        }
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}