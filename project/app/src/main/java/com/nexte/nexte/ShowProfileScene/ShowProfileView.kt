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
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.nexte.nexte.EditProfileScene.EditProfileView
import com.nexte.nexte.R
import kotlinx.android.synthetic.main.activity_show_profile.*
import java.security.KeyStore
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.data.BarDataSet



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
        val mChart = findViewById<LineChart>(R.id.mChart) as LineChart

        var entriesvictories = ArrayList<Entry>()

            entriesvictories.add(Entry(4f, 0f))
            entriesvictories.add(Entry(8f, 1f))
            entriesvictories.add(Entry(6f, 2f))
            entriesvictories.add(Entry(2f, 3f))
            entriesvictories.add(Entry(18f, 4f))
            entriesvictories.add(Entry(9f, 5f))


        var entriesloss = ArrayList<Entry>()

            entriesloss.add(Entry(15f, 0f))
            entriesloss.add(Entry(9f, 1f))
            entriesloss.add(Entry(12f, 2f))
            entriesloss.add(Entry(6f, 3f))
            entriesloss.add(Entry(20f, 4f))
            entriesloss.add(Entry(8f, 5f))

        var entriesmatch = ArrayList<Entry>()

            entriesmatch.add(Entry(12f, 0f))
            entriesmatch.add(Entry(10f, 1f))
            entriesmatch.add(Entry(13f, 2f))
            entriesmatch.add(Entry(9f, 3f))
            entriesmatch.add(Entry(4f, 4f))
            entriesmatch.add(Entry(7f, 5f))


        var labels = ArrayList<String>()
            labels.add("Set")
            labels.add("Out")
            labels.add("Nov")
            labels.add("Dez")


        val dataSet1 = LineDataSet(entriesvictories, "Vitórias")
        dataSet1.color = Color.BLUE
        val dataSet2 = LineDataSet (entriesloss, "Derrotas")
        dataSet2.color = Color.RED
        val dataSet3 = LineDataSet (entriesmatch, "Partidas")
        dataSet3.color = Color.GREEN

        dataSet = ArrayList<Entry>
        dataSet.addColor()
        dataSet.
        return dataSet
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
