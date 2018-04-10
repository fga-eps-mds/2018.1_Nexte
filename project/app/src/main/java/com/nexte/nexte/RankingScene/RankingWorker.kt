package com.nexte.nexte.RankingScene

import com.nexte.nexte.FeedScene.FeedModel

/**
 * Created by albino on 02/04/18.
 */

class RankingWorker {

    fun getUsersInRanking(request: RankingModel.Request, completion: (RankingModel.Response) -> Unit) {
        val response = RankingModel.Response(this.generateRankingMockData())

        completion(response)

    }

    private fun generateRankingMockData(): Array<RankingModel.RankingActivity> {

        val userRanking1 = RankingModel.RankingPlayer("Helena", R.mipmap.ic_launcher, 10, 0, 1)
        val userRanking2 = RankingModel.RankingPlayer("Leticia", R.mipmap.ic_launcher, 9, 1, 2)
        val userRanking3 = RankingModel.RankingPlayer("Gabriel", R.mipmap.ic_launcher, 8, 2, 3)
        val userRanking4 = RankingModel.RankingPlayer("Lorrany", R.mipmap.ic_launcher, 7, 3, 4)
        val userRanking5 = RankingModel.RankingPlayer("Alexandre", R.mipmap.ic_launcher, 6, 4, 5)
        val userRanking6 = RankingModel.RankingPlayer("Luis Gustavo", R.mipmap.ic_launcher, 5, 5, 6)
        val userRanking7 = RankingModel.RankingPlayer("Guilherme", R.mipmap.ic_launcher, 4, 6, 7)
        val userRanking8 = RankingModel.RankingPlayer("Giovanni", R.mipmap.ic_launcher, 3, 7, 8)
        val userRanking9 = RankingModel.RankingPlayer("Miguel", R.mipmap.ic_launcher, 2, 6, 9)
        val userRanking10 = RankingModel.RankingPlayer("Larissa", R.mipmap.ic_launcher, 1, 5, 10)

        val rankingActivity1 = RankingModel.RankingActivity(userRanking1)
        val rankingActivity2 = RankingModel.RankingActivity(userRanking2)
        val rankingActivity3 = RankingModel.RankingActivity(userRanking3)
        val rankingActivity4 = RankingModel.RankingActivity(userRanking4)
        val rankingActivity5 = RankingModel.RankingActivity(userRanking5)
        val rankingActivity6 = RankingModel.RankingActivity(userRanking6)
        val rankingActivity7 = RankingModel.RankingActivity(userRanking7)
        val rankingActivity8 = RankingModel.RankingActivity(userRanking8)
        val rankingActivity9 = RankingModel.RankingActivity(userRanking9)
        val rankingActivity10 = RankingModel.RankingActivity(userRanking10)

        return arrayOf(rankingActivity1, rankingActivity2, rankingActivity3, rankingActivity4,
                rankingActivity5, rankingActivity6, rankingActivity7, rankingActivity8, rankingActivity9, rankingActivity10)


    }
}