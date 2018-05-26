package com.nexte.nexte.PlayersListScene

import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.Player
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class PlayersListWorker {



    /**
     * Function to get players 5 rank positions above the logged player
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun fetchPlayersToChallenge (request: PlayersListModel.ShowRankingPlayersRequest.Request,
                                 completion: (PlayersListModel.ShowRankingPlayersRequest.Response) -> Unit) {
        val rankingPostion = request.challengerRankingPosition

        var selectedPlayers: List<Player> = listOf()
        val players = PlayersListMocker.createPlayers()

        players.forEach {
            if (it.rankingPosition >= rankingPostion-rankingGap && it.rankingPosition < rankingPostion) {
                selectedPlayers += it
            }
        }

        val response = PlayersListModel.ShowRankingPlayersRequest.Response(selectedPlayers)

       //Variable that allows the "No Players" message
       // val response = PlayersListModel.ShowRankingPlayersRequest.Response(mutableListOf())
        completion(response)
    }

    /**
     * Function to get the clicked player detailed info
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
        fun fetchChallengedDetails (request: PlayersListModel.SelectPlayerForChallengeRequest.Request,
                                    completion: (PlayersListModel.SelectPlayerForChallengeRequest.Response) -> Unit) {
        val challengedPosition = request.challengedRankingPosition

        var selectedPlayer: PlayersListModel.PlayerRankingDetails?= null

        val players = PlayersListMocker.createPlayerDetailedInfo()

        players.forEach {
            if (it.rankingPosition == challengedPosition){
                selectedPlayer = it
            }
        }

        val response = PlayersListModel.SelectPlayerForChallengeRequest.Response(selectedPlayer!!)

        completion(response)
    }

    /**
     * Function to get a user that's going to be challenged
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun generateChallenge(request: PlayersListModel.ChallengeButtonRequest.Request,
                          completion: (PlayersListModel.ChallengeButtonRequest.Response) -> Unit) {

        val user = request.userChallenged

        val challenged = MatchModel.MatchPlayer(user, R.mipmap.ic_launcher_round)
        val challenger = MatchModel.MatchPlayer(UserSingleton.getUserInformations().name, R.mipmap.ic_launcher_round)
        val match = MatchModel.MatchData(challenged, challenger)

        val response = PlayersListModel.ChallengeButtonRequest.Response(user, match)

        completion(response)

    }

    companion object {

        const val rankingGap = 5
    }

}