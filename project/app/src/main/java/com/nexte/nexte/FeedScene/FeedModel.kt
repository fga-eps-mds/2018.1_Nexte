package com.nexte.nexte.FeedScene

import java.util.*

/**
 * Class to define Model of Feed Scene to send informations between layers
 */
class FeedModel {

    /**
     * Class responsible to pass data of view to interactor and after to worker can do request data.
     */
    class Request

    /**
     * Class responsible to store received informations of worker to pass for Presenter
     *
     * @param feedActivities Array that hold informations to show in screen
     */
    class Response(var feedActivities: Array<FeedActivity>)

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @param feedActivities Array that hold informations to show in screen
     */
    class ViewModel(var feedActivities: List<FeedActivityFormatted>)


    // --------- Aux classes to use in this scene ---------


    /**
     * Class for players in feed that contains just needed data
     *
     * @param name String that contains name of player
     * @param photo Profile image of player
     * @param set Result in Set of match that this player done
    */
    class FeedPlayer(var name: String, var photo: Int, var set: Int)

    /**
     * Class to define a challenge for feed activities
     *
     * @param challenger Player that are challenger
     * @param challenged Player that are challenged
     * @param challengeDate Date that the match are played
     */
    class FeedChallenge(var challenger: FeedPlayer,
                        var challenged: FeedPlayer,
                        var challengeDate: Date)

    /**
     * Class that contains informations about activity to show in feed screen
     *
     * @param challenge Challenge informations with players, result and date
     * @param feedDate Date of feed are created
     */
    class FeedActivity(var challenge: FeedChallenge,
                       var feedDate: Date)

    /**
     * Class responsible to define how the list view will display with the formatted data
     *
     * @param challengerName String that contains name of challenger player
     * @param challengerPhoto Profile image of challenger
     * @param challengerSets String that have sets of challenger
     * @param challengedName String that contains name of challenged player
     * @param challengedPhoto Profile image of challenged
     * @param challengedSets String that have sets of challenger
     * @param feedDate String date of feed to show on screen
     */
    class FeedActivityFormatted(var challengerName: String,
                                var challengerPhoto: Int,
                                var challengerSets: String,
                                var challengedName: String,
                                var challengedPhoto: Int,
                                var challengedSets: String,
                                var feedDate: String)
}