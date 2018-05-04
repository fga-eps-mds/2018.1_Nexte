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
        class Request(
                var challengerRankingPosition: Int // Rank position of the logged user
        )

        /**
         * Response responsible for holding the selected players information
         */
        class Response(
                var usersAbove: List<Player> //The players above our logged user in ranking
        )

        /**
         * ViewModel responsible for format the selected players information
         */
        class ViewModel(
                var formattedPlayer: List<FormattedPlayer> /* Information about the
                players above our user already ready for display */
        )
    }

    /**
     * This class is responsible for define what will be passed between layers for expand selected recyclerview user.
     */
    class SelectPlayerForChallengeRequest{

        /**
         * Request containing an uniqui information about the clicked player.
         */
        class Request(
                var challengedRankingPosition: Int /*the rank of the clicked user (rank is
            unique for each player, allowing us to recover the information)*/
        )

        /**
         * Responsible with detailed information about player with the selected ID
         */
        class Response(
                var challengedPersonalDetails: PlayerRankingDatails //detailed info about clicked player
        )

        /**
         * Class responsible to edit the player data that will be exhibited
         */
        class ViewModel(
                var challengedRankingDetails: FormattedRankingDetails // formatted detailed info about clicked player
        )

    }

    class ChallengeButtonRequest {

        class Request(var userChallenged: String)

        class Response(var userClicked: Boolean)

        class ViewModel(var messageForChallenger: String)

    }

    /**
     * Class that holds the formatted player data of the 5 users above player
     */
    class FormattedPlayer(var name: String, // player name
                          var rankingPosition: String, //player position in ranking
                          var pictureAddress: String) //player picture url

    /**
     * Class that holds the selected player formatted information.
     */
    class FormattedRankingDetails(var name: String, //player name
                                  var wins: String, //player wins
                                  var loses: String, //player loses
                                  var rankingPosition: String) //player position in ranking

    /**
     *  Class that contains the detailed information about player ranking
     */
    class PlayerRankingDatails(var name: String, //player name
                               var wins: Int, //player raw wins
                               var loses: Int, //player raw loses
                               var rankingPosition: Int //player raw position in ranking
    )
}