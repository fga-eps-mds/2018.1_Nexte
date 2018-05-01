package com.nexte.nexte.MatchScene

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.nexte.nexte.R
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater
import java.util.zip.Inflater


interface MatchDisplayLogic {

    fun displayMatch(viewModel: MatchModel.InitScene.ViewModel)

}

class MatchView : AppCompatActivity(), MatchDisplayLogic {

    var interactor: MatchInteractor? = null
    var matchViewAdapter: ViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_view)

        this.setUpMatchScene()



    }


    private fun setUpMatchScene() {

        var interactor = MatchInteractor()
        var presenter = MatchPresenter()
        var view = this

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewController = view


    }

    class MatchDataAdapter (private val matchInfo: MatchModel.FormattedMatchData,
                            private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemViewType(position: Int): Int {
            if (position == 0) {
                return R.layout.row_match_info
            }
            else if (position == 1) {
                return R.layout.row_match_sets
            }
            else
                return R.layout.row_match_time
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

            val holder = RecyclerView.ViewHolder
         }
    }



}