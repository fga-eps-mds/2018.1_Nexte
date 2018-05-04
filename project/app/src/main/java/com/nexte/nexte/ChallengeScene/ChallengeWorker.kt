package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class ChallengeWorker {

    companion object {

        const val rankingGap = 5
    }

    /**
     * Function to get players 5 rank positions above the logged player
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun fetchPlayersToChallenge (request: ChallengeModel.ShowRankingPlayersRequest.Request,
                                 completion: (ChallengeModel.ShowRankingPlayersRequest.Response) -> Unit) {
        val rankingPostion = request.challengerRankingPosition

        var selectedPlayers: List<Player> = listOf()
        val players = ChallengeMocker.createPlayers()

        players.forEach {
            if (it.rankingPosition >= rankingPostion-rankingGap && it.rankingPosition < rankingPostion) {
                selectedPlayers += it
            }
        }

        val response = ChallengeModel.ShowRankingPlayersRequest.Response(selectedPlayers)


        completion(response)
    }

    /**
     * Function to get the clicked player detailed info
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
        fun fetchChallengedDetails (request: ChallengeModel.SelectPlayerForChallengeRequest.Request,
                                    completion: (ChallengeModel.SelectPlayerForChallengeRequest.Response) -> Unit) {
        val challengedPosition = request.challengedRankingPosition

        var selectedPlayer: ChallengeModel.PlayerRankingDetails?= null

        val players = ChallengeMocker.createPlayerDetailedInfo()

        players.forEach {
            if (it.rankingPosition == challengedPosition){
                selectedPlayer = it
            }
        }

        val response = ChallengeModel.SelectPlayerForChallengeRequest.Response(selectedPlayer!!)

        completion(response)
    }

    fun fetchMessageForChallenge(request: ChallengeModel.ChallengeButtonRequest.Request,
                                 completion: (ChallengeModel.ChallengeButtonRequest.Response) -> Unit) {

        val user = request.userChallenged

        val response = ChallengeModel.ChallengeButtonRequest.Response(true)

        completion(response)

    }

}