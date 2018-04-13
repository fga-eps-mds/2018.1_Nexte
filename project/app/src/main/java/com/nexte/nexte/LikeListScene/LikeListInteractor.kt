package com.nexte.nexte.LikeListScene

import android.app.DownloadManager

/**
 * Created by lorrany on 12/04/18.
 */
interface LikeListBusinessLogic {

    fun getPlayersForList(request: LikeListModel.Request)
}

class ListLikeInteractor: LikeListBusinessLogic {
    var worker: LikeListWorker()
    var presenter: LikeListPresentationLogic


    override fun getPlayersForList(request: LikeListModel.Request) {

        worker.getPlayersForList(request) { response ->

        }
    }
}
