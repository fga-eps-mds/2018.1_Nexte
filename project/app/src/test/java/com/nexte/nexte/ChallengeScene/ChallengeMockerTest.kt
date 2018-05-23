package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player
import org.junit.Test

import org.junit.Assert.*

class ChallengeMockerTest {

    @Test
    fun successCreatePlayers() {
        //prepare
        val player = Player(
                "nome1",
                1,
                "",
                "",
                "",
                "",
                0,
                "",
                ""
        )

        //call
        val players = ChallengeMocker.createPlayers()

        //assert
        assertEquals(player.name, players[0].name)
        assertEquals(player.rankingPosition, players[0].rankingPosition)
        assertEquals(players.size, 10)
    }

    @Test
    fun successCreatedDetailedPlayer(){
        //prepare
        val name = String.format("nome%d", 1)
        val wins = 10
        val loses = 0
        val rankPosition = 1
        val player = ChallengeModel.PlayerRankingDetails(name,
                wins,
                loses,
                rankPosition
        )

        //call
        val players = ChallengeMocker.createPlayerDetailedInfo()

        //assert
        assertEquals(player.name, players[0].name)
        assertEquals(player.loses, players[0].loses)
        assertEquals(player.wins, players[0].wins)
        assertEquals(player.rankingPosition, players[0].rankingPosition)
        assertEquals(players.size, 10)
    }

}