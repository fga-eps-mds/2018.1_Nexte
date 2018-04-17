package com.nexte.nexte.ShowProfileScene

/**
 * This interface defines the methods that will be used to intermediate
 * communication between worker and presenter
 */
interface ShowProfileBusinessLogic {

    /**
     * Method that defines profile informations that will be responsible to
     * pass the request to Worker and send the response to Presenter
     *
     * @param request Request model of feed that contains data to pass for Worker
     */
    fun showProfile(request: ShowProfileModel.Request)
}

/**
 * This class defines how the communication between worker and presenter will be
 *
 * Class that implements [ShowProfileModel] and is responsible for the communication
 * between Worker and
 *
 * @property worker
 * @property presenter
 */
class ShowProfileInteractor : ShowProfileBusinessLogic {

    private var worker = ShowProfileWorker() // Reference for the worker that will
    // Receives the request and create an response
    var presenter : ShowProfilePresentationLogic? = null // Reference for the presenter
    // That will receive the response and create an viewModel

    /**
     * This method does the communication between Worker and Presenter
     *
     * @param request Contains the request that was send by our View
     */
    override fun showProfile(request: ShowProfileModel.Request) {

        worker.getUserProfile(request) { response ->
            this.presenter?.presentUserProfile(response)
        }
    }
}

