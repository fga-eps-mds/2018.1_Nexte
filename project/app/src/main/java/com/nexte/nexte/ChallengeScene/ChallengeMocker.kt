package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

/**
 * Singleton responsible for mock challenge data
 */
object ChallengeMocker {

    /**
     * @property numPlayers Constant to define number of mocked players
     */
    private const val numPlayers = 9

    /**
     * Method responsible for generating players.
     *
     * @returns List<Player> containing the mocked data.
     */
    fun createPlayers(): List<Player>{

        var list: List<Player> = listOf()

        for(count in 0..numPlayers){
            val player = Player(String.format("nome%d",count+1), count+1,
                    "", "", "", "", 0, "")

            list += player
        }

        return list
    }

    fun createPlayerDetailedInfo(): List<ChallengeModel.PlayerRankingDetails>{

        var list: List<ChallengeModel.PlayerRankingDetails> = listOf()

        for(count in 0..numPlayers){
            val player = ChallengeModel.PlayerRankingDetails(String.format("nome%d", count+1),
                    10-count,
                    count,
                    count+1
            )

            list += player
        }

        return list
    }


}