package com.nexte.nexte.FeedScene

/**
 * Created by leticia on 27/03/18.
 */

class FeedWorker {

    constructor() { }

    fun getLastGame(request: FeedModel.Request, completion: (FeedModel.Response) -> Unit) {

        val lastGame: String? = request.lastGame

        val response: FeedModel.Response = FeedModel.Response()
        completion(response)


    }

}