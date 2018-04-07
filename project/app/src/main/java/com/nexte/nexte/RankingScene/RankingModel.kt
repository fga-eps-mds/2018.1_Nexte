package com.nexte.nexte.RankingScene

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView

/**
 * Created by albino on 02/04/18.
 */

class RankingModel {

    class Request {

        val context: Context

        constructor(context: Context){

            this.context = context
        }
    }

    class Player {

        var name: String? = null
        var pictureURL: String? = null
        var wins: Int? = null
        var losses: Int? = null
        var rankPosition : Int? = null

        constructor(name: String, pictureURL: String, wins: Int, losses: Int,
                    lastGame: String, rankPosition: Int) {

            this.name = name
            this.pictureURL = pictureURL
            this.wins = wins
            this.losses = losses
            this.rankPosition = rankPosition
        }
    }

    class Response {

        var players: Array<Player>? = null
        val context: Context

        constructor(players: Array<Player>, context: Context) {

            this.players = players
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