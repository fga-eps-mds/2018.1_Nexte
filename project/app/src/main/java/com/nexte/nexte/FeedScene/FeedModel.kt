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
     * @property feedActivities Array that hold informations to show in screen
     */
    class Response(var feedActivities: List<FeedActivity>)

    /**
     * Class responsible to define how the list view will display the formatted data, passed to view
     *
     * @property feedActivities Array that hold informations to show in screen
     */
    class ViewModel(var feedActivities: List<FeedActivityFormatted>)


    // --------- Aux classes to use in this scene ---------


    /**
     * Class for players in feed that contains just needed data
     *
     * @property name String that contains name of player
     * @property photo Profile image of player
     * @property set Result in Set of match that this player done
    */
    class FeedPlayer(var name: String, var photo: Int, var set: Int)

    /**
     * Class to define a challenge for feed activities
     *
     * @property challenger Player that are challenger
     * @property challenged Player that are challenged
     * @property challengeDate Date that the match are played
     */
    class FeedChallenge(var challenger: FeedPlayer,
                        var challenged: FeedPlayer,
                        var challengeDate: Date
                        )

    /**
     * Class that contains informations about activity to show in feed screen
     *
     * @property identifier String identifier for this activity
     * @property challenge Challenge informations with players, result and date
     * @property feedDate Date of feed are created
     */
    class FeedActivity(var identifier: String,
                       var challenge: FeedChallenge,
                       var feedDate: Date,
                       var likes: MutableList<FeedModel.FeedPlayer>)

    /**
     * Class responsible to define how the list view will display with the formatted data
     *
     * @property identifier String identifier for this activity
     * @property challengerName String that contains name of challenger player
     * @property challengerPhoto Profile image of challenger
     * @property challengerSets String that have sets of challenger
     * @property challengedName String that contains name of challenged player
     * @property challengedPhoto Profile image of challenged
     * @property challengedSets String that have sets of challenger
     * @property feedDate String date of feed to show on screen
     */
    class FeedActivityFormatted(var identifier: String,
                                var challengerName: String,
                                var challengerPhoto: Int,
                                var challengerSets: String,
                                var challengedName: String,
                                var challengedPhoto: Int,
                                var challengedSets: String,
                                var feedDate: String,
                                var numberOfLikes: String)
}