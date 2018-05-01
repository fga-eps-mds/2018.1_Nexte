package com.nexte.nexte.MatchScene

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.nexte.nexte.R
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.content.ReceiverCallNotAllowedException
import android.view.LayoutInflater
import com.nexte.nexte.FeedScene.FeedView
import java.util.zip.Inflater
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.row_match_info.*
import kotlinx.android.synthetic.main.row_match_sets.*
import kotlinx.android.synthetic.main.row_match_time.*




interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)

}

class MatchView : AppCompatActivity(), MatchDisplayLogic {

    var interactor: MatchInteractor? = null
    var matchViewAdapter: MatchDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        this.setUpMatchScene()

        val empty = MatchModel.FormattedMatchData("", 1,
                                                  "", 1, MatchModel.SetsNumber.WO)

        this.matchViewAdapter = MatchDataAdapter(empty,this)
        matchRecyclerView.adapter = this.matchViewAdapter
        matchRecyclerView.layoutManager = LinearLayoutManager(this)


        val request = MatchModel.InitScene.Request("identifier")
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


    override fun displayMatch(viewModel: MatchModel.InitScene.ViewModel) {

        matchViewAdapter?.updateMatchInfo(viewModel.matchFormatted)
    }


    class MatchDataAdapter (private var matchInfo: MatchModel.FormattedMatchData,
                            private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {

            var layoutMatch : Int

            when(matchInfo.setsNumber.number){
                1 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_info
                }

                3 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_sets
                    3 -> layoutMatch = R.layout.row_match_sets
                    4 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_info
                }
                5 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    1 -> layoutMatch = R.layout.row_match_sets
                    2 -> layoutMatch = R.layout.row_match_sets
                    3 -> layoutMatch = R.layout.row_match_sets
                    4 -> layoutMatch = R.layout.row_match_sets
                    5 -> layoutMatch = R.layout.row_match_sets
                    6 -> layoutMatch = R.layout.row_match_time
                    else -> layoutMatch = R.layout.row_match_info
                }
                0 -> when(position) {
                    0 -> layoutMatch = R.layout.row_match_info
                    else -> layoutMatch = R.layout.row_match_info
                }

                else -> layoutMatch = R.layout.row_match_info

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
            else  { //viewType == R.layout.row_match_time
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_time, parent,false)
                holder = MatchView.MatchDataAdapter.TimeViewHolder(view)
            }

            return holder
         }

        override fun getItemCount(): Int {

            when(matchInfo.setsNumber.number){
            1 -> return 3
            3 -> return 5
            5 -> return 7
            0 -> return 1
            else -> return 1
        }
    }

        fun updateMatchInfo(newMatchInfo: MatchModel.FormattedMatchData) {

            this.matchInfo = newMatchInfo
            this.notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            if(holder is InfoViewHolder) {

                holder.infoBindView(matchInfo)

            }

            if(holder is SetsViewHolder) {

                holder.setsBindView()
            }

            if(holder is TimeViewHolder) {

                holder.timeBindView()
            }
        }

        class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun infoBindView(matchInfo: MatchModel.FormattedMatchData) {

            }

        }

        class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun setsBindView() {

            }
        }

        class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun timeBindView() {

            }
        }


    }



}