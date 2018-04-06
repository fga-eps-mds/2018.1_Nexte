package com.nexte.nexte.HistoryScene

import com.nexte.nexte.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.row_history.view.*
import org.w3c.dom.Text

/**
 * Created by helena on 03/04/18.
 */

interface HistoryPresentationLogic {
    fun formatPlayerMatches(response: HistoryModel.Response)
}
/*
This class need to format response so it can be displayed on activity
 */
class HistoryPresenter : HistoryPresentationLogic {

    var viewScene: HistoryDisplayLogic? = null // reference to the activity where data will be displayed

    /*
    Method responsible to format data (games that user played) for the view
     */
    override fun formatPlayerMatches(response: HistoryModel.Response) {
        val compareDate = HistoryModel.Date(2018, 4, 5, 22, 35)
        var formatedList: Array<HistoryModel.Match> = arrayOf() // Empty array to hold the matches starting, the first items will be matches that didn't happened yet

        //This loop sorts list with recent games in front of older ones
        for (counter in 0 until response.match.size) {
            for(secondCounter in counter until response.match.size) {
                if(response.match[counter].matchDate?.isDateAlreadyPassed(response.match[secondCounter].matchDate!!)!!) {
                    val gameTemp = response.match[counter]
                    response.match[counter] = response.match[secondCounter]
                    response.match[secondCounter] = gameTemp
                }
            }
        }

        //this loop split the pendent games from the finished ones
        for (game in response.match) {
            if(!game.matchDate?.isDateAlreadyPassed(compareDate)!!) {
                formatedList += game
            }
        }

        // this loop adds the finished games in the end of pendent games list
        for (game in response.match) {
            if(!formatedList.contains(game)) {
                formatedList += game
            }
        }

        val adapter = HistoryAdapter(formatedList, response.context, compareDate, response.requesterName)
        var viewModel: HistoryModel.ViewModel = HistoryModel.ViewModel(adapter)
        viewScene?.displayPlayerMatches(viewModel)
    }

    /*
    This class is responsible for format data that will be displayed in the list view
     */
    class HistoryAdapter: BaseAdapter {

        var date: HistoryModel.Date
        var context: Context // reference to my activity
        var playerMatches: Array<HistoryModel.Match> // data that will be formatted
        var requesterName: String

        constructor (playerMatches: Array<HistoryModel.Match>, context: Context, date: HistoryModel.Date, requesterName: String) : super() {
            this.playerMatches = playerMatches
            this.context = context
            this.date = date
            this.requesterName = requesterName
        }

        /*
        Returns the listview rows number
         */
        override fun getCount(): Int {
            return playerMatches.size
        }

        /*
        This method returns each row of my list view
         */
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(context) //it is a kind of converter, that can covert layout files to views
            val currentRow = layoutInflater.inflate(R.layout.row_history, viewGroup, false) //converts our layout to a view

            currentRow.firstPlayerName.text = playerMatches[position].players[0].playerName
            currentRow.secondPlayerName.text = playerMatches[position].players[1].playerName

            if(playerMatches[position].matchDate?.isDateAlreadyPassed(date)!!) {
                currentRow.firstPlayerScore.text = String.format("%d", playerMatches[position].players[0].score)
                currentRow.secondPlayerScore.text = String.format("%d", playerMatches[position].players[1].score)
                currentRow.firstPlayerRank.text = String.format("#%d", playerMatches[position].players[0].rank)
                currentRow.secondPlayerRank.text = String.format("#%d", playerMatches[position].players[1].rank)
                if(playerMatches[position].getWinner().playerName == requesterName){
                    currentRow.setBackgroundColor(Color.GREEN)
                }
                else{
                    currentRow.setBackgroundColor(Color.RED)
                }
            }
            else {
                currentRow.firstPlayerRank.visibility = TextView.INVISIBLE
                currentRow.secondPlayerRank.visibility = TextView.INVISIBLE
                currentRow.versusTextView.visibility = TextView.INVISIBLE
                currentRow.firstPlayerScore.visibility = TextView.INVISIBLE
                currentRow.secondPlayerScore.visibility = TextView.INVISIBLE
                currentRow.pendencyTextView.visibility = TextView.VISIBLE
                currentRow.setBackgroundColor(Color.GRAY)
            }

            return currentRow
        }

        /*
        This method returns the item that must be formatted and is contained in 'position'
         */
        override fun getItem(position: Int): Any {
            return playerMatches[position]
        }

        /*
        Returns an unique ID for each item on my data list.
         */
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

    }
}