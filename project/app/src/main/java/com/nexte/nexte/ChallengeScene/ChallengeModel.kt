package com.nexte.nexte.ChallengeScene
import com.nexte.nexte.Player

/**
 * Class to define the Model of Challenge Scene to send informations between layers
 */
class ChallengeModel{

    class showRankingPlayersRequest {

        class Request(var challengerRankPosition: rankingPosition.Player)

        class Response(var fiveUsersAbove: Array<Player>)

        class ViewModel(var formattedPlayer: formattedPlayer)

    }

    class selectPlayerForChallenge{

        class Request(var challengedRankPosition: challengedRankingPosition)

        class Response(var challengedPersonalDetails: Player)

        class ViewModel(var challengedRankingDetails: formattedRankingDetails)

    }

    class formattedPlayer(var name: String,
                          var rankingPosition: String,
                          var pictureAddress: String)

    class formattedRankingDetails(var name: String,
                                  var wins: Int,
                                  var losses: Int,
                                  var rankingPosition: String)
}