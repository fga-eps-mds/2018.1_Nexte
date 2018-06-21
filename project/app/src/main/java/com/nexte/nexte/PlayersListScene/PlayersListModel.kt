package com.nexte.nexte.PlayersListScene
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.MatchScene.MatchModel
import java.util.*


/**
 * Class to define the Model of Challenge Scene to send informations between layers
 */
class PlayersListModel {

    /**
     * Information that will be passed between layers when user first open the activity
     */
    class ShowRankingPlayersRequest {

        /**
         * Request with needed information for getting the users above player ranking
         *
         * @param challengerRankingPosition Ranking position of the logged user
         */
        class Request(var challengerRankingPosition: Int)

        /**
         * Response responsible for holding the selected players information
         *
         * @param usersAbove The players above our logged user in ranking
         */
        class Response(var usersAbove: List<User>)

        /**
         * ViewModel responsible for format the selected players information
         *
         * @param formattedPlayer Information about the players above our user
         * already ready for display
         */
        class ViewModel(var formattedPlayer: List<FormattedPlayer>)
    }

    /**
     * This class is responsible for define what will be passed between layers for
     * expand selected recyclerview user.
     */
    class SelectPlayerForChallengeRequest {

        /**
         * Request containing an unique information about the clicked player.
         *
         * @param challengedRankingPosition the ranking of the clicked user (rank is
        unique for each player, allowing us to recover the information)
         */
        class Request(var challengedRankingPosition: Int)

        /**
         * Responsible with detailed information about player with the selected ID
         *
         * @param challengedPersonalDetails detailed info about clicked player
         */
        class Response(var challengedPersonalDetails: User)

        /**
         * Class responsible to edit the player data that will be exhibited
         *
         * @param challengedRankingDetails formatted detailed info about clicked player
         */
        class ViewModel(var challengedRankingDetails: FormattedRankingDetails)
    }

    /**
     * This class is responsible for the button of sending a challenge
     */
    class ChallengeButtonRequest {

        /**
         * Request containing the user that's going to be challenged
         *
         * @param userChallenged is the name of the user that's going to be challenged
         */
        class Request(var userChallenged: String)

        /**
         * Responsible for the user of the challenge
         *
         * @param username is the challenged user
         * @param challenge is the challenge
         */
        class Response(var username: String,
                       var challenge: MatchModel.MatchData)

        /**
         * Class responsible to show a confirmation message informing that a challenged
         * has been sent
         *
         * @param messageForChallenger message with the confirmation of the challenge sent
         * @param matchMessage indicate the response of the challenge
         */
        class ViewModel(var messageForChallenger: String,
                        var matchMessage: String,
                        var matchData : MatchModel.MatchData?)
    }

    // ---------- Aux classes ----------
    /**
     * Class that holds the formatted player data of the 5 users above player
     */
    class FormattedPlayer(var identifier: String, // user itendifier
                          var name: String, // player name
                          var rankingPosition: String, //player position in ranking
                          var pictureAddress: String) //player picture url

    /**
     * Class that holds the selected player formatted information.
     */
    class FormattedRankingDetails(var name: String, //player name
                                  var wins: String, //player wins
                                  var rankingPosition: String,
                                  var category: String,
                                  var efficiency: String,
                                  var latestGame: String,
                                  var userLastGames: List<Int>,
                                  var id: String)
    /**
     *  Class that contains the detailed information about player ranking
     *
     *  @param name player name
     *  @param wins player raw wins
     *  @param loses player raw loses
     *  @param rankingPosition player raw position in ranking
     */
    class PlayerRankingDetails(var name: String,
                               var wins: Int,
                               var rankingPosition: Int,
                               var loses: Int)
}