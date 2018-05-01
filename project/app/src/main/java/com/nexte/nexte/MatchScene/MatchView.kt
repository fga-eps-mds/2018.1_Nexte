package com.nexte.nexte.MatchScene

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.nexte.nexte.R
import android.view.View
import android.view.ViewGroup
import android.content.Context
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



    }


    private fun setUpMatchScene() {

        var interactor = MatchInteractor()
        var presenter = MatchPresenter()
        var view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view


    }

    class MatchDataAdapter (private val matchInfo: MatchModel.FormattedMatchData,
                            private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            when(matchInfo.setsNumber.number){
                1 -> when(position) {
                    0 -> return R.layout.row_match_info
                    1 -> return R.layout.row_match_sets
                    2 -> return R.layout.row_match_time
                }

                3 -> when(position) {
                    0 -> return R.layout.row_match_info
                    1 -> return R.layout.row_match_sets
                    2 -> return R.layout.row_match_sets
                    3 -> return R.layout.row_match_sets
                    4 -> return R.layout.row_match_time
            }
                5 -> when(position) {
                    0 -> return R.layout.row_match_info
                    1 -> return R.layout.row_match_sets
                    2 -> return R.layout.row_match_sets
                    3 -> return R.layout.row_match_sets
                    4 -> return R.layout.row_match_sets
                    5 -> return R.layout.row_match_sets
                    6 -> return R.layout.row_match_time
                }
                0 -> when(position) {
                    0 -> return R.layout.row_match_info
                }


            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

            if(viewType == R.layout.row_match_info) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_info, parent,false)
                return MatchView.MatchDataAdapter.InfoViewHolder(view)
            }
            else if(viewType == R.layout.row_match_sets) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_sets, parent,false)
                return MatchView.MatchDataAdapter.SetsViewHolder(view)
            }
            else if(viewType == R.layout.row_match_time) {
                val view = LayoutInflater.from(context).inflate(R.layout.row_match_time, parent,false)
                return MatchView.MatchDataAdapter.TimeViewHolder(view)
            }

         }

        override fun getItemCount(): Int {

            when(matchInfo.setsNumber.number){
            1 -> return 3
            3 -> return 5
            5 -> return 7
            0 -> return 1
        }
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