package com.nexte.nexte.MatchScene

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.row_match_info.view.*
import kotlinx.android.synthetic.main.row_match_sets.view.*
import kotlinx.android.synthetic.main.row_match_time.view.*
import kotlinx.android.synthetic.main.row_match_wo.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Interface to define Display Logic to MatchFragment Class that will receive information
 * from Presenter
 */
interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)

    fun displayMatchResultMessage(viewModel: MatchModel.SendMatchResult.ViewModel)
}

/**
 * Class that implements [MatchDisplayLogic] and is responsible to control feed screen
 *
 * @property interactor Interactor layer for send requests, reference to [MatchInteractor]
 * @property matchViewAdapter FeedAdapter instance for broad using on class
 * @property numberOfSets enum class to define the number of sets, which define the presentation
 * @property hasChallenge defines whenever an match exists, this will define which xml it will inflate
 * @property sendButton Instance of button that is used to send challenge
 * @property recyclerView Instance of recyclerView used to display match result data
 * @property challenged Challenged name to be displayed
 * @property challenger Challenger name to be displayed
 * of recycler view
 */
class MatchFragment : Fragment(), MatchDisplayLogic {

    private var matchViewAdapter: MatchDataAdapter? = null
    private var hasChallenge: Int = 0
    private var sendButton: Button?= null
    private var recyclerView: RecyclerView?= null
    var interactor: MatchInteractor? = null
    var numberOfSets = MatchModel.SetsNumber.One
    var challenged: String = ""
    var challenger: String = ""

    /**
     * Method created because in the future maybe this class will receive arguments.
     * @param challenge is what define if there will be displayed match result data or an fragment with a textlabel
     */
    fun getInstance(challenge: MatchModel.MatchData?): MatchFragment {
        val fragmentFirst = MatchFragment()
        val bundle = Bundle()
        /**
         * When a null challenge is passed for this method the bundle arguments receive false on hasChallenge
         * In the OnCreateView method the hasChallenge variable is used to define wich XML will be inflated
         */
        if(challenge == null){
            bundle.putInt("HasChallenge", 0)
            bundle.putString("Challenger", "")
            bundle.putString("Challenged", "")
        }
        else {
            bundle.putInt("HasChallenge", 1)
            bundle.putString("Challenger", challenge.challenger.name)
            bundle.putString("Challenged", challenge.challenged.name)
        }

        fragmentFirst.arguments = bundle

        return fragmentFirst
    }

    private fun validateProfessionalSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
        if (this.validateNormalSet(challenger, challenged, isValid)){
            return isValid
        }
        if(challenger == 9 && challenged == 8){
            return isValid
        }
        if(challenged == 9 && challenger == 8){
            return isValid
        }
        if(challenger == 8 && challenged <= 6){
            return isValid
        }
        if(challenged == 8 && challenger <= 6){
            return isValid
        }
        return false
    }

    private fun validateTieBreakSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
        if(this.validateNormalSet(challenger, challenged, isValid)){
            return isValid
        }
        else if (challenger == 10 && challenged <= 9){
            return isValid
        }
        else if (challenged == 10 && challenger <= 9) {
            return isValid
        }
        return false
    }

    private fun validateNormalSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
        return if (challenger == 7 && (challenged == 6 || challenged == 5)) {
            return isValid
        }
        else if(challenged == 7 && (challenger == 6 || challenger == 5)) {
            return isValid
        }
        else if(challenged == 6 && challenger <= 4){
            return isValid
        }
        else if (challenger == 6 && challenged <= 4)
            return isValid
        else if (challenged < 6 && challenger < 6)
            return false
        else
            return false
    }

    private fun defineSetsValid(){
        sendButton?.isEnabled = validateSetResults()
    }

    private fun validateSetResults(): Boolean {
        var isSetResultsValid = true
        recyclerView?.adapter as MatchDataAdapter

        for(i in 1 until recyclerView?.adapter?.itemCount!!){
            val item = recyclerView?.findViewHolderForAdapterPosition(i)

            val challengerResult = item?.itemView?.findViewById<EditText>(R.id.challengerResult)
            val challengedResult = item?.itemView?.findViewById<EditText>(R.id.challengedResult)

            val iChallengedResult:Int
            val iChallengerResult: Int
            if(challengedResult != null && challengerResult != null) {
                try {
                    iChallengedResult = Integer.parseInt(challengedResult.text.toString())
                    iChallengerResult = Integer.parseInt(challengerResult.text.toString())

                    isSetResultsValid = when (numberOfSets) {
                        MatchModel.SetsNumber.One -> {
                            validateProfessionalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                        }
                        MatchModel.SetsNumber.Three -> {
                            when (i) {
                                1 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                2 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                3 -> validateTieBreakSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                else -> {
                                    isSetResultsValid
                                }
                            }

                        }
                        MatchModel.SetsNumber.Five -> {
                            when (i) {
                                1 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                2 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                3 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                4 -> validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                5 -> validateTieBreakSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                                else -> {
                                    isSetResultsValid
                                }
                            }
                        }
                        else -> {
                            isSetResultsValid
                        }
                    }
                }
                catch (e: NumberFormatException){
                    return false
                }
            }
        }
        return isSetResultsValid
    }

    /**
     * Method called whenever the view is created, and it is responsible to get the bundle arguments and transfer
     * it to the class, this need to be done becaus Fragment superclass do not allow custom constructors
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.challenged = arguments.getString("Challenged")
        this.challenger = arguments.getString("Challenger")
        this.hasChallenge = arguments.getInt("HasChallenge")
    }

    /**
     * Method called after OnCreate and it is responsible to return the view that will be rendered.
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view: View?
        /**
         * Here we decide wich view will be inflated based on hasChallenge property
         */

        if(hasChallenge == 1) {
            view = inflater?.inflate(R.layout.activity_match, container, false)
            this.setUpMatchScene()
            this.recyclerView = view?.findViewById(R.id.matchRecyclerView)
            this.sendButton = view?.findViewById(R.id.sendButton)

            val match = MatchModel.FormattedMatchData(challenged,
                    R.mipmap.ic_launcher_round,
                    challenger,
                    R.mipmap.ic_launcher_round)

            this.sendButton?.isEnabled = false

            this.matchViewAdapter = MatchDataAdapter(match, this)
            recyclerView?.adapter = this.matchViewAdapter
            recyclerView?.layoutManager = LinearLayoutManager(activity)

            val request = MatchModel.InitScene.Request(MatchModel.MatchData(
                    MatchModel.MatchPlayer(challenged, R.mipmap.ic_launcher_round),
                    MatchModel.MatchPlayer(challenger, R.mipmap.ic_launcher_round)
            ))
            interactor?.getInfoMatches(request)

            sendButton?.setOnClickListener {
                sendMatchResult()
            }

        }
        else {
            view = inflater?.inflate(R.layout.fragment_nochallenge, container, false)
        }

        return view!!
    }

    /**
     * Method responsible to send the match result request to the interactor
     */
    private fun sendMatchResult(){
        val request = MatchModel.SendMatchResult.Request()
        this.interactor?.getMatchResult(request)
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    private fun setUpMatchScene() {

        val interactor = MatchInteractor()
        val presenter = MatchPresenter()
        val view = this

        interactor.worker.updateLogic = interactor
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
     * Method that will create a popup with the appropriate message related to the match result
     *
     * @param viewModel information that will be displayed in the popup
     */
    override fun displayMatchResultMessage(viewModel: MatchModel.SendMatchResult.ViewModel) {
        val builder = AlertDialog.Builder(context)

        builder.setCancelable(true)
        builder.setMessage(viewModel.message)
        builder.setPositiveButton("Ok", { dialogInterface, _ ->
            dialogInterface.cancel()
        })

        val alert = builder.create()
        alert.show()
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
                one -> layoutMatch = when(position) {
                    zero -> R.layout.row_match_info
                    one -> R.layout.row_match_sets
                    two -> R.layout.row_match_time
                    else -> throw IllegalArgumentException()
                }

                three -> layoutMatch = when(position) {
                    zero -> R.layout.row_match_info
                    one -> R.layout.row_match_sets
                    two -> R.layout.row_match_sets
                    three -> R.layout.row_match_sets
                    four -> R.layout.row_match_time
                    else -> throw IllegalArgumentException()
                }

                five -> layoutMatch = when(position) {
                    zero -> R.layout.row_match_info
                    one -> R.layout.row_match_sets
                    two -> R.layout.row_match_sets
                    three -> R.layout.row_match_sets
                    four -> R.layout.row_match_sets
                    five -> R.layout.row_match_sets
                    six -> R.layout.row_match_time
                    else -> throw IllegalArgumentException()
                }

                zero -> layoutMatch = when(position) {
                    zero -> R.layout.row_match_info
                    one -> R.layout.row_match_wo
                    else -> throw IllegalArgumentException()
                }

                else -> throw IllegalArgumentException()
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
                    holder.itemView.challengedResult.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            (fragment as MatchFragment).defineSetsValid()
                        }
                    })
                    holder.itemView.challengerResult.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {
                        }

                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        }

                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                            (fragment as MatchFragment).defineSetsValid()
                        }
                    })
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
                one -> three
                three -> five
                five -> seven
                zero -> two
                else -> two
            }
        }

        /**
         * Function that refresh entire RecyclerView as the information of the [matchInfo] changes
         *
         * @param newMatchInfo contains the information about the new match, that will be used when notifyDataSetChanged is called.
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

                holder.setsBindView(position, (fragment as MatchFragment).numberOfSets)
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

                itemView.buttonGroup.buttonOne.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.One)
                }
                itemView.buttonGroup.buttonThree.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.Three)
                }

                itemView.buttonGroup.buttonFive.setOnClickListener {
                    (fragment as MatchFragment).updateSetsNumber(MatchModel.SetsNumber.Five)
                }

                itemView.buttonGroup.buttonWO.setOnClickListener {
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

            fun setsBindView(position: Int, numberOfSets: MatchModel.SetsNumber) {

                when(numberOfSets) {
                    MatchModel.SetsNumber.One -> {
                        itemView.setLabel.text = "Set Profissional"
                    }
                    MatchModel.SetsNumber.Three -> {
                        when(position) {
                            1 -> itemView.setLabel.text = "Primeiro Set"
                            2 -> itemView.setLabel.text = "Segundo Set"
                            3 -> itemView.setLabel.text = "Tie Break"
                        }
                    }
                    MatchModel.SetsNumber.Five -> {
                        when(position) {
                            1 -> itemView.setLabel.text = "Primeiro Set"
                            2 -> itemView.setLabel.text = "Segundo Set"
                            3 -> itemView.setLabel.text = "Terceiro Set"
                            4 -> itemView.setLabel.text = "Quarto Set"
                            5 -> itemView.setLabel.text = "Tie Break"
                        }
                    }
                }

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
        const val zero = 0
        const val one = 1
        const val two = 2
        const val three = 3
        const val four = 4
        const val five = 5
        const val six = 6
        const val seven = 7
        const val eight = 8
        const val nine = 9
    }
}