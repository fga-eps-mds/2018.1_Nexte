package com.nexte.nexte.LikeListScene

/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListDisplayLogic {
    fun displayLikeList(viewModel: LikeListModel.ViewModel)
}

class LikeListView : LikeListDisplayLogic {

    var interactor: LikeListInteractor? = null

}
    override fun onCreate(savedInstanceState: Bundle?) {

    }
