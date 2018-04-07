package com.nexte.nexte.HistoryScene

import android.widget.BaseAdapter
import android.content.Context

/**
 * Created by helena on 03/04/18.
 */

/*
This is the most important class that is used to flow information between layers
*/
class HistoryModel {

    /*
    This class is responsible to holds information about dates
     */
    class Date {
        var year: Int
        var month: Int
        var day: Int
        var hour: Int
        var minute: Int

        constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
            this.year = year
            this.month = month
            this.day = day
            this.hour = hour
            this.minute = minute
        }
        /*
        This method returns if an certain data is higher than the class date
         */
        fun isDateAlreadyPassed(date: Date): Boolean {
            var passed: Boolean
            if (date.year > this.year) {
                passed = true
            } else if (date.year < this.year) {
                passed = false
            } else {
                if (date.month > this.month) {
                    passed = true
                } else if (date.month < this.month) {
                    passed = false
                } else {
                    if (date.day > this.day) {
                        passed = true
                    } else if (date.day < this.day) {
                        passed = false
                    } else {
                        if (date.hour > this.hour) {
                            passed = true
                        } else if (date.hour < this.hour) {
                            passed = false
                        } else {
                            passed = date.minute >= this.minute
                        }
                    }
                }
            }
            return passed
        }
    }

    /*
    This is the class that is used to describe players information
    */
    class Player {
        var playerName: String? = null // Player name
        var pictureUrl: String? = null // Player picture in form of URL (link to player profile picture)
        var rank: Int? = null // Player position in rank
        var score: Int? = null // Player score in the game that it belongs

        constructor(playerName: String, pictureUrl: String, rank: Int, score: Int) {
            this.playerName = playerName
            this.pictureUrl = pictureUrl
            this.rank = rank
            this.score = score
        }
    }

    /*
    This is the class that is used to describe matches information
    */
    class Match {
        var players: Array<Player> // Hold information about players that played this match
        var matchDate: Date? = null //The date that match should occur

        constructor(players: Array<Player>, matchDate: Date) {
            this.players = players
            this.matchDate = matchDate
        }

        /*
        This method is responsible to returns the winner of match
         */
        fun getWinner(): Player {
            val firstScore: Int? = players[0].score
            val secondScore: Int? = players[1].score
            if (firstScore!! > secondScore!!) {
                return players[0]
            } else {
                return players[1]
            }
        }

        /*
        This method returns whenever the player is in the match or isn't
         */
        fun userPlayed(playerName: String): Boolean {
            return (players[0].playerName == playerName || players[1].playerName == playerName)
        }
    }

    /*
    This class is responsible to indicate the player name that is requesting the history
    */
    class Request {
        var name: String? = null // Player name
        var context: Context // Reference to the view that will be used on presenter and needs to get there somehow

        constructor(name: String, context: Context) {
            this.context = context
            this.name = name
        }
    }

    /*
    This class is responsible to pass the data for Presenter
     */
    class Response {
        var match: Array<Match> // Holds information about matches that user played
        var context: Context // Reference to the view that will be used on presenter
        var requesterName: String // Name of user that requested the history
        constructor(match: Array<Match>, context: Context, requestedName: String) {
            this.match = match
            this.context = context
            this.requesterName = requestedName
        }
    }

    /*
    Class responsible to define how the list view will display the formatted data
     */
    class ViewModel {
        var adapter: BaseAdapter? = null  // Hold information about each row in the list view

        constructor(adapter: BaseAdapter) {
            this.adapter = adapter
        }

    }
}