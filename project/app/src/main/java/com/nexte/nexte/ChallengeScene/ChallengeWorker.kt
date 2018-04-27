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
     * @param request Challenge Model request that contains needed informations to send to server
     * @param completion Method to call on parent class
     */
    fun fetchPlayersToChallenge (request: ChallengeModel.ShowRankingPlayersRequest.Request,
                                 completion: (ChallengeModel.ShowRankingPlayersRequest.Response) -> Unit) {
        val rankingPostion = request.challengerRankingPosition

        var selectedPlayers: List<Player> = listOf()
        val players = ChallengeMocker.createPlayers()

        for (player in players){
            if(player.rankingPosition >= rankingPostion-rankingGap && player.rankingPosition < rankingPostion){
                selectedPlayers += player
            }
        }

        val response = ChallengeModel.ShowRankingPlayersRequest.Response(selectedPlayers)

        completion(response)
    }

    TODO("CRIAR FUNÇÃO PARA RECEBER UMA REQUEST (SEGUNDA REQUEST) E RETORNAR O JOGADOR QUE SE ENCOTNRA NO RANKING N")
}