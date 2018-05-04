package com.nexte.nexte.MatchScene

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.row_match_info.view.*
import kotlinx.android.synthetic.main.row_match_time.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)

}

class MatchView : AppCompatActivity(), MatchDisplayLogic {

    var interactor: MatchInteractor? = null
    var matchViewAdapter: MatchDataAdapter? = null
    var numberOfSets = MatchModel.SetsNumber.One

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        this.setUpMatchScene()

        val empty = MatchModel.FormattedMatchData("", 1,
                                                  "", 1)

        this.matchViewAdapter = MatchDataAdapter(empty,this)
        matchRecyclerView.adapter = this.matchViewAdapter
        matchRecyclerView.layoutManager = LinearLayoutManager(this)


        val prevIntent = intent.getStringExtra("identifier")
        val request = MatchModel.InitScene.Request(prevIntent)
        interactor?.getInfoMatches(request)

        sendButton.isEnabled = false

    }


    private fun setUpMatchScene() {

        var interactor = MatchInteractor()
        var presenter = MatchPresenter()
        var view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view


    }

    private fun updateSetsNumber (setsNumber: MatchModel.SetsNumber) {
        numberOfSets = setsNumber
        matchViewAdapter?.notifyDataSetChanged()

    }

    override fun displayMatch(viewModel: MatchModel.InitScene.ViewModel) {

        matchViewAdapter?.updateMatchInfo(viewModel.matchFormatted)
    }


    class MatchDataAdapter (private var matchInfo: MatchModel.FormattedMatchData,
                            private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {

            var layoutMatch : Int

            when((context as MatchView).numberOfSets.number){
                1 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_wo
                }

                3 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_sets
                    3 -> layoutMatch = R.layout.row_match_sets
                    4 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_wo
                }
                5 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_sets
                    3 -> layoutMatch = R.layout.row_match_sets
                    4 -> layoutMatch = R.layout.row_match_sets
                    5 -> layoutMatch = R.layout.row_match_sets
                    6 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_wo
                }
                0 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_wo
                    else -> layoutMatch = R.layout.row_match_wo
                }

                else -> layoutMatch = R.layout.row_match_wo

            }

            return layoutMatch
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            var holder: RecyclerView.ViewHolder

            if(viewType == R.layout.row_match_info) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_info, parent,false)
                holder = MatchView.MatchDataAdapter.InfoViewHolder(view)
            }
            else if(viewType == R.layout.row_match_sets) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_sets, parent,false)
                holder = MatchView.MatchDataAdapter.SetsViewHolder(view)
            }
            else if(viewType == R.layout.row_match_time) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_time, parent,false)
                holder = MatchView.MatchDataAdapter.TimeViewHolder(view)
            }
            else  { //viewType == R.layout.row_match_wo
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_wo, parent,false)
                holder = MatchView.MatchDataAdapter.WOViewHolder(view)
            }

            return holder
         }

        override fun getItemCount(): Int {

            when((context as MatchView).numberOfSets.number){
            1 -> return 3
            3 -> return 5
            5 -> return 7
            0 -> return 2
            else -> return 2
        }
    }

        fun updateMatchInfo(newMatchInfo: MatchModel.FormattedMatchData) {

            this.matchInfo = newMatchInfo
            this.notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if(holder is InfoViewHolder) {

                holder.infoBindView(matchInfo, context)

            }

            if(holder is SetsViewHolder) {

                holder.setsBindView()
            }

            if(holder is TimeViewHolder) {

                holder.timeBindView()
            }

            if(holder is WOViewHolder) {
                holder.WOBindView()
            }
        }

        class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun infoBindView(matchInfo: MatchModel.FormattedMatchData, context: Context) {

                itemView.challengedName.text = matchInfo.challengedName
                itemView.challengerName.text = matchInfo.challengerName
                itemView.imageChallenged.setImageResource(matchInfo.challengedPhoto)
                itemView.imageChallenger.setImageResource(matchInfo.challengerPhoto)


                itemView.buttonOne.setOnClickListener {
                    (context as MatchView).updateSetsNumber(MatchModel.SetsNumber.One)
                }
                itemView.buttonThree.setOnClickListener {
                    (context as MatchView).updateSetsNumber(MatchModel.SetsNumber.Three)
                }

                itemView.buttonFive.setOnClickListener {
                    (context as MatchView).updateSetsNumber(MatchModel.SetsNumber.Five)
                }

                itemView.buttonWO.setOnClickListener {
                    (context as MatchView).updateSetsNumber(MatchModel.SetsNumber.WO)
                }
            }

        }

        class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun setsBindView() {

            }
        }

        class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun timeBindView() {
                val dateToShow = SimpleDateFormat("ccc, dd 'of' LLL")
                val time = dateToShow.format(Date())

                itemView.dateText.setText(time)
            }
        }

        class WOViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun WOBindView() {

            }
        }


    }



}