package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import java.util.*

/**
 * Object class for use static mode in other classes.
 * This Object is responsible to generate feed mocker.
 */
object FeedMocker {

    /**
     * Method for create initial mocker data to use in fictional app mode of feed scene
     *
     * @return Mutable List with mocker feed data
     */
    fun createFeedList(): MutableList<FeedModel.FeedActivity> {
        var list: MutableList<FeedModel.FeedActivity> = mutableListOf()

        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val likesList1: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger2 = FeedModel.FeedPlayer("Letícia", R.mipmap.ic_launcher, 2)
        val challenged2 = FeedModel.FeedPlayer("Lorrany", R.mipmap.ic_launcher, 1)
        val likesList2: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger3 = FeedModel.FeedPlayer("Luis", R.mipmap.ic_launcher, 2)
        val challenged3 = FeedModel.FeedPlayer("Miguel", R.mipmap.ic_launcher, 0)
        val likesList3: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger4 = FeedModel.FeedPlayer("Geovanni", R.mipmap.ic_launcher, 3)
        val challenged4 = FeedModel.FeedPlayer("Alexandre", R.mipmap.ic_launcher, 2)
        val likesList4: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger5 = FeedModel.FeedPlayer("Larissa", R.mipmap.ic_launcher, 0)
        val challenged5 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 1)
        val likesList5: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger6 = FeedModel.FeedPlayer("Miguel", R.mipmap.ic_launcher, 0)
        val challenged6 = FeedModel.FeedPlayer("Letícia", R.mipmap.ic_launcher, 2)
        val likesList6: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger7 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val challenged7 = FeedModel.FeedPlayer("Geovanni", R.mipmap.ic_launcher, 1)
        val likesList7: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenger8 = FeedModel.FeedPlayer("Alexandre", R.mipmap.ic_launcher, 3)
        val challenged8 = FeedModel.FeedPlayer("Larissa", R.mipmap.ic_launcher, 0)
        val likesList8: MutableList<FeedModel.FeedPlayer> = mutableListOf()

        val challenge1 = FeedModel.FeedChallenge(challenger1, challenged1, Date())
        val challenge2 = FeedModel.FeedChallenge(challenger2, challenged2, Date())
        val challenge3 = FeedModel.FeedChallenge(challenger3, challenged3, Date())
        val challenge4 = FeedModel.FeedChallenge(challenger4, challenged4, Date())
        val challenge5 = FeedModel.FeedChallenge(challenger5, challenged5, Date())
        val challenge6 = FeedModel.FeedChallenge(challenger6, challenged6, Date())
        val challenge7 = FeedModel.FeedChallenge(challenger7, challenged7, Date())
        val challenge8 = FeedModel.FeedChallenge(challenger8, challenged8, Date())

        val feedActivity1 = FeedModel.FeedActivity("1", challenge1, Date(), likesList1)
        val feedActivity2 = FeedModel.FeedActivity("2", challenge2, Date(), likesList2)
        val feedActivity3 = FeedModel.FeedActivity("3", challenge3, Date(), likesList3)
        val feedActivity4 = FeedModel.FeedActivity("4", challenge4, Date(), likesList4)
        val feedActivity5 = FeedModel.FeedActivity("5", challenge5, Date(), likesList5)
        val feedActivity6 = FeedModel.FeedActivity("6", challenge6, Date(), likesList6)
        val feedActivity7 = FeedModel.FeedActivity("7", challenge7, Date(), likesList7)
        val feedActivity8 = FeedModel.FeedActivity("8", challenge8, Date(), likesList8)

        list.add(feedActivity1)
        list.add(feedActivity2)
        list.add(feedActivity3)
        list.add(feedActivity4)
        list.add(feedActivity5)
        list.add(feedActivity6)
        list.add(feedActivity7)
        list.add(feedActivity8)

        return list
    }
}