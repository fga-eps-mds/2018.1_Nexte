package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player
import org.junit.Test

import org.junit.Assert.*

class ChallengeMockerTest {

    @Test
    fun createPlayers() {
        //prepare
        val player = Player(
                "nome1",
                1,
                "",
                "",
                "",
                "",
                0,
                ""
        )

        //call
        val players = ChallengeMocker.createPlayers()

        //assert
        assertEquals(player, players[0])
        assertEquals(players.size, 10)
    }
}