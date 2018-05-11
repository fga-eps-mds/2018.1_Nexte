package com.nexte.nexte.LikeListScene

/**
 * Interface to define Business Logic of Like List Class
 * that will be used to call this Interactor on other class layer
 */
interface LikeListBusinessLogic {

    /**
     * * Method that defines fetch players information to list and
     * pass request for the Worker and send the response to Presenter
     *
     * @param request Request model of list that contains data to pass for Worker
     */
    fun fetchDataToList(request: LikeListModel.Request)
}

/**
 * Interface to define Response Logic of Like List Class
 * that will be used to make the communication between worker and presenter
 */
interface UpdateResponseLogic {

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun getUsers(response: LikeListModel.Response)
}

/**
 *  * Class that implements [LikeListBusinessLogic] and is responsible for the communication
 *  between worker and presenter
 *
 * @property worker Reference to worker [LikeListWorker]
 * @property presenter Reference to presenter [LikeListPresenter]
 */
class LikeListInteractor(var presenter: LikeListPresentationLogic? = null ) : LikeListBusinessLogic, UpdateResponseLogic {

    var worker: LikeListWorker = LikeListWorker()

    override fun fetchDataToList(request: LikeListModel.Request) {
        this.worker.getListLikesPlayers(request = request)
    }

    override fun getUsers(response: LikeListModel.Response) {
        this.presenter?.formatLikeList(response)
    }
}
