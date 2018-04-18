package com.nexte.nexte.FeedScene

import java.util.*

/**
 * Class to define the Model of Feed Scene to send information between layers
 */
class FeedModel {

    /**
     * Class containing [Request], [Response] and [ViewModel] classes to be used on the activity
     * flow of getting the scene started and to get the information from FeedMocker data
     */
    class GetFeedActivities {

        /**
         * Class responsible to pass data from View to Interactor and then to worker,
         * so it can request data
         */
        class Request

        /**
         * Class responsible to store received informations from Worker and pass it to Presenter
         *
         * @property feedActivities MutableList that hold informations to show in screen
         */
        class Response(var feedActivities: MutableList<FeedActivity>)

        /**
         * Class responsible to define how the list view will display the formatted data passed to view
         *
         * @property feedActivities MutableList that hold informations to show in screen
         */
        class ViewModel(var feedActivities: MutableList<FeedActivityFormatted>)
    }

    /**
     * Class containing [Request], [Response] and [ViewModel] classes to be used on the activity
     * flow of allowing an user
     */
    class LikeAndUnlike {

        /**
         * Class responsible to receive the identifier to be passed to worker
         *
         * @property identifier identifier of the activity to be found in worker
         */
        class Request(var identifier: String)

        /**
         * Class responsible to store the activity to add or remove user from likes list
         *
         * @property likedActivity activity to be altered on likes list (adding or removing user)
         */
        class Response(var likedActivity: FeedActivity)

        /**
         * Class responsible to define the format of the activity to be displayed
         *
         * @property formattedLikedActivities activity already altered and ready to display on screen
         */
        class ViewModel(var formattedLikedActivities: FeedActivityFormatted)
    }

    // --------- Aux classes ---------

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
                        var challengeDate: Date)

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
     * Class responsible to define how the list view will display the formatted data
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