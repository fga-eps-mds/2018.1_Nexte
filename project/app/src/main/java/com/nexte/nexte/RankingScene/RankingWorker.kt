package com.nexte.nexte.RankingScene

import com.nexte.nexte.Entities.User.UserMocker

/**
 * Class responsible to do request for anywhere, format Response and
 * call completion to send data for called class
 */
class RankingWorker {

    /**
     * Function to get users in ranking
     *
     * @param request Ranking Model Request that contains need information to send for server
     * @param completion Method to call on parent class
     */
    fun getUsersInRanking(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {

        val userList = UserMocker.generateUsers()

        val response = RankingModel.Response(userList.sortedWith(compareBy { it.rankingPosition }))

        completion(response)
    }

}