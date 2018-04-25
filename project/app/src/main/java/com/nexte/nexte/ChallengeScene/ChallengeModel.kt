package com.nexte.nexte.ChallengeScene
import com.nexte.nexte.Player

/**
 * Class to define the Model of Challenge Scene to send informations between layers
 */
class ChallengeModel{

    class ShowRankingPlayersRequest {

        class Request(var challengerRankingPosition: Int)

        class Response(var fiveUsersAbove: List<Player>)

        class ViewModel(var formattedPlayer: List<FormattedPlayer>)
    }

    class SelectPlayerForChallengeRequest{

        class Request(var challengedRankingPosition: Int)

        class Response(var challengedPersonalDetails: Player)

        class ViewModel(var challengedRankingDetails: FormattedRankingDetails)

    }

    class FormattedPlayer(var name: String,
                          var rankingPosition: String,
                          var pictureAddress: String)

    class FormattedRankingDetails(var name: String,
                                  var wins: Int,
                                  var losses: Int,
                                  var rankingPosition: String)
}