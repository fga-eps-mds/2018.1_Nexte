package com.nexte.nexte.LikeListScene

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.nexte.nexte.LikeListScene.LikeListModel
import com.nexte.nexte.R
import com.nexte.nexte.LikeListScene.LikeListDisplayLogic
import kotlinx.android.synthetic.main.activity_list_like.*

/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListDisplayLogic {
    fun displayLikeList(viewModel: LikeListModel.ViewModel)
}

class LikeListView : AppCompatActivity(), LikeListDisplayLogic {

    var interactor: LikeListInteractor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_like)

        setUpLikeListScene()

        feedRecyclerView.layoutManager = LinearLayoutManager(this)
        this.

        val request = LikeListModel.Request()
        interactor?.fetchDataToList(request)


    }

    private fun setUpLikeListScene() {

        var view = this
        var interactor = LikeListInteractor()
        var presenter = LikeListPresenter()

        view.interactor = interactor
        interactor.presenter = presenter
        presenter.viewList = view
    }
}