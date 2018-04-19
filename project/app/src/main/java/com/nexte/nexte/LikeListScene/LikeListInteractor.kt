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
 *  * Class that implements [LikeListBusinessLogic] and is responsible for the communication
 *  between worker and presenter
 *
 * @property worker Reference to worker [LikeListWorker]
 * @property presenter Reference to presenter [LikeListPresenter]
 */
class LikeListInteractor(var presenter: LikeListPresentationLogic? = null ) : LikeListBusinessLogic {

    var worker: LikeListWorker = LikeListWorker()

    override fun fetchDataToList(request: LikeListModel.Request) {
        worker.getListLikesPlayers(request) { response ->
            presenter?.formatLikeList(response)

        }
    }
}
