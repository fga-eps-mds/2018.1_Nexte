package com.nexte.nexte.RankingScene

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView

/**
 * Created by albino on 02/04/18.
 */

class RankingModel {

    class Request(var context: Context)


    class Response(var rankingActivities: Array<RankingActivity>)

    //val context: Context


    class ViewModel(var rankingActivities: Array<RankingActivityFormatted>)


    class RankingPlayer(var name: String, var pictureURL: String, var wins: Int, var losses: Int,
                        var rankPosition: Int)

    class RankingActivity(var userRanking: RankingPlayer)

    class RankingActivityFormatted(var userName: String,
                                   var userPictureURL: Int,
                                   var userWins: Int,
                                   var userLosses: Int,
                                   var userRankPosition: String)
}

