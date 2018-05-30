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
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileFragment
import com.nexte.nexte.R
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
class ShowProfileView : Fragment(), ShowProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null
    var buttonEditProfile : Button? = null
    var rankingChart : LineChart? = null
    var newLineChart : LineChart? = null

    /**
     * Method called when screen is loaded, responsible to load user information
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setupShowProfileScene()

        this.createShowProfileRequest()

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val newView = inflater?.inflate(R.layout.activity_show_profile, container, false)
        buttonEditProfile =  newView?.findViewById(R.id.editProfileButton)
        buttonEditProfile?.setOnClickListener {
            
            val editProfileFragment = EditProfileFragment().getInstance()
            val fragmentManager = activity.fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, editProfileFragment, "editProfile")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


        newLineChart = newView?.findViewById(R.id.lineChart)
        rankingChart = newView?.findViewById(R.id.rankingChart)

        this.createGraph()
        this.createRankingGraph()


        return newView


    }
    /**
     * Method responsible for creating the show profile request and passing it to the interactor
     */
    fun createShowProfileRequest() {
        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)

    }


    /**
     * Method responsable to define a param to X axis.
     * @property xVals array responsable to store all values of X
     * @property tam responsable to store the data of X axis.
     */

    fun setXAxisValues(): ArrayList<Entry> {

        val xVals = ArrayList<Entry>()

        return xVals
    }

    /**
     * Method responsible to define the data of Y axis.
     *  @property yVals array responsible to store all values of Y
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
     *  @property yValsRanking array responsible to store all values of Y
     */

    fun setYAxisValuesRanking(): ArrayList<Entry> {

        val yValsRanking = ArrayList<Entry>()

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
     *  @property lineChart instance a fragment from xml.
     * @property yAxes responsible to access the method setYAxisValues
     * @property dataSet Created an array which has type ILineDataSet(Type defined by MPAndroidChart)
     * @property line Access the data of yAxes, introduce a legend and customize the graphic
     * @property lastMonths Responsible to create an array that store the string about last months of matches of the user
     * @property lineData Type: LineData, access the data defined, and xml LineChart have access to it
     */

    fun createGraph() {

        val xAxis = newLineChart?.xAxis
        val xAxes = setXAxisValues()
        val yAxes = setYAxisValues()
        val dataSets = ArrayList<ILineDataSet>()

        val line = LineDataSet(yAxes, "Vitoria")
        line.fillAlpha = houndredLine
        line.color = Color.BLUE
        line.axisDependency =YAxis.AxisDependency.LEFT
        dataSets.add(line)

        val lastMonths = arrayOf("Set", "Out", "Nov", "Dez","Jan","Fev")
        xAxis?.valueFormatter = IndexAxisValueFormatter(lastMonths)
        xAxis?.granularity = 1f
        xAxis?.textColor = Color.WHITE

        val points = LineData(dataSets)
        points.setValueTextColor(Color.WHITE)

        val lineData = LineData(dataSets)
        newLineChart?.data = lineData
        newLineChart?.axisLeft?.setAxisMaxValue(8f)
        newLineChart?.axisLeft?.setAxisMinValue(0f)
        newLineChart?.axisRight?.setAxisMaxValue(0f)
        newLineChart?.axisRight?.setAxisMinValue(8f)
        newLineChart?.axisLeft?.setDrawGridLines(false)
        newLineChart?.xAxis?.setDrawGridLines(false)
        newLineChart?.setScaleEnabled(false)
        newLineChart?.invalidate()
    }
    /**
    * Method responsible to create the graph, using the function
    * SetYAxisValuesRanking.
    *  @property rankingChart instance a fragment from xml.
    * @property yAxesRanking responsible to access the method setYAxisValuesRanking
    * @property dataSetsRanking Created an array which has type ILineDataSet(Type defined by MPAndroidChart)
    * @property lineRanking Access the data of yAxes, introduce a legend and customize the graphic
    * @property lastMonths Responsible to create an array that store the string about last months of matches of the user
    * @property RankingData Type: LineData, access the data defined, and xml LineChart have access to it
    */

    fun createRankingGraph() {

        val xAxisRanking = rankingChart?.xAxis
        val yAxesRanking = setYAxisValuesRanking()
        val dataSetsRanking = ArrayList<ILineDataSet>()

        Log.e("Entrou", "aqui")

        val lineRanking = LineDataSet(yAxesRanking, "Posição no Ranking")
        lineRanking.fillAlpha = houndredLine
        lineRanking.color = Color.RED
        lineRanking.axisDependency =YAxis.AxisDependency.LEFT
        dataSetsRanking.add(lineRanking)

        val lastMonths = arrayOf("Set", "Out", "Nov", "Dez","Jan","Fev")
        xAxisRanking?.valueFormatter = IndexAxisValueFormatter(lastMonths)
        xAxisRanking?.granularity = 1f
        xAxisRanking?.textColor = Color.WHITE

        val points = LineData(dataSetsRanking)
        points.setValueTextColor(Color.WHITE)

        val rankingData = LineData(dataSetsRanking)
        rankingChart?.data = rankingData
        rankingChart?.axisLeft?.setAxisMaxValue(8f)
        rankingChart?.axisLeft?.setAxisMinValue(0f)
        rankingChart?.axisRight?.setAxisMaxValue(0f)
        rankingChart?.axisRight?.setAxisMinValue(8f)
        rankingChart?.axisLeft?.setDrawGridLines(false)
        rankingChart?.xAxis?.setDrawGridLines(false)
        rankingChart?.setScaleEnabled(false)
        rankingChart?.invalidate()
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
    }

    /**
     * Method responsible to receive data from presenter and show it
     *
     * @param viewModel Contains the formatted data to be displayed
     */
    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {

        username?.text = viewModel.playerInfo.name
        RankingID?.text = viewModel.playerInfo.rank
        club?.text = viewModel.playerInfo.club
        age?.text = viewModel.playerInfo.age
        email?.text = viewModel.playerInfo.email
    }

    companion object {
        const val houndredLine = 110

        fun newInstance(): ShowProfileView = ShowProfileView()

        }
    }

