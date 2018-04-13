package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

/**
 * This interface define the methods that will be used to intermediate communication between worker and presenter
 */
interface ShowProfileBusinessLogic {

    fun showProfile(request: ShowProfileModel.Request)
}

/**
 * This class define how the communication between worker and presenter will be
 */
class ShowProfileInteractor : ShowProfileBusinessLogic {

    private var worker = ShowProfileWorker() // Reference for the worker that will receive the request and create an response
    var presenter : ShowProfilePresentationLogic? = null // Reference for the presenter that will receive the response and create an viewModel

    /**
     * This method does the communication between worker and presenter
     *
     * @param request Contains the request that was send by our view
     */
    override fun showProfile(request: ShowProfileModel.Request) {

        worker.getUserProfile(request) { response ->
            this.presenter?.presentUserProfile(response)
        }
    }
}

