package com.nexte.nexte.ChallengeScene
import com.nexte.nexte.Player

/**
 * Class to define the Model of Challenge Scene to send informations between layers
 */
class ChallengeModel{

    /**
     * Information that will be passed between layers when user first open the activity
     */
    class ShowRankingPlayersRequest {

        /**
         * Request with needed information for getting the users above player rank
         */
        class Request(var challengerRankingPosition: Int)

        /**
         * Response responsible for holding the selected players information
         */
        class Response(var fiveUsersAbove: List<Player>)

        /**
         * ViewModel responsible for format the selected players information
         */
        class ViewModel(var formattedPlayer: List<FormattedPlayer>)
    }

    /**
     * This class is responsible for define what will be passed between layers for expand selected recyclerview user.
     */
    class SelectPlayerForChallengeRequest{

        /**
         * Request containing an uniqui information about the clicked player.
         */
        class Request(var challengedRankingPosition: Int)

        /**
         * Responsible with detailed information about player with the selected ID
         */
        class Response(var challengedPersonalDetails: Player)

        /**
         * Class responsible to edit the player data that will be exhibited
         */
        class ViewModel(var challengedRankingDetails: FormattedRankingDetails)

    }

    /**
     * Class that holds the formatted player data of the 5 users above player
     */
    class FormattedPlayer(var name: String,
                          var rankingPosition: String,
                          var pictureAddress: String)

    /**
     * Class that holds the selected player formatted information.
     */
    class FormattedRankingDetails(var name: String,
                                  var wins: Int,
                                  var losses: Int,
                                  var rankingPosition: String)
}