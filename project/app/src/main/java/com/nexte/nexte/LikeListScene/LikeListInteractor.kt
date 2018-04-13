package com.nexte.nexte.LikeListScene

import android.app.DownloadManager

/**
 * Created by lorrany on 12/04/18.
 */

/**
 * Interface to define Business Logic to LikeList Class that will used to call this Interactor on other class layer
 */
interface LikeListBusinessLogic {
    /**
     * * Method responsable to fetch players informations to list and
     * pass request for the Worker and send response to the Presenter
     *
     */
    fun fetchDataToList(request: LikeListModel.Request)
}

class LikeListInteractor : LikeListBusinessLogic {

    var worker: LikeListWorker = LikeListWorker()
    var presenter: LikeListPresentationLogic


    override fun fetchDataToList(request: LikeListModel.Request) {
        worker.fetchDataToList(request) { response ->
            presenter.presentLikeList(response)

        }
    }
}
