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
        val response = FeedModel.Response(this.generateFeedMockData())
        completion(response)
    }

    /**
     * Auxiliar method for generate fictional data to use in fictional app mode
     *
     * @return Array of feed activities
     */
    private fun generateFeedMockData(): Array<FeedModel.FeedActivity> {

        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        var likesList1  = ArrayList<FeedModel.FeedPlayer>()

        val challenger2 = FeedModel.FeedPlayer("Letícia", R.mipmap.ic_launcher, 2)
        val challenged2 = FeedModel.FeedPlayer("Lorrany", R.mipmap.ic_launcher, 1)
        var likesList2  = ArrayList<FeedModel.FeedPlayer>()

        val challenger3 = FeedModel.FeedPlayer("Luis", R.mipmap.ic_launcher, 2)
        val challenged3 = FeedModel.FeedPlayer("Miguel", R.mipmap.ic_launcher, 0)
        var likesList3  = ArrayList<FeedModel.FeedPlayer>()

        val challenger4 = FeedModel.FeedPlayer("Geovanni", R.mipmap.ic_launcher, 3)
        val challenged4 = FeedModel.FeedPlayer("Alexandre", R.mipmap.ic_launcher, 2)
        var likesList4  = ArrayList<FeedModel.FeedPlayer>()

        val challenger5 = FeedModel.FeedPlayer("Larissa", R.mipmap.ic_launcher, 0)
        val challenged5 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 1)
        var likesList5  = ArrayList<FeedModel.FeedPlayer>()

        val challenger6 = FeedModel.FeedPlayer("Miguel", R.mipmap.ic_launcher, 0)
        val challenged6 = FeedModel.FeedPlayer("Letícia", R.mipmap.ic_launcher, 2)
        var likesList6  = ArrayList<FeedModel.FeedPlayer>()

        val challenger7 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val challenged7 = FeedModel.FeedPlayer("Geovanni", R.mipmap.ic_launcher, 1)
        var likesList7  = ArrayList<FeedModel.FeedPlayer>()

        val challenger8 = FeedModel.FeedPlayer("Alexandre", R.mipmap.ic_launcher, 3)
        val challenged8 = FeedModel.FeedPlayer("Larissa", R.mipmap.ic_launcher, 0)
        var likesList8  = ArrayList<FeedModel.FeedPlayer>()

        val challenge1 = FeedModel.FeedChallenge(challenger1, challenged1, Date())
        val challenge2 = FeedModel.FeedChallenge(challenger2, challenged2, Date())
        val challenge3 = FeedModel.FeedChallenge(challenger3, challenged3, Date())
        val challenge4 = FeedModel.FeedChallenge(challenger4, challenged4, Date())
        val challenge5 = FeedModel.FeedChallenge(challenger5, challenged5, Date())
        val challenge6 = FeedModel.FeedChallenge(challenger6, challenged6, Date())
        val challenge7 = FeedModel.FeedChallenge(challenger7, challenged7, Date())
        val challenge8 = FeedModel.FeedChallenge(challenger8, challenged8, Date())

        val feedActivity1 = FeedModel.FeedActivity(challenge1, Date(), likesList1)
        val feedActivity2 = FeedModel.FeedActivity(challenge2, Date(), likesList2)
        val feedActivity3 = FeedModel.FeedActivity(challenge3, Date(), likesList3)
        val feedActivity4 = FeedModel.FeedActivity(challenge4, Date(), likesList4)
        val feedActivity5 = FeedModel.FeedActivity(challenge5, Date(), likesList5)
        val feedActivity6 = FeedModel.FeedActivity(challenge6, Date(), likesList6)
        val feedActivity7 = FeedModel.FeedActivity(challenge7, Date(), likesList7)
        val feedActivity8 = FeedModel.FeedActivity(challenge8, Date(), likesList8)


        return arrayOf(feedActivity1, feedActivity2, feedActivity3, feedActivity4,
                feedActivity5, feedActivity6, feedActivity7, feedActivity8)
    }
}