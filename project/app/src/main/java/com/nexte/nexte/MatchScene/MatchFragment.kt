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
import android.widget.TextView
import com.nexte.nexte.ChallengeTabsFragment
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.MainActivity
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_challenger.*
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

    fun displayDeclineMatch(viewModel: MatchModel.DeclineChallengeRequest.ViewModel): Unit?
}

@Suppress("DEPRECATION")
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
    private var declineButton: Button? = null
    private var recyclerView: RecyclerView?= null
    var interactor: MatchInteractor? = null
    var numberOfSets = MatchModel.SetsNumber.One
    var challenged: String = ""
    var challenger: String = ""
    var challengeId: String = ""
    private var challengeManager: ChallengeManager? = null

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
            bundle.putString("ChallengeId", "")
        }
        else {
            bundle.putInt("HasChallenge", 1)
            bundle.putString("Challenger", challenge.challenger.name)
            bundle.putString("Challenged", challenge.challenged.name)
            bundle.putString("ChallengeId", challenge.challengeId)
        }

        fragmentFirst.arguments = bundle

        return fragmentFirst
    }

    private class ValidateSets{
        /**
         * This method validates if the result is 8x9 or 9x8, returns true if it is and false if it's not
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         */
        fun validateNineVsEight(challenger: Int, challenged: Int) : Boolean{
            return when {
                challenger == nine && challenged == eight -> true
                else -> challenged == nine && challenger == eight
            }
        }

        /**
         * This method validates if the result is 8x6(or less) or 6(or less)x8, returns true if it is and false if it's not
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         */
        fun validateEightVsAny(challenger: Int, challenged: Int) : Boolean {
            return when {
                challenger == eight && challenged <= six -> true
                else -> challenged == eight && challenger <= six
            }
        }

        /**
         * This method validates a professional set style
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         * @param isValid Contains the information about previous validation, if it's false, then this validation will also fail
         *
         */
        fun validateProfessionalSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
            return when {
                this.validateNormalSet(challenger, challenged, isValid) -> isValid
                validateNineVsEight(challenger,challenged) -> isValid
                validateEightVsAny(challenger, challenged) -> isValid
                else -> false
            }
        }

        /**
         * This method validates an tie-break style set
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         * @param isValid Contains the information about previous validation, if it's false, then this validation will also fail
         */
        fun validateTieBreakSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
            return if(this.validateNormalSet(challenger, challenged, isValid)){
                isValid
            }
            else if (challenger == ten && challenged <= nine){
                isValid
            }
            else if (challenged == ten && challenger <= nine) {
                isValid
            }
            else {
                false
            }
        }

        /**
         * This method validates if the result is 7x6, 7x5, 6x7 or 5x7, returns true if it is and false if it's not
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         */
        fun validateSevenVsSixOrFive(challenger: Int, challenged: Int): Boolean{
            return when {
                challenger == seven && (challenged == six || challenged == five) -> true
                else -> challenged == seven && (challenger == six || challenger == five)
            }
        }

        /**
         * This method validates if the result is 8x9 or 9x8, returns true if it is and false if it's not
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         */
        fun validateSixVsAny(challenger: Int, challenged: Int): Boolean{
            return when {
                challenger == six && challenged <= four -> true
                else -> challenged == six && challenger <= four
            }
        }

        /**
         * This method validates if a normal set is valid or not
         *
         * @param challenged Contains the challenged points
         * @param challenger Contains the challenger points
         * @param isValid Indicates whenever the validation is success or failing for the all previous sets.
         */
        fun validateNormalSet(challenger: Int, challenged: Int, isValid: Boolean) : Boolean{
            return when {
                validateSevenVsSixOrFive(challenger, challenged) -> isValid
                validateSixVsAny(challenger, challenged) -> isValid
                else -> false
            }
        }

        /**
         * This method validates the result of each set for a BO3 game
         *
         * @param set Contains the number of the set that is being validated because the validation may be different on last set
         * @param iChallengedResult contains the points made by the challenged
         * @param iChallengerResult contains the points made by the challenger
         * @param isSetResultsValid contains if the result till now is valid or not, because if it is not valid, it will keep it false.
         */
        fun checkThreeSetsValid(set: Int, iChallengerResult: Int, iChallengedResult: Int, isSetResultsValid: Boolean) : Boolean{
            return when (set) {
                one -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                two -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                three -> this.validateTieBreakSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                else -> {
                    isSetResultsValid
                }
            }
        }

        /**
         * This method validates the result of each set for a BO5 game
         *
         * @param set Contains the number of the set that is being validated because the validation may be different on last set
         * @param iChallengedResult contains the points made by the challenged
         * @param iChallengerResult contains the points made by the challenger
         * @param isSetResultsValid contains if the result till now is valid or not, because if it is not valid, it will keep it false.
         */
        fun checkFiveSetsValid(set: Int, iChallengerResult: Int, iChallengedResult: Int, isSetResultsValid: Boolean) : Boolean{
            return when (set) {
                one -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                two -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                three -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                four -> this.validateNormalSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                five -> this.validateTieBreakSet(iChallengerResult, iChallengedResult, isSetResultsValid)
                else -> {
                    isSetResultsValid
                }
            }
        }
    }

    /**
     * This method validates enable or disable the send button according to the validation of sets result
     */
    private fun defineSetsValid(){
        sendButton?.isEnabled = true//validateSetResults()
    }

    private fun checkIfSetResultIsValid(i: Int,
                                        numberOfSets: MatchModel.SetsNumber,
                                        iChallengerResult: Int,
                                        iChallengedResult: Int,
                                        isLocalResultsValid:Boolean): Boolean{
        return when (numberOfSets) {
            MatchModel.SetsNumber.One -> {
                ValidateSets().validateProfessionalSet(iChallengerResult, iChallengedResult, isLocalResultsValid)
            }
            MatchModel.SetsNumber.Three -> {
                ValidateSets().checkThreeSetsValid(i, iChallengerResult, iChallengedResult, isLocalResultsValid)
            }
            MatchModel.SetsNumber.Five -> {
                ValidateSets().checkFiveSetsValid(i, iChallengerResult, iChallengedResult, isLocalResultsValid)
            }
            else -> {
                false
            }
        }
    }

    /**
     * This method iterates within all rows of an recycler view, grabbing the result and validating it
     *
     */
    private fun validateSetResults(): Boolean {
        var isSetResultsValid = true
        recyclerView?.adapter as MatchDataAdapter

        for (i in 1 until recyclerView?.adapter?.itemCount!!) {
            var isLocalResultsValid = true
            val item = recyclerView?.findViewHolderForAdapterPosition(i)

            val challengerResult = item?.itemView?.findViewById<EditText>(R.id.challengerResult)
            val challengedResult = item?.itemView?.findViewById<EditText>(R.id.challengedResult)

            val iChallengedResult: Int
            val iChallengerResult: Int
            if (challengedResult != null && challengerResult != null) {
                if(challengedResult.text.toString() == "" || challengerResult.text.toString() == "") {
                    return false
                }
                iChallengedResult = Integer.parseInt(challengedResult.text.toString())
                iChallengerResult = Integer.parseInt(challengerResult.text.toString())

                isLocalResultsValid =
                        checkIfSetResultIsValid(i, numberOfSets, iChallengerResult, iChallengedResult, isLocalResultsValid)
                if(!isLocalResultsValid) {
                    isSetResultsValid = false
                }

                val labelToBePainted = item.itemView?.findViewById<TextView>(R.id.setLabel)
                val wrongLabelColor = resources.getColor(R.color.red)
                val rightLabelColor = resources.getColor(R.color.darker_gray)

                if(!isLocalResultsValid) {
                    labelToBePainted?.setTextColor(wrongLabelColor)
                }
                else {
                    labelToBePainted?.setTextColor(rightLabelColor)
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

        this.challenged = arguments!!.getString("Challenged")
        this.challengeId = arguments!!.getString("ChallengeId")
        this.challenger = arguments!!.getString("Challenger")
        this.hasChallenge = arguments!!.getInt("HasChallenge")
    }

    /**
     * Method called after OnCreate and it is responsible to return the view that will be rendered.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View?
        /**
         * Here we decide wich view will be inflated based on hasChallenge property
         */

        if(hasChallenge == 1) {
            view = inflater.inflate(R.layout.activity_match, container, false)
            this.setUpMatchScene()
            this.recyclerView = view?.findViewById(R.id.matchRecyclerView)
            this.sendButton = view?.findViewById(R.id.sendButton)
            this.declineButton = view?.findViewById(R.id.declineButton)

            val match = MatchModel.FormattedMatchData(challenged,
                    R.drawable.profile_image1,
                    challenger,
                    R.drawable.profile_image2)

            this.sendButton?.isEnabled = true//false

            this.matchViewAdapter = MatchDataAdapter(match, this)
            recyclerView?.adapter = this.matchViewAdapter
            recyclerView?.layoutManager = LinearLayoutManager(activity)

            val request = MatchModel.InitScene.Request(MatchModel.MatchData(
                    MatchModel.MatchPlayer(challenged, R.drawable.profile_image1),
                    MatchModel.MatchPlayer(challenger, R.drawable.profile_image2),
                    this.challengeId))
            interactor?.getInfoMatches(request)

            sendButton?.setOnClickListener {
                sendMatchResult()
            }

            declineButton?.setOnClickListener {
                declineMatch()
            }

        }
        else {
            view = inflater?.inflate(R.layout.fragment_nochallenge, container, false)
        }

        challengeManager = ChallengeManager()
        return view!!
    }

    /**
     * Method responsible for the decline match result request to the interactor
     */
    fun declineMatch(){
        //aqui
        val request = MatchModel.DeclineChallengeRequest.
                Request(this.challengeId)
        interactor?.declineMatchResult(request)
    }


    /**
     *  Function that is reponsible to the action of declining a challenge
     *
     *  @param viewModel match model to send a decline challenge request
     */
    override fun displayDeclineMatch(viewModel: MatchModel.DeclineChallengeRequest.ViewModel) =
            if (viewModel.status == MatchModel.DeclineChallengeRequest.Status.SUCCESS) {

                val challenge = (this.activity as MainActivity).supportFragmentManager.findFragmentByTag("challenge") as ChallengeTabsFragment
                challenge.match = null
                (this.activity as MainActivity).tabs.getTabAt(0)?.select()
                challenge.viewpager?.adapter?.notifyDataSetChanged()

            } else {
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
     * Method responsible to send the match result request to the interactor
     */
     fun sendMatchResult(){
        val request = MatchModel.SendMatchResult.
                Request(this.challengeId)
        this.interactor?.getMatchResult(request)
    }

    /**
     * Method responsible to setup all the references of this scene
     */
    fun setUpMatchScene() {

        val interactor = MatchInteractor()
        val presenter = MatchPresenter()
        val view = this

        interactor.worker.updateLogic = interactor
        interactor.worker.challengeManager = challengeManager
        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view

    }

    /**
     * Function to update the list shown on activity
     *
     * @param setsNumber get the actual number of sets being used as reference
     */
     fun updateSetsNumber (setsNumber: MatchModel.SetsNumber) {
        numberOfSets = setsNumber
        matchViewAdapter?.notifyDataSetChanged()

    }

    /**
     * Function to get the recycle fragment started throuhr the object [matchViewAdapter]
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
         * on different positions of the RecyclerView for the one set option
         *
         * @param position row position of the recycler view
         */

        private fun getOneSetItemViewType(position: Int): Int {
            return when(position) {
                zero -> R.layout.row_match_info
                one -> R.layout.row_match_sets
                two -> R.layout.row_match_time
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView for the three set option
         *
         * @param position row position of the recycler view
         */
        private fun getThreeSetItemViewType(position: Int): Int {
            return when(position) {
                zero -> R.layout.row_match_info
                one -> R.layout.row_match_sets
                two -> R.layout.row_match_sets
                three -> R.layout.row_match_sets
                four -> R.layout.row_match_time
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView for the five set option
         *
         * @param position row position of the recycler view
         */
        private fun getFiveSetItemViewType(position: Int): Int {
            return when(position) {
                zero -> R.layout.row_match_info
                one -> R.layout.row_match_sets
                two -> R.layout.row_match_sets
                three -> R.layout.row_match_sets
                four -> R.layout.row_match_sets
                five -> R.layout.row_match_sets
                six -> R.layout.row_match_time
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView for the WO set option
         *
         * @param position row position of the recycler view
         */
        private fun getWOItemViewType(position: Int): Int {
            return when(position) {
                zero -> R.layout.row_match_info
                one -> R.layout.row_match_wo
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Function to get the type of the row to be displayed
         * on different positions of the RecyclerView for each set option
         *
         * @param position row position of the recycler fragment
         */
        override fun getItemViewType(position: Int): Int {

            return when((fragment as MatchFragment).numberOfSets.number){
                one -> getOneSetItemViewType(position)
                three -> getThreeSetItemViewType(position)
                five -> getFiveSetItemViewType(position)
                zero -> getWOItemViewType(position)
                else -> throw IllegalArgumentException()
            }
        }

        /**
         * Function to set the textChangedListener in each row of recyclerView that it's designed to
         * receive the result, and the action listener is responsible to validate whenever the set is valid or not
         *
         * @param holder contains the viewholder that will receive the action listener
         */
        private fun setTextActionListener(holder: RecyclerView.ViewHolder){
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

        /**
         * Following the viewType obtained, defines the row to be inflated and the holder
         * inner class to be used on defining the attributes of the fragment
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
                    setTextActionListener(holder)
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
         * Function that defines the size of the recycler fragment following the [numberOfSets]
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

                holder.wOBindView(fragment as MatchFragment)
            }
        }

        /**
         * Class that implements the elements of the Info row
         *
         * @param itemView fragment that contains elements of the row to be altered
         */
        class InfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun infoBindView(matchInfo: MatchModel.FormattedMatchData, fragment: Fragment) {

                itemView.challengedName.text = matchInfo.challengedName
                itemView.challengerName.text = matchInfo.challengerName
                itemView.imageChallenged.setImageResource(matchInfo.challengedPhoto)
                itemView.imageChallenger.setImageResource(matchInfo.challengerPhoto)

                val position = when((fragment as MatchFragment).numberOfSets){
                    MatchModel.SetsNumber.One -> zero
                    MatchModel.SetsNumber.Three -> one
                    MatchModel.SetsNumber.Five -> two
                    MatchModel.SetsNumber.WO -> three

                }
                itemView.buttonGroup.setPosition(position, true)

                itemView.buttonGroup.buttonOne.setOnClickListener {
                    itemView.buttonGroup.setPosition(zero, true)
                    fragment.updateSetsNumber(MatchModel.SetsNumber.One)
                }
                itemView.buttonGroup.buttonThree.setOnClickListener {
                    itemView.buttonGroup.setPosition(one, true)
                    fragment.updateSetsNumber(MatchModel.SetsNumber.Three)
                }

                itemView.buttonGroup.buttonFive.setOnClickListener {
                    itemView.buttonGroup.setPosition(two, true)
                    fragment.updateSetsNumber(MatchModel.SetsNumber.Five)
                }

                itemView.buttonGroup.buttonWO.setOnClickListener {
                    itemView.buttonGroup.setPosition(three, true)
                    fragment.updateSetsNumber(MatchModel.SetsNumber.WO)
                }
            }
        }

        /**
         * Class that implements the elements of the sets row
         *
         * @param itemView fragment that contains elements of the row to be altered
         */
        class SetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            @SuppressLint("SetTextI18n")
            private fun setThreeSetsLabels(position: Int){
                when(position) {
                    one -> itemView.setLabel.text = "Primeiro Set"
                    two -> itemView.setLabel.text = "Segundo Set"
                    three -> itemView.setLabel.text = "Tie Break"
                }
            }

            @SuppressLint("SetTextI18n")
            private fun setFiveSetsLabels(position: Int){
                when(position) {
                    one -> itemView.setLabel.text = "Primeiro Set"
                    two -> itemView.setLabel.text = "Segundo Set"
                    three -> itemView.setLabel.text = "Terceiro Set"
                    four -> itemView.setLabel.text = "Quarto Set"
                    five -> itemView.setLabel.text = "Tie Break"
                }
            }

            @SuppressLint("SetTextI18n")
            fun setsBindView(position: Int, numberOfSets: MatchModel.SetsNumber) {

                when(numberOfSets) {
                    MatchModel.SetsNumber.One -> {
                        itemView.setLabel.text = "Set Profissional"
                    }
                    MatchModel.SetsNumber.Three -> {
                        setThreeSetsLabels(position)
                    }
                    MatchModel.SetsNumber.Five -> {
                        setFiveSetsLabels(position)
                    }
                    else -> {
                        /*do nothing*/
                    }
                }

            }
        }

        /**
         * Class that implements the elements of the time row
         *
         * @param itemView fragment that contains elements of the row to be altered
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
         * @param itemView fragment that contains elements of the row to be altered
         */
        class WOViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun wOBindView(fragment: MatchFragment) {

                itemView.buttonGroupWO.buttonChallenger.setOnClickListener {
                    itemView.buttonGroupWO.setPosition(0, true)
                    fragment.sendButton?.isEnabled = true
                }
                itemView.buttonGroupWO.buttonSelect.setOnClickListener {
                    itemView.buttonGroupWO.setPosition(1, true)
                    fragment.sendButton?.isEnabled = true//false
                }
                itemView.buttonGroupWO.buttonChallenged.setOnClickListener {
                    itemView.buttonGroupWO.setPosition(2, true)
                    fragment.sendButton?.isEnabled = true
                }


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
        const val ten = 10
    }
}
