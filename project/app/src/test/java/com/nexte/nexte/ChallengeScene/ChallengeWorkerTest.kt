package com.nexte.nexte.ChallengeScene

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ChallengeWorkerTest {

    private var worker: ChallengeWorker? = null

    @Before
    fun setUp() {
        this.worker = ChallengeWorker()
    }

    @Test
    fun fetchPlayersToChallenge() {
        //prepare
        val request = ChallengeModel.ShowRankingPlayersRequest.Request(6)
        val request2 = ChallengeModel.ShowRankingPlayersRequest.Request(2)

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