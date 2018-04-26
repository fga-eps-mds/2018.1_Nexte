package com.nexte.nexte.ShowProfileScene

import android.content.Context
import android.content.Entity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CallLog
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileView
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_show_profile.*
import java.security.KeyStore
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.LineData
import java.nio.file.Files.size
import java.nio.file.Files.size
import java.nio.file.Files.size
import java.nio.file.Files.size











/**
 * This interface will be responsible to define the methods that
 * will receive the formatted data from [ShowProfilePresenter]
 */
interface ShowProfileDisplayLogic {

    fun displayProfile(viewModel : ShowProfileModel.ViewModel)
}

/**
 * This class implements ShowProfileDisplayLogic, and it is responsible to
 * display information about the user
 *
 * @property showProfileInteractor responsible to receive request and send it to worker
 */
class ShowProfileView : AppCompatActivity(), ShowProfileDisplayLogic {

    var xAxes = ArrayList<String>()
    var yAxes = ArrayList<Entry>()
    var lineDataSets = ArrayList<ILineDataSet>()

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
        val lineChart = findViewById<LineChart>(R.id.lineChart) as LineChart

            xAxes.add("Set")
            xAxes.add("Out")
            xAxes.add("Nov")
            xAxes.add("Dez")

            yAxes.add(Entry(10f, 0f))
            yAxes.add(Entry(20f, 1f))
            yAxes.add(Entry(30f, 2f))
            yAxes.add(Entry(40f, 3f))
            yAxes.add(Entry(50f, 4f))
            yAxes.add(Entry(60f, 5f))

        val xaxes = ArrayList<String>(xAxes.size)


        for (i in 0 until xAxes.size) {
            xaxes[i] = xAxes[i].toString()
        }

        val lineDataSet = LineDataSet(yAxes, "values")

        lineDataSet.setDrawCircles(true)
        lineDataSet.setColors(Color.BLUE)

        lineDataSets.add(lineDataSet)

        lineChart.data = LineData(lineDataSets)

        lineChart.setVisibleXRangeMaximum(65f)
        lineChart.setTouchEnabled(true)
        lineChart.isDragEnabled

        

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
