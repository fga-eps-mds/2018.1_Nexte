package com.nexte.nexte.LikeListScene


/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListPresentationLogic {

    fun presentLikeList(response: LikeListModel.Response)
}

class LikeListPresenter : LikeListPresentationLogic {

    override fun presentLikeList(response: LikeListModel.Response) {
        val viewModel: LikeListModel.ViewModel
    }


}