package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

object ChallengeMocker {

    fun createPlayers(): List<Player>{

        var list: List<Player> = listOf()

        for(count in 0..10){
            val player = Player(String.format("nome%d",count+1), count+1,
                    "", "", "", "", 0, "")

            list += player
        }

        return list
    }



}