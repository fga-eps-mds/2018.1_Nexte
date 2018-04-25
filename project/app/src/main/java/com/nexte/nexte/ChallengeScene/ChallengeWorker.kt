package com.nexte.nexte.ChallengeScene

import com.nexte.nexte.Player

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class ChallengeWorker {

    /**
     * Function to set a match
     *
     * @param request Challenge Model request that contains needed informations to send to server
     * @param completion Method to call on parent class
     */
    fun fetchPlayersToChallenge (request: ChallengeModel.ShowRankingPlayersRequest.Request, completion: (ChallengeModel.ShowRankingPlayersRequest.Response) -> Unit) {
        val rankingPostion = request.challengerRankingPosition

        val rankingGap = 5

        var selectedPlayers: List<Player> = listOf()
        val players = ChallengeMocker.createPlayers()

        for (player in players){
            if((player.rankingPosition >= rankingPostion-rankingGap) && player.rankingPosition < rankingPostion){
                selectedPlayers += player
            }
        }

        val response = ChallengeModel.ShowRankingPlayersRequest.Response(selectedPlayers)

        completion(response)
    }
}