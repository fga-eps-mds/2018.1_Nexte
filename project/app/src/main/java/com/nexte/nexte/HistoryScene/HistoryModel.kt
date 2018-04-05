package com.nexte.nexte.HistoryScene

import android.widget.BaseAdapter
import android.content.Context

/**
 * Created by helena on 03/04/18.
 */


class HistoryModel {

    class Player {
        var playerName: String? = null
        var pictureUrl: String? = null
        var rank: Int? = null
        var score: Int? = null

        constructor(playerName: String, pictureUrl: String, rank: Int, score: Int) {
            this.playerName = playerName
            this.pictureUrl = pictureUrl
            this.rank = rank
            this.score = score
        }
    }

    class Match {
        var players: Array<Player>

        constructor(players: Array<Player>) {
            this.players = players
        }


        fun getWinner(): Player {
            val firstScore: Int? = players[0].score
            val secondScore: Int? = players[1].score
            if (firstScore!! > secondScore!!) {
                return players[0]
            } else {
                return players[1]
            }
        }

        fun userPlayed(playerName: String): Boolean {
            return (players[0].playerName == playerName || players[1].playerName == playerName)
        }

    }

    class Request {
        var name: String? = null
        var context: Context
        constructor(name: String, context: Context) {
            this.context = context
            this.name = name
        }
    }

    class Response {
        var match: Array<Match>
        var context: Context

        constructor(match: Array<Match>, context: Context) {
            this.match = match
            this.context = context
        }
    }

    class ViewModel {
        var adapter: BaseAdapter? = null

        constructor(adapter: BaseAdapter) {
            this.adapter = adapter
        }

    }
}