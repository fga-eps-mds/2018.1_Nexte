package com.nexte.nexte.ShowProfileScene

import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.support.v4.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileFragment

import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_show_profile.*
import kotlinx.android.synthetic.main.columns_challenges_show.view.*
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.widget.Toast
import com.squareup.picasso.Picasso



/**
 * This interface will be responsible to define the methods that
 * will receive the formatted data from [ShowProfilePresenter]
 */
interface ShowProfileDisplayLogic {

    fun displayProfile(viewModel : ShowProfileModel.ViewModel)
}

@Suppress("DEPRECATION")

/**
 * This class implements ShowProfileDisplayLogic, and it is responsible to
 * display information about the user
 *
 * @property showProfileInteractor responsible to receive request and send it to worker
 */
class ShowProfileFragment : Fragment(), ShowProfileDisplayLogic {

    private var showProfileInteractor: ShowProfileBusinessLogic? = null
    private var buttonEditProfile: Button? = null
    var rankingChart: LineChart? = null
    private var newLineChart: LineChart? = null // First chart view
    private var playerIdToShow: String = ""
    var userManager: UserManager? = null
    private var showProfileRecyclerView: RecyclerView? = null
    private val graphManager = GraphManager(this)
    private var contactButton: Button? = null
    private val editProfileFragment = EditProfileFragment().getInstance(UserSingleton.loggedUserID)

    /*
    This method is called on instantiate, and it's responsible to set the player that the profile will be
    displayed
    */
    fun getInstance(playerToShowName: String?): ShowProfileFragment {
        val bundle = Bundle()
        val showProfileFragment = ShowProfileFragment()

        if (playerToShowName != null) {
            bundle.putString("id", playerToShowName)
        } else {
            bundle.putString("id", "")
        }

        showProfileFragment.arguments = bundle
        return showProfileFragment
    }


    /**
     * Method called when screen is loaded, responsible to load user information
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.userManager = UserManager()
        setupShowProfileScene()
        this.playerIdToShow = arguments.getString("id")
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val newView = inflater?.inflate(R.layout.activity_show_profile, container, false)

        showProfileRecyclerView = newView?.findViewById(R.id.challengesShowRecyclerView)

        buttonEditProfile = newView?.findViewById(R.id.editProfileButton)
        buttonEditProfile?.setOnClickListener {

            val fragmentManager = activity.fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_frame_layout, editProfileFragment, "editProfile")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        contactButton = newView?.findViewById(R.id.contactButton)
        contactButton?.setOnClickListener {
            val contact = ContactDialogFragment()
            contact.show(this.fragmentManager, "Contact")
        }

        this.createShowProfileRequest()
        newLineChart = newView?.findViewById(R.id.lineChart)
        rankingChart = newView?.findViewById(R.id.rankingChart)

        this.graphManager.createGraph()
        this.graphManager.createRankingGraph()

        return newView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val player = userManager?.get(playerIdToShow)
        if(this.playerIdToShow == UserSingleton.loggedUserID){
            this.statusButton.setOnClickListener {
                val depart = DepartDialogFragment()
                depart.show(this.fragmentManager, "Depart")
            }
        }
        else {
            statusButton.isEnabled = false
            when (player?.status) {
                User.Status.AVAILABLE -> {
                    this.statusButton.background = resources.getDrawable(R.drawable.edge_text_profile_green)
                    this.statusButton.text = getString(R.string.disponivel)
                    this.statusButton.setTextColor(resources.getColor(R.color.profile_green))
                }
                else -> {
                    this.statusButton.background = resources.getDrawable(R.drawable.edge_text_profile_gray)
                    this.statusButton.text = getString(R.string.afastado)
                    this.statusButton.setTextColor(resources.getColor(R.color.profile_gray))
                }
            }
        }
        if(player != null){
            this.numb_games.text = (player.wins + player.loses).toString()
            this.numb_victory.text = player.wins.toString()
        }

    }


    /**
     * Method responsible for creating the show profile request and passing it to the interactor
     */
    private fun createShowProfileRequest() {
        val showUserProfileRequest = ShowProfileModel.Request(playerIdToShow)
        this.showProfileInteractor?.showProfile(showUserProfileRequest)
    }

    /**
     * class used to manage graph data and exhibition
     */
    class GraphManager (private var showProfileFragment : ShowProfileFragment) {

        /**
         * Method responsible to define the data of Y axis.
         */
        private fun setYAxisValuesVictories(): ArrayList<Entry> {
            val yValsVictories = ArrayList<Entry>()
            yValsVictories.add(Entry(0f, 2f))
            yValsVictories.add(Entry(1f, 3f))
            yValsVictories.add(Entry(2f, 4f))
            yValsVictories.add(Entry(3f, 2f))
            yValsVictories.add(Entry(4f, 1f))
            yValsVictories.add(Entry(5f, 5f))

            return yValsVictories
        }

        /**
         * Method responsible to define the data of Y axis.
         */
        private fun setYAxisValuesLosses(): ArrayList<Entry> {
            val yValsLosses = ArrayList<Entry>()
            yValsLosses.add(Entry(0f, 4f))
            yValsLosses.add(Entry(1f, 3f))
            yValsLosses.add(Entry(2f, 4f))
            yValsLosses.add(Entry(3f, 1f))
            yValsLosses.add(Entry(4f, 3f))
            yValsLosses.add(Entry(5f, 2f))

            return yValsLosses
        }

        /**
         * Method responsible to define the data of Y axis.
         */
        private fun setYAxisValuesRanking(): ArrayList<Entry> {

            val yValsRanking = ArrayList<Entry>() //array responsible to store all values of Y
            yValsRanking.add(Entry(0f, 6f))
            yValsRanking.add(Entry(1f, 6f))
            yValsRanking.add(Entry(2f, 8f))
            yValsRanking.add(Entry(3f, 3f))
            yValsRanking.add(Entry(4f, 4f))
            yValsRanking.add(Entry(5f, 7f))

            return yValsRanking
        }

        /**
         * Method responsible to create the graph, using the function and
         * @param yAxes values from yAxes
         * @param label chart label
         * @color chart lineColor
         */
        private fun customizeChartLine(yAxes: ArrayList<Entry>, label: String, color: Int): LineDataSet {

            val line = LineDataSet(yAxes, label) // Access the data of yAxes, introduce a legend and customize the graphic
            line.setDrawCircles(false) // Circle for important values
            line.setDrawCircleHole(true) // Draw Circles
            line.setDrawValues(false)// Hide values from a point in chart
            line.mode = LineDataSet.Mode.CUBIC_BEZIER // Make it curves
            line.cubicIntensity = cubicIntensity// Line curves intensity
            line.fillAlpha = houndredLine
            line.color = color // Line color
            line.lineWidth = lineWidth // Line width
            line.axisDependency = YAxis.AxisDependency.LEFT

            return line
        }

        /**
         * Load chart with desired properties
         * @param data chart data values
         * @param chart main chart
         * @param description chart description
         */
        private fun loadChart(data: LineData, chart: LineChart, description: Description) {

            chart.data = data
            chart.legend!!.isEnabled = false
            chart.description = description
            chart.setDrawBorders(false)
            chart.setScaleEnabled(false) // Allows interaction
            chart.isScaleXEnabled = false
            chart.isScaleYEnabled = false
            chart.axisRight?.isEnabled = false // Take off left edge

            this.customizeAxisX(chart.xAxis)
            this.customizeLeftAxis(chart.axisLeft)
            this.customizeChart(chart)
        }

        /**
         * Customize chart animation and view edge
         * @param chart desired chart
         */
        private  fun customizeChart(chart: LineChart) {

            val left = 0f
            val right = 0f
            val bottom = 0f
            chart.invalidate()
            chart.setExtraOffsets(left,top,right,bottom)
        }

        /**
         * Customize left chart properties
         * @param leftAxis leftAxis from chart
         */
        private fun customizeLeftAxis(leftAxis: YAxis) {

            val granularity = 2.0f
            leftAxis.textColor = Color.WHITE
            leftAxis.textSize = textSize
            leftAxis.setDrawAxisLine(false)
            leftAxis.setDrawGridLines(false)
            leftAxis.setAxisMaxValue(8f)
            leftAxis.setAxisMinValue(0f)
            leftAxis.granularity = granularity
        }

        /**
         * Method responsible to create Main Chart
         * @param axisX axis x property from chart
         */
        private fun customizeAxisX(axisX: XAxis) {

            val granularity = 1f
            val lastMonths = arrayOf("Set", "Out", "Nov", "Dez", "Jan", "Fev")
            axisX.valueFormatter = IndexAxisValueFormatter(lastMonths)
            axisX.granularity = granularity
            axisX.textColor = Color.WHITE
            axisX.setDrawGridLines(false)
            axisX.setDrawAxisLine(false)
            axisX.position = XAxis.XAxisPosition.BOTTOM
        }


        /**
         * Method responsible to create Main Chart
         */
        fun createGraph() {

            val victoryResults = setYAxisValuesVictories() //responsible to access the method setYAxisValues
            val losesResults = setYAxisValuesLosses()
            val matchesResults = setYAxisValuesRanking()
            val orange = ContextCompat.getColor(showProfileFragment.context!!, R.color.orange_chart)
            val green = ContextCompat.getColor(showProfileFragment.context!!, R.color.green_chart)
            val blue = ContextCompat.getColor(showProfileFragment.context!!, R.color.blue_chart)
            val dataSets = ArrayList<ILineDataSet>() // Created an array which has type ILineDataSet(Type defined by MPAndroidChart)
            val victoryLine = this.customizeChartLine(losesResults, "Vitoria", green)
            val losesLine = this.customizeChartLine(victoryResults, "Derrotas", orange)
            val matchesLine = this.customizeChartLine(matchesResults, "Partidas", blue)
            dataSets.add(victoryLine)
            dataSets.add(losesLine)
            dataSets.add(matchesLine)

            val lineData = LineData(dataSets) // Added data to chart
            val description = Description()
            description.text = ""
            this.loadChart(lineData, showProfileFragment.newLineChart!!, description)
        }

        /**
         * Method responsible to create the graph, using the function
         * SetYAxisValuesRanking.
         */
        fun createRankingGraph() {

            val yellow = ContextCompat.getColor(showProfileFragment.context!!, R.color.yellow_chart)
            val yAxesRanking = setYAxisValuesRanking() //responsible to access the method setYAxisValuesRanking
            val dataSetsRanking = ArrayList<ILineDataSet>()//Created an array which has type ILineDataSet(Type defined by MPAndroidChart)
            val line = this.customizeChartLine(yAxesRanking, "Ranking", yellow)
            dataSetsRanking.add(line)
            val lineData = LineData(dataSetsRanking) // Added data to chart
            val description = Description()
            description.text = ""

            this.loadChart(lineData, showProfileFragment.rankingChart!!, description)
        }
    }

    /**
     * This method is called when user edits information.
     */
    override fun onResume() {
        super.onResume()
        this.createShowProfileRequest()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.createShowProfileRequest()
    }

    /**
     * Method responsible to set all the references on this scene
     */
    private fun setupShowProfileScene() {
        val viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        presenter.challengeManager = ChallengeManager()
        presenter.userManager = UserManager()
        viewScene.showProfileInteractor = interactor
        interactor.worker.updateLogic = interactor
        interactor.worker.userManager = userManager
    }

    /**
     * Method responsible to receive data from presenter and show it
     *
     * @param viewModel Contains the formatted data to be displayed
     */
    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        username?.text = viewModel.playerInfo.name
        RankingID?.text = viewModel.playerInfo.rank

        imageView?.let {
            val url = getResources().getString(R.string.image_server_URL) +
                    UserSingleton.loggedUser.id +
                    ".png"
            Picasso.get().load(url).into(imageView)
        }

        showProfileRecyclerView?.adapter = ShowProfileAdapter(viewModel.formattedChallenges,this)

        if(viewModel.playerInfo.name != UserSingleton.loggedUser.name){
            buttonEditProfile?.visibility = View.INVISIBLE
        }
        contactButton?.setOnClickListener {
            val contact = ContactDialogFragment()
            contact.playerInfo = viewModel.playerInfo
            contact.show(this.fragmentManager, "Contact")
        }

    }

    /**
     * Adapter Class to control recycler fragment on Show Profile
     *
     * @property challengesFormatted It's a latest played games of the user formatted
     * @property fragment Fragment that will show this adapter
     */
    class ShowProfileAdapter(private val challengesFormatted: List<ShowProfileModel.FormattedChallenge>,
                             private val fragment: Fragment) : RecyclerView.Adapter<ShowProfileAdapter.ViewHolder>(){

        override fun getItemCount(): Int {

             return this.challengesFormatted.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindView(challengesFormatted[position])

        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShowProfileAdapter.ViewHolder{
            val view = LayoutInflater.from(fragment.activity).inflate(R.layout.columns_challenges_show, parent,
                    false)
            return ShowProfileAdapter.ViewHolder(view)
        }


        /**
         * View Holder Class to control items that will show on Recycler fragment
         *
         * @property itemView View that contains properties to show on recycler fragment
         */
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

            /**
             * Function to bind all information about last played games of the user
             *
             * @param challengeFormatted Formatted data to show last played games
             */
            fun bindView(challengeFormatted: ShowProfileModel.FormattedChallenge){
                itemView.challengedPhoto.setImageResource(challengeFormatted.opponentPictureAdress)
                itemView.nameLabel.text = challengeFormatted.opponentName
                itemView.dateLabel.text = challengeFormatted.challengeDates
                itemView.setsLabel.text = challengeFormatted.setsResult
                itemView.gamesLabel.text =  challengeFormatted.gamesResults
                itemView.headsLabel.text = challengeFormatted.headToHeadResults

                when {
                    challengeFormatted.challengeResult == ShowProfileModel.ChallengeResult.WON -> {
                        itemView.wonCard.visibility = View.VISIBLE
                        itemView.lostCard.visibility = View.INVISIBLE
                    }
                    challengeFormatted.challengeResult == ShowProfileModel.ChallengeResult.LOST -> {
                        itemView.wonCard.visibility = View.INVISIBLE
                        itemView.lostCard.visibility = View.VISIBLE
                    }
                    else -> {
                        itemView.wonCard.visibility = View.INVISIBLE
                        itemView.lostCard.visibility = View.INVISIBLE

                    }
                }

            }

        }


    }

    companion object {
        const val houndredLine = 110
        const val cubicIntensity = 0.2f
        const val lineWidth = 4.0f
        const val top = 15f
        const val textSize = 12.0f
    }
}

/**
 * This class implements DialogFragment, and it is responsible for implementing the functionality of
 * creating an Alertdialog, which brings 4 interaction options to users
 *
 * @property playerInfo responsible to access information from a formatted player of Model
 */

class ContactDialogFragment: DialogFragment() {

    var playerInfo: ShowProfileModel.FormattedPlayer? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Contatos")
                .setItems(R.array.contactArray, DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        0 -> {
                            callContactDialog()
                        }
                        1 -> {
                            addContactDialog()
                        }
                        2 -> {
                            emailDialog()
                        }
                        3 -> {
                                whatsAppDialog()
                            }
                        4 -> {
                            telegramDialog()
                        }
                    }
                }).setIcon(R.drawable.icon_date)
        return builder.create()
    }

    private fun callContactDialog() {
        val number = Uri.parse("tel:${playerInfo?.number}")
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        startActivity(callIntent)
    }

    private fun addContactDialog() {
        val emailIntent = Intent(android.content.Intent.ACTION_SEND)
        emailIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        emailIntent.type = "vnd.android.cursor.item/email"
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf(playerInfo?.email))
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nexte Notification")
        startActivity(Intent.createChooser(emailIntent, "Enviando email..."))
    }

    private fun emailDialog() {
        val contactIntent = Intent(Intent.ACTION_INSERT)
        contactIntent.type = ContactsContract.Contacts.CONTENT_TYPE
        contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, playerInfo?.name)
        contactIntent.putExtra(ContactsContract.Intents.Insert.EMAIL, playerInfo?.email)
        contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, playerInfo?.number)
        startActivity(contactIntent)
    }

    private fun whatsAppDialog() {
        try {

            val whatsIntent = Intent(Intent.ACTION_SEND)
            val url = "https://api.whatsapp.com/send?phone=" + playerInfo?.number
            whatsIntent.data = Uri.parse(url)
            whatsIntent.type = "text/plain"
            whatsIntent.setPackage("com.whatsapp")
            whatsIntent.putExtra(Intent.EXTRA_TEXT, "Olá, está disponível para jogar?")
            whatsIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, playerInfo?.number)
            startActivity(whatsIntent)

        } catch (e: ActivityNotFoundException){
            Toast.makeText(activity,"Você não possui o aplicativo WhatsApp instalado",Toast.LENGTH_SHORT).show()
        }
    }

    private fun telegramDialog() {
        try {

            val telegramIntent = Intent(Intent.ACTION_SEND)
            telegramIntent.type = "text/plain"
            telegramIntent.setPackage("org.telegram.messenger")
            telegramIntent.putExtra(Intent.EXTRA_TEXT, "Olá, está disponível para jogar?")
            telegramIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, playerInfo?.number)

        } catch (e: ActivityNotFoundException){
            Toast.makeText(activity,"Você não possui o aplicativo Telegram instalado" + e.toString(),Toast.LENGTH_SHORT).show()
        }
    }

}


/**
 * This class implements DialogFragment, and it is responsible for implementing the functionality of
 * creating an Alertdialog, which brings 3 options of depart for user
 *
 */

class DepartDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Afastar-se")
                .setItems(R.array.departArray, DialogInterface.OnClickListener { _, which ->
                    when (which) {
                        0 -> {
                            setStatus(User.Status.AVAILABLE)
                        }
                        1 -> {
                            setStatus(User.Status.UNAVAILABLE)
                        }
                        2 -> {
                            setStatus(User.Status.INJURED)
                        }
                    }
                }).setIcon(R.drawable.icon_time)
        return builder.create()
    }

    private fun setStatus(status: User.Status) {
        val user = UserSingleton.loggedUser
        UserManager().update(User(user.id, user.name, user.profilePicture,
                user.nickname, user.birthDate, user.rankingPosition, user.email,
                user.phone,  user.wins, user.loses, user.gender, user.category, status, user.challengeSended,
                user.challengeReceived, user.latestGames))
    }
}
