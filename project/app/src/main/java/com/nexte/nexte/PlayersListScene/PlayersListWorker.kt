package com.nexte.nexte.PlayersListScene


import com.nexte.nexte.Entities.Challenge.Challenge
import com.nexte.nexte.Entities.Challenge.ChallengeManager
import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Entities.User.UserManager
import com.nexte.nexte.MatchScene.MatchModel
import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import java.util.*

interface PlayerListUpdateLogic{

    /**
     * * Method that will be used to pass response data for the presenter
     *
     * @param response Response model of list that contains data to pass for Presenter
     */
    fun getPlayersToChallenge(request: PlayersListModel.ShowRankingPlayersRequest.Request)
}
/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class PlayersListWorker {

    var userManager: UserManager = UserManager()
    var challengeManager: ChallengeManager = ChallengeManager()

    /**
     * Function to get players 5 rank positions above the logged player
     *
     * @param request Challenge Model request that contains needed information to send to server
     * @param completion Method to call on parent class
     */
    fun fetchPlayersToChallenge (request: PlayersListModel.ShowRankingPlayersRequest.Request,
                                 completion: (PlayersListModel.ShowRankingPlayersRequest.Response) -> Unit) {
        val rankingPostion = request.challengerRankingPosition

        var selectedPlayers: List<User> = listOf()
        val players = userManager?.getAll()

        players?.forEach {
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
        var selectedPlayer: User?= null
        val players = userManager?.getAll()

        players?.forEach {
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

        var challengedUser = userManager.get(request.userChallenged)
        challengedUser?.let {
            val challenge = Challenge(UUID.randomUUID().toString(),
                    UserSingleton.loggedUserID,
                    it.id,
                    Date(),
                    Challenge.Status.CONFIRMED,
                    Challenge.Stage.Scheduled())

            challengeManager.update(challenge)

            val challenged = MatchModel.MatchPlayer(challengedUser.name, R.mipmap.ic_launcher_round)
            val challenger = MatchModel.MatchPlayer(UserSingleton.loggedUser.name, R.mipmap.ic_launcher_round)
            val match = MatchModel.MatchData(challenged, challenger)
            val response = PlayersListModel.ChallengeButtonRequest.Response(challengedUser.name, match)

            completion(response)
        }
    }

    companion object {  const val rankingGap = 5 }
}