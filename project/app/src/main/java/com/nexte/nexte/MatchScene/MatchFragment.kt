package com.nexte.nexte.MatchScene

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.os.Bundle
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
import java.util.*

/**
 * Interface to define Display Logic to MatchFragment Class that will receive information
 * from Presenter
 */
interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)
}

/**
 * Class that implements [MatchDisplayLogic] and is responsible to control feed screen
 *
 * @property interactor Interactor layer for send requests, reference to [MatchInteractor]
 * @property matchViewAdapter FeedAdapter instance for broad using on class
 * @property numberOfSets enum class to define the number of sets, which define the presentation
 * of recycler view
 */
class MatchFragment : Fragment(), MatchDisplayLogic {

    var interactor: MatchInteractor? = null
    private var matchViewAdapter: MatchDataAdapter? = null
    var numberOfSets = MatchModel.SetsNumber.One

    //method created because in the future maybe this class will receive arguments.
    fun getInstance(): MatchFragment {
        val fragmentFirst = MatchFragment()
        return fragmentFirst
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater?.inflate(R.layout.activity_match, container, false)
        this.setUpMatchScene()

        val empty = MatchModel.FormattedMatchData("", 1,
                "", 1)

        this.matchViewAdapter = MatchDataAdapter(empty,this)
        matchRecyclerView.adapter = this.matchViewAdapter
        matchRecyclerView.layoutManager = LinearLayoutManager(activity)

        /**
         * Passing string through intent, once the label of the string to be
         * used in this scene is a string thrown by main activity
         */
        val prevIntent = activity.intent.getStringExtra("identifier")
        val request = MatchModel.InitScene.Request(prevIntent)
        interactor?.getInfoMatches(request)

        sendButton.isEnabled = false

        return view!!
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    private fun setUpMatchScene() {

        val interactor = MatchInteractor()
        val presenter = MatchPresenter()
        val view = this

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
     * @param fragment fragment to display defined elements
     */
    class MatchDataAdapter (private var matchInfo: MatchModel.FormattedMatchData,
                            private val fragment: Fragment): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView
         *
         * @param position row position of the recycler view
         */
        override fun getItemViewType(position: Int): Int {

            val layoutMatch : Int

            when((fragment as MatchFragment).numberOfSets.number){
                firstPosition -> layoutMatch = when(position) {
                    zeroPosition -> R.layout.row_match_info
                    firstPosition -> R.layout.row_match_sets
                    secondPosition -> R.layout.row_match_time
                    else -> R.layout.row_match_wo
                }

                thirdPosition -> layoutMatch = when(position) {
                    zeroPosition -> R.layout.row_match_info
                    firstPosition -> R.layout.row_match_sets
                    secondPosition -> R.layout.row_match_sets
                    thirdPosition -> R.layout.row_match_sets
                    fourthPosition -> R.layout.row_match_time
                    else -> R.layout.row_match_wo
                }
                fifthPosition -> layoutMatch = when(position) {
                    zeroPosition -> R.layout.row_match_info
                    firstPosition -> R.layout.row_match_sets
                    secondPosition -> R.layout.row_match_sets
                    thirdPosition -> R.layout.row_match_sets
                    fourthPosition -> R.layout.row_match_sets
                    fifthPosition -> R.layout.row_match_sets
                    sixthPosition -> R.layout.row_match_time
                    else -> R.layout.row_match_wo
                }
                zeroPosition -> layoutMatch = when(position) {
                    zeroPosition -> R.layout.row_match_info
                    firstPosition -> R.layout.row_match_wo
                    else -> R.layout.row_match_wo
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

            val holder: RecyclerView.ViewHolder

            when (viewType) {
                R.layout.row_match_info -> {
                    val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_match_info, parent,false)
                    holder = MatchFragment.MatchDataAdapter.InfoViewHolder(view)
                }
                R.layout.row_match_sets -> {
                    val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_match_sets, parent,false)
                    holder = MatchFragment.MatchDataAdapter.SetsViewHolder(view)
                }
                R.layout.row_match_time -> {
                    val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_match_time, parent,false)
                    holder = MatchFragment.MatchDataAdapter.TimeViewHolder(view)
                }
                else -> { //viewType == R.layout.row_match_wo
                    val view = LayoutInflater.from(fragment.activity).inflate(R.layout.row_match_wo, parent,false)
                    holder = MatchFragment.MatchDataAdapter.WOViewHolder(view)
                }
            }

            return holder
        }

        /**
         * Function that defines the size of the recycler view following the [numberOfSets]
         * on [MatchFragment]
         */
        override fun getItemCount(): Int {

            return when((fragment as MatchFragment).numberOfSets.number) {
                firstPosition -> thirdPosition
                thirdPosition -> fifthPosition
                fifthPosition -> seventhPosition
                zeroPosition -> secondPosition
                else -> secondPosition
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

                holder.infoBindView(matchInfo,fragment)
            }

            if(holder is SetsViewHolder) {

                holder.setsBindView()
            }

            if(holder is TimeViewHolder) {

                holder.timeBindView()
            }

            if(holder is WOViewHolder) {

                holder.wOBindView()
            }
        }

        /**
         * Class that implements the elements of the Info row
         *
         * @param itemView view that contains elements of the row to be altered
         */
        class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun infoBindView(matchInfo: MatchModel.FormattedMatchData, fragment: Fragment) {

                itemView.challengedName.text = matchInfo.challengedName
                itemView.challengerName.text = matchInfo.challengerName
                itemView.imageChallenged.setImageResource(matchInfo.challengedPhoto)
                itemView.imageChallenger.setImageResource(matchInfo.challengerPhoto)

                itemView.buttonOne.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.One)
                }
                itemView.buttonThree.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.Three)
                }

                itemView.buttonFive.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.Five)
                }

                itemView.buttonWO.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.WO)
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

            @SuppressLint("SimpleDateFormat")
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

            fun wOBindView() {

            }
        }
    }

    companion object {
        const val zeroPosition = 0
        const val firstPosition = 1
        const val secondPosition = 2
        const val thirdPosition = 3
        const val fourthPosition = 4
        const val fifthPosition = 5
        const val sixthPosition = 6
        const val seventhPosition = 7
    }
}