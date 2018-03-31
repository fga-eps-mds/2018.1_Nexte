package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

interface ShowProfileBusinessLogic{
    fun showProfile(request: ShowProfileModel.Request)
}

class ShowProfileInteractor : ShowProfileBusinessLogic {

    private var worker = ShowProfileWorker()
    var presenter : ShowProfilePresentationLogic? = null

    override fun showProfile(request: ShowProfileModel.Request) {
        worker.getUserProfile(request) { response ->
            this.presenter?.presentUserProfile(response)
        }
    }
}

