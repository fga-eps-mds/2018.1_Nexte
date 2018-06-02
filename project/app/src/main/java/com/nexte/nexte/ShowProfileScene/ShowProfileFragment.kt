package com.nexte.nexte.ShowProfileScene

import android.content.Intent
import android.support.v4.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileView
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import kotlinx.android.synthetic.main.activity_show_profile.*

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

    var showProfileInteractor: ShowProfileBusinessLogic? = null
    var buttonEditProfile: Button? = null
    var rankingChart: LineChart? = null
    private var newLineChart: LineChart? = null // First chart view
    private var anotherPlayerName: String = ""
    var userManager: UserManager? = null
    val graphManager = GraphManager(this)

    /*
    This method is called on instantiate, and it's responsible to set the player that the profile will be
    displayed
    */
    fun getInstance(playerToShowName: String?): ShowProfileFragment {
        val bundle = Bundle()
        val showProfileFragment = ShowProfileFragment()

        if (playerToShowName != null) {
            bundle.putString("anotherPlayerName", playerToShowName)
        } else {
            bundle.putString("anotherPlayerName", "")
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
        this.anotherPlayerName = arguments.getString("anotherPlayerName")
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val newView = inflater?.inflate(R.layout.activity_show_profile, container, false)
        buttonEditProfile = newView?.findViewById(R.id.editProfileButton)
        buttonEditProfile?.setOnClickListener {
            val intent = Intent(activity, EditProfileView::class.java)
            startActivity(intent)
        }

        this.createShowProfileRequest()
        newLineChart = newView?.findViewById(R.id.lineChart)
        rankingChart = newView?.findViewById(R.id.rankingChart)

        this.graphManager.createGraph()
        this.graphManager.createRankingGraph()

        return newView
    }

    /**
     * Method responsible for creating the show profile request and passing it to the interactor
     */
    fun createShowProfileRequest() {
        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request(anotherPlayerName)
        this.showProfileInteractor?.showProfile(showUserProfileRequest)
    }

    /**
     * class used to manage graph data and exhibition
     */
    class GraphManager (var showProfileFragment : ShowProfileFragment) {

        /**
         * Method responsible to define the data of Y axis.
         */
        fun setYAxisValues(): ArrayList<Entry> {
            val yVals = ArrayList<Entry>()
            yVals.add(Entry(0f, 2f))
            yVals.add(Entry(1f, 3f))
            yVals.add(Entry(2f, 4f))
            yVals.add(Entry(3f, 2f))
            yVals.add(Entry(4f, 1f))
            yVals.add(Entry(5f, 5f))

            return yVals
        }

        /**
         * Method responsible to define the data of Y axis.
         */

        fun setYAxisValuesRanking(): ArrayList<Entry> {
            val yValsRanking = ArrayList<Entry>() //array responsible to store all values of Y
            yValsRanking.add(Entry(0f, 3f))
            yValsRanking.add(Entry(1f, 2f))
            yValsRanking.add(Entry(2f, 5f))
            yValsRanking.add(Entry(3f, 2f))
            yValsRanking.add(Entry(4f, 1f))
            yValsRanking.add(Entry(5f, 4f))

            return yValsRanking
        }

        /**
         * Method responsible to create the graph, using the function and
         * SetYAxisValues.
         */
        fun customizeChartLine(yAxes: ArrayList<Entry>, label: String): LineDataSet {
            val line = LineDataSet(yAxes, label) // Access the data of yAxes, introduce a legend and customize the graphic
            line.setDrawCircles(false) // Circle for important values
            line.setDrawCircleHole(true) // Draw Circles
            line.setDrawValues(false)// Hide values from a point in chart
            line.setMode(LineDataSet.Mode.CUBIC_BEZIER) // Make it curves
            line.cubicIntensity = 0.2f // Line curves intensity
            line.fillAlpha = houndredLine
            line.color = Color.BLUE // Line color
            line.lineWidth = 4.0f // Line width
            line.axisDependency = YAxis.AxisDependency.LEFT

            return line
        }

        fun createChart(chart: LineChart, dataSets: ArrayList<ILineDataSet>) {

        }



        fun createGraph() {
            val xAxis = showProfileFragment.newLineChart?.xAxis //instance a view from xml.
            val yAxes = setYAxisValues() //responsible to access the method setYAxisValues
            val dataSets = ArrayList<ILineDataSet>() // Created an array which has type ILineDataSet(Type defined by MPAndroidChart)

            val line = this.customizeChartLine(yAxes, "Vitoria")
            dataSets.add(line)

            //Responsible to create an array that store the string about last months of matches of the user
            val lastMonths = arrayOf("Set", "Out", "Nov", "Dez", "Jan", "Fev")
            xAxis?.valueFormatter = IndexAxisValueFormatter(lastMonths)
            xAxis?.granularity = 1f
            xAxis?.textColor = Color.WHITE

            /* TO DO - Review this fragment of code
            LineData, access the data defined, and xml LineChart have access to it
            val points = LineData(dataSets)
            points.setValueTextColor(Color.WHITE)
            */

            val lineData = LineData(dataSets) // Added data to chart
            val description = Description()
            description.text = ""

            //  General Chart Settings
            showProfileFragment.newLineChart?.data = lineData
            showProfileFragment.newLineChart?.legend!!.isEnabled = false
            showProfileFragment.newLineChart?.description = description
            showProfileFragment.newLineChart?.setDrawBorders(false)
            showProfileFragment.newLineChart?.setScaleEnabled(false) // Allows interaction
            showProfileFragment.newLineChart?.isScaleXEnabled = false
            showProfileFragment.newLineChart?.isScaleYEnabled = false

            showProfileFragment.newLineChart?.axisLeft?.textColor = Color.WHITE
            showProfileFragment.newLineChart?.axisLeft?.textSize = 12.0f
            showProfileFragment.newLineChart?.axisLeft?.setDrawAxisLine(false)
            showProfileFragment.newLineChart?.axisLeft?.setDrawGridLines(false)
            showProfileFragment.newLineChart?.axisLeft?.setAxisMaxValue(8f)
            showProfileFragment.newLineChart?.axisLeft?.setAxisMinValue(0f)
            showProfileFragment.newLineChart?.animateXY(2000, 2000)

            showProfileFragment.newLineChart?.axisRight?.isEnabled = false // Take off left edge

            showProfileFragment.newLineChart?.xAxis?.textSize = 12.0f
            showProfileFragment.newLineChart?.xAxis?.setDrawAxisLine(false)
            showProfileFragment.newLineChart?.xAxis?.setDrawGridLines(false)

            showProfileFragment.newLineChart?.invalidate()
            showProfileFragment.newLineChart?.setExtraOffsets(0f,0f,0f,0f)
        }

        /**
         * Method responsible to create the graph, using the function
         * SetYAxisValuesRanking.
         */
        fun createRankingGraph() {
            val xAxisRanking = showProfileFragment.rankingChart?.xAxis //instance a view from xml.
            val yAxesRanking = setYAxisValuesRanking() //responsible to access the method setYAxisValuesRanking
            val dataSetsRanking = ArrayList<ILineDataSet>()//Created an array which has type ILineDataSet(Type defined by MPAndroidChart)

            val lineData = LineData(dataSetsRanking) // Added data to chart
            val description = Description()
            description.text = ""

            val lineRanking = LineDataSet(yAxesRanking, "Posição no Ranking") //Access the data of yAxes,
            // introduce a legend and customize the graphic
            lineRanking.setDrawCircles(false) // Circle for important values
            lineRanking.setDrawCircleHole(true) // Draw Circles
            lineRanking.setDrawValues(false)// Hide values from a point in chart
            lineRanking.setMode(LineDataSet.Mode.CUBIC_BEZIER) // Make it curves
            lineRanking.cubicIntensity = 0.2f // Line curves intensity
            lineRanking.fillAlpha = houndredLine
            lineRanking.color = Color.RED
            lineRanking.lineWidth = 4.0f // Line width
            lineRanking.axisDependency = YAxis.AxisDependency.LEFT
            dataSetsRanking.add(lineRanking)

            val lastMonths = arrayOf("Set", "Out", "Nov", "Dez", "Jan", "Fev") //Responsible to create an array that store the
            // string about last months of matches of the user
            xAxisRanking?.valueFormatter = IndexAxisValueFormatter(lastMonths)
            xAxisRanking?.granularity = 1f
            xAxisRanking?.textColor = Color.WHITE

            val points = LineData(dataSetsRanking) //access the data defined, and xml LineChart have access to it
            points.setValueTextColor(Color.WHITE)

            val rankingData = LineData(dataSetsRanking)
            showProfileFragment.rankingChart?.data = rankingData
            showProfileFragment.rankingChart?.legend!!.isEnabled = false
            showProfileFragment.rankingChart?.description = description
            showProfileFragment.rankingChart?.setDrawBorders(false)
            showProfileFragment.rankingChart?.setScaleEnabled(false)
            showProfileFragment.rankingChart?.isScaleXEnabled = false
            showProfileFragment.rankingChart?.isScaleYEnabled = false

            showProfileFragment.rankingChart?.axisLeft?.setDrawAxisLine(false)
            showProfileFragment.rankingChart?.axisLeft?.setDrawGridLines(false)
            showProfileFragment.rankingChart?.axisLeft?.textColor = Color.WHITE
            showProfileFragment.rankingChart?.axisLeft?.textSize = 12.0f
            showProfileFragment.rankingChart?.axisLeft?.setAxisMaxValue(8f)
            showProfileFragment.rankingChart?.axisLeft?.setAxisMinValue(0f)
            showProfileFragment.rankingChart?.axisLeft?.setDrawGridLines(false)
            showProfileFragment.rankingChart?.xAxis?.setDrawGridLines(false)
            showProfileFragment.rankingChart?.setScaleEnabled(false)
            showProfileFragment.rankingChart?.animateXY(2000, 2000)

            showProfileFragment.rankingChart?.axisRight?.isEnabled = false

            showProfileFragment.rankingChart?.xAxis?.textSize = 12.0f
            showProfileFragment.rankingChart?.xAxis?.setDrawAxisLine(false)
            showProfileFragment.rankingChart?.xAxis?.setDrawGridLines(false)

            showProfileFragment.rankingChart?.setExtraOffsets(0f,0f,0f,0f)
            showProfileFragment.rankingChart?.invalidate()


        }
    }

    /**
     * This method is called when user edits information.
     */
    override fun onResume() {
        super.onResume()
        this.createShowProfileRequest()
    }

    /**
     * Method responsible to set all the references on this scene
     */
    fun setupShowProfileScene() {
        val viewScene = this
        val interactor = ShowProfileInteractor()
        val presenter = ShowProfilePresenter()

        interactor.presenter = presenter
        presenter.viewScene = viewScene
        viewScene.showProfileInteractor = interactor
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
//        email?.text = viewModel.playerInfo.email
        if(viewModel.playerInfo.name != UserSingleton.getUserInformations().name){
            buttonEditProfile?.visibility = View.INVISIBLE
        }
    }

    companion object { const val houndredLine = 110 }
}

