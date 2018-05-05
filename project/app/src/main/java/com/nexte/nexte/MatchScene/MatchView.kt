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


/**
 * Interface to define Display Logic to MatchView Class that will receive information
 * from Presenter
 */
interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)

}

/**
 * Class that implements [MatchID] and is responsible to control feed screen
 *
 * @property interactor Interactor layer for send requests, reference to [MatchInteractor]
 * @property matchViewAdapter FeedAdapter instance for broad using on class
 * @property numberOfSets enum class to define the number of sets, which define the presentation
 * of recycler view
 */

class MatchView : AppCompatActivity(), MatchDisplayLogic {

    var interactor: MatchInteractor? = null
    var matchViewAdapter: MatchDataAdapter? = null
    var numberOfSets = MatchModel.SetsNumber.One

    /**
     * On Create is a method that will setup this scene and call first Request for Interactor
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        this.setUpMatchScene()

        val empty = MatchModel.FormattedMatchData("", 1,
                                                  "", 1)

        this.matchViewAdapter = MatchDataAdapter(empty,this)
        matchRecyclerView.adapter = this.matchViewAdapter
        matchRecyclerView.layoutManager = LinearLayoutManager(this)

        /**
         * Passing string through intent, once the label of the string to be
         * used in this scene is a string thrown by main activity
         */
        val prevIntent = intent.getStringExtra("identifier")
        val request = MatchModel.InitScene.Request(prevIntent)
        interactor?.getInfoMatches(request)

        sendButton.isEnabled = false

    }

    /**
     * Method responsible to setup all the references of this scene
     *
     * @param view used to define that local interactor refers to [MatchInteractor]
     * @param presenter used to define this view as the parameter view on [MatchPresenter]
     * @param interactor used to send requests through local interactor instance
     */
    private fun setUpMatchScene() {

        var interactor = MatchInteractor()
        var presenter = MatchPresenter()
        var view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view


    }


    /**
     * Function to update the list shown on activity
     *
     * @param setsNumber get the actual number of sets being used as reference
     */
    private fun updateSetsNumber (setsNumber: MatchModel.SetsNumber) {
        numberOfSets = setsNumber
        matchViewAdapter?.notifyDataSetChanged()

    }

    /**
     * Function to get the recycle view started throuhr the object [matchViewAdapter]
     *
     * @param viewModel information to be displayed on activity
     */
    override fun displayMatch(viewModel: MatchModel.InitScene.ViewModel) {

        matchViewAdapter?.updateMatchInfo(viewModel.matchFormatted)
    }

    /**
     * Class ta extends RecyclerView.Adapter, to get functionality of the entire RecyclerView
     * implemented, and do the management of the rows displays
     *
     * @param matchInfo information of Data obtained as formatted classes
     * @param context activity to display defined elements
     */
    class MatchDataAdapter (private var matchInfo: MatchModel.FormattedMatchData,
                            private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView
         *
         * @param position row position of the recycler view
         */
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

        /**
         * Following the viewType obtained, defines the row to be inflated and the holder
         * inner class to be used on defining the attributes of the view
         *
         * @param parent
         * @param viewType Type of the layout to be displayed
         */
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

        /**
         * Function that defines the size of the recycler view following the [numberOfSets]
         * on [MatchView]
         */
        override fun getItemCount(): Int {

            when((context as MatchView).numberOfSets.number){
            1 -> return 3
            3 -> return 5
            5 -> return 7
            0 -> return 2
            else -> return 2
        }
    }

        /**
         * Function that refresh entire RecyclerView as the information of the [matchInfo] changes
         */
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

        /**
         * Class that implements the elements of the Info row
         *
         * @param itemView view that contains elements of the row to be altered
         */
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

        /**
         * Class that implements the elements of the sets row
         *
         * @param itemView view that contains elements of the row to be altered
         */
        class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun setsBindView() {

            }
        }

        /**
         * Class that implements the elements of the time row
         *
         * @param itemView view that contains elements of the row to be altered
         */
        class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun timeBindView() {
                val dateToShow = SimpleDateFormat("ccc, dd 'of' LLL")
                val time = dateToShow.format(Date())

                itemView.dateText.setText(time)
            }
        }

        /**
         * Class that implements the elements of the WO row
         *
         * @param itemView view that contains elements of the row to be altered
         */
        class WOViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun WOBindView() {

            }
        }


    }



}