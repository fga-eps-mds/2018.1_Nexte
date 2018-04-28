package com.nexte.nexte.ShowProfileScene

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileView
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
class ShowProfileView : AppCompatActivity(), ShowProfileDisplayLogic {

    var showProfileInteractor : ShowProfileBusinessLogic? = null

    /**
     * Method called when screen is loaded, responsible to load user information
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)
        setupShowProfileScene()

        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)

        editProfileButton.setOnClickListener {

            val intent = Intent(this, EditProfileView::class.java)

            startActivity(intent)
        }

        this.createGraph()
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
     * Method responsable to define the data of Y axis.
     *  @property yVals array responsable to store all values of Y
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
     * Method responsible to create the graph, using the function setXAxisValues and
     * SetYAxisValues.
     *  @property lineChart instance a view from xml.
     * @property xAxis responsible to access the method setXAxisValues
     * @property yAxes responsible to access the method setYAxisValues
     * @property dataSet Created an array which has type ILineDataSet(Type defined by MPAndroidChart)
     * @property line Access the data of yAxes, introduce a legend and customize the graphic
     * @property lastMonths Responsible to create an array that store the string about last months of matches of the user
     * @property lineData Type: LineData, access the data defined, and xml LineChart have access to it
     */

    fun createGraph() {

        val xAxis = lineChart.xAxis
        val xAxes = setXAxisValues()
        val yAxes = setYAxisValues()
        val dataSets = ArrayList<ILineDataSet>()

        val line = LineDataSet(yAxes, "Vitoria")
        line.fillAlpha = 110
        line.color = Color.BLUE
        line.axisDependency =YAxis.AxisDependency.LEFT
        dataSets.add(line)

        val lastMonths = arrayOf("Set", "Out", "Nov", "Dez","Jan","Fev")
        xAxis.valueFormatter = IndexAxisValueFormatter(lastMonths)
        xAxis.granularity = 1f
        xAxis.textColor = Color.WHITE

        val points = LineData(dataSets)
        points.setValueTextColor(Color.WHITE)

        val lineData = LineData(dataSets)
        lineChart.data = lineData
        lineChart.axisLeft.setAxisMaxValue(8f)
        lineChart.axisLeft.setAxisMinValue(0f)
        lineChart.axisRight.setAxisMaxValue(0f)
        lineChart.axisRight.setAxisMinValue(8f)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.setScaleEnabled(false)
        lineChart.invalidate()
    }

    /**
     * This method is called when user edits information.
     */
    override fun onResume() {

        super.onResume()

        val showUserProfileRequest: ShowProfileModel.Request = ShowProfileModel.Request("gabrielalbino",
                "AUFDSASFSA321IEUNFDI23FIQ2F")
        this.showProfileInteractor?.showProfile(showUserProfileRequest)
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
        viewScene.showProfileInteractor = interactor
    }

    /**
     * Method responsible to receive data from presenter and show it
     *
     * @param viewModel Contains the formatted data to be displayed
     */
    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {

        username.text = viewModel.playerInfo.name
        RankingID.text = viewModel.playerInfo.rank
        club.text = viewModel.playerInfo.club
        age.text = viewModel.playerInfo.age
        email.text = viewModel.playerInfo.email
    }

}
