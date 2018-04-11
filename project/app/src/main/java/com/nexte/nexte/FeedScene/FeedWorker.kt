package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Class responsible to do request for anywhere, format response and call completion to send data for called class
 */
class FeedWorker {

    /**
     * Function to fetch feed data of server
     *
     * @param request Feed model request that contains need information to send for server
     * @param completion Method for call on parent class
     */
    fun fetchFeedData(request: FeedModel.Request, completion: (FeedModel.Response) -> Unit) {
        val response = FeedModel.Response(FeedManager.feedList)
        completion(response)
    }
}